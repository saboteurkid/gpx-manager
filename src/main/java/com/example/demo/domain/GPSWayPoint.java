package com.example.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "GPSWayPoint")
@Table(name = "gps_waypoint")
public class GPSWayPoint implements Serializable {

    @EmbeddedId
    private Id id = new Id();

    public GPSWayPoint(Id id) {
        this.id = id;
    }

    public GPSWayPoint() {
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPSWayPoint that = (GPSWayPoint) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Embeddable
    public static class Id implements Serializable {
        public Id(long gpsId, long waypointId) {
            this.gpsId = gpsId;
            this.waypointId = waypointId;
        }

        public Id() {
        }

        @Column(name = "gps_id")
        private long gpsId;

        @Column(name = "waypoint_id")
        private long waypointId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Id id = (Id) o;
            return gpsId == id.gpsId &&
                    waypointId == id.waypointId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(gpsId, waypointId);
        }

        public long getGpsId() {
            return gpsId;
        }

        public void setGpsId(long gpsId) {
            this.gpsId = gpsId;
        }

        public long getWaypointId() {
            return waypointId;
        }

        public void setWaypointId(long waypointId) {
            this.waypointId = waypointId;
        }
    }
}
