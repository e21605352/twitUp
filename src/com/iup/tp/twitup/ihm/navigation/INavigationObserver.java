package com.iup.tp.twitup.ihm.navigation;

public interface INavigationObserver
{
  public void notifyHome();

  public void notifySearch();

  public void notifyFollow();

  public void notifyNotifications();

  public void notifyProfile();

  public void notifyLogOut();
}
