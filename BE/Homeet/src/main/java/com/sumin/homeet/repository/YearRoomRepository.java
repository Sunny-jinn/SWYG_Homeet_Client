package com.sumin.homeet.repository;

import com.sumin.homeet.domain.room.Room;
import com.sumin.homeet.domain.room.YearRoom;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;

@Repository
public interface YearRoomRepository extends RoomRepository{
    List<YearRoom> findByLocation(String location);
    YearRoom getReferenceById(Long id);
}
