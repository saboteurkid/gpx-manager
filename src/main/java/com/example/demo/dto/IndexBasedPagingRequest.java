package com.example.demo.dto;

import java.util.Objects;

public class IndexBasedPagingRequest {

    private long last = 0L;
    private int count = 50;

    public IndexBasedPagingRequest() {
    }

    public IndexBasedPagingRequest(long last, int count) {
        this.last = last;
        this.count = count;
    }

    public long getLast() {
        return last;
    }

    public void setLast(long last) {
        this.last = last;
    }

    public int getCount() {
        if (count <= 0 || count >= 50) {
            return 50;
        } else {
            return count;
        }
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexBasedPagingRequest that = (IndexBasedPagingRequest) o;
        return last == that.last &&
                count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(last, count);
    }

    @Override
    public String toString() {
        return "IndexBasedPagingRequest{" +
                "last=" + last +
                ", count=" + count +
                '}';
    }
}
