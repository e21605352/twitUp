package com.iup.tp.twitup.ihm.search.searchbar;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.twittimeline.TwitTimelineModel;

public class SearchBarController implements IDatabaseObserver, ISearchBarComponentObserver
{
  protected final IDatabase database;
  protected final TwitTimelineModel twitTimelineModel;
  protected final SearchBarComponent searchBarComponent;

  public SearchBarController(IDatabase database, TwitTimelineModel twitTimelineModel,
                             SearchBarComponent searchBarComponent)
  {
    this.database = database;
    this.twitTimelineModel = twitTimelineModel;
    this.searchBarComponent = searchBarComponent;
  }

  @Override
  public void notifyTwitAdded(Twit addedTwit)
  {
    // TODO
  }

  @Override
  public void notifyTwitDeleted(Twit deletedTwit)
  {
    // TODO
  }

  @Override
  public void notifyNewSearch(String search)
  {
    String text = ""
    if (this.isUserSearch())
    {
      this.database.getTwitsWithUserTag(search.substring(1));
    }
    else if (this.isTagSearch())
    {
      this.database.getTwitsWithTag(search.substring(1));
    }
  }

  protected boolean isUserSearch()
  {
    // TODO Auto-generated method stub
    return false;
  }

  protected boolean isTagSearch()
  {
    // TODO Auto-generated method stub
    return false;
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
