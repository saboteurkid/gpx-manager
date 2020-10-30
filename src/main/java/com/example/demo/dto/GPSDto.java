package com.example.demo.dto;

import java.util.Objects;

public class GPSDto {
    private long id;
    private String url;

    public GPSDto() {
    }

    public GPSDto(long id, String url) {
        this.id = id;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPSDto gpsDto = (GPSDto) o;
        return id == gpsDto.id &&
                Objects.equals(url, gpsDto.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
