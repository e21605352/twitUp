package com.iup.tp.twitup.ihm.signin;

import java.util.HashSet;
import java.util.Set;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;

public class SignInController implements ISignInComponentObserver
{
  protected final IDatabase database;

  protected final Set<ISignInObserver> observers;

  public SignInController(IDatabase database)
  {
    this.database = database;
    this.observers = new HashSet<>();
  }

  @Override
  public void notifyConnect(String login, String password)
  {
    User connectedUser = null;

    if (this.isFormValid(login, password))
    {
      for (User user : this.database.getUsers())
      {
        if (this.isSameUser(login, password, user))
        {
          connectedUser = user;
          break;
        }
      }
    }

    if (connectedUser != null)
    {
      for (ISignInObserver observer : this.observers)
      {
        observer.notifyUserConnected(connectedUser);
      }
    }
    else
    {
      // TODO : Signaler utilisateur inconnu
    }
  }

  @Override
  public void notifyCancel()
  {
    for (ISignInObserver observer : this.observers)
    {
      observer.notifyCancel();
    }
  }

  protected boolean isSameUser(String userTag, String password, User user)
  {
    return user.getUserTag().equals(userTag) && user.getUserPassword().equals(password);
  }

  protected boolean isFormValid(String login, String password)
  {
    return !login.isEmpty() && !password.isEmpty();
  }

  // ================================================================================
  // Gestion observeurs
  // ================================================================================

  public void addObserver(ISignInObserver observer)
  {
    this.observers.add(observer);
  }

  public void deleteObserver(ISignInObserver observer)
  {
    this.observers.remove(observer);
  }
}
