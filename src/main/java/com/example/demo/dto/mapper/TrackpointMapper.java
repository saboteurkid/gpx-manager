package com.example.demo.dto.mapper;

import com.example.demo.domain.TrackPoint;
import com.example.demo.dto.TrackPointDto;
import org.springframework.stereotype.Component;

@Component
public class TrackpointMapper {

    public TrackPointDto domain2dto(TrackPoint trackPoint) {
        return TrackPointDto.TrackPointDtoBuilder.aTrackPointDto()
                .withGpxId(trackPoint.getGps().getId())
                .withId(trackPoint.getId())
                .withLat(trackPoint.getLat().doubleValue())
                .withLon(trackPoint.getLon().doubleValue())
                .withTimestamp(trackPoint.getTimestamp())
                .build();
    }
}
