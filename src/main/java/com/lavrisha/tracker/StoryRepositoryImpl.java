package com.lavrisha.tracker;

import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Story> query = cb.createQuery(Story.class);

        Root<Story> storyRoot = query.from(Story.class);
        query.where(Specifications.<Story>
            where((root, query1, builder) -> builder.like(root.get("title"), "%" + searchParams.getTitle() + "%"))
            .and((root, query1, builder) -> builder.equal(root.get("project"), project))
            .toPredicate(storyRoot, query, cb)
        );

        return entityManager.createQuery(query).getResultList();
    }
}
