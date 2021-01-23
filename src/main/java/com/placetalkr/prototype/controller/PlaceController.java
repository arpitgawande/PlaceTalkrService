package com.placetalkr.prototype.controller;

import com.placetalkr.prototype.dto.PlaceDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/place")
public class PlaceController {

    @GetMapping("/get")
    public ResponseEntity<PlaceDto> getPlace(@RequestParam String id){
        PlaceDto place = new PlaceDto("1", "doctor", "physyotheropy");
        return ResponseEntity.ok(place);
    }

//    @PostMapping("/add")
//    public ResponseEntity<PlaceDto> addPlace(@RequestBody PlaceDto placeDto){
//
//    }
}
