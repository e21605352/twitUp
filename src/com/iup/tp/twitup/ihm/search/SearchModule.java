package com.iup.tp.twitup.ihm.search;

import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.IModule;
import com.iup.tp.twitup.ihm.search.searchbar.SearchBarModule;
import com.iup.tp.twitup.ihm.twittimeline.TwitTimelineComponent;
import com.iup.tp.twitup.ihm.twittimeline.TwitTimelineModel;

public class SearchModule implements IModule
{
  protected final TwitTimelineModel twitTimelineModel;
  protected final TwitTimelineComponent twitTimelineComponent;
  protected final SearchComponent searchComponent;
  protected final SearchBarModule searchBarModule;

  public SearchModule(IDatabase database)
  {
    this.twitTimelineModel = new TwitTimelineModel();
    this.twitTimelineComponent = new TwitTimelineComponent(this.twitTimelineModel);
    this.twitTimelineModel.addObserver(this.twitTimelineComponent);

    this.searchBarModule = new SearchBarModule(database, this.twitTimelineModel);
    this.searchComponent = new SearchComponent(searchBarModule, twitTimelineComponent);

  }

  @Override
  public JPanel getView()
  {
    return this.searchComponent;
  }

  @Override
  public void dispose()
  {
    this.twitTimelineModel.deleteObserver(this.twitTimelineComponent);
    this.searchBarModule.dispose();
  }
}
