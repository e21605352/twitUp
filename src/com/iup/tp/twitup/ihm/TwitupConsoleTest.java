package com.iup.tp.twitup.ihm;

import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public class TwitupConsoleTest implements IDatabaseObserver
{

  @Override
  public void notifyTwitAdded(Twit addedTwit)
  {
    System.out.println("Twit added");
  }

  @Override
  public void notifyTwitDeleted(Twit deletedTwit)
  {
    System.out.println("Twit deleted");
  }

  @Override
  public void notifyTwitModified(Twit modifiedTwit)
  {
    System.out.println("Twit modified");
  }

  @Override
  public void notifyUserAdded(User addedUser)
  {
    System.out.println("User added");
  }

  @Override
  public void notifyUserDeleted(User deletedUser)
  {
    System.out.println("User deleted");
  }

  @Override
  public void notifyUserModified(User modifiedUser)
  {
    System.out.println("User modified");
  }
}
