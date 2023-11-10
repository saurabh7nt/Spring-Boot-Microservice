package com.hotelservice.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotelservice.entity.Hotel;
import com.hotelservice.exceptions.EmptyFieldsException;
import com.hotelservice.exceptions.NoDataFoundException;
import com.hotelservice.exceptions.NoResourceFoundException;
import com.hotelservice.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public List<Hotel> getAllHotels() {
		List<Hotel> hotelList = hotelRepository.findAll();
		
		if(hotelList.isEmpty()) {
			throw new NoDataFoundException("No data is present.");
		}
		
		return hotelList;
	}

	@Override
	public Hotel getHotelById(String hotelId) {
		Hotel fetchedHotel = hotelRepository.findById(hotelId).orElseThrow(() -> new NoResourceFoundException("Hotel not found"));
		return fetchedHotel;
	}
	
	@Override
	public Hotel saveHotel(Hotel hotel) {
		
		if(hotel.getHotelLocation().length() == 0 || hotel.getHotelAbout().length() == 0 || hotel.getHotelLocation().length() == 0) {
			throw new EmptyFieldsException("Hotel Fields cannot be empty.");
		}
		
		String randomHotelID = UUID.randomUUID().toString();
		hotel.setHotelId(randomHotelID);
		Hotel savedHotel = hotelRepository.save(hotel);
		
		return savedHotel;
	}
	
	@Override
	public void deleteById(String hotelId) {
		
		if (hotelId == null) {
			throw new IllegalArgumentException("null as Id is not acceptable.");
		}

		if (! hotelRepository.existsById(hotelId)) {
			throw new NoResourceFoundException(
					"Delete operation cannot be performed hotelId is not present:  "
							+ hotelId);
		}
		hotelRepository.deleteById(hotelId);
	}
	
	@Override
	public Hotel updateHotel(String hotelId, Hotel hotel) {
		Hotel updateHotel = null;
		
		if(hotelRepository.existsById(hotelId)) {
			updateHotel = hotelRepository.findById(hotelId).get();
			
			if(hotel.getHotelName() != null) {
				updateHotel.setHotelName(hotel.getHotelName());
			}
			if(hotel.getHotelAbout() != null) {
				updateHotel.setHotelAbout(hotel.getHotelAbout());
			}
			if(hotel.getHotelLocation() != null) {
				updateHotel.setHotelLocation(hotel.getHotelLocation());
			}
			hotelRepository.save(updateHotel);
		}
		
		return updateHotel;
	}
	
}

