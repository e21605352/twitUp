package com.iup.tp.twitup.ihm.signin;

import com.iup.tp.twitup.datamodel.User;

/**
 * Interface des observateurs de la connexion à Twitup.
 * 
 * @author KAN
 *
 */
public interface ISignInControllerObserver
{

  /**
   * Notification lorsqu'un utilisateur se connecte
   */
  void notifyUserConnected(User connectedUser);
}
