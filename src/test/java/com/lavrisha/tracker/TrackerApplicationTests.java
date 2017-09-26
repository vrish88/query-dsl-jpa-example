package com.lavrisha.tracker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class TrackerApplicationTests {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void projectsCanHaveStories() {
        Story story = Story.builder().title("Save me!").build();
        Project tractor = Project.builder().name("Tractor").stories(asList(story)).build();

        projectRepository.save(tractor);

        assertThat(projectRepository.findOne(tractor.getId()).getStories()).contains(story);
    }

}
