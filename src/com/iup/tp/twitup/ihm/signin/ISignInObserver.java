package com.iup.tp.twitup.ihm.signin;

import com.iup.tp.twitup.datamodel.User;

public interface ISignInObserver
{
  public void notifyUserConnected(User user);

  public void notifyCancel();
}
