package com.meli.quasar.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.meli.quasar.exception.CoordinatesNotFoundException;
import com.meli.quasar.model.Satellite;
import com.meli.quasar.model.dto.LocationDto;
import com.meli.quasar.model.request.TopSecretSatelliteRequest;
import com.meli.quasar.utils.Constants;

@Service
public class CalculateCoordinatesService {

	@Autowired
	private SatelliteService ss;

	public LocationDto calculateCoordinates(List<TopSecretSatelliteRequest> satellites)
			throws CoordinatesNotFoundException {

		List<Satellite> satellitesInfo = ss.getSatellites();
		Map<String, Double> map;

		try {
			map = satellites.stream().collect(
					Collectors.toMap(TopSecretSatelliteRequest::getName, TopSecretSatelliteRequest::getDistance));
		} catch (Exception e) {
			throw new CoordinatesNotFoundException(Constants.COORDINATES_BROKEN);
		}

		if (map.size() != satellitesInfo.size()) {
			throw new CoordinatesNotFoundException(Constants.COORDINATES_BROKEN);
		}

		double[][] positions = new double[satellites.size()][2];
		double[] distances = new double[map.size()];
		int idx = 0;

		for (Satellite s : satellitesInfo) {
			if (map.get(s.getName()) != null) {
				positions[idx][0] = s.getLocation().getX();
				positions[idx][1] = s.getLocation().getY();
				distances[idx] = (double) map.get(s.getName());
				idx++;
			} else {
				throw new CoordinatesNotFoundException(Constants.COORDINATES_BROKEN);
			}
		}

		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
				new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
		Optimum optimum = solver.solve();

		Double x = optimum.getPoint().getEntry(0);
		Double y = optimum.getPoint().getEntry(1);

		LocationDto position = new LocationDto();
		position.setX(round(x, 2));
		position.setY(round(y, 2));

		return position;
	}

	private static double round(double value, int places) {
		if (places < 0)
			throw new CoordinatesNotFoundException(Constants.WRONG_PLACES);

		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}
