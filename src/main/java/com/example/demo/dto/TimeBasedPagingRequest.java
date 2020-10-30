package com.example.demo.dto;


import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class TimeBasedPagingRequest {

    private Timestamp last;
    private int count;

    public TimeBasedPagingRequest() {
    }

    public TimeBasedPagingRequest(Timestamp last, int count) {
        this.last = last;
        this.count = count;
    }

    public Timestamp getLast() {
        return last;
    }

    public void setLast(String last) {
        Instant instant = Instant.parse(last);
        this.last = Timestamp.from(instant);
    }

    public int getCount() {
        if (count <= 0 || count >= 50) {
            return 50;
        } else {
            return count;
        }
    }

    public void setCount(@Min(value = 1) int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeBasedPagingRequest that = (TimeBasedPagingRequest) o;
        return count == that.count &&
                Objects.equals(last, that.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(last, count);
    }

    @Override
    public String toString() {
        return "TimeBasedPagingRequest{" +
                "last=" + last +
                ", count=" + count +
                '}';
    }
}
