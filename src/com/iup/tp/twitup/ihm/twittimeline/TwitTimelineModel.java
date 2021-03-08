package com.iup.tp.twitup.ihm.twittimeline;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.iup.tp.twitup.datamodel.Twit;

public class TwitTimelineModel
{
  protected final List<Twit> twits;
  protected final Set<ITwitTimelineModelObserver> observers;

  public TwitTimelineModel()
  {
    this.twits = new ArrayList<>();
    this.observers = new HashSet<>();
  }

  protected void addTwit(Twit addedTwit)
  {
    this.twits.add(addedTwit);

    for (ITwitTimelineModelObserver observer : this.observers)
    {
      observer.notifyTwitAdded(addedTwit);
    }
  }

  protected void removeTwit(Twit removedTwit)
  {
    this.twits.remove(removedTwit);

    for (ITwitTimelineModelObserver observer : this.observers)
    {
      observer.notifyTwitAdded(removedTwit);
    }
  }

  // ================================================================================
  // Gestion observeurs
  // ================================================================================

  public void addObserver(ITwitTimelineModelObserver observer)
  {
    this.observers.add(observer);
  }

  public void deleteObserver(ITwitTimelineModelObserver observer)
  {
    this.observers.remove(observer);
  }

}
