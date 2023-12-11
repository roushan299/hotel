package com.booking.hotel.service;

import com.booking.hotel.communicator.RatingServiceCommunicator;
import com.booking.hotel.exceptions.HotelNotFound;
import com.booking.hotel.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import java.util.*;

@Service
public class HotelService {

    private List<Hotel> hotelList = new ArrayList<>();
    private Map<String, Hotel> hotelMap = new HashMap<>();
    @Autowired
    private RatingServiceCommunicator ratingServiceCommunicator;

    public void createHotel(Hotel hotel) {
        Map<String, Long> ratingMap = new HashMap<>();
        ratingMap.put(hotel.getId(), hotel.getRating());
        this.hotelList.add(hotel);
        this.hotelMap.put(hotel.getId(), hotel);
        this.ratingServiceCommunicator.addRating(ratingMap);
    }

    public Hotel getHotelById(String id) {
        if(ObjectUtils.isEmpty(hotelMap.get(id))){
            throw new HotelNotFound("Hotel not found for id: "+id);
        }
        Hotel hotel = this.hotelMap.get(id);
        //rest service to fetch the rating by id
        long updatedRating = this.ratingServiceCommunicator.getRating(id);
        hotel.setRating(updatedRating);
        return hotel;
    }

    public List<Hotel> getAllHotels() {
        return this.hotelList;
    }

    public void deleteHotelById(String id) {
        if(hotelMap.containsKey(id)){
            Hotel hotel = this.getHotelById(id);
            this.hotelList.remove(hotel);
            this.hotelMap.remove(id);

            //deleting hotel rating from the rating service
            this.ratingServiceCommunicator.deleteRating(id);
        }
    }

    public void updateHotel(Hotel hotel) {
        //get the exiting data of the hotel
        if(this.hotelMap.containsKey(hotel.getId())){
            Hotel prevHotel = this.hotelMap.get(hotel.getId());
            this.hotelList.remove(prevHotel);
            this.hotelList.add(hotel);
            this.hotelMap.put(hotel.getId(), hotel);

            //updating rating in the rating service api
            if(!prevHotel.getRating().equals(hotel.getRating())){
                Map<String, Long> updatedRating = new HashMap<String,Long>();
                updatedRating.put(hotel.getId(), hotel.getRating());
                this.ratingServiceCommunicator.updateRating(updatedRating);
            }
        }
    }

}
