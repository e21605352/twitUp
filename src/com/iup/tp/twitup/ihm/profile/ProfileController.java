package com.iup.tp.twitup.ihm.profile;

import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.search.searchbar.SearchBarController;
import com.iup.tp.twitup.ihm.twittimeline.TwitTimelineModel;

public class ProfileController implements IDatabaseObserver
{

  protected final User user;
  protected final TwitTimelineModel twitTimelineModel;
  protected final SearchBarController searchController;

  public ProfileController(User user, TwitTimelineModel twitTimelineModel, SearchBarController searchController)
  {
    this.user = user;
    this.twitTimelineModel = twitTimelineModel;
    this.searchController = searchController;
  }

  @Override
  public void notifyTwitAdded(Twit addedTwit)
  {
    if (addedTwit.getTwiter().equals(user))
    {
      this.twitTimelineModel.addTwit(addedTwit);
    }
  }

  @Override
  public void notifyTwitDeleted(Twit deletedTwit)
  {
    if (deletedTwit.getTwiter().equals(user))
    {
      this.twitTimelineModel.removeTwit(deletedTwit);
    }
  }

  @Override
  public void notifyTwitModified(Twit modifiedTwit)
  {
    // NOT IMPLEMENTED
  }

  @Override
  public void notifyUserAdded(User addedUser)
  {
    // DO NOTHING
  }

  @Override
  public void notifyUserDeleted(User deletedUser)
  {
    // DO NOTHING
  }

  @Override
  public void notifyUserModified(User modifiedUser)
  {
    // DO NOTHING
  }
}
