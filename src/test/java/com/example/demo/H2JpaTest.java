package com.example.demo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = DemoApplication.class
)
@TestPropertySource(locations = "classpath:h2.properties")
public class H2JpaTest {

    @Autowired
    Environment env;

    @Before
    public void setup() {

    }

    @Test
    public void smokeTest() {

        Boolean isCustom = env.getProperty("my.external.prop", Boolean.class);
        Assert.assertTrue(isCustom);

//        GPS gps = GPS.GPSBuilder.aGPS()
//                .withUserId(1L)
//                .withTimestamp(Timestamp.valueOf(LocalDateTime.now()))
//                .withName("1gps")
//                .withDesc("Fake desc")
//                .withLink(new GPS.Link("https://example.com", "Example"))
//                .build();
//        System.out.println("111");
//        GPS savedGps = gpsRepository.save(gps);
//
//        WayPoint wayPoint = new WayPoint();
//        wayPoint.setLat(BigDecimal.valueOf(0.1));
//        wayPoint.setLon(BigDecimal.valueOf(0.2));
//        wayPoint.setName("LoL");
//        wayPoint.setSymlink("/mars/highest-mount");
//        WayPoint savedWayPoint = wayPointRepository.save(wayPoint);
//
//
//        GPSWayPoint gpsWayPoint = new GPSWayPoint(new GPSWayPoint.Id(savedGps.getId(), savedWayPoint.getId()));
//        gpsWayPointRepository.save(gpsWayPoint);
//
//        List<GPS> gpses = gpsRepository.findByWayPointId(savedWayPoint.getId());
//        assertThat(gps).isEqualTo(gpses.get(0));


        //

//        WayPoint anotherWaypoint1 = wayPointRepository.findFirstByLatAndLon(BigDecimal.valueOf(0.1), BigDecimal.valueOf(0.2));
//        assertThat(anotherWaypoint1.getId()).isEqualTo(wayPoint.getId())
//                .withFailMessage("should equals");
//
//        TrackPoint trackPoint = new TrackPoint();
//        trackPoint.setElevation(BigDecimal.ONE);
//        trackPoint.setLat(BigDecimal.ZERO);
//        trackPoint.setLon(BigDecimal.TEN);
//        trackPoint.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
//        trackPoint.setGps(gps);
//        trackPoint = trackPointRepository.save(trackPoint);
//
//        Assert.assertNull(gps.getTrackPoints());
//        Assert.assertNull(gps.getWayPoints());
//
//        gps.setTrackPoints(Collections.singletonList(trackPoint));
//
//
//        Assert.assertEquals(1, gps.getTrackPoints().size());
//        Assert.assertEquals(1, gps.getWayPoints().size());
//        Assert.assertTrue("id must be greater than 0", save.getId() > 0L);
    }

    @After
    public void destroy() {
    }


}
