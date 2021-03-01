package com.iup.tp.twitup.datamodel;

import java.util.Set;

/**
 * Interface de la base de données de l'application.
 * 
 * @author S.Lucas
 */
public interface IDatabase {

	/**
	 * Ajoute un observateur sur les modifications de la base de données.
	 * 
	 * @param observer
	 */
	void addObserver(IDatabaseObserver observer);

	/**
	 * Supprime un observateur sur les modifications de la base de données.
	 * 
	 * @param observer
	 */
	void deleteObserver(IDatabaseObserver observer);

	/**
	 * Retourne la liste des utilisateurs
	 */
	Set<User> getUsers();

	/**
	 * Retourne la liste des twits
	 */
	Set<Twit> getTwits();

	/**
	 * Ajoute un twit à la base de données.
	 * 
	 * @param twitToAdd
	 */
	void addTwit(Twit twitToAdd);

	/**
	 * Supprime un twit de la base de données.
	 * 
	 * @param twitToRemove
	 */
	void removeTwit(Twit twitToRemove);

	/**
	 * Modification d'un twit de la base de données. <br/>
	 * <i>Normalement peu probable qu'un twit soit modifié...</i>
	 * 
	 * @param twitToModify
	 */
	void modifiyTwit(Twit twitToModify);

	/**
	 * Ajoute un utilisateur à la base de données.
	 * 
	 * @param userToAdd
	 */
	void addUser(User userToAdd);

	/**
	 * Supprime un utilisateur de la base de données.
	 * 
	 * @param userToRemove
	 */
	void removeUser(User userToRemove);

	/**
	 * Modification d'un utilisateur de la base de données.
	 * 
	 * @param userToModify
	 */
	void modifiyUser(User userToModify);

	/**
	 * Supprime l'intégralité des twits enregistrés.
	 */
	void clearTwits();

	/**
	 * Supprime l'intégralité des utilisateurs enregistrés.
	 */
	void clearUsers();

	/**
	 * Supprime l'intégralité des données.
	 */
	void clear();

	/**
	 * Retourne tous les Twits présents en base ayant le tag donné
	 * 
	 * @param tag
	 *            , tag à rechercher.
	 */
	Set<Twit> getTwitsWithTag(String tag);

	/**
	 * Retourne tous les Twits présents en base ayant le tag utilisateur donné
	 * 
	 * @param userTag
	 *            , tag utilisateur à rechercher.
	 */
	Set<Twit> getTwitsWithUserTag(String userTag);

	/**
	 * Retourne tous les Twits d'un utilisateur.
	 * 
	 * @param user
	 *            , utilisateur dont les twits sont à rechercher.
	 */
	Set<Twit> getUserTwits(User user);

	/**
	 * Retourne tous les utilisateurs suivant l'utilisateur donnée
	 * 
	 * @param user
	 *            , utilisateur dont les followers sont à rechercher.
	 */
	Set<User> getFollowers(User user);

	/**
	 * Retourne le nombre de followers pour l'utilisateur donné.
	 * 
	 * @param user
	 *            , utilisateur dont le nombre de followers est à rechercher.
	 */
	int getFollowersCount(User user);

	/**
	 * Retourne l'utilisateur inconnu du système.
	 */
	public User getUnknowUser();

}
