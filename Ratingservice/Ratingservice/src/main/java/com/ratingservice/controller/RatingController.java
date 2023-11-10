package com.ratingservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingservice.entity.Rating;
import com.ratingservice.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/save")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		return new ResponseEntity<>(ratingService.createRating(rating), HttpStatus.CREATED);
	}
	
	@GetMapping("/getall")
	public ResponseEntity<List<Rating>> getRatings(){
		return new ResponseEntity<List<Rating>>(ratingService.listOfRating(), HttpStatus.OK);
	}
	
	@GetMapping("/get/hotel/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId){
		return new ResponseEntity<List<Rating>>(ratingService.getRatingByHotelId(hotelId), HttpStatus.OK);
	}
	
	@GetMapping("/get/user/{userId}")
	public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
		return new ResponseEntity<List<Rating>>(ratingService.getRatingByUserId(userId), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{ratingId}")
	public ResponseEntity<String> deleteRatingById(@PathVariable String ratingId){
		ratingService.deleteRatingById(ratingId);
		return new ResponseEntity<>("Rating Deleted.", HttpStatus.OK);
	}
	
}
