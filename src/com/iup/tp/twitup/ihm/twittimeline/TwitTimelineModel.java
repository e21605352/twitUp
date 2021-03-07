package com.iup.tp.twitup.ihm.twittimeline;

import java.util.HashSet;
import java.util.Set;

import com.iup.tp.twitup.ihm.twit.read.ReadTwitComponent;

public class TwitTimelineModel
{

  protected Set<ReadTwitComponent> twitComponents;

  protected Set<ITwitTimelineModelObserver>
  
  public TwitTimelineModel()
  {
    this.twitComponents = new HashSet<>();
  }

}
