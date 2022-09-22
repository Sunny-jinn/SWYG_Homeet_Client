package com.sumin.homeet.domain.room;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Y")
public class YearRoom extends Room{
    private int YearPrice;
}
