package com.placetalkr.prototype.controller;

import com.placetalkr.prototype.dto.PlaceDto;
import com.placetalkr.prototype.model.Place;
import com.placetalkr.prototype.repository.PlaceRepository;
import com.placetalkr.prototype.service.PlaceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/place")
public class PlaceController {
    @Autowired
    PlaceService placeService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/get")
    public ResponseEntity<PlaceDto> getPlace(@RequestParam String id){
        Place place = placeService.getPlace(id);
        if(place != null){
            PlaceDto placeDto = modelMapper.map(place, PlaceDto.class);
            return new ResponseEntity<>(placeDto, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<PlaceDto> addPlace(@RequestBody PlaceDto placeDto){
        Place place = modelMapper.map(placeDto, Place.class);
        place = placeService.addPlace(place);
        placeDto.setId(place.getId());
        return new ResponseEntity<PlaceDto>(placeDto, HttpStatus.OK);
    }
}
