package com.placetalkr.prototype.repository;

import com.placetalkr.prototype.model.Place;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaceRepository extends MongoRepository<Place, String> {
}
