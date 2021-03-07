package com.iup.tp.twitup.ihm.dashboard;

import javax.swing.JPanel;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.twit.create.CreateTwitComponent;
import com.iup.tp.twitup.ihm.twittimeline.TwitTimelineComponent;

public class DashboardModule
{
  protected final DashboardComponent dashboardComponent;

  public DashboardModule(IDatabase database, EntityManager entityManager)
  {
    TwitTimelineComponent twitTimelineComponent = new TwitTimelineComponent(database);
    database.addObserver(twitTimelineComponent);

    this.dashboardComponent = new DashboardComponent(new CreateTwitComponent(null), twitTimelineComponent);
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public JPanel getDashboardComponent()
  {
    return this.dashboardComponent;
  }
}
