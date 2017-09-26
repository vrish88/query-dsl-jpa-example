package com.lavrisha.tracker;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
}
