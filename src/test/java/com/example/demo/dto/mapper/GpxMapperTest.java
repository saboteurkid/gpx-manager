package com.example.demo.dto.mapper;

import com.example.demo.H2JpaTest;
import com.example.demo.domain.GPS;
import com.example.demo.domain.GPSWayPoint;
import com.example.demo.domain.TrackPoint;
import com.example.demo.domain.WayPoint;
import com.example.demo.dto.GPXWrapper;
import com.example.demo.repository.GPSRepository;
import com.example.demo.repository.GPSWayPointRepository;
import com.example.demo.repository.WayPointRepository;
import com.example.demo.util.Domains;
import io.jenetics.jpx.GPX;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class GpxMapperTest extends H2JpaTest {

    @Autowired
    private GPSRepository gpsRepository;
    @Autowired
    private GPSWayPointRepository gpsWayPointRepository;
    @Autowired
    private WayPointRepository wayPointRepository;


    @Autowired
    GpxMapper gpxMapper;

    private void safeSleep() {
        try {
            Thread.sleep(1);
        } catch (Exception ex) {

        }
    }

    @Test
    public void domain2dto() throws IOException {
        // Given
        GPS gps = new GPS();
        gps.setUserId(1L);
        gps.setDesc("Assumenda fugiat dicta sed.");
        gps.setName("Dorethea Davis");
        gps.setLink(new GPS.Link("https://s3.amazonaws.com/uifaces/faces/twitter/tanveerrao/128.jpg", "Ilene Wright"));
        gps.setTimestamp(Timestamp.from(Instant.parse("2020-10-29T17:00:43.915Z")));
        List<TrackPoint> trackPoints = new ArrayList<>(2);
        {
            // 1
            TrackPoint trackPoint = new TrackPoint();
            trackPoint.setGps(gps);
            trackPoint.setTimestamp(Timestamp.from(Instant.parse("2020-10-29T17:00:43.946Z")));
            trackPoint.setLat(BigDecimal.valueOf(16.51D));
            trackPoint.setLon(BigDecimal.valueOf(27.27D));
            trackPoint.setElevation(BigDecimal.valueOf(85033.4D));
            trackPoints.add(trackPoint);
        }
        {
            // 2
            TrackPoint trackPoint = new TrackPoint();
            trackPoint.setGps(gps);
            trackPoint.setTimestamp(Timestamp.from(Instant.parse("2020-10-29T17:00:43.948Z")));
            trackPoint.setLat(BigDecimal.valueOf(39.62D));
            trackPoint.setLon(BigDecimal.valueOf(-35.02D));
            trackPoint.setElevation(BigDecimal.valueOf(98021.83D));
            trackPoints.add(trackPoint);
        }
        gps.setTrackPoints(trackPoints);
        GPS saved = gpsRepository.save(gps);
        WayPoint wayPoint = new WayPoint();
        {
            // Waypoint
            wayPoint.setName("Holly Day");
            wayPoint.setSymlink("https://s3.amazonaws.com/uifaces/faces/twitter/ryandownie/128.jpg");
            wayPoint.setLat(BigDecimal.valueOf(49.17D));
            wayPoint.setLon(BigDecimal.valueOf(79.0D));
        }
        wayPointRepository.save(wayPoint);
        GPSWayPoint gpsWayPoint = new GPSWayPoint(new GPSWayPoint.Id(gps.getId(), wayPoint.getId()));
        gpsWayPointRepository.save(gpsWayPoint);

        List<WayPoint> wayPoints = wayPointRepository.findByGpsId(gps.getId());
        saved.setWayPoints(Sets.newHashSet(wayPoints));

        // When
        GPXWrapper gpxWrapper = gpxMapper.domain2dto(gps);
        GPX gpx = GPX.read("./sample/test-mapper.gpx");

        // Then
        assertEquals(GPX.writer().toString(gpx), gpxWrapper.toString());
    }

    @Test
    public void dto2domain() throws IOException {
        // Given
        GPX gpx = GPX.read("./sample/test-mapper.gpx");
        GPXWrapper gpxWrapper = new GPXWrapper();
        gpxWrapper.setId(1L);
        gpxWrapper.setGpx(gpx);
        gpxWrapper.setUserId(1L);

        // When
        GPS gps = gpxMapper.dto2domain(gpxWrapper);
        Set<WayPoint> wayPoints = gps.getWayPoints();
        // Then
        assertEquals(1L, wayPoints.size());
        GPS savedGPS = gpsRepository.save(gps);
        assertEquals(2, savedGPS.getTrackPoints().size());
        assertEquals("Dorethea Davis", savedGPS.getName());
    }


}