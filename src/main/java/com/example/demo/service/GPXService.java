package com.example.demo.service;

import com.example.demo.domain.GPS;
import com.example.demo.domain.WayPoint;
import com.example.demo.dto.GPXWrapper;
import com.example.demo.dto.IndexBasedPagingRequest;
import com.example.demo.dto.mapper.GpxMapper;
import com.example.demo.repository.GPSRepository;
import com.example.demo.repository.WayPointRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GPXService {

    private final GpxMapper mapper;
    private final GPSRepository repository;
    private final WayPointRepository wayPointRepository;

    public GPXService(GpxMapper mapper,
                      GPSRepository repository,
                      WayPointRepository wayPointRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.wayPointRepository = wayPointRepository;
    }

    public GPXWrapper save(GPXWrapper gpx) {
        if (gpx == null) {
            return null;
        }
        GPS gps = mapper.dto2domain(gpx);
        GPS saved = repository.save(gps);
        return mapper.domain2dto(saved);
    }

    public GPXWrapper findById(long id) {
        GPS gps = repository.findOne(id);
        if (gps == null) {
            return null;
        }
        List<WayPoint> wayPoints = wayPointRepository.findByGpsId(id);
        gps.setWayPoints(new HashSet<>(wayPoints));
        return mapper.domain2dto(gps);
    }

    public List<GPXWrapper> findByUserId(long userId, IndexBasedPagingRequest pagingRequest) {
        List<GPS> gps = repository.pagingByUserId(userId, pagingRequest.getLast(), pagingRequest.getCount());
        return gps.stream()
                .map(mapper::domain2dto)
                .collect(Collectors.toList());
    }
}
