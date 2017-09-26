package com.lavrisha.tracker;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

@Entity
@Data
@Builder
public class Story {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;
    private String description;
    private String owner;
    private String requester;

    @OneToOne
    @RestResource
    private Project project;
}
