package com.mathew.example.springboot.service.roomreservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RoomReservationController {

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/rooms", method = RequestMethod.GET)
	public List<Room> getAllRooms() {

		ResponseEntity<List<Room>> roomResponse = restTemplate.exchange("http://ROOMSERVICES/rooms", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Room>>() {
				});
		return roomResponse.getBody();

	}

	@RequestMapping(value = "testString", method = RequestMethod.GET)
	public String testSting(@RequestParam(name = "testStr", required = true) String testS) {
		String url = "http://ROOMSERVICES/teststringfromroomservice";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		System.out.println(response.getStatusCodeValue());
		String retVal = response.getBody();
		return retVal;

	}

	@RequestMapping(value = "roombynumber", method = RequestMethod.GET)
	public Room getRoomByRoomNumber(@RequestParam(name = "roomNumber", required = true) String roomNumber) {
		String url = "http://ROOMSERVICES/roombynumber?roomNumber="+roomNumber;
		ResponseEntity<Room> response = restTemplate.exchange(url, HttpMethod.GET, null, Room.class);
		System.out.println(response.getStatusCodeValue());
		Room retVal = response.getBody();
		return retVal;
	}

}
