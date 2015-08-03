package com.qeeka.repository;

import com.qeeka.SimpleQuery;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.core.GenericTypeResolver;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;


/**
 * Created by Neal on 8/3 0003.
 */
public abstract class BaseSearchRepository<T> {
    @PersistenceContext
    protected EntityManager entityManager;
    //Get T  Real Class
    protected Class entityClass;
    protected String entityName;

    public BaseSearchRepository() {
        Class<?>[] arguments = GenericTypeResolver.resolveTypeArguments(getClass(), BaseSearchRepository.class);
        if (arguments == null || arguments.length != 1) {
            throw new IllegalArgumentException(MessageFormatter.format("repository must extend with generic type like BaseSearchRepository<T>, class={}", getClass()).getMessage());
        }
        entityClass = arguments[0];

        String simpleName = entityClass.getSimpleName();
        System.out.println(simpleName);
        Annotation annotation = entityClass.getAnnotation(Entity.class);
        if (annotation == null) {
            throw new IllegalArgumentException("repository must extend with generic type Entity.class");
        } else {
            if (annotation instanceof Entity) {
                Entity entity = (Entity) annotation;
                if (entity.name() != null && !"".equals(entity.name())) {
                    entityName = entity.name();
                } else {
                    entityName = simpleName;
                }
            } else {
                throw new IllegalArgumentException("repository must extend with generic type Entity");
            }
        }
    }

    public List<T> search(SimpleQuery query) {
        StringBuilder hql = new StringBuilder("FROM ").append(entityName).append(" WHERE ").append(query.getHql());
        TypedQuery<T> typedQuery = entityManager.createQuery(hql.toString(), entityClass);
        for (Map.Entry<String, Object> entry : query.getParameters().entrySet()) {
            typedQuery.setParameter(entry.getKey(), entry.getValue());
        }
        return typedQuery.getResultList();
    }
}
