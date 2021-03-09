package com.iup.tp.twitup.ihm.twit.create;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public class CreateTwitController implements ICreateTwitComponentObserver
{
  protected final EntityManager entityManager;
  protected final User session;

  protected final CreateTwitComponent createTwitComponent;

  public CreateTwitController(EntityManager entityManager, User session, CreateTwitComponent createTwitComponent)
  {
    this.entityManager = entityManager;
    this.session = session;
    this.createTwitComponent = createTwitComponent;
  }

  @Override
  public void notifySendTwit(String twitText)
  {
    this.entityManager.sendTwit(new Twit(this.session, twitText));
    this.createTwitComponent.clearText();
  }
}
