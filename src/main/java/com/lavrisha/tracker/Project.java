package com.lavrisha.tracker;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@Builder
public class Project {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany
    private List<Story> stories;
}
