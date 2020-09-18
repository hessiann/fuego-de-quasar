package com.meli.quasar.model.response;

import com.meli.quasar.model.dto.LocationDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopSecretResponse {

	private String message;
	private LocationDto position;
}
