package com.iup.tp.twitup.ihm.signup;

import com.iup.tp.twitup.datamodel.User;

public interface ISignUpObserver
{
  public void userCreated(User user);

  public void creationCancelled();
}
