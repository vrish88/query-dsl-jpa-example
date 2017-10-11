package com.lavrisha.tracker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface StoryRepository extends JpaRepository<Story, Integer> {
    List<Story> search(Project project, SearchParams searchParams);

    ProjectPoints findProjectStories(Project project);

    void updateState(Story story, String newState);

    List<RejectionDate> rejectionHistogram(Project project);
}
