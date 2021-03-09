package com.iup.tp.twitup.ihm.twittimeline;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.iup.tp.twitup.datamodel.Twit;

public class TwitTimelineModel implements Serializable
{
  private static final long serialVersionUID = -8298922813249596752L;

  protected final List<Twit> twits;
  protected final Set<ITwitTimelineModelObserver> observers;

  public TwitTimelineModel()
  {
    this.twits = new ArrayList<>();
    this.observers = new HashSet<>();
  }

  public void addTwit(Twit addedTwit)
  {
    this.twits.add(addedTwit);
    this.sortTwitsByEmissionDateOrder();

    for (ITwitTimelineModelObserver observer : this.observers)
    {
      observer.notifyTwitAdded(addedTwit);
    }
  }

  public void removeTwit(Twit removedTwit)
  {
    this.twits.remove(removedTwit);

    for (ITwitTimelineModelObserver observer : this.observers)
    {
      observer.notifyTwitAdded(removedTwit);
    }
  }

  protected void sortTwitsByEmissionDateOrder()
  {
    this.twits.sort((Twit t1, Twit t2) -> Long.compare(t1.getEmissionDate(), t2.getEmissionDate()) * -1);
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

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public List<Twit> getTwits()
  {
    return new ArrayList<>(this.twits);
  }
}
