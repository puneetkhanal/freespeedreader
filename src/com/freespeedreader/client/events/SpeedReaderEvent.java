package com.freespeedreader.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class SpeedReaderEvent extends GwtEvent<SpeedReaderEventHandler> {
  public static Type<SpeedReaderEventHandler> TYPE = new Type<SpeedReaderEventHandler>();
  
  @Override
  protected void dispatch(SpeedReaderEventHandler handler) {
    handler.onSpeedRead(this);
  }

  @Override
  public Type<SpeedReaderEventHandler> getAssociatedType() {
    return TYPE;
  }
}
