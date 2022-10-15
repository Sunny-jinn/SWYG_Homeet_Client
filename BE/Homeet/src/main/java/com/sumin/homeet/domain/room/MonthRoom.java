package com.sumin.homeet.domain.room;

import com.sumin.homeet.dto.MonthRoomDto;
import com.sumin.homeet.dto.YearRoomDto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class MonthRoom extends Room{
    private int OnePrice;
    private int PerPrice;
    public void changeMonth(MonthRoomDto room){
        this.OnePrice = room.getOnePrice();
        this.PerPrice = room.getPerPrice();
        this.setLocation(room.getLocation());
        this.setDuplex(room.isDuplex());

    }
}
