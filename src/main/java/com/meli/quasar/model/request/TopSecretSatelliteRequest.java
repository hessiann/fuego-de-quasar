package com.meli.quasar.model.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopSecretSatelliteRequest {

	private String name;
	private Double distance;
	private List<String> message;

}
