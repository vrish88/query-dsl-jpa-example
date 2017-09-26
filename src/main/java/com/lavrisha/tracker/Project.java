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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    @OneToMany(cascade = {javax.persistence.CascadeType.PERSIST})
    private List<Story> stories;
}
