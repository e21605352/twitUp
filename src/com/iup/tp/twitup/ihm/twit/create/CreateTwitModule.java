package com.iup.tp.twitup.ihm.twit.create;

import javax.swing.JPanel;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.IModule;

public class CreateTwitModule implements IModule
{

  protected final CreateTwitComponent createTwitComponent;
  protected final CreateTwitController createTwitController;

  public CreateTwitModule(EntityManager entityManager, User session)
  {
    this.createTwitComponent = new CreateTwitComponent(session);
    this.createTwitController = new CreateTwitController(entityManager, session, this.createTwitComponent);
    this.createTwitComponent.addObserver(this.createTwitController);
  }

  @Override
  public JPanel getView()
  {
    return this.createTwitComponent;
  }

  @Override
  public void dispose()
  {
    this.createTwitComponent.deleteObserver(this.createTwitController);
  }
}
