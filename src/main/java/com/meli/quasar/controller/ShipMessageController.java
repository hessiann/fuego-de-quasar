package com.meli.quasar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.meli.quasar.exception.InformationNotSufficientException;
import com.meli.quasar.exception.SatellitesNotFoundException;
import com.meli.quasar.model.dto.LocationDto;
import com.meli.quasar.model.request.TopSecretRequest;
import com.meli.quasar.model.request.TopSecretSatelliteRequest;
import com.meli.quasar.model.request.TopSecretSplitRequest;
import com.meli.quasar.model.response.RemoveResponse;
import com.meli.quasar.model.response.TopSecretResponse;
import com.meli.quasar.model.response.TopSecretSplitResponse;
import com.meli.quasar.service.CalculateCoordinatesService;
import com.meli.quasar.service.ManageSplitSatelliteInformationService;
import com.meli.quasar.service.RetrieveMessageService;
import com.meli.quasar.utils.Constants;

@RestController
public class ShipMessageController {

	@Autowired
	private CalculateCoordinatesService coordinatesService;

	@Autowired
	private RetrieveMessageService messageService;

	@Autowired
	private ManageSplitSatelliteInformationService save;

	@PostMapping("/topsecret")
	public TopSecretResponse getMessageAndLocation(@RequestBody TopSecretRequest message) {

		if (message.getSatellites() == null || message.getSatellites().isEmpty()) {
			throw new SatellitesNotFoundException(Constants.NO_SATELLITES);
		}

		String fullMessage = messageService.retrieveMessage(message.getSatellites());
		LocationDto location = coordinatesService.calculateCoordinates(message.getSatellites());
		TopSecretResponse response = new TopSecretResponse(fullMessage, location);

		return response;
	}

	@PostMapping("/topsecret_split/{satellite_name}")
	public TopSecretSplitResponse postMessageAndLocation(@PathVariable String satellite_name,
			@RequestBody TopSecretSplitRequest message) {

		save.saveSplitSatelliteInformation(
				new TopSecretSatelliteRequest(satellite_name, message.getDistance(), message.getMessage()));

		return new TopSecretSplitResponse("Message received");
	}

	@GetMapping("/topsecret_split/{satellite_name}")
	public TopSecretResponse getMessageAndLocation(@PathVariable String satellite_name,
			@RequestBody TopSecretSplitRequest message) {

		if (save.saveSplitSatelliteInformation(
				new TopSecretSatelliteRequest(satellite_name, message.getDistance(), message.getMessage()))) {

			String fullMessage = messageService.retrieveMessage(save.getListRequest());
			LocationDto location = coordinatesService.calculateCoordinates(save.getListRequest());
			TopSecretResponse response = new TopSecretResponse(fullMessage, location);

			return response;

		} else {
			throw new InformationNotSufficientException(Constants.INFORMATION_NOT_SUFFICIENT);
		}
	}

	@GetMapping("/topsecret_split/removeAll")
	public RemoveResponse removeSatelliteInformation() {
		save.removeSatelliteInformation();
		return new RemoveResponse("List deleted");
	}
}
