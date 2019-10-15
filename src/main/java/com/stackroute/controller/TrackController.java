package com.stackroute.controller;


import com.stackroute.domain.Track;
import com.stackroute.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/v1/")
@RestController
public class TrackController {

   private TrackService trackService;

    public TrackController(TrackService trackService){
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack (@RequestBody Track track){
        ResponseEntity responseEntity;
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully Created!",HttpStatus.CREATED);
        }catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return  responseEntity;
    }

    @GetMapping("track")
    //RequestBody annotation of reference Track is not required
    public ResponseEntity<?> getAllTracks(){
        ResponseEntity responseEntity;
        try {
            trackService.getAllTracks();
            responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("track")
    public ResponseEntity<?> deleteTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully Deleted!",HttpStatus.ACCEPTED);
        }catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

    @PutMapping("track/{id}/{comment}")
    public ResponseEntity<?> updateTrack(@PathVariable int id, @PathVariable String comment) {
        ResponseEntity responseEntity;
        try {
            trackService.updateTrack(id,comment);
            responseEntity = new ResponseEntity<>(trackService.getTrackById(id), HttpStatus.CREATED);
        }
        catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
