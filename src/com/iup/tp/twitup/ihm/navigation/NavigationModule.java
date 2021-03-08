package com.iup.tp.twitup.ihm.navigation;

import javax.swing.JPanel;

public class NavigationModule
{
  protected final NavigationComponent navigationComponent;

  public NavigationModule()
  {
    this.navigationComponent = new NavigationComponent();
  }

  // ================================================================================
  // Gestion observeurs
  // ================================================================================

  public void addObserver(INavigationObserver observer)
  {
    this.navigationComponent.addObserver(observer);
  }

  public void deleteObserver(INavigationObserver observer)
  {
    this.navigationComponent.deleteObserver(observer);
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public JPanel getComponent()
  {
    return this.navigationComponent;
  }
}
