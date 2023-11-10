package com.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.userservice.entity.Hotel;
import com.userservice.entity.Rating;
import com.userservice.entity.User;
import com.userservice.exception.EmptyFieldsException;
import com.userservice.exception.NoDataFoundException;
import com.userservice.exception.ResourceNotFoundException;
import com.userservice.externalservice.HotelService;
import com.userservice.externalservice.RatingService;
import com.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HotelService hotelService;

	@Autowired
	private RatingService ratingService;

	@Override
	public User saveUser(User user) {

		// Generating a unique userId
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);

		if (user.getUserAbout().length() == 0 || user.getUserEmail().length() == 0
				|| user.getUserName().length() == 0) {
			throw new EmptyFieldsException("User fields cannot be empty.");
		}

		User savedUser = userRepository.save(user);

		return savedUser;
	}

	@Override
	public List<User> getAllUser() {

		// step 1 : fetched the userlist
		List<User> userList = userRepository.findAll();

		if (userList.isEmpty()) {
			throw new NoDataFoundException("No Data is present in Database.");
		}

		for (User user : userList) {

			// step 2 : fetched the ratings based on userId
			List<Rating> ratings = ratingService.getRating(user.getUserId());

			// step 3 : fetched the hotel based on ratingId
			List<Rating> fetchedRatingList = new ArrayList<>();

			for (Rating rating : ratings) {

				Hotel hotel = hotelService.getHotel(rating.getHotelId());

				// step 4 : setting the details of each hotel to its corresponding ratingId.
				rating.setHotel(hotel);

				fetchedRatingList.add(rating);
			}

			user.setRating(fetchedRatingList);
		}
		// step 5 : return the userList
		return userList;
	}

	@Override
	public User getUserById(String userId) {

		if (userId == null) {
			throw new IllegalArgumentException("null as Id is not acceptable.");
		}

		// Fetching userById
		User fetchedUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with this given id is not present: " + userId));

		List<Rating> fetchedRatingList = ratingService.getRating(fetchedUser.getUserId());

		List<Rating> ratingList = new ArrayList<>();

		for (Rating rating : fetchedRatingList) {
			// Using feign client
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			// Setting the hotel's information
			rating.setHotel(hotel);

			ratingList.add(rating);
		}

		// Setting the rating list data with fetched hotel information
		fetchedUser.setRating(ratingList);
		return fetchedUser;
	}

	@Override
	public void deleteUserById(String userId) {

		if (userId == null) {
			throw new IllegalArgumentException("null as Id is not acceptable.");
		}

		if (!userRepository.existsById(userId)) {
			throw new ResourceNotFoundException(
					"The userId which you have provided is not present in dB, so delete operation cannot be performed: "
							+ userId);
		}

		// step 1: delete the ratings first
		List<Rating> ratings = ratingService.getRating(userId);

		for (Rating rating : ratings) {
			ratingService.deleteRating(rating.getRatingId());
		}

		// Step 2: then delete the user.
		userRepository.deleteById(userId);
	}

	
	
	@Override
	public User updateUserById(String userId, User updatedFields) {
		User savedUser = null;

		if (userId == null) {
			throw new IllegalArgumentException("null as Id is not acceptable.");
		}
		
		if (!userRepository.existsById(userId)) {
			throw new ResourceNotFoundException(
					"The userId which you have provided is not present in dB, so update operation cannot be performed: "
							+ userId);
		}
		
		if (userRepository.existsById(userId)) {
			User existingUser = userRepository.findById(userId).get();

			if (updatedFields.getUserName() != null) {
				existingUser.setUserName(updatedFields.getUserName());
			}

			if (updatedFields.getUserEmail() != null) {
				existingUser.setUserEmail(updatedFields.getUserEmail());
			}

			if (updatedFields.getUserAbout() != null) {
				existingUser.setUserAbout(updatedFields.getUserAbout());
			}

			savedUser = userRepository.save(existingUser);
		}

		return savedUser;
	}

}
