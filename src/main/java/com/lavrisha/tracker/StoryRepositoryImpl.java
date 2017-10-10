package com.lavrisha.tracker;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.sql.Configuration;
import com.querydsl.sql.H2Templates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.spring.SpringConnectionProvider;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static com.lavrisha.tracker.QStory.story;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class StoryRepositoryImpl extends SimpleJpaRepository<Story, Integer> implements StoryRepository {
    private final EntityManager entityManager;
    private final DataSource dataSource;
    private static List<FieldConditionMappings> searchParamsMapping = asList(
        new FieldConditionMappings<>(SearchParams::getTitle, story.title::contains),
        new FieldConditionMappings<>(SearchParams::getRequester, story.requester::contains),
        new FieldConditionMappings<>(SearchParams::getPoints, story.points::eq)
    );

    public StoryRepositoryImpl(
        EntityManager entityManager,
        DataSource dataSource
    ) {
        super(new JpaMetamodelEntityInformation<>(Story.class, entityManager.getMetamodel()), entityManager);
        this.entityManager = entityManager;
        this.dataSource = dataSource;
    }


    @Override
    public List<Story> search(Project project, SearchParams searchParams) {
        if (searchParams.getTitle() == null && searchParams.getRequester() == null && searchParams.getPoints() == null) {
            return emptyList();
        }

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        return jpaQueryFactory.select(story)
            .from(story)
            .innerJoin(story.project)
            .fetchJoin()
            .where(story.project.eq(project).and(convertToConditions(searchParams)))
            .fetchResults()
            .getResults();
    }

    @Override
    public ProjectPoints findProjectStories(Project project) {
        Configuration configuration = new Configuration(H2Templates.DEFAULT);
        SQLQueryFactory queryFactory = new SQLQueryFactory(configuration, new SpringConnectionProvider(dataSource));

        return queryFactory
            .select(
                Projections.constructor(
                    ProjectPoints.class,
                    SqlProject.project.name,
                    SqlStory.story.points.sum()
                )
            )
            .from(SqlStory.story)
            .innerJoin(SqlProject.project).on(SqlProject.project.id.eq(SqlStory.story.projectId))
            .where(SqlStory.story.projectId.eq(project.getId()))
            .fetchOne();
    }

    @Override
    public void updateState(Story story, String newState) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        jpaQueryFactory.update(QStory.story)
            .where(QStory.story.eq(story))
            .set(QStory.story.state, newState)
            .execute();
    }

    private Predicate convertToConditions(SearchParams searchParams) {
        return searchParamsMapping.stream()
            .map(m -> m.applyMapping(searchParams))
            .reduce(new BooleanBuilder(), BooleanBuilder::and, BooleanBuilder::and);
    }

    private static class FieldConditionMappings<T> {
        private final Function<SearchParams, T> getter;
        private final Function<T, BooleanExpression> condition;

        FieldConditionMappings(
            Function<SearchParams, T> getter,
            Function<T, BooleanExpression> condition
        ) {
            this.getter = getter;
            this.condition = condition;
        }

        BooleanExpression applyMapping(SearchParams params) {
            return Optional.ofNullable(getter.apply(params)).map(condition).orElse(null);
        }
    }
}
