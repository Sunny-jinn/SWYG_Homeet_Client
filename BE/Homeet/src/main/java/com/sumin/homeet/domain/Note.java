package com.sumin.homeet.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue
    @Column(name = "note_id")
    private Long id;

    private String content;
    @Enumerated(EnumType.STRING)
    private RoleType role;

    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userNote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomNote_id")
    private RoomNote roomNote;

}
