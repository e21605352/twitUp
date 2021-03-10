package com.iup.tp.twitup.ihm.twittimeline;

import java.util.Set;

import com.iup.tp.twitup.datamodel.Twit;

public interface ITwitTimelineModelObserver
{
  public void notifyTwitAdded(Twit addedTwit);

  public void notifyTwitsAdded(Set<Twit> twits);

  public void notifyTwitDeleted(Twit deletedTwit);

  public void notifyTwitsDeleted(Set<Twit> deletedTwits);

  public void notifyClear();
}
