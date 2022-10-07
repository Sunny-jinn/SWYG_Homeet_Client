package com.sumin.homeet.domain;


import com.sumin.homeet.domain.room.Room;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roomnote")
public class RoomNote {
    @Id
    @GeneratedValue
    @Column(name = "roomNote_id")
    private Long id;

    @OneToMany(mappedBy = "roomNote")
    private List<Note> noteList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userRoomNote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;
}
