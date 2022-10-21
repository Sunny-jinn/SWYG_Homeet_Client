package com.sumin.homeet.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sumin.homeet.domain.room.Room;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "roomnote")
public class RoomNote {
    @Id
    @GeneratedValue
    @Column(name = "roomNote_id")
    private Long id;
    @JsonIgnore
    @OneToMany(mappedBy = "roomNote")
    private List<Note> noteList = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //방 작성자의 아이디
    private User userRoomNote;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public void addList(Note note){
        this.noteList.add(note);
    }
}
