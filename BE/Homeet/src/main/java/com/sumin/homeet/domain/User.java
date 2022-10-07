package com.sumin.homeet.domain;


import com.sumin.homeet.domain.room.Room;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String email;
    private String nickname;

    @OneToMany(mappedBy = "userRoomNote")
    private List<RoomNote> roomNoteList = new ArrayList<>();

    @OneToMany(mappedBy = "userLike")
    private List<LikeUser> likeUserList = new ArrayList<>();

    @OneToMany(mappedBy = "userRoom")
    private List<Room> roomList = new ArrayList<>();

}
