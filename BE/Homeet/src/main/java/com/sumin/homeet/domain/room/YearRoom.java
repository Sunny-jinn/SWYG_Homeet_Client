package com.sumin.homeet.domain.room;

import com.sumin.homeet.dto.YearRoomDto;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Y")
public class YearRoom extends Room{
    private int YearPrice;
    public void changeYear(YearRoomDto room){
        this.YearPrice = room.getYearPrice();
        this.setLocation(room.getLocation());
        this.setDuplex(room.isDuplex());

    }
}
