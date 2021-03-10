package com.iup.tp.twitup.ihm.profile;

import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.IModule;
import com.iup.tp.twitup.ihm.profile.profileInfos.ProfileInfosComponent;
import com.iup.tp.twitup.ihm.search.searchbar.SearchBarModule;
import com.iup.tp.twitup.ihm.twittimeline.TwitTimelineComponent;
import com.iup.tp.twitup.ihm.twittimeline.TwitTimelineModel;

public class ProfileModule implements IModule
{
  protected final User user;
  protected final IDatabase database;

  protected final ProfileComponent profileComponent;
  protected final ProfileController profileController;
  protected final TwitTimelineModel twitTimelineModel;
  protected final TwitTimelineComponent twitTimelineComponent;
  protected final ProfileInfosComponent profileInfosComponent;
  protected final SearchBarModule searchBarModule;

  public ProfileModule(User user, IDatabase database)
  {
    this.user = user;
    this.database = database;

    this.twitTimelineModel = new TwitTimelineModel();
    this.twitTimelineComponent = new TwitTimelineComponent(this.twitTimelineModel);
    this.twitTimelineModel.addObserver(this.twitTimelineComponent);

    this.profileController = new ProfileController(user, twitTimelineModel);
    this.database.addObserver(this.profileController);

    this.profileInfosComponent = new ProfileInfosComponent(this.user);
    this.profileComponent = new ProfileComponent(this.profileInfosComponent, this.twitTimelineComponent);
  }

  @Override
  public JPanel getView()
  {
    return this.profileComponent;
  }

  @Override
  public void dispose()
  {
    this.twitTimelineModel.deleteObserver(this.twitTimelineComponent);
    this.database.deleteObserver(this.profileController);
  }

  public User getUser()
  {
    return this.user;
  }
}
