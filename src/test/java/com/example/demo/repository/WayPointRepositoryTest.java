package com.example.demo.repository;

import com.example.demo.H2JpaTest;
import com.example.demo.domain.GPS;
import com.example.demo.domain.GPSWayPoint;
import com.example.demo.domain.WayPoint;
import com.example.demo.util.Domains;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class WayPointRepositoryTest extends H2JpaTest {


    @Autowired
    private GPSRepository gpsRepository;
    @Autowired
    private GPSWayPointRepository gpsWayPointRepository;
    @Autowired
    private WayPointRepository wayPointRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void Test_FindByGpsID() {
        // Given
        GPS gps = Domains.newGPS(1L);
        gpsRepository.save(gps);
        WayPoint wayPoint = Domains.newWayPoint();
        wayPointRepository.save(wayPoint);
        GPSWayPoint gpsWayPoint = new GPSWayPoint(new GPSWayPoint.Id(gps.getId(), wayPoint.getId()));
        gpsWayPointRepository.save(gpsWayPoint);
        // When
        List<WayPoint> waypoints = wayPointRepository.findByGpsId(gps.getId());
        // Then
        Assertions.assertThat(waypoints).isNotEmpty();
        Assertions.assertThat(waypoints.get(0)).isEqualTo(wayPoint);
    }
}