package com.iup.tp.twitup.ihm.search.searchbar;

import java.util.LinkedHashSet;
import java.util.Set;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.ihm.twittimeline.ITwitTimelineModelObserver;
import com.iup.tp.twitup.ihm.twittimeline.TwitTimelineModel;

public class SearchBarController implements ITwitTimelineModelObserver, ISearchBarComponentObserver
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

    this.initModel();
  }

  protected void initModel()
  {
    this.twitTimelineModel.addAllTwits(this.database.getTwits());
  }

  public void twitAdded(Twit addedTwit)
  {
    String search = this.searchBarComponent.getSearch();
    String text = this.getTextWithoutElementDelimiter(search);

    if (this.isUserSearch(search) && addedTwit.containsUserTag(text))
    {
      this.twitTimelineModel.addTwit(addedTwit);
    }
    else if (this.isTagSearch(search) && addedTwit.containsTag(text))
    {
      this.twitTimelineModel.addTwit(addedTwit);
    }
    else if (addedTwit.containsUserTag(text) || addedTwit.containsTag(text))
    {
      this.twitTimelineModel.addTwit(addedTwit);
    }
  }

  public void twitDeleted(Twit deletedTwit)
  {
    this.twitTimelineModel.removeTwit(deletedTwit);
  }

  @Override
  public void notifyNewSearch(String search)
  {
    this.twitTimelineModel.clear();

    if (search.isEmpty())
    {
      this.twitTimelineModel.addAllTwits(this.database.getTwits());
    }
    else
    {
      String text = this.getTextWithoutElementDelimiter(search);

      if (this.isUserSearch(search))
      {
        this.twitTimelineModel.addAllTwits(this.database.getTwitsWithUserTag(text));
      }
      else if (this.isTagSearch(search))
      {
        this.twitTimelineModel.addAllTwits(this.database.getTwitsWithTag(text));
      }
      else
      {
        Set<Twit> twits = new LinkedHashSet<>(this.database.getTwitsWithUserTag(search));
        twits.addAll(this.database.getTwitsWithTag(search));
        this.twitTimelineModel.addAllTwits(twits);
      }
    }
  }

  protected String getTextWithoutElementDelimiter(String search)
  {
    if (!search.isEmpty())
    {
      search = search.substring(1);
    }

    return search;
  }

  protected boolean isUserSearch(String search)
  {
    return search.startsWith(Constants.USER_TAG_DELIMITER);
  }

  protected boolean isTagSearch(String search)
  {
    return search.startsWith(Constants.WORD_TAG_DELIMITER);
  }

  @Override
  public void notifyTwitAdded(Twit addedTwit)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void notifyTwitsAdded(Set<Twit> twits)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void notifyTwitDeleted(Twit deletedTwit)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void notifyTwitsDeleted(Set<Twit> deletedTwits)
  {
    // TODO Auto-generated method stub

  }

  @Override
  public void notifyClear()
  {
    // TODO Auto-generated method stub

  }
}
