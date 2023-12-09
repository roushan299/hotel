package com.booking.hotel.service;

import com.booking.hotel.exceptions.HotelNotFound;
import com.booking.hotel.model.Hotel;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class HotelService {

    private List<Hotel> hotelList = new ArrayList<>();

    private Map<String, Hotel> hotelMap = new HashMap<>();
    public void createHotel(Hotel hotel) {
        this.hotelList.add(hotel);
        this.hotelMap.put(hotel.getId(), hotel);
    }

    public Hotel getHotelById(String id) {
        if(ObjectUtils.isEmpty(hotelMap.get(id))){
            throw new HotelNotFound("Hotel not found for id: "+id);
        }
        return hotelMap.get(id);
    }

    public List<Hotel> getAllHotels() {
        return this.hotelList;
    }

    public void deleteHotelById(String id) {
        if(hotelMap.containsKey(id)){
            Hotel hotel = this.getHotelById(id);
            this.hotelList.remove(hotel);
            this.hotelMap.remove(id);
        }
    }

    public void updateHotel(Hotel hotel) {
        //get the exiting data of the hotel
        if(this.hotelMap.containsKey(hotel.getId())){
            Hotel prevHotel = this.hotelMap.get(hotel.getId());
            this.hotelList.remove(prevHotel);
            this.hotelList.add(hotel);
            this.hotelMap.put(hotel.getId(), hotel);
        }
    }
}
