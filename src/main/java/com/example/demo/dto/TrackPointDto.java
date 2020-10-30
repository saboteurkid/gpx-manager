package com.example.demo.dto;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class TrackPointDto {
    private long id;
    private Timestamp timestamp;
    private long gpxId;
    private Double lon;
    private Double lat;

    public String getTimestamp() {
        return timestamp.toInstant().toString();
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackPointDto trackPointDto = (TrackPointDto) o;
        return id == trackPointDto.id &&
                gpxId == trackPointDto.gpxId &&
                Objects.equals(lon, trackPointDto.lon) &&
                Objects.equals(lat, trackPointDto.lat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gpxId, lon, lat);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGpxId() {
        return gpxId;
    }

    public void setGpxId(long gpxId) {
        this.gpxId = gpxId;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }


    public static final class TrackPointDtoBuilder {
        private long id;
        private Timestamp timestamp;
        private long gpxId;
        private Double lon;
        private Double lat;

        private TrackPointDtoBuilder() {
        }

        public static TrackPointDtoBuilder aTrackPointDto() {
            return new TrackPointDtoBuilder();
        }

        public TrackPointDtoBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public TrackPointDtoBuilder withTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public TrackPointDtoBuilder withGpxId(long gpxId) {
            this.gpxId = gpxId;
            return this;
        }

        public TrackPointDtoBuilder withLon(Double lon) {
            this.lon = lon;
            return this;
        }

        public TrackPointDtoBuilder withLat(Double lat) {
            this.lat = lat;
            return this;
        }

        public TrackPointDto build() {
            TrackPointDto trackPointDto = new TrackPointDto();
            trackPointDto.setId(id);
            trackPointDto.setTimestamp(timestamp);
            trackPointDto.setGpxId(gpxId);
            trackPointDto.setLon(lon);
            trackPointDto.setLat(lat);
            return trackPointDto;
        }
    }
}
