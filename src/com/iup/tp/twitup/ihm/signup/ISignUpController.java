package com.iup.tp.twitup.ihm.signup;

public interface ISignUpController
{

  /**
   * Ajoute un observateur sur la création d'un utilisateur.
   * 
   * @param observer
   */
  void addObserver(ISignUpObserver observer);

  /**
   * Supprime un observateur sur la création d'un utilisateur.
   * 
   * @param observer
   */
  void deleteObserver(ISignUpObserver observer);
}
