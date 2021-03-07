package com.iup.tp.twitup.ihm.twit.create;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.Twit;

public class CreateTwitController implements Serializable
{
  private static final long serialVersionUID = -531304686449912295L;

  protected final EntityManager entityManager;
  protected CreateTwitComponent createTwitComponent;

  protected final Set<ICreateTwitObserver> observers;

  public CreateTwitController(EntityManager entityManager)
  {
    this.entityManager = entityManager;
    this.observers = new HashSet<>();
  }

  public void twit()
  {
    Twit twit = new Twit(null, "");
  }

  protected Twit generateTwit()
  {

    return null;
  }
}
