package com.iup.tp.twitup.ihm.twittimeline;

import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.IModule;

public class TwitTimelineModule implements IModule
{
  protected final IDatabase database;
  protected final TwitTimelineModel twitTimelineModel;
  protected final TwitTimelineComponent twitTimelineComponent;
  protected TwitTimelineController twitTimelineController;

  public TwitTimelineModule(IDatabase database)
  {
    this.database = database;

    this.twitTimelineModel = new TwitTimelineModel();
    this.twitTimelineComponent = new TwitTimelineComponent(this.twitTimelineModel);
    this.twitTimelineModel.addObserver(this.twitTimelineComponent);
  }

  public void initModule(TwitTimelineController twitTimelineController)
  {
    this.twitTimelineController = twitTimelineController;
    this.twitTimelineController.setTwitTimelineModel(this.twitTimelineModel);
    this.database.addObserver(this.twitTimelineController);
  }

  @Override
  public JPanel getView()
  {
    return this.twitTimelineComponent;
  }

  @Override
  public void dispose()
  {
    this.database.deleteObserver(this.twitTimelineController);
  }

}
