package com.sumin.homeet.domain.room;


import com.sumin.homeet.domain.LikeUser;
import com.sumin.homeet.domain.RoomNote;
import com.sumin.homeet.domain.User;
import com.sumin.homeet.dto.YearRoomDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "room")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Room {
    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private Long id;

    private String location;
    private String content;

    @ElementCollection
    private List<String> imageUrl = new ArrayList<>();

    private Boolean duplex;

    @OneToMany(mappedBy = "roomLike")
    private List<LikeUser> likeUserList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userRoom;

    @OneToMany(mappedBy = "room")
    private List<RoomNote> roomNoteList = new ArrayList<>();
}
