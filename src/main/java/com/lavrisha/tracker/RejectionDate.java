package com.lavrisha.tracker;

import lombok.Value;

import java.time.LocalDate;

@Value
public class RejectionDate {
    public final LocalDate date;
    public final Long numberOfRejections;
}
