package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.GPXService;
import com.example.demo.service.TrackpointService;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import javax.validation.constraints.Min;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/user")
@RestController
public class UserController implements ServletContextAware {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    final GPXService gpxService;
    final TrackpointService trackpointService;
    private ServletContext servletContext;

    public UserController(GPXService gpxService, TrackpointService trackpointService) {
        this.gpxService = gpxService;
        this.trackpointService = trackpointService;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @GetMapping(
            value = "/{userId}/gpxes",
            produces = MimeTypeUtils.APPLICATION_XML_VALUE
    )
    public ResponseEntity<?> listGPXByUser(
            @PathVariable("userId") @Min(1) long userId,
            IndexBasedPagingRequest pagingRequest
    ) {
        log.info("userId: {}, pagingRequest: {}", userId, pagingRequest);
        List<GPSDto> dtos = gpxService.findByUserId(userId, pagingRequest)
                .stream()
                .map(GPXWrapper::getId)
                .map(id -> new GPSDto(id, servletContext.getContextPath() + "/gps/" + id))
                .collect(Collectors.toList());
        GPSXMLResponse GPSXMLResponse = new GPSXMLResponse();
        GPSXMLResponse.addAll(dtos);
        return ResponseEntity.ok(GPSXMLResponse);
    }

    @GetMapping(
            value = "/{userId}/trackpoints",
            produces = MimeTypeUtils.APPLICATION_XML_VALUE
    )
    public ResponseEntity<?> listTrackpoint(
            @PathVariable("userId") long userId,
            TimeBasedPagingRequest pagingRequest
    ) {
        log.info("userId: {}, pagingRequest: {}", userId, pagingRequest);
        List<TrackPointDto> trackpointsDto = trackpointService.findLatestTrackpointsByUser(userId, pagingRequest);
        TrackPointResponse resp = new TrackPointResponse();
        resp.addAll(trackpointsDto);
        return ResponseEntity.ok(resp);
    }

    @JacksonXmlRootElement(localName = "gpses")
    static
    class GPSXMLResponse extends ArrayList<GPSDto> {
    }

    @JacksonXmlRootElement(localName = "trackpoints")
    static
    class TrackPointResponse extends ArrayList<TrackPointDto> {

    }

}
