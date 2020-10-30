package com.example.demo.controller;

import com.example.demo.dto.GPXWrapper;
import com.example.demo.service.GPXService;
import io.jenetics.jpx.GPX;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;

@RestController
@RequestMapping(path = "/gps")
public class GPSController {

    final GPXService service;

    public GPSController(GPXService service) {
        this.service = service;
    }

    @PostMapping(
            value = "/new",
            produces = MimeTypeUtils.ALL_VALUE
    )
    public ResponseEntity<?> newGps(@RequestParam("file") MultipartFile file, @RequestParam("userId") long userId) throws IOException {
        GPX gpx = GPX.read(file.getInputStream());
        GPXWrapper saved = service.save(new GPXWrapper(gpx, 0L, userId));
        return ResponseEntity.ok(saved);
    }

    @GetMapping(
            value = "/{id}",
            produces = MimeTypeUtils.APPLICATION_XML_VALUE
    )
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        GPXWrapper gpx = service.findById(id);
        if (gpx == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gpx.toString());
    }
}
