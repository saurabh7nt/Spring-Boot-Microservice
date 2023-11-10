package com.ratingservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ratingservice.entity.Rating;
import com.ratingservice.exceptions.EmptyFieldsException;
import com.ratingservice.exceptions.NoDataFoundException;
import com.ratingservice.exceptions.NoResourceFoundException;
import com.ratingservice.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	@Override
	public Rating createRating(Rating rating) {

		if (rating.getFeedBack().length() == 0 || rating.getUserId().length() == 0 || rating.getRating() < 0
				|| rating.getHotelId().length() == 0) {
			throw new EmptyFieldsException("Rating fields cannot be empty.");
		}

		String ratingId = UUID.randomUUID().toString();
		rating.setRatingId(ratingId);
		Rating savedRating = ratingRepository.save(rating);
		return savedRating;
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		List<Rating> ratingList = ratingRepository.findByHotelId(hotelId);
		
		if(ratingList.isEmpty()) {
			throw new NoDataFoundException("Rating for given hotel is not present.");
		}
		
		return ratingList;
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		List<Rating> ratingList = ratingRepository.findByUserId(userId);
		
		if(ratingList.isEmpty()) {
			throw new NoDataFoundException("Rating for given hotel is not present");
		}
		
		return ratingList;
	}

	@Override
	public List<Rating> listOfRating() {
		List<Rating> ratingList = ratingRepository.findAll();
		
		if(ratingList.isEmpty()) {
			throw new NoDataFoundException("No rating data is present.");
		}
		
		return ratingList;
	}

	@Override
	public void deleteRatingById(String ratingId) {
		
		if (ratingId == null) {
			throw new IllegalArgumentException("null as Id is not acceptable.");
		}

		if (! ratingRepository.existsById(ratingId)) {
			throw new NoResourceFoundException("The ratingId which you have provided is not present:" +ratingId);
		}
		
		ratingRepository.deleteById(ratingId);
	}

}
