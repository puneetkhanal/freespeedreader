package com.freespeedreader.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SpeedReadPlace extends Place {
	public static class Tokenizer implements PlaceTokenizer<SpeedReadPlace> {
		@Override
		public SpeedReadPlace getPlace(String token) {
			return new SpeedReadPlace(token);
		}

		@Override
		public String getToken(SpeedReadPlace place) {
			return place.getHelloName();
		}

	}

	private String helloName;

	public SpeedReadPlace(String token) {
		this.helloName = token;
	}

	public String getHelloName() {
		return helloName;
	}

}
