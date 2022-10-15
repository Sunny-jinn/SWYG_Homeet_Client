package com.sumin.homeet.service;

import com.sumin.homeet.domain.room.MonthRoom;
import com.sumin.homeet.domain.room.YearRoom;
import com.sumin.homeet.dto.MonthRoomDto;
import com.sumin.homeet.dto.RoomDto;
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
    private final AwsS3Service awsS3Service;
    @Transactional
    public void register(RoomDto room, List<MultipartFile> multipartFiles){
        List<String> urlLists = awsS3Service.uploadFile(multipartFiles);
        room.setImageUrl(urlLists);
        if (room.getDtype() == "Y"){
            YearRoom r = new YearRoom();
            r.setImageUrl(room.getImageUrl());
            r.setDuplex(room.getDuplex());
            r.setContent(room.getContent());
            r.setLocation(room.getLocation());
            r.setYearPrice(room.getYearPrice());
            yearRoomRepository.save(r);
        }
        else if (room.getDtype() == "M"){
            MonthRoom r = new MonthRoom();
            r.setImageUrl(room.getImageUrl());
            r.setDuplex(room.getDuplex());
            r.setContent(room.getContent());
            r.setLocation(room.getLocation());
            r.setPerPrice(room.getPerPrice());
            r.setOnePrice(room.getOnePrice());
            monthRoomRepository.save(r);
        }
    }
    public RoomDto findOne(Long roomId, String dtype) {
        if (dtype=="Y"){
            YearRoom room = yearRoomRepository.getReferenceById(roomId);
            return RoomDto.builder()
                    .imageUrl(room.getImageUrl())
                    .duplex(room.getDuplex())
                    .content(room.getContent())
                    .yearPrice(room.getYearPrice())
                    .build();
        }
        else{
            MonthRoom room = monthRoomRepository.getReferenceById(roomId);
            return RoomDto.builder()
                    .imageUrl(room.getImageUrl())
                    .duplex(room.getDuplex())
                    .content(room.getContent())
                    .perPrice(room.getPerPrice())
                    .onePrice(room.getOnePrice())
                    .build();
        }

    }
    public List<RoomDto> findAllByLocation(String location){
        List<YearRoom> yearRoomList = yearRoomRepository.findByLocation(location);
        List<MonthRoom> monthRoomList = monthRoomRepository.findByLocation(location);
        List<RoomDto> roomDtoList = new ArrayList<>();
        yearRoomList.forEach(room -> {
            roomDtoList.add(RoomDto.builder()
                            .imageUrl(room.getImageUrl())
                            .content(room.getContent())
                            .duplex(room.getDuplex())
                            .id(room.getId())
                            .yearPrice(room.getYearPrice())
                    .build());
        });
        monthRoomList.forEach(room -> {
            roomDtoList.add(RoomDto.builder()
                    .imageUrl(room.getImageUrl())
                    .content(room.getContent())
                    .duplex(room.getDuplex())
                    .id(room.getId())
                    .onePrice(room.getOnePrice())
                    .perPrice(room.getPerPrice())
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
