package com.sumin.homeet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue
    @Column(name = "note_id")
    private Long id;

    private String content;
    private Long receiverId;

    @CreatedDate
    private LocalDateTime createDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User sendUser;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomNote_id")
    private RoomNote roomNote;

}
