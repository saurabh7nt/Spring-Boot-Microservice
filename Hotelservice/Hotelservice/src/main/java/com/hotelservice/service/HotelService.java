package com.hotelservice.service;

import java.util.List;

import com.hotelservice.entity.Hotel;

public interface HotelService {

	Hotel saveHotel(Hotel hotel);
	
	Hotel getHotelById(String hotelId);
	
	List<Hotel> getAllHotels();

	void deleteById(String hotelId);
	
	Hotel updateHotel(String hotelId, Hotel hotel);
	
}
