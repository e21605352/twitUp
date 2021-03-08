package com.iup.tp.twitup.ihm.signin;

import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.IModule;

public class SignInModule implements IModule
{
  protected final SignInController signInController;
  protected final SignInComponent signInComponent;

  public SignInModule(IDatabase database)
  {
    this.signInComponent = new SignInComponent();
    this.signInController = new SignInController(database);
    this.signInComponent.addObserver(this.signInController);
  }

  @Override
  public JPanel getView()
  {
    return this.signInComponent;
  }

  @Override
  public void dispose()
  {
    this.signInComponent.deleteObserver(this.signInController);
  }

  // ================================================================================
  // Gestion observeurs
  // ================================================================================

  public void addObserver(ISignInObserver observer)
  {
    this.signInController.addObserver(observer);
  }

  public void deleteObserver(ISignInObserver observer)
  {
    this.signInController.deleteObserver(observer);
  }
}
