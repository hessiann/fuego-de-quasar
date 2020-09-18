package com.meli.quasar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meli.quasar.exception.MessageNotFoundException;
import com.meli.quasar.model.dto.LocationDto;
import com.meli.quasar.model.request.TopSecretRequest;
import com.meli.quasar.model.response.TopSecretResponse;
import com.meli.quasar.service.CalculateCoordinatesService;
import com.meli.quasar.service.RetrieveMessageService;

@RestController
public class ShipMessageController {

	@Autowired
	private CalculateCoordinatesService coordinatesService;

	@Autowired
	private RetrieveMessageService messageService;

	@PostMapping("/topsecret")
	public TopSecretResponse getMessageAndLocation(@RequestBody TopSecretRequest message)
			throws MessageNotFoundException {

		if (message.getSatellites() == null || message.getSatellites().isEmpty()) {
			throw new MessageNotFoundException("La lista vino vacia");
		}

		String fullMessage = messageService.retrieveMessage(message.getSatellites());
		LocationDto location = coordinatesService.calculateCoordinates(message.getSatellites());
		TopSecretResponse response = new TopSecretResponse(fullMessage, location);

		return response;
		// throw new MessageNotFoundException("LA CONCHA BIEN DE TU MADRE");
	}

//	@ExceptionHandler(value = MessageNotFoundException.class)
//	@ResponseStatus(code = HttpStatus.NOT_FOUND)
//	public String handleMessageNotFoundException(MessageNotFoundException ex) {
//		return ex.getMessage();
//	}

}
