package com.placetalkr.prototype.service;

import com.placetalkr.prototype.model.Place;
import com.placetalkr.prototype.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PlaceServiceImpl implements PlaceService{

    private PlaceRepository placeRepository;
    @Autowired
    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public Place getPlace(String id) {
        Optional<Place> place = placeRepository.findById(id);
        return place.get();
    }

    @Override
    public Place addPlace(Place place) {
        place = placeRepository.save(place);
        return place;
    }
}
