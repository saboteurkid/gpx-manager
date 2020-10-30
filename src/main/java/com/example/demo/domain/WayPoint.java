package com.example.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "WayPoint")
@Table(name = "waypoint",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"lon", "lat"})
        })
public class WayPoint implements Serializable {

    static final long serialVersionUID = -1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "lon", nullable = false)
    private BigDecimal lon;
    @Column(name = "lat", nullable = false)
    private BigDecimal lat;
    @Column(name = "`name`")
    private String name;
    @Column(name = "symlink")
    private String symlink;

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymlink() {
        return symlink;
    }

    public void setSymlink(String symlink) {
        this.symlink = symlink;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Embeddable
    public static class Point implements Serializable {
        public Point(BigDecimal lon, BigDecimal lat) {
            this.lon = lon;
            this.lat = lat;
        }

        public Point() {
        }

        @Column(name = "lon", nullable = false)
        private BigDecimal lon;
        @Column(name = "lat", nullable = false)
        private BigDecimal lat;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return Objects.equals(lon, point.lon) &&
                    Objects.equals(lat, point.lat);
        }

        @Override
        public int hashCode() {
            return Objects.hash(lon, lat);
        }

        public BigDecimal getLon() {
            return lon;
        }

        public void setLon(BigDecimal lon) {
            this.lon = lon;
        }

        public BigDecimal getLat() {
            return lat;
        }

        public void setLat(BigDecimal lat) {
            this.lat = lat;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WayPoint wayPoint = (WayPoint) o;
        return id == wayPoint.id &&
                Objects.equals(lon, wayPoint.lon) &&
                Objects.equals(lat, wayPoint.lat) &&
                Objects.equals(name, wayPoint.name) &&
                Objects.equals(symlink, wayPoint.symlink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lon, lat, name, symlink);
    }
}
