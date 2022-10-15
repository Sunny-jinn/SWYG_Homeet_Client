package com.sumin.homeet.repository;

import com.sumin.homeet.domain.room.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long>{
    List<Room> findByLocation(String location);

}
