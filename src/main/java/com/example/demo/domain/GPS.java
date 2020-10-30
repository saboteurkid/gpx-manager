package com.example.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@Table(
        name = "gps",
        indexes = {
                @Index(columnList = "user_id"),
                @Index(columnList = "`timestamp`")
        }
)
@Entity(name = "GPS")
public class GPS implements Serializable {

    static final long serialVersionUID = -1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "`name`")
    private String name;
    @Column(name = "`desc`", columnDefinition = "TEXT")
    private String desc;

    @Column(name = "user_id")
    private long userId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "href", column = @Column(name = "link_href")),
            @AttributeOverride(name = "text", column = @Column(name = "link_text")),
    })
    private Link link;
    @Column(name = "`timestamp`")
    private Timestamp timestamp;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "gps"
    )
    private List<TrackPoint> trackPoints = new ArrayList<>();


    @Transient
    private Set<WayPoint> wayPoints = new HashSet<>();

    public Set<WayPoint> getWayPoints() {
        return wayPoints;
    }

    public void setWayPoints(Set<WayPoint> wayPoints) {
        this.wayPoints = wayPoints;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public List<TrackPoint> getTrackPoints() {
        return trackPoints;
    }

    public void setTrackPoints(List<TrackPoint> trackPoints) {
        this.trackPoints = trackPoints;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPS gps = (GPS) o;
        return id == gps.id &&
                Objects.equals(name, gps.name) &&
                Objects.equals(desc, gps.desc) &&
                Objects.equals(link, gps.link) &&
                Objects.equals(userId, gps.userId) &&
                Objects.equals(timestamp, gps.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, link, userId, timestamp);
    }

    @Embeddable
    public static class Link implements Serializable {

        public Link() {
        }

        public Link(String href, String text) {
            this.href = href;
            this.text = text;
        }

        String href;
        String text;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Link link = (Link) o;
            return Objects.equals(href, link.href) &&
                    Objects.equals(text, link.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(href, text);
        }
    }


    public static final class GPSBuilder {
        private long id;
        private String name;
        private String desc;
        private long userId;
        private Link link;
        private Timestamp timestamp;
        private List<TrackPoint> trackPoints;
        private Set<WayPoint> wayPoints;

        private GPSBuilder() {
        }

        public static GPSBuilder aGPS() {
            return new GPSBuilder();
        }

        public GPSBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public GPSBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public GPSBuilder withDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public GPSBuilder withUserId(long userId) {
            this.userId = userId;
            return this;
        }

        public GPSBuilder withLink(Link link) {
            this.link = link;
            return this;
        }

        public GPSBuilder withTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public GPSBuilder withTrackPoints(List<TrackPoint> trackPoints) {
            this.trackPoints = trackPoints;
            return this;
        }

        public GPSBuilder withWayPoints(Set<WayPoint> wayPoints) {
            this.wayPoints = wayPoints;
            return this;
        }

        public GPS build() {
            GPS gPS = new GPS();
            gPS.setId(id);
            gPS.setName(name);
            gPS.setDesc(desc);
            gPS.setUserId(userId);
            gPS.setLink(link);
            gPS.setTimestamp(timestamp);
            gPS.setTrackPoints(trackPoints);
            return gPS;
        }
    }
}
