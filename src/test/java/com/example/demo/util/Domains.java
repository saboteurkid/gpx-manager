package com.example.demo.util;

import com.example.demo.domain.GPS;
import com.example.demo.domain.TrackPoint;
import com.example.demo.domain.WayPoint;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public final class Domains {

    private static final Faker faker = new Faker();

    private Domains() {
        throw new UnsupportedOperationException();
    }


    public static TrackPoint newTrackPoint(GPS gps) {
        TrackPoint trackPoint = new TrackPoint();
        trackPoint.setGps(gps);
        trackPoint.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        trackPoint.setLon(BigDecimal.valueOf(faker.number().randomDouble(2, -90, 90)));
        trackPoint.setLat(BigDecimal.valueOf(faker.number().randomDouble(2, -90, 90)));
        trackPoint.setElevation(BigDecimal.valueOf(faker.number().randomDouble(2, 0, 100_000)));
        trackPoint.setUserId(gps.getUserId());
        return trackPoint;
    }

    public static WayPoint newWayPoint() {
        WayPoint wayPoint = new WayPoint();
        wayPoint.setLon(BigDecimal.valueOf(faker.number().randomDouble(2, -90, 90)));
        wayPoint.setLat(BigDecimal.valueOf(faker.number().randomDouble(2, -90, 90)));
        wayPoint.setSymlink(faker.avatar().image());
        wayPoint.setName(faker.funnyName().name());
        return wayPoint;
    }

    public static GPS newGPS(long userId) {
        GPS gps = new GPS();
        gps.setDesc(faker.lorem().sentence());
        gps.setLink(new GPS.Link(faker.avatar().image(), faker.funnyName().name()));
        gps.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        gps.setName(faker.name().name());
        gps.setUserId(userId);
        return gps;
    }
}
