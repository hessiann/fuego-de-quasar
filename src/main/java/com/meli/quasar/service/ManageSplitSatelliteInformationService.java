package com.meli.quasar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.quasar.model.Satellite;
import com.meli.quasar.model.request.TopSecretSatelliteRequest;

@Service
public class ManageSplitSatelliteInformationService {

	@Autowired
	private SatelliteService ss;

	private List<TopSecretSatelliteRequest> listRequest = new ArrayList<TopSecretSatelliteRequest>();

	public boolean saveSplitSatelliteInformation(TopSecretSatelliteRequest splitSatellite) {

		List<Satellite> satellitesInfo = ss.getSatellites();

		if (satellitesInfo.stream().filter(sat -> sat.getName().equalsIgnoreCase(splitSatellite.getName()))
				.collect(Collectors.toList()).isEmpty()) {
			return false;
		}

		if (listRequest.isEmpty()) {
			listRequest.add(splitSatellite);
			return false;
		}

		boolean added = false;

		for (int i = 0; i < listRequest.size(); i++) {
			if (listRequest.get(i).getName().equalsIgnoreCase(splitSatellite.getName())) {
				listRequest.remove(i);
				listRequest.add(splitSatellite);
				added = true;
			}
		}

		if (!added) {
			listRequest.add(splitSatellite);
		}

		return listRequest.size() == 3 ? true : false;
	}

	public List<TopSecretSatelliteRequest> getListRequest() {
		return listRequest;
	}

	public void removeSatelliteInformation() {
		listRequest.removeAll(listRequest);
	}
}
