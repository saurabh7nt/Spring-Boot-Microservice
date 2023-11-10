package com.userservice.externalservice;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.userservice.entity.Rating;


@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	@GetMapping("/rating/get/user/{userId}")
	List<Rating> getRating(@PathVariable String userId);
	
	@DeleteMapping("/rating/delete/{ratingId}")
	Void deleteRating(@PathVariable String ratingId);
	
}
