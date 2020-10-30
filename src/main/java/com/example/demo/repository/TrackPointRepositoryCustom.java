package com.example.demo.repository;

import com.example.demo.domain.GPS;
import com.example.demo.domain.TrackPoint;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface TrackPointRepositoryCustom {
    List<TrackPoint> findByUserIdOrderByTimestampDesc(long userId, Timestamp timestamp, int count);
}
