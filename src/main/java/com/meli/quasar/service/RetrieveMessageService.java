package com.meli.quasar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.meli.quasar.exception.MessageNotFoundException;
import com.meli.quasar.model.request.TopSecretSatelliteRequest;

@Service
public class RetrieveMessageService {

	public String retrieveMessage(List<TopSecretSatelliteRequest> satellites) throws MessageNotFoundException {

		List<List<String>> messages = satellites.stream().map(TopSecretSatelliteRequest::getMessage)
				.collect(Collectors.toList());

		StringBuilder fullMessage = new StringBuilder();
		int messageLength = messages.get(0).size();

		if (!messages.stream().allMatch(message -> sameSize(message, messageLength))) {
			throw new MessageNotFoundException("404 RETRIEVING THE MESSAGE");
		}

		StringBuilder word = new StringBuilder("");

		for (int i = 0; i < messageLength; i++) {
			for (List<String> list : messages) {
				if (!"".equalsIgnoreCase(list.get(i).toString())) {
					if (word.length() == 0) {
						word.append(list.get(i).toString());
						fullMessage.append(word.toString()).append(" ");
					} else if (!word.toString().equalsIgnoreCase(list.get(i).toString())) {
						throw new MessageNotFoundException("404 RETRIEVING THE MESSAGE");
					}
				}
			}

			if (word.length() == 0) {
				throw new MessageNotFoundException("404 RETRIEVING THE MESSAGE");
			}

			word.delete(0, word.length());
		}

		return fullMessage.toString();
	}

	private boolean sameSize(List<String> message, int messageLength) {
		return message.size() == messageLength;
	}

//	@ExceptionHandler(value = MessageNotFoundException.class)
//	@ResponseStatus(code = HttpStatus.NOT_FOUND)
//	public String handleMessageNotFoundException(MessageNotFoundException ex) {
//		return ex.getMessage();
//	}

}
