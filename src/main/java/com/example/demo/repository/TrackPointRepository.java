package com.example.demo.repository;

import com.example.demo.domain.GPS;
import com.example.demo.domain.TrackPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackPointRepository extends JpaRepository<TrackPoint, Long>, TrackPointRepositoryCustom {
}
