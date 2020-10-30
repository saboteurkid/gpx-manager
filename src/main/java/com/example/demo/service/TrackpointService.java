package com.example.demo.service;

import com.example.demo.domain.TrackPoint;
import com.example.demo.dto.TimeBasedPagingRequest;
import com.example.demo.dto.TrackPointDto;
import com.example.demo.dto.mapper.TrackpointMapper;
import com.example.demo.repository.TrackPointRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackpointService {

    private final TrackPointRepository repository;
    private final TrackpointMapper mapper;

    public TrackpointService(TrackPointRepository repository,
                             TrackpointMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<TrackPointDto> findLatestTrackpointsByUser(long userId, TimeBasedPagingRequest pagingRequest) {
        List<TrackPoint> points = repository.findByUserIdOrderByTimestampDesc(userId,
                pagingRequest.getLast(),
                pagingRequest.getCount()
        );
        return points.stream()
                .map(mapper::domain2dto)
                .collect(Collectors.toList());
    }
}
