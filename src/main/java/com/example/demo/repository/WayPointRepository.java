package com.example.demo.repository;

import com.example.demo.domain.GPS;
import com.example.demo.domain.WayPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface WayPointRepository extends JpaRepository<WayPoint, WayPoint.Point> {
    WayPoint findFirstByLatAndLon(BigDecimal lat, BigDecimal lon);

    @Query(nativeQuery = true,
            value = "SELECT w.* FROM waypoint w LEFT JOIN gps_waypoint gw ON w.id = gw.waypoint_id WHERE gw.gps_id = :gps_id")
    List<WayPoint> findByGpsId(@Param("gps_id") long gpsId);
}
