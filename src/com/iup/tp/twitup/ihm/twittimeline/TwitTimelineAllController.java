package com.iup.tp.twitup.ihm.twittimeline;

import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;

public class TwitTimelineAllController extends TwitTimelineController implements IDatabaseObserver
{

  @Override
  public void notifyTwitAdded(Twit addedTwit)
  {
    this.twitTimelineModel.addTwit(addedTwit);
  }

  @Override
  public void notifyTwitDeleted(Twit deletedTwit)
  {
    this.twitTimelineModel.removeTwit(deletedTwit);
  }
}
