package com.freespeedreader.client.mvp;

import com.freespeedreader.client.places.SpeedReadPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;


@WithTokenizers({ SpeedReadPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {

}
