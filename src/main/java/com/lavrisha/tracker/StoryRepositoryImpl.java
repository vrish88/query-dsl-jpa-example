package com.lavrisha.tracker;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.lavrisha.tracker.QStory.story;

public class StoryRepositoryImpl extends SimpleJpaRepository<Story, Integer> implements StoryRepository {
    private final EntityManager entityManager;

    public StoryRepositoryImpl(
        EntityManager entityManager
    ) {
        super(new JpaMetamodelEntityInformation<>(Story.class, entityManager.getMetamodel()), entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public List<Story> search(Project project, SearchParams searchParams) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        return jpaQueryFactory.select(story)
            .from(story)
            .where(
                story.title.contains(Optional.ofNullable(searchParams.getTitle()).orElse(""))
                    .and(story.project.eq(project))
            )
            .fetchResults()
            .getResults();
    }
}
