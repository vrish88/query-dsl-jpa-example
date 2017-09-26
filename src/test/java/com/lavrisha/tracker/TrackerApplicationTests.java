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
    @Autowired
    private StoryRepository storyRepository;

    @Test
    public void projectsCanHaveStories() {
        Story story = Story.builder().title("Save me!").build();
        Project tractor = Project.builder().name("Tractor").stories(asList(story)).build();

        projectRepository.save(tractor);
        projectRepository.flush();

        assertThat(projectRepository.findOne(tractor.getId()).getStories()).contains(story);
    }

//    @Test
//    public void searchStories() throws Exception {
//        Story johnDeere = Story.builder().title("Build John Deere").build();
//        Project project = Project.builder().name("Tractor").stories(asList(
//            Story.builder().title("Build Tykes Tractor").build(),
//            johnDeere
//        )).build();
//
//        projectRepository.save(project);
//
//        List<Story> results = storyRepository.search(SearchParams.builder().title("Build John Deere").build());
//
//        assertThat(results).containsOnly(johnDeere);
//    }
}
