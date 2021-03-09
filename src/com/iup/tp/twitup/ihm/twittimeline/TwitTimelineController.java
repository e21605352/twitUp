package com.iup.tp.twitup.ihm.twittimeline;

import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public abstract class TwitTimelineController implements IDatabaseObserver
{
  protected TwitTimelineModel twitTimelineModel;

  @Override
  public void notifyTwitModified(Twit modifiedTwit)
  {
    // NOT IMPLEMENTED
  }

  @Override
  public void notifyUserAdded(User addedUser)
  {
    // NOT IMPLEMENTED
  }

  @Override
  public void notifyUserDeleted(User deletedUser)
  {
    // NOT IMPLEMENTED
  }

  @Override
  public void notifyUserModified(User modifiedUser)
  {
    // NOT IMPLEMENTED
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public void setTwitTimelineModel(TwitTimelineModel twitTimelineModel)
  {
    this.twitTimelineModel = twitTimelineModel;
  }
}
