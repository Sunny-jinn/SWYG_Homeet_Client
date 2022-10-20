package com.sumin.homeet.repository;

import com.sumin.homeet.domain.room.MonthRoom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthRoomRepository extends RoomRepository{
    List<MonthRoom> findByLocation(String location);
    MonthRoom getReferenceById(Long id);
}
