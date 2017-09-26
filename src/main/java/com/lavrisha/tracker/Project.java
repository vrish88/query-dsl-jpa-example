package com.lavrisha.tracker;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
public class Project {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @OneToMany(cascade = {CascadeType.PERSIST})
    private List<Story> stories;
}
