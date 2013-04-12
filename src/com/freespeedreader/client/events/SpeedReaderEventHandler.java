package com.freespeedreader.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface SpeedReaderEventHandler extends EventHandler {
  void onSpeedRead(SpeedReaderEvent event);
}
