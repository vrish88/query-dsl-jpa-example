package com.lavrisha.tracker;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.lavrisha.tracker.QStory.story;
import static java.util.Collections.emptyList;

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
        if (searchParams.getTitle() == null && searchParams.getRequester() == null) {
            return emptyList();
        }

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        return jpaQueryFactory.select(story)
            .from(story)
            .innerJoin(story.project)
            .fetchJoin()
            .where(
                story.project.eq(project)
                    .and(
                        Optional.ofNullable(searchParams.getTitle()).map(story.title::contains).orElse(null)
                    )
                    .and(
                        Optional.ofNullable(searchParams.getRequester()).map(story.requester::contains).orElse(null)
                    )
            )
            .fetchResults()
            .getResults();
    }

    @Override
    public void updateState(Story story, String newState) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        jpaQueryFactory.update(QStory.story)
            .where(QStory.story.eq(story))
            .set(QStory.story.state, newState)
            .execute();
    }
}
