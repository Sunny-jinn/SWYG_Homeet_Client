package com.sumin.homeet.domain;


import com.sumin.homeet.domain.room.Room;

import javax.persistence.*;

@Entity
public class LikeUser {
    @Id
    @GeneratedValue
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userLike;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room roomLike;
}
