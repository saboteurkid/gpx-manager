package com.example.demo.repository;

import com.example.demo.domain.GPS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GPSRepository extends JpaRepository<GPS, Long>, GPSRepositoryCustom {
    @Query(nativeQuery = true,
            value = "SELECT g.* FROM gps g LEFT JOIN gps_waypoint gw ON g.id = gw.gps_id WHERE gw.waypoint_id = :waypoint_id")
    List<GPS> findByWayPointId(@Param("waypoint_id") long waypointId);

    GPS findFirstByUserIdOrderByTimestampDesc(long userId);
}
