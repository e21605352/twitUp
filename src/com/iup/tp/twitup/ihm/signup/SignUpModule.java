package com.iup.tp.twitup.ihm.signup;

import javax.swing.JPanel;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;

public class SignUpModule
{
  protected final SignUpController signUpController;
  protected final SignUpComponent signUpComponent;

  public SignUpModule(IDatabase database, EntityManager entityManager)
  {
    this.signUpController = new SignUpController(database, entityManager);
    this.signUpComponent = new SignUpComponent(this.signUpController);
    this.signUpController.setSignUpComponent(this.signUpComponent);
  }

  // ================================================================================
  // Gestion observeurs
  // ================================================================================

  public void addObserver(ISignUpObserver observer)
  {
    this.signUpController.addObserver(observer);
  }

  public void deleteObserver(ISignUpObserver observer)
  {
    this.signUpController.deleteObserver(observer);
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public JPanel getComponent()
  {
    return signUpComponent;
  }
}
