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
        Project tractor = Project.builder().name("Tractor").build();
        Story story = Story.builder().title("Save me!").project(tractor).build();

        projectRepository.save(tractor);
        storyRepository.save(story);

        assertThat(storyRepository.findOne(story.getId()).getProject()).isEqualTo(tractor);
    }

    @Test
    public void searchStoriesByTitle() throws Exception {
        Project project = Project.builder().name("Tractor").build();
        Story johnDeere = Story.builder().title("Build John Deere").project(project).build();
        Story tykes = Story.builder().title("Build Tykes Tractor").project(project).build();

        Project interstellar = Project.builder().name("Interstellar Tractor").build();
        Story tractorBeam = Story.builder().title("Galactic Tractor Beam").project(interstellar).build();
        Story johnDeere1 = Story.builder().title("Build John Deere").project(interstellar).build();

        projectRepository.save(project);
        projectRepository.save(interstellar);
        storyRepository.save(asList(tykes, johnDeere, johnDeere1, tractorBeam));

        assertThat(storyRepository.search(
            project,
            SearchParams.builder().title("Build John Deere").build()
        )).containsOnly(johnDeere);
        assertThat(storyRepository.search(
            project,
            SearchParams.builder().title("Build John").build()
        )).containsOnly(johnDeere);
    }

    @Test
    public void noSearchParamsNoResults() throws Exception {
        Project project = Project.builder().name("Tractor").build();
        Story nullTitle = Story.builder().title("null").project(project).build();

        projectRepository.save(project);
        storyRepository.save(asList(nullTitle, Story.builder().build()));

        assertThat(storyRepository.search(project, SearchParams.builder().build())).isEmpty();
    }

    @Test
    public void fetchProjectWithStoryDuringSearch() throws Exception {
        Project project = Project.builder().name("Tractor").build();
        Story johnDeere = Story.builder().title("Build John Deere").project(project).build();
        projectRepository.save(project);
        storyRepository.save(johnDeere);
        storyRepository.flush();
        projectRepository.flush();

        List<Story> stories = storyRepository.search(project, SearchParams.builder().title("John").build());
        Project project1 = stories.get(0).getProject();
        assertThat(project1).isEqualTo(project);
    }
}
