package com.iup.tp.twitup.ihm.signup;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;

public class SignUpController implements ISignUpController, Serializable
{
  private static final long serialVersionUID = -8453495085017955791L;

  protected final IDatabase database;
  protected final EntityManager entityManager;
  protected SignUpComponent signUpComponent;

  protected final Set<ISignUpObserver> observers;

  public SignUpController(IDatabase database, EntityManager entityManager)
  {
    this.database = database;
    this.entityManager = entityManager;
    this.observers = new HashSet<>();
  }

  public void createUser()
  {
    User userCreated = this.generateUser();

    if (this.userAlreadyExist(userCreated))
    {
      // TODO : Graphique notification ou truc direct
      System.out.println("USER ALREADY EXIST");
    }
    else
    {
      this.entityManager.sendUser(userCreated);

      // Notification aux observeurs de la création
      for (ISignUpObserver observer : this.observers)
      {
        observer.userCreated(userCreated);
      }
    }
  }

  protected User generateUser()
  {
    String userTag = this.signUpComponent.getLogin();
    String userPassword = this.signUpComponent.getPassword();
    String userName = this.signUpComponent.getLastname();

    return new User(UUID.randomUUID(), userTag, userPassword, userName, new HashSet<>(), ""); // FIXME
  }

  protected boolean userAlreadyExist(User user)
  {
    Set<User> users = this.database.getUsers();
    return users.contains(user);
  }

  public void cancelCreation()
  {
    // Notification aux observeurs de l'annulation
    for (ISignUpObserver observer : this.observers)
    {
      observer.creationCancelled();
    }
  }

  // ================================================================================
  // Gestion observeurs
  // ================================================================================

  @Override
  public void addObserver(ISignUpObserver observer)
  {
    this.observers.add(observer);
  }

  @Override
  public void deleteObserver(ISignUpObserver observer)
  {
    this.observers.remove(observer);
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public SignUpComponent getSignUpComponent()
  {
    return signUpComponent;
  }

  public void setSignUpComponent(SignUpComponent signUpComponent)
  {
    this.signUpComponent = signUpComponent;
  }
}
