package com.placetalkr.prototype.service;

import com.placetalkr.prototype.model.Place;
import com.placetalkr.prototype.repository.PlaceRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PlaceServiceImplTest {
    @Mock
    private PlaceRepository placeRepository;
    private PlaceServiceImpl placeService;

    @Before
    public void setup(){
        placeRepository = Mockito.mock(PlaceRepository.class);
        placeService = new PlaceServiceImpl(placeRepository);
    }

    @Test
    public void getPlaceTest(){
        Place place = new Place();
        place.setId("1");
        place.setCity("Pune");
        when(placeRepository.findById(any(String.class))).thenReturn(Optional.of(place));
        Place p = placeService.getPlace("1");
        Assert.assertEquals("Pune", p.getCity());
    }

    @Test
    public void addPlaceTest(){
        Place place = new Place();
        place.setId("1");
        place.setCity("Pune");
        when(placeRepository.save(any(Place.class))).thenReturn(place);
        Place p = placeService.addPlace(place);
        Assert.assertEquals("Pune", p.getCity());
    }
}
