package com.iup.tp.twitup.ihm.signin;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;

public class SignInModule implements ISignInControllerObserver
{
  IDatabase database;

  SignInController signInController;
  SignInComponent signInComponent;

  public SignInModule(IDatabase database)
  {
    this.database = database;

    this.signInController = new SignInController();
    this.signInComponent = new SignInComponent(this.signInController);
    this.signInController.setSignInView(this.signInComponent);
    this.signInController.addObserver(this);
  }

  @Override
  public void notifyUserConnected(User connectedUser)
  {
    System.out.println("NOTIFICATION FIN MODULE");
  }

  public void dispose()
  {
    this.signInController.deleteObserver(this);
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public SignInComponent getSignInComponent()
  {
    return this.signInComponent;
  }
}
