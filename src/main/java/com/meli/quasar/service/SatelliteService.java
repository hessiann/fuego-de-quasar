package com.meli.quasar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.meli.quasar.model.Location;
import com.meli.quasar.model.Satellite;

import lombok.Data;

@Data
@Service
public class SatelliteService implements InitializingBean {

	private List<Satellite> satellites;

	@Override
	public void afterPropertiesSet() throws Exception {

		satellites = new ArrayList<Satellite>();

//		Location position1 = new Location(-500d, -200d);
//		Location position2 = new Location(100d, -100d);
//		Location position3 = new Location(500d, 100d);

		Location position1 = new Location(5d, 4d);
		Location position2 = new Location(4d, -3d);
		Location position3 = new Location(-4d, 13d);

		Satellite kenobi = new Satellite(position1, "kenobi");
		Satellite skywalker = new Satellite(position2, "skywalker");
		Satellite sato = new Satellite(position3, "sato");

		satellites.add(kenobi);
		satellites.add(skywalker);
		satellites.add(sato);
	}

}
