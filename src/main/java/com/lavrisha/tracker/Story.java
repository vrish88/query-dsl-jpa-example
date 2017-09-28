package com.lavrisha.tracker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Story {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private String description;
    private String owner;
    private String requester;
    private String state;
    private Integer points;

    @ManyToOne
    private Project project;
}
