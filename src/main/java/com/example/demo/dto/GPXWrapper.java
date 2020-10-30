package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.jenetics.jpx.GPX;

import java.util.Objects;

@JacksonXmlRootElement(localName = "gpx")
public class GPXWrapper {

    @JsonIgnore
    private GPX gpx;
    private long id;

    @JsonIgnore
    private long userId;

    public GPXWrapper(GPX gpx, long id, long userId) {
        this.gpx = gpx;
        this.id = id;
        this.userId = userId;
    }

    public GPXWrapper() {
    }

    public GPX getGpx() {
        return gpx;
    }

    public void setGpx(GPX gpx) {
        this.gpx = gpx;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPXWrapper that = (GPXWrapper) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(gpx, that.gpx);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gpx, id, userId);
    }

    @Override
    public String toString() {
        return GPX.writer().toString(gpx);
    }
}
