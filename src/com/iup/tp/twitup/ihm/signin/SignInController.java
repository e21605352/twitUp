package com.iup.tp.twitup.ihm.signin;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.iup.tp.twitup.datamodel.IDatabase;

public class SignInController implements ISignInController, Serializable
{
  private static final long serialVersionUID = 3174730389204825979L;

  protected IDatabase database;
  protected SignInComponent signInGraphic;

  /**
   * Liste des observateurs de la connexion d'un utilisateur.
   */
  protected final Set<ISignInControllerObserver> observers;

  public SignInController()
  {
    this.observers = new HashSet<>();
  }

  // TODO : Connexion
  public void connect()
  {
    // Connexion
    this.connectUser();
    System.out.println("CONNEXION");

    // Notification des observateurs
    for (ISignInControllerObserver observer : this.observers)
    {
      observer.notifyUserConnected(null);
    }
  }

  // TODO : Cancel
  public void cancel()
  {
    System.out.println("CANCEL");
  }

  protected void connectUser()
  {

  }

  protected boolean isFormValid()
  {
    String login = this.signInGraphic.getLogin();
    String password = this.signInGraphic.getPassword();

    return !login.isEmpty() && !password.isEmpty();
  }

  // ================================================================================
  // Gestion observeurs
  // ================================================================================

  @Override
  public void addObserver(ISignInControllerObserver observer)
  {
    this.observers.add(observer);
  }

  @Override
  public void deleteObserver(ISignInControllerObserver observer)
  {
    this.observers.remove(observer);
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public SignInComponent getSignInGraphic()
  {
    return signInGraphic;
  }

  public void setSignInView(SignInComponent signInGraphic)
  {
    this.signInGraphic = signInGraphic;
  }
}
