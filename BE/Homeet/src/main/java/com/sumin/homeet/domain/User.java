package com.sumin.homeet.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sumin.homeet.domain.room.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    private String email;
    private String nickname;

//    @OneToMany(mappedBy = "userRoomNote")
//    private List<RoomNote> roomNoteList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "userLike")
    private List<LikeUser> likeUserList = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "userRoom")
    private List<Room> roomList = new ArrayList<>();

}
