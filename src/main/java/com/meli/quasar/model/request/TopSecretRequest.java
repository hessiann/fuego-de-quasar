package com.meli.quasar.model.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopSecretRequest {

	private List<TopSecretSatelliteRequest> satellites;

}
