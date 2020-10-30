package com.example.demo.repository;

import com.example.demo.domain.TrackPoint;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class TrackPointRepositoryImpl implements TrackPointRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public TrackPointRepositoryImpl() {
    }

    @Override
    public List<TrackPoint> findByUserIdOrderByTimestampDesc(long userId, Timestamp timestamp, int count) {
        List<TrackPoint> trackPoints = entityManager.createQuery("SELECT tp FROM TrackPoint tp " +
                "WHERE tp.userId = :userId AND tp.timestamp < :timestamp " +
                "ORDER BY tp.timestamp DESC", TrackPoint.class)
                .setMaxResults(count)
                .setParameter("userId", userId)
                .setParameter("timestamp", timestamp)
                .getResultList();
        return trackPoints;
    }
}
