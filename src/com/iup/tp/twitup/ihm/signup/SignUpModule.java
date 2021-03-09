package com.iup.tp.twitup.ihm.signup;

import javax.swing.JPanel;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.ihm.IModule;
import com.iup.tp.twitup.ihm.components.filechooser.ImageFileChooserComponent;

public class SignUpModule implements IModule
{
  protected final SignUpController signUpController;
  protected final SignUpComponent signUpComponent;
  protected final ImageFileChooserComponent imageFileChooserComponent;

  public SignUpModule(IDatabase database, EntityManager entityManager)
  {
    this.imageFileChooserComponent = new ImageFileChooserComponent();
    this.signUpComponent = new SignUpComponent(this.imageFileChooserComponent);
    this.imageFileChooserComponent.addObserver(this.signUpComponent);

    this.signUpController = new SignUpController(database, entityManager);
    this.signUpComponent.addObserver(this.signUpController);
  }

  @Override
  public JPanel getView()
  {
    return this.signUpComponent;
  }

  @Override
  public void dispose()
  {
    this.signUpComponent.deleteObserver(this.signUpController);
    this.imageFileChooserComponent.deleteObserver(this.signUpComponent);
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

}
