package com.iup.tp.twitup.datamodel;

import java.util.HashSet;
import java.util.Set;

import com.iup.tp.twitup.common.Constants;

/**
 * Classe représentant les donénes chargées dans l'application.
 * 
 * @author S.Lucas
 */
public class Database implements IDatabase {
	/**
	 * Liste des utilisateurs enregistrés.
	 */
	protected final Set<User> mUsers;

	/**
	 * Liste des Twit enregistrés.
	 */
	protected final Set<Twit> mTwits;

	/**
	 * Liste des observateurs de modifications de la base.
	 */
	protected final Set<IDatabaseObserver> mObservers;

	/**
	 * Constructeur.
	 */
	public Database() {
		mUsers = new HashSet<User>();
		mTwits = new HashSet<Twit>();
		mObservers = new HashSet<IDatabaseObserver>();
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public Set<User> getUsers() {
		// Clonage pour éviter les modifications extérieures.
		return new HashSet<User>(this.mUsers);
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public Set<Twit> getTwits() {
		// Clonage pour éviter les modifications extérieures.
		return new HashSet<Twit>(this.mTwits);
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public void addTwit(Twit twitToAdd) {
		// Ajout du twit
		this.mTwits.add(twitToAdd);

		// Notification des observateurs
		for (IDatabaseObserver observer : mObservers) {
			observer.notifyTwitAdded(twitToAdd);
		}
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public void removeTwit(Twit twitToRemove) {
		// Suppression du twit
		this.mTwits.remove(twitToRemove);

		// Notification des observateurs
		// Notification des observateurs
		for (IDatabaseObserver observer : mObservers) {
			observer.notifyTwitDeleted(twitToRemove);
		}
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public void modifiyTwit(Twit twitToModify) {
		// Le ré-ajout va écraser l'ancienne copie.
		this.mTwits.add(twitToModify);

		// Notification des observateurs
		for (IDatabaseObserver observer : mObservers) {
			observer.notifyTwitModified(twitToModify);
		}
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public void addUser(User userToAdd) {
		// Ajout de l'utilisateur
		this.mUsers.add(userToAdd);

		// Notification des observateurs
		for (IDatabaseObserver observer : mObservers) {
			observer.notifyUserAdded(userToAdd);
		}
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public void removeUser(User userToRemove) {
		// Suppression de l'utilisateur
		this.mUsers.remove(userToRemove);

		// Notification des observateurs
		for (IDatabaseObserver observer : mObservers) {
			observer.notifyUserDeleted(userToRemove);
		}
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public void modifiyUser(User userToModify) {
		// Le ré-ajout va écraser l'ancienne copie.
		this.mUsers.add(userToModify);

		// Notification des observateurs
		for (IDatabaseObserver observer : mObservers) {
			observer.notifyUserModified(userToModify);
		}
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public void clearTwits() {
		// Parcours de la liste clonnée des twits
		Set<Twit> clonedTwits = this.mTwits;
		for (Twit twit : clonedTwits) {
			// Suppression de chacun des twits
			this.removeTwit(twit);
		}
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public void clearUsers() {
		// Parcours de la liste clonnée des utilisateurs
		Set<User> clonedUsers = this.mUsers;
		for (User user : clonedUsers) {
			// Suppression de chacun des utlisateurs
			this.removeUser(user);
		}
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public void clear() {
		this.clearTwits();
		this.clearUsers();
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public Set<Twit> getTwitsWithTag(String tag) {
		Set<Twit> taggedTwits = new HashSet<Twit>();

		// Parcours de tous les twits de la base
		for (Twit twit : this.getTwits()) {
			// Si le twit contiens le tag demandé
			if (twit.containsTag(tag)) {
				taggedTwits.add(twit);
			}
		}

		return taggedTwits;
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public Set<Twit> getTwitsWithUserTag(String userTag) {
		Set<Twit> taggedTwits = new HashSet<Twit>();

		// Parcours de tous les twits de la base
		for (Twit twit : this.getTwits()) {
			// Si le twit contiens le tag utilisateur demandé
			if (twit.containsUserTag(userTag)) {
				taggedTwits.add(twit);
			}
		}

		return taggedTwits;
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public Set<Twit> getUserTwits(User user) {
		Set<Twit> userTwits = new HashSet<Twit>();

		// Parcours de tous les twits de la base
		for (Twit twit : this.getTwits()) {
			// Si le twiter est celui recherché
			if (twit.getTwiter().equals(user)) {
				userTwits.add(twit);
			}
		}

		return userTwits;
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public Set<User> getFollowers(User user) {
		Set<User> followers = new HashSet<User>();

		// Parcours de tous les utilisateurs de la base
		for (User otherUser : this.getUsers()) {
			// Si le l'utilisateur courant suit l'utilisateur donné
			if (otherUser.isFollowing(user)) {
				followers.add(otherUser);
			}
		}

		return followers;
	}

	public Set<User> getFollowed(User user) {
		Set<User> followers = new HashSet<User>();

		// Parcours de tous les utilisateurs de la base
		for (User otherUser : this.getUsers()) {
			// Si le l'utilisateur courant suit l'utilisateur donné
			if (user.getFollows().contains(otherUser.getUserTag())) {
				followers.add(otherUser);
			}
		}

		return followers;
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public int getFollowersCount(User user) {
		return this.getFollowers(user).size();
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public User getUnknowUser() {
		return new User(Constants.UNKNONWN_USER_UUID, "<Inconnu>", "--", "<Inconnu>", new HashSet<String>(), "");
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public void addObserver(IDatabaseObserver observer) {
		this.mObservers.add(observer);

		// Notification pour le nouvel observateur
		for (Twit twit : this.getTwits()) {
			observer.notifyTwitAdded(twit);
		}

		// Notification pour le nouvel observateur
		for (User user : this.getUsers()) {
			// Pas de notification pour l'utilisateur inconnu
			if (user.getUuid().equals(Constants.UNKNONWN_USER_UUID) == false) {
				observer.notifyUserAdded(user);
			}
		}
	}

	/**
	 * @{inheritDoc
	 */
	@Override
	public void deleteObserver(IDatabaseObserver observer) {
		this.mObservers.remove(observer);
	}

	/**
	 * Retourne une liste clonées des observateurs de modifications.
	 */
	protected Set<IDatabaseObserver> getObservers() {
		return new HashSet<IDatabaseObserver>(this.mObservers);
	}
}
