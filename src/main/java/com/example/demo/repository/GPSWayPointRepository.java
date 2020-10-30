package com.example.demo.repository;

import com.example.demo.domain.GPSWayPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GPSWayPointRepository extends JpaRepository<GPSWayPoint, GPSWayPoint.Id> {

}
