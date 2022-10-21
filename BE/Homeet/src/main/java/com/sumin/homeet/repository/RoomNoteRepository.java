package com.sumin.homeet.repository;

import com.sumin.homeet.domain.RoomNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomNoteRepository extends JpaRepository<RoomNote,Long> {
}
