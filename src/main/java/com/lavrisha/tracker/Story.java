package com.lavrisha.tracker;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Clock;
import java.time.LocalDate;

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
    private LocalDate rejectedDate;

    @ManyToOne
    private Project project;

    public void reject(Clock clock) {
        rejectedDate = LocalDate.now(clock);
        state = "rejected";
    }
}
