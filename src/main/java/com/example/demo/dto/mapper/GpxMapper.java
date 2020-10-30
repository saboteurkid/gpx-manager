package com.example.demo.dto.mapper;

import com.example.demo.domain.GPS;
import com.example.demo.domain.TrackPoint;
import com.example.demo.dto.GPXWrapper;
import io.jenetics.jpx.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GpxMapper {
    public GPXWrapper domain2dto(GPS gps) {
        if (gps == null) {
            return null;
        }
        GPXWrapper gpxWrapper = new GPXWrapper();

        GPX gpx = GPX.builder()
                .metadata(Metadata.builder()
                        .addLink(Link.of(gps.getLink().getHref(), gps.getLink().getText(), null))
                        .author("" + gps.getUserId())
                        .desc(gps.getDesc())
                        .name(gps.getName())
                        .time(gps.getTimestamp().getTime())
                        .build())
                .wayPoints(gps.getWayPoints()
                        .stream()
                        .map(w -> WayPoint.builder()
                                .name(w.getName())
                                .sym(w.getSymlink())
                                .build(w.getLat().doubleValue(), w.getLon().doubleValue()))
                        .collect(Collectors.toList()))
                .addTrack(track -> track.addSegment(seg -> {
                    gps.getTrackPoints().forEach(tp -> seg.addPoint(WayPoint.builder()
                            .time(Optional.ofNullable(tp.getTimestamp()).map(Timestamp::getTime).orElse(0L))
                            .ele(tp.getElevation().doubleValue())
                            .build(tp.getLat().doubleValue(), tp.getLon().doubleValue())
                    ));
                }).build())
                .build();
        gpxWrapper.setGpx(gpx);
        gpxWrapper.setId(gps.getId());
        gpxWrapper.setUserId(gps.getUserId());
        return gpxWrapper;
    }

    public GPS dto2domain(GPXWrapper gpxWrapper) {
        if (gpxWrapper == null) {
            return null;
        }
        GPS gps = new GPS();

        GPX gpx = gpxWrapper.getGpx();
        gps.setId(gpxWrapper.getId());
        gps.setUserId(gpxWrapper.getUserId());
        gps.setName(gpx.getMetadata().flatMap(Metadata::getName).orElse(""));
        gps.setTimestamp(gpx.getMetadata().flatMap(Metadata::getTime).map(t -> Timestamp.from(t.toInstant())).orElse(Timestamp.valueOf(LocalDateTime.now())));
        gps.setDesc(gpx.getMetadata().flatMap(Metadata::getDescription).orElse(""));

        GPS.Link gpsLink = gpx.getMetadata().map(m -> {
            if (m.getLinks().isEmpty()) {
                return null;
            } else {
                return m.getLinks().get(0);
            }
        }).map(link -> new GPS.Link(link.getHref().toString(), link.getText().orElse(""))).orElse(null);
        gps.setLink(gpsLink);

        List<TrackPoint> trackPoints = gpx.getTracks()
                .stream().flatMap(t -> t.getSegments().stream())
                .flatMap(s -> s.getPoints().stream())
                .map(p -> {
                    BigDecimal ele = p.getElevation().map(l -> BigDecimal.valueOf(l.doubleValue())).orElse(BigDecimal.ZERO);
                    Timestamp timestamp = p.getTime().map(z -> Timestamp.from(z.toInstant()))
                            .orElse(null);
                    TrackPoint trackPoint = new TrackPoint();
                    trackPoint.setUserId(gpxWrapper.getUserId());
                    trackPoint.setElevation(ele);
                    trackPoint.setLon(BigDecimal.valueOf(p.getLongitude().doubleValue()));
                    trackPoint.setLat(BigDecimal.valueOf(p.getLatitude().doubleValue()));
                    trackPoint.setTimestamp(timestamp);
                    trackPoint.setGps(gps);
                    return trackPoint;
                })
                .collect(Collectors.toList());
        gps.setTrackPoints(trackPoints);

        Set<com.example.demo.domain.WayPoint> wayPoints = gpx.getWayPoints().stream().map(w -> {
            com.example.demo.domain.WayPoint wayPoint = new com.example.demo.domain.WayPoint();
            wayPoint.setLat(BigDecimal.valueOf(w.getLatitude().doubleValue()));
            wayPoint.setLon(BigDecimal.valueOf(w.getLongitude().doubleValue()));
            wayPoint.setSymlink(w.getSymbol().orElse(null));
            wayPoint.setName(w.getName().orElse(null));
            return wayPoint;
        }).collect(Collectors.toSet());
        gps.setWayPoints(wayPoints);
        return gps;
    }

}
