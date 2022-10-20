package com.sumin.homeet.service;

import com.sumin.homeet.domain.User;
import com.sumin.homeet.domain.room.MonthRoom;
import com.sumin.homeet.domain.room.YearRoom;
import com.sumin.homeet.dto.MonthRoomDto;
import com.sumin.homeet.dto.RoomDto;
import com.sumin.homeet.dto.UserDto;
import com.sumin.homeet.dto.YearRoomDto;
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
    public void register(RoomDto room, List<MultipartFile> multipartFiles, String email){
        List<String> urlLists = awsS3Service.uploadFile(multipartFiles);
        User user = userService.findOneByEmail(email);
        room.setImageUrl(urlLists);
        if (room.getDtype() == "Y"){
            YearRoom r = getYearRoom(room, user);
            yearRoomRepository.save(r);
        }
        else if (room.getDtype() == "M"){
            MonthRoom r = getMonthRoom(room, user);
            monthRoomRepository.save(r);
        }
    }

    private static MonthRoom getMonthRoom(RoomDto room, User user) {
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

    private static YearRoom getYearRoom(RoomDto room, User user) {
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
        if (dtype=="Y"){
            YearRoom room = yearRoomRepository.getReferenceById(roomId);
            User user = room.getUserRoom();
            UserDto userDto = UserDto.builder().userId(user.getId()).nickname(user.getNickname()).email(user.getEmail()).build();
            return RoomDto.builder()
                    .imageUrl(room.getImageUrl())
                    .duplex(room.getDuplex())
                    .content(room.getContent())
                    .yearPrice(room.getYearPrice())
                    .user(userDto)
                    .build();
        }
        else{
            MonthRoom room = monthRoomRepository.getReferenceById(roomId);
            User user = room.getUserRoom();
            UserDto userDto = UserDto.builder().userId(user.getId()).nickname(user.getNickname()).email(user.getEmail()).build();
            return RoomDto.builder()
                    .imageUrl(room.getImageUrl())
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
                            .content(room.getContent())
                            .duplex(room.getDuplex())
                            .id(room.getId())
                            .user(userDto)
                            .yearPrice(room.getYearPrice())
                    .build());
        });
        monthRoomList.forEach(room -> {
            User user = room.getUserRoom();
            UserDto userDto = UserDto.builder().userId(user.getId()).nickname(user.getNickname()).email(user.getEmail()).build();
            roomDtoList.add(RoomDto.builder()
                    .imageUrl(room.getImageUrl())
                    .content(room.getContent())
                    .duplex(room.getDuplex())
                    .id(room.getId())
                    .onePrice(room.getOnePrice())
                    .perPrice(room.getPerPrice())
                    .user(userDto)
                    .build());
        });
        return roomDtoList;
    }
    public void update(YearRoomDto room){
        YearRoom one = yearRoomRepository.getReferenceById(room.getId());
        one.changeYear(YearRoomDto.builder()
                .id(room.getId())
                .location(room.getLocation())
                .yearPrice(room.getYearPrice())
                .duplex(room.isDuplex())
                .content(room.getContent())
                .build());
    }
    public void update(MonthRoomDto room){
        MonthRoom one = monthRoomRepository.getReferenceById(room.getId());
        one.changeMonth(MonthRoomDto.builder()
                .id(room.getId())
                .onePrice(room.getOnePrice())
                .perPrice(room.getPerPrice())
                .location(room.getLocation())
                .duplex(room.isDuplex())
                .content(room.getContent())
                .build());
    }
    public void delete(Long roomId){
        roomRepository.deleteById(roomId);
    }

}
