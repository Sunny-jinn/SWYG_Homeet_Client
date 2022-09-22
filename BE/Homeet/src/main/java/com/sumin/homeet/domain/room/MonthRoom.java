package com.sumin.homeet.domain.room;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class MonthRoom extends Room{
    private int OnePrice;
    private int PerPrice;
}
