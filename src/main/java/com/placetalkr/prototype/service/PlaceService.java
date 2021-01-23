package com.placetalkr.prototype.service;

import com.placetalkr.prototype.model.Place;

public interface PlaceService {
    Place getPlace(String id);
    Place addPlace(Place place);
}
