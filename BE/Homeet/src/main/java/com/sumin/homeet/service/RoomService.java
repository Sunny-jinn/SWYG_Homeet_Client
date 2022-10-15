package com.sumin.homeet.service;

import com.sumin.homeet.domain.room.MonthRoom;
import com.sumin.homeet.domain.room.Room;
import com.sumin.homeet.domain.room.YearRoom;
import com.sumin.homeet.dto.MonthRoomDto;
import com.sumin.homeet.dto.YearRoomDto;
import com.sumin.homeet.repository.RoomRepository;
import com.sumin.homeet.s3.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomService {
    private final RoomRepository roomRepository;
    private final AwsS3Service awsS3Service;
    @Transactional
    public void register(Room room, List<MultipartFile> multipartFiles){
        List<String> urlLists = awsS3Service.uploadFile(multipartFiles);
        room.setImageUrl(urlLists);
        roomRepository.save(room);
    }
    public Room findOne(Long roomId) {
        Room room = roomRepository.getReferenceById(roomId);
        return room;
    }
    public List<Room> findAllByLocation(String location){
        List<Room> roomList = roomRepository.findByLocation(location);
        return roomList;
    }
    public void update(YearRoomDto room){
        YearRoom one = (YearRoom) roomRepository.getReferenceById(room.getId());
        one.changeYear(YearRoomDto.builder()
                .id(room.getId())
                .location(room.getLocation())
                .yearPrice(room.getYearPrice())
                .duplex(room.isDuplex())
                .content(room.getContent())
                .build());
    }
    public void update(MonthRoomDto room){
        MonthRoom one = (MonthRoom) roomRepository.getReferenceById(room.getId());
        one.changeMonth(MonthRoomDto.builder()
                .id(room.getId())
                .OnePrice(room.getOnePrice())
                .PerPrice(room.getPerPrice())
                .location(room.getLocation())
                .duplex(room.isDuplex())
                .content(room.getContent())
                .build());
    }
    public void delete(Long roomId){
        roomRepository.deleteById(roomId);
    }

}
