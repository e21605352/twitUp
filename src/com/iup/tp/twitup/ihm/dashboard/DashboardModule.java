package com.iup.tp.twitup.ihm.dashboard;

import javax.swing.JPanel;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.IModule;
import com.iup.tp.twitup.ihm.twit.create.CreateTwitComponent;
import com.iup.tp.twitup.ihm.twittimeline.TwitTimelineComponent;

public class DashboardModule implements IModule
{
  protected final IDatabase database;
  protected final DashboardComponent dashboardComponent;

  public DashboardModule(IDatabase database, EntityManager entityManager)
  {
    this.database = database;
    TwitTimelineComponent twitTimelineComponent = new TwitTimelineComponent(this.database);
    database.addObserver(twitTimelineComponent);

    this.dashboardComponent = new DashboardComponent(new CreateTwitComponent(null), twitTimelineComponent);
  }

  @Override
  public JPanel getView()
  {
    return this.dashboardComponent;
  }

  @Override
  public void dispose()
  {
    // TODO
  }
}
