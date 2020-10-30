package com.example.demo.repository;


import com.example.demo.H2JpaTest;
import com.example.demo.util.Domains;
import com.example.demo.DemoApplication;
import com.example.demo.domain.GPS;
import com.example.demo.domain.TrackPoint;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrackPointRepositoryTest extends H2JpaTest {

    @Autowired
    TrackPointRepository trackPointRepository;

    @Autowired
    GPSRepository gpsRepository;


    @Test
    public void setup() {

    }

    @Test
    public void teardown() {

    }


    private void safeSleep() {
        try {
            Thread.sleep(1);
        } catch (Exception ex) {

        }

    }

    @Test
    public void Test_findLatestTrackpointByUser() {
        // Given
        GPS gps = Domains.newGPS(1L);
        List<TrackPoint> trackPoints = IntStream.range(0, 10)
                .peek(any -> safeSleep())
                .mapToObj(any -> Domains.newTrackPoint(gps))
                .collect(Collectors.toList());
        gps.setTrackPoints(trackPoints);

        GPS saved = gpsRepository.save(gps);
        // When
        TrackPoint latestTrackpointByUser = trackPointRepository.findByUserIdOrderByTimestampDesc(1L,
                new Timestamp(System.currentTimeMillis()),
                1).get(0);
        // Then
        TrackPoint expected = saved.getTrackPoints().get(trackPoints.size() - 1);
        Assertions.assertThat(latestTrackpointByUser).isEqualTo(expected);
    }
}
