package com.iup.tp.twitup.ihm.dashboard;

import javax.swing.JPanel;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.IModule;
import com.iup.tp.twitup.ihm.twit.create.CreateTwitModule;
import com.iup.tp.twitup.ihm.twittimeline.TwitTimelineAllController;
import com.iup.tp.twitup.ihm.twittimeline.TwitTimelineModule;

public class DashboardModule implements IModule
{
  protected final DashboardComponent dashboardComponent;
  protected final CreateTwitModule createTwitModule;
  protected final TwitTimelineModule twitTimelineModule;

  public DashboardModule(IDatabase database, EntityManager entityManager, User session)
  {
    this.createTwitModule = new CreateTwitModule(entityManager, session);
    this.twitTimelineModule = new TwitTimelineModule(database);
    this.twitTimelineModule.initModule(new TwitTimelineAllController());

    this.dashboardComponent = new DashboardComponent(this.createTwitModule, this.twitTimelineModule);
  }

  @Override
  public JPanel getView()
  {
    return this.dashboardComponent;
  }

  @Override
  public void dispose()
  {
    this.createTwitModule.dispose();
    this.twitTimelineModule.dispose();
  }
}
