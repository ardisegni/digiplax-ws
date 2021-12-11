package org.worldemunah.digiplax.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.worldemunah.digiplax.api.service.StreamingService;

/**
 * User: Ariel
 * Date: 3/12/2019
 */
@RestControllerAdvice
@CrossOrigin
@RequestMapping("/api")
public class StreamingController extends BaseController {

    @Autowired
    private StreamingService streamingService;

    @GetMapping(value = "/streamPlaques")
    public ResponseEntity<?> streamPlaques(@RequestParam(required = true) String project) {
        return ResponseEntity.ok(streamingService.getStreamingPlaques(project));
    }

    @GetMapping(value = "/getScheduledPlaques")
    public ResponseEntity<?> getScheduledPlaques(@RequestParam(required = true) String project) {
        return ResponseEntity.ok(streamingService.getScheduledPlaques(project));
    }
}
