package com.example.demo.repository;

import com.example.demo.domain.GPS;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GPSRepositoryImpl implements GPSRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<GPS> pagingByUserId(long userId, long lastIndex, int count) {
        List<GPS> gpsList = entityManager.createQuery("SELECT g FROM GPS g WHERE g.userId = :userId AND g.id > :lastIndex ORDER BY g.id DESC", GPS.class)
                .setParameter("userId", userId)
                .setParameter("lastIndex", lastIndex)
                .setMaxResults(count)
                .getResultList();
        return gpsList;
    }
}
