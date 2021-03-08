package com.iup.tp.twitup.ihm.navigation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import com.iup.tp.twitup.ihm.components.iconbutton.IconButton;

public class NavigationComponent extends JPanel
{
  private static final long serialVersionUID = -6310964056350071397L;

  protected JPanel contentPane;
  protected final Set<INavigationObserver> observers;

  public NavigationComponent()
  {
    this.observers = new HashSet<>();
    this.initComponent();
  }

  protected void initComponent()
  {
    this.setLayout(new GridBagLayout());

    this.contentPane = new JPanel(new GridBagLayout());
    this.contentPane.setOpaque(false);
    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.VERTICAL, new Insets(10, 10, 10, 10), 0, 0));

    this.placeComponents();
  }

  protected void placeComponents()
  {
    IconButton homeButton = new IconButton("home_icon.png", "home_icon_hover.png", "Accueil");
    homeButton.addIIconButtonListener(() -> this.doHome());
    this.contentPane.add(homeButton, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));

    IconButton searchButton = new IconButton("search_icon.png", "search_icon_hover.png", "Recherche");
    searchButton.addIIconButtonListener(() -> this.doSearch());
    this.contentPane.add(searchButton, new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));

    IconButton followButton = new IconButton("follow_icon.png", "follow_icon_hover.png", "Follows");
    followButton.addIIconButtonListener(() -> this.doFollow());
    this.contentPane.add(followButton, new GridBagConstraints(0, 2, 1, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));

    IconButton notificationsButton = new IconButton("alert_icon.png", "alert_icon_hover.png", "Notifications");
    notificationsButton.addIIconButtonListener(() -> this.doNotifications());
    this.contentPane.add(notificationsButton, new GridBagConstraints(0, 3, 1, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));

    IconButton profileButton = new IconButton("profile_icon.png", "profile_icon_hover.png", "Profil");
    profileButton.addIIconButtonListener(() -> this.doProfile());
    this.contentPane.add(profileButton, new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
  }

  protected void doHome()
  {
    for (INavigationObserver observer : this.observers)
    {
      observer.notifyHome();
    }
  }

  protected void doSearch()
  {
    for (INavigationObserver observer : this.observers)
    {
      observer.notifySearch();
    }
  }

  protected void doFollow()
  {
    for (INavigationObserver observer : this.observers)
    {
      observer.notifyFollow();
    }
  }

  protected void doNotifications()
  {
    for (INavigationObserver observer : this.observers)
    {
      observer.notifyNotifications();
    }
  }

  protected void doProfile()
  {
    for (INavigationObserver observer : this.observers)
    {
      observer.notifyProfile();
    }
  }

  // ================================================================================
  // Gestion observeurs
  // ================================================================================

  public void addObserver(INavigationObserver observer)
  {
    this.observers.add(observer);
  }

  public void deleteObserver(INavigationObserver observer)
  {
    this.observers.remove(observer);
  }
}
