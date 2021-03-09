package com.iup.tp.twitup.ihm.signup;

public interface ISignUpComponentObserver
{
  public void notifySignup(String login, String username, String password, String avatarPath);

  public void notifySignin();
}
