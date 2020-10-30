package com.example.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Table(
        name = "trackpoint",
        indexes = {
                @Index(columnList = "user_id,`timestamp`")
        }
)
@Entity(name = "TrackPoint")
public class TrackPoint implements Serializable {
    static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "lat")
    private BigDecimal lat;
    @Column(name = "lon")
    private BigDecimal lon;
    @Column(name = "elevation")
    private BigDecimal elevation; // in meter
    @Column(name = "`timestamp`")
    private Timestamp timestamp;
    // Ref
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gps_id", nullable = false)
    private GPS gps;

    @Column(name = "user_id")
    private long userId;


    public long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackPoint that = (TrackPoint) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(lat, that.lat) &&
                Objects.equals(lon, that.lon) &&
                Objects.equals(elevation, that.elevation) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(gps, that.gps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lat, lon, elevation, timestamp, gps, userId);
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getElevation() {
        return elevation;
    }

    public void setElevation(BigDecimal elevation) {
        this.elevation = elevation;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public GPS getGps() {
        return gps;
    }

    public void setGps(GPS gps) {
        this.gps = gps;
    }


    @Override
    public String toString() {
        return "TrackPoint{" +
                "id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                ", elevation=" + elevation +
                ", timestamp=" + timestamp +
                ", userId=" + userId +
                '}';
    }
}
