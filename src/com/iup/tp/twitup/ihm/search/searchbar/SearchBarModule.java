package com.iup.tp.twitup.ihm.search.searchbar;

import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.IModule;
import com.iup.tp.twitup.ihm.twittimeline.TwitTimelineModel;

public class SearchBarModule implements IModule
{
  protected final IDatabase database;
  protected final TwitTimelineModel twitTimelineModel;
  protected final SearchBarController searchBarController;
  protected final SearchBarComponent searchBarComponent;

  public SearchBarModule(IDatabase database, TwitTimelineModel twitTimelineModel)
  {
    this.database = database;
    this.twitTimelineModel = twitTimelineModel;

    this.searchBarComponent = new SearchBarComponent();
    this.searchBarController = new SearchBarController(database, twitTimelineModel, searchBarComponent);
    this.database.addObserver(this.searchBarController);
    this.searchBarComponent.addObserver(this.searchBarController);
  }

  @Override
  public JPanel getView()
  {
    return this.searchBarComponent;
  }

  @Override
  public void dispose()
  {
    this.database.deleteObserver(this.searchBarController);
    this.searchBarComponent.deleteObserver(this.searchBarController);
  }
}
