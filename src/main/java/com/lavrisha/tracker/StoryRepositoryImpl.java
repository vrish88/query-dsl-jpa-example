package com.lavrisha.tracker;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
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
            .innerJoin(story.project)
            .fetchJoin()
            .where(
                story.project.eq(project).and(convertSearchParamsToConditions(searchParams))
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

    private Predicate convertSearchParamsToConditions(SearchParams searchParams) {
        if (searchParams.getTitle() == null && searchParams.getRequester() == null) {
            return ExpressionUtils.eq(Expressions.FALSE, Expressions.TRUE);
        }

        return ExpressionUtils.allOf(
            mapFieldToColumn(searchParams.getTitle(), story.title),
            mapFieldToColumn(searchParams.getRequester(), story.requester)
        );
    }

    private BooleanExpression mapFieldToColumn(String value, StringPath path) {
        return Optional.ofNullable(value).map(path::contains).orElse(null);
    }
}
