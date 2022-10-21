package com.sumin.homeet.service;

import com.sumin.homeet.domain.User;
import com.sumin.homeet.domain.room.MonthRoom;
import com.sumin.homeet.domain.room.Room;
import com.sumin.homeet.domain.room.YearRoom;
import com.sumin.homeet.dto.*;
import com.sumin.homeet.repository.MonthRoomRepository;
import com.sumin.homeet.repository.RoomRepository;
import com.sumin.homeet.repository.YearRoomRepository;
import com.sumin.homeet.s3.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomService {
    private final MonthRoomRepository monthRoomRepository;
    private final YearRoomRepository yearRoomRepository;
    private final RoomRepository roomRepository;
    private final UserService userService;
    private final AwsS3Service awsS3Service;
    @Transactional
    public void register(RegRoomDto room, List<MultipartFile> multipartFiles, String email){
        List<String> urlLists = awsS3Service.uploadFile(multipartFiles);
        User user = userService.findOneByEmail(email);
        room.setImageUrl(urlLists);
        if (room.getDtype().equals("Y")){
            YearRoom r = getYearRoom(room, user);
            roomRepository.save(r);
        }
        else if (room.getDtype().equals("M")){
            MonthRoom r = getMonthRoom(room, user);
            roomRepository.save(r);
        }
    }

    private static MonthRoom getMonthRoom(RegRoomDto room, User user) {
        MonthRoom r = new MonthRoom();
        r.setImageUrl(room.getImageUrl());
        r.setDuplex(room.getDuplex());
        r.setContent(room.getContent());
        r.setLocation(room.getLocation());
        r.setPerPrice(room.getPerPrice());
        r.setOnePrice(room.getOnePrice());
        r.setUserRoom(user);
        return r;
    }

    private static YearRoom getYearRoom(RegRoomDto room, User user) {
        YearRoom r = new YearRoom();
        r.setImageUrl(room.getImageUrl());
        r.setDuplex(room.getDuplex());
        r.setContent(room.getContent());
        r.setLocation(room.getLocation());
        r.setYearPrice(room.getYearPrice());
        r.setUserRoom(user);
        return r;
    }

    public RoomDto findOne(Long roomId, String dtype) {
        if (dtype.equals("Y")){
            YearRoom room = (YearRoom) yearRoomRepository.findById(roomId).get();
            User user = room.getUserRoom();
            UserDto userDto = UserDto.builder().userId(user.getId()).nickname(user.getNickname()).email(user.getEmail()).build();
            return RoomDto.builder()
                    .imageUrl(room.getImageUrl())
                    .id(room.getId())
                    .dtype(dtype)
                    .location(room.getLocation())
                    .duplex(room.getDuplex())
                    .content(room.getContent())
                    .yearPrice(room.getYearPrice())
                    .user(userDto)
                    .build();
        }
        else{
            MonthRoom room = (MonthRoom) monthRoomRepository.findById(roomId).get();
            User user = room.getUserRoom();
            UserDto userDto = UserDto.builder().userId(user.getId()).nickname(user.getNickname()).email(user.getEmail()).build();
            return RoomDto.builder()
                    .imageUrl(room.getImageUrl())
                    .location(room.getLocation())
                    .id(room.getId())
                    .dtype(dtype)
                    .duplex(room.getDuplex())
                    .content(room.getContent())
                    .perPrice(room.getPerPrice())
                    .onePrice(room.getOnePrice())
                    .user(userDto)
                    .build();
        }

    }
    public List<RoomDto> findAllByLocation(String location){
        List<YearRoom> yearRoomList = yearRoomRepository.findByLocation(location);
        List<MonthRoom> monthRoomList = monthRoomRepository.findByLocation(location);
        List<RoomDto> roomDtoList = new ArrayList<>();
        yearRoomList.forEach(room -> {
            User user = room.getUserRoom();
            UserDto userDto = UserDto.builder().userId(user.getId()).nickname(user.getNickname()).email(user.getEmail()).build();
            roomDtoList.add(RoomDto.builder()
                    .imageUrl(room.getImageUrl())
                    .id(room.getId())
                    .dtype("Y")
                    .location(room.getLocation())
                    .duplex(room.getDuplex())
                    .content(room.getContent())
                    .yearPrice(room.getYearPrice())
                    .user(userDto)
                    .build());
        });
        monthRoomList.forEach(room -> {
            User user = room.getUserRoom();
            UserDto userDto = UserDto.builder().userId(user.getId()).nickname(user.getNickname()).email(user.getEmail()).build();
            roomDtoList.add(RoomDto.builder()
                    .imageUrl(room.getImageUrl())
                    .location(room.getLocation())
                    .id(room.getId())
                    .dtype("M")
                    .duplex(room.getDuplex())
                    .content(room.getContent())
                    .perPrice(room.getPerPrice())
                    .onePrice(room.getOnePrice())
                    .user(userDto)
                    .build());
        });
        return roomDtoList;
    }
    @Transactional
    public void updateYear(YearRoomDto room){
        YearRoom one = (YearRoom) yearRoomRepository.findById(room.getId()).get();
        one.changeYear(YearRoomDto.builder()
                .id(room.getId())
                .location(room.getLocation())
                .yearPrice(room.getYearPrice())
                .duplex(room.isDuplex())
                .content(room.getContent())
                .build());
    }
    @Transactional
    public void updateMonth(MonthRoomDto room){
        MonthRoom one = (MonthRoom) monthRoomRepository.findById(room.getId()).get();
        one.changeMonth(MonthRoomDto.builder()
                .id(room.getId())
                .onePrice(room.getOnePrice())
                .perPrice(room.getPerPrice())
                .location(room.getLocation())
                .duplex(room.isDuplex())
                .content(room.getContent())
                .build());
    }
    @Transactional
    public void delete(Long roomId){
        Room room = roomRepository.findById(roomId).get();
        for (String img : room.getImageUrl()) {
            String[] split = img.split("/");
            awsS3Service.deleteFile(split[split.length - 1]);
        }
        roomRepository.deleteById(roomId);
    }
    public Room findRoomOne(Long roomId){
        return roomRepository.findById(roomId).get();
    }
}
