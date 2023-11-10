package com.hotelservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelservice.entity.Hotel;
import com.hotelservice.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@PostMapping("/save")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		Hotel savedHotel = hotelService.saveHotel(hotel);
		return new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId){
		Hotel fetchedHotel = hotelService.getHotelById(hotelId);
		return new ResponseEntity<>(fetchedHotel, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Hotel>> getAllHotels(){
		List<Hotel> hotelList = hotelService.getAllHotels();
		return new ResponseEntity<>(hotelList, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{hotelId}")
	public ResponseEntity<String> deleteHotelById(@PathVariable String hotelId){
		hotelService.deleteById(hotelId);
		return new ResponseEntity<>("Hotel Deleted.", HttpStatus.OK);
	}
	
	@PutMapping("update/{hotelId}")
	public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel, @PathVariable String hotelId){
		Hotel updatedHotel = hotelService.updateHotel(hotelId, hotel);
		return new ResponseEntity<Hotel>(updatedHotel, HttpStatus.OK);
	}
	
}
