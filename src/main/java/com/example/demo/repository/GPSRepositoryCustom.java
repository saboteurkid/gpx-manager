package com.example.demo.repository;

import com.example.demo.domain.GPS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface GPSRepositoryCustom {
    List<GPS> pagingByUserId(long userId, long lastIndex, int count);
}
