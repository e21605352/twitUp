package com.iup.tp.twitup.ihm;

import java.awt.event.WindowEvent;
import java.io.Serializable;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.dashboard.DashboardModule;
import com.iup.tp.twitup.ihm.navigation.INavigationObserver;
import com.iup.tp.twitup.ihm.signin.ISignInObserver;
import com.iup.tp.twitup.ihm.signin.SignInModule;
import com.iup.tp.twitup.ihm.signup.ISignUpObserver;
import com.iup.tp.twitup.ihm.signup.SignUpModule;

public class TwitupController implements INavigationObserver, ISignUpObserver, ISignInObserver, Serializable
{
  private static final long serialVersionUID = 4092742481805901354L;

  protected final IDatabase database;
  protected final EntityManager entityManager;
  protected User session;

  protected TwitupMainView twitupMainView;

  protected SignInModule signInModule;
  protected SignUpModule signUpModule;
  protected DashboardModule dashboardModule;

  public TwitupController(IDatabase database, EntityManager entityManager, TwitupMainView twitupMainView)
  {
    this.database = database;
    this.entityManager = entityManager;
    this.twitupMainView = twitupMainView;
  }

  public void start()
  {
    this.showSignIn();
    this.twitupMainView.openWindow();
  }

  /**
   * Ferme l'application.
   */
  public void exitTwitup()
  {
    this.signInModule.dispose();
    this.twitupMainView.dispatchEvent(new WindowEvent(this.twitupMainView, WindowEvent.WINDOW_CLOSING));
  }

  // ================================================================================
  // Affichage des vues
  // ================================================================================

  public void showSignIn()
  {
    if (this.signInModule == null)
    {
      this.signInModule = new SignInModule(this.database);
      this.signInModule.addObserver(this);
    }

    this.twitupMainView.showView(this.signInModule, false);
  }

  public void showSignUp()
  {
    if (this.signUpModule == null)
      this.signUpModule = new SignUpModule(this.database, this.entityManager);
    this.signUpModule.addObserver(this);

    this.twitupMainView.showView(this.signUpModule, false);
  }

  public void showHome()
  {
    if (this.dashboardModule == null)
    {
      this.dashboardModule = new DashboardModule(this.database, this.entityManager, this.session);
    }
    this.twitupMainView.showView(this.dashboardModule, true);
  }

  public void showSearch()
  {

  }

  // ================================================================================
  // Méthodes INavigationObserver
  // ================================================================================

  @Override
  public void notifyHome()
  {
    this.showHome();
  }

  @Override
  public void notifySearch()
  {
    System.out.println("NAVIGATE SEARCH");
  }

  @Override
  public void notifyFollow()
  {
    System.out.println("NAVIGATE FOLLOW");
  }

  @Override
  public void notifyNotifications()
  {
    System.out.println("NAVIGATE NOTIFICATIONS");
  }

  @Override
  public void notifyProfile()
  {
    System.out.println("NAVIGATE PROFILE");
  }

  @Override
  public void notifyLogOut()
  {
    // TODO
  }

  // ================================================================================
  // Méthodes ISignInObserver
  // ================================================================================

  @Override
  public void notifyUserConnected(User user)
  {
    this.session = user;
    this.showHome();
  }

  @Override
  public void notifySignUp()
  {
    this.showSignUp();
  }

  // ================================================================================
  // Méthodes ISignUpObserver
  // ================================================================================

  @Override
  public void notifyUserCreated(User user)
  {
    this.session = user;
    this.showHome();
  }

  @Override
  public void notifySignIn()
  {
    this.showSignIn();
  }

}
