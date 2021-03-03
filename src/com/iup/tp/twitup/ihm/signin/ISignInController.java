package com.iup.tp.twitup.ihm.signin;

public interface ISignInController
{

  /**
   * Ajoute un observateur sur la connexion d'un utilisateur.
   * 
   * @param observer
   */
  void addObserver(ISignInControllerObserver observer);

  /**
   * Supprime un observateur sur la connexion d'un utilisateur.
   * 
   * @param observer
   */
  void deleteObserver(ISignInControllerObserver observer);
}
