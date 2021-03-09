package com.iup.tp.twitup.ihm.twittimeline;

import com.iup.tp.twitup.datamodel.Twit;

public interface ITwitTimelineModelObserver
{
  public void notifyTwitAdded(Twit addedTwit);

  public void notifyTwitDeleted(Twit deletedTwit);

  public void notifyTwitSorted();

  public void notifyTwitModified(Twit modifiedTwit);
}
