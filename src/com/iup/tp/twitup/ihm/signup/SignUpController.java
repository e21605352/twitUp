package com.iup.tp.twitup.ihm.signup;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;

public class SignUpController implements ISignUpComponentObserver
{
  protected final IDatabase database;
  protected final EntityManager entityManager;

  protected final Set<ISignUpObserver> observers;

  public SignUpController(IDatabase database, EntityManager entityManager)
  {
    this.database = database;
    this.entityManager = entityManager;
    this.observers = new HashSet<>();
  }

  @Override
  public void notifySignup(String login, String username, String password, String avatarPath)
  {
    if (this.isFormValid(login, username, password, avatarPath) && !this.userAlreadyExist(login))
    {
      User createdUser = new User(UUID.randomUUID(), login, username, password, new HashSet<>(), avatarPath);
      this.entityManager.sendUser(createdUser);

      for (ISignUpObserver observer : this.observers)
      {
        observer.notifyUserCreated(createdUser);
      }
    }
    else
    {// TODO
    }
  }

  @Override
  public void notifySignin()
  {
    for (ISignUpObserver observer : this.observers)
    {
      observer.notifySignIn();
    }
  }

  protected boolean isFormValid(String login, String username, String password, String avatarPath)
  {
    return !login.isEmpty() && !username.isEmpty() && !password.isEmpty() && !avatarPath.isEmpty();
  }

  protected boolean userAlreadyExist(String login)
  {
    boolean exist = false;

    for (User user : this.database.getUsers())
    {
      if (user.getUserTag().equals(login))
      {
        exist = true;
        break;
      }
    }

    return exist;
  }

  // ================================================================================
  // Gestion observeurs
  // ================================================================================

  public void addObserver(ISignUpObserver observer)
  {
    this.observers.add(observer);
  }

  public void deleteObserver(ISignUpObserver observer)
  {
    this.observers.remove(observer);
  }
}
