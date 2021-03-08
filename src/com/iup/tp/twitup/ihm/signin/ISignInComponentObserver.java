package com.iup.tp.twitup.ihm.signin;

public interface ISignInComponentObserver
{
  public void notifyConnect(String login, String password);

  public void notifyCancel();
}
