package com.iup.tp.twitup.ihm.twittimeline;

import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public class TwitTimelineController implements IDatabaseObserver
{

  protected TwitTimelineModel twitTimelineModel;
  protected TwitTimelineComponent twitTimelineComponent;

  @Override
  public void notifyTwitAdded(Twit addedTwit)
  {
    this.twitTimelineModel.addTwit(addedTwit);
  }

  @Override
  public void notifyTwitDeleted(Twit deletedTwit)
  {
    this.twitTimelineModel.removeTwit(deletedTwit);
  }

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

}
