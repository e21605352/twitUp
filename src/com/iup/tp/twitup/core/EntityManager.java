package com.iup.tp.twitup.core;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.datamodel.converter.XmlbeanDatamodelConverter;
import com.iup.tp.twitup.datamodel.jaxb.JaxbReader;
import com.iup.tp.twitup.datamodel.jaxb.JaxbWriter;
import com.iup.tp.twitup.datamodel.jaxb.bean.twit.TwitXml;
import com.iup.tp.twitup.datamodel.jaxb.bean.user.UserXml;
import com.iup.tp.twitup.events.file.IWatchableDirectoryObserver;

/**
 * Classe de gestion de la mise à jour de la base de données et de génération
 * des fichiers
 * 
 * @author S.Lucas
 */
public class EntityManager implements IWatchableDirectoryObserver {

	/**
	 * Base de donnée de l'application.
	 */
	protected final IDatabase mDatabase;

	/**
	 * Chemin d'accès au répertoire d'échange.
	 */
	protected String mDirectoryPath;

	/**
	 * Map reliant les UUID aux utilisateurs associés.
	 */
	protected final Map<UUID, User> mUserMap;

	/**
	 * Map reliant les noms de fichiers aux twits associés.
	 */
	protected final Map<String, Twit> mTwitFileMap;

	/**
	 * Map reliant les noms de fichiers aux utilisateurs associés.
	 */
	protected final Map<String, User> mUserFileMap;

	/**
	 * Constructeur.
	 */
	public EntityManager(IDatabase database) {
		this.mDatabase = database;
		this.mUserMap = new HashMap<UUID, User>();
		this.mTwitFileMap = new HashMap<String, Twit>();
		this.mUserFileMap = new HashMap<String, User>();

		// Ajout de l'utilisateur inconnu
		User unknowUser = database.getUnknowUser();
		this.mUserMap.put(unknowUser.getUuid(), unknowUser);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notifyPresentFiles(Set<File> presentFiles) {
		// L'initialisation est une phase d'ajout massive
		this.notifyNewFiles(presentFiles);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notifyNewFiles(Set<File> newFiles) {
		//
		// Récupération des fichiers utilisateurs en premier
		// (nécessaires pour gérer les twits)
		Set<File> userFiles = this.getUserFiles(newFiles);

		// Parcours de la liste des fichiers utilisateurs
		for (File userFile : userFiles) {

			// Extraction du nouvel utilisateur
			User newUser = this.extractUser(userFile);

			if (newUser != null) {
				// Ajout de l'utilisateur
				this.mDatabase.addUser(newUser);

				// Stockage dans les maps
				mUserMap.put(newUser.getUuid(), newUser);
				mUserFileMap.put(userFile.getName(), newUser);
			}
		}

		//
		// Récupération des fichiers de Twits.
		Set<File> twitFiles = this.getTwitFiles(newFiles);

		// Parcours de la liste des nouveaux twits
		for (File twitFile : twitFiles) {

			// Extraction du nouveau twit
			Twit newTwit = this.extractTwit(twitFile);

			if (newTwit != null) {
				// Ajout du twit
				this.mDatabase.addTwit(newTwit);

				// MAJ de la map
				this.mTwitFileMap.put(twitFile.getName(), newTwit);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notifyDeletedFiles(Set<File> deletedFiles) {
		//
		// Récupération des fichiers d'utilisateurs
		Set<File> userFiles = this.getUserFiles(deletedFiles);

		// Parcours de la liste des fichiers utilisateurs supprimés
		for (File deletedUserFile : userFiles) {

			// Récupération de l'utilisateur correspondant
			User deletedUser = this.mUserFileMap.get(deletedUserFile.getName());

			if (deletedUser != null) {
				// Suppression de l'utilisateur
				this.mDatabase.removeUser(deletedUser);

				// MAJ des maps
				mUserMap.remove(deletedUser.getUuid());
				mUserFileMap.remove(deletedUserFile.getName());
			}
		}

		//
		// Récupération des fichiers twit supprimés
		Set<File> deletedTwitFiles = this.getTwitFiles(deletedFiles);

		// Parcours de la liste des fichiers twit supprimés
		for (File deletedTwitFile : deletedTwitFiles) {

			// Récupération du twit correspondant
			Twit deletedTwit = this.mTwitFileMap.get(deletedTwitFile.getName());

			if (deletedTwit != null) {
				// Suppression du twit
				this.mDatabase.removeTwit(deletedTwit);

				// MAJ de la map
				mTwitFileMap.remove(deletedTwitFile.getName());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void notifyModifiedFiles(Set<File> modifiedFiles) {
		//
		// Récupération des utilisateurs en premier (nécessaires pour gérer
		// les
		// twit)
		Set<File> userFiles = this.getUserFiles(modifiedFiles);

		// Récupération et parcours de la liste des utilisateurs modifiés
		for (User modifiedUser : this.extractAllUsers(userFiles)) {
			// Modification de l'utilisateur
			this.mDatabase.modifiyUser(modifiedUser);

			// Stockage dans la map
			mUserMap.put(modifiedUser.getUuid(), modifiedUser);
		}

		//
		// Récupération des Twit.
		Set<File> twitFiles = this.getTwitFiles(modifiedFiles);

		// Récupération et parcours de la liste des twits modifiés
		for (Twit modifiedTwit : this.extractAllTwits(twitFiles)) {
			// Ajout du twit
			this.mDatabase.modifiyTwit(modifiedTwit);
		}
	}

	/**
	 * Extraction de tous les twit d'une liste de fichier.
	 * 
	 * @param allTwitFiles
	 *            , Liste des fichiers de twit.
	 */
	protected Set<Twit> extractAllTwits(Set<File> allTwitFiles) {
		Set<Twit> allTwits = new HashSet<Twit>();

		// Parcours de tous les fichiers de twit
		for (File twitFile : allTwitFiles) {
			// Extraction du twit pour le fichier courant
			Twit twit = this.extractTwit(twitFile);

			// Si le twit a bien été récupéré
			if (twit != null) {
				// Sauvegarde de l'objet
				allTwits.add(twit);
			}
		}

		return allTwits;
	}

	/**
	 * Extraction du fichier pour récupérer le Twit correspondant. <br/>
	 * <i>Retourne <code>null</code> si un problème est rencontré</i>.
	 * 
	 * @param twitFile
	 *            , Fichier XML du twit à ectraire.
	 */
	protected Twit extractTwit(File twitFile) {
		Twit newTwit = null;

		// Si le fichier est valide
		if (twitFile != null && twitFile.exists() && twitFile.getName().endsWith(Constants.TWIT_FILE_EXTENSION)) {
			// Lecture du fichier pour récupérer le bean xml
			TwitXml xmlTwit = JaxbReader.readTwit(twitFile.getAbsolutePath());

			// Si le fichier a bien été lu
			if (xmlTwit != null) {
				// Conversion vers l'objet du modèle
				newTwit = XmlbeanDatamodelConverter.convertAsModelTwit(xmlTwit, mUserMap);
			}
		}

		return newTwit;
	}

	/**
	 * Extraction de tous les utilisateur d'une liste de fichier.
	 * 
	 * @param allUserFiles
	 *            , Liste des fichiers d'utilisateur.
	 */
	protected Set<User> extractAllUsers(Set<File> allUserFiles) {
		Set<User> allUsers = new HashSet<User>();

		// Parcours de tous les fichiers de l'utilsiateur
		for (File userFile : allUserFiles) {
			// Extraction de l'utilisateur pour le fichier courant
			User user = this.extractUser(userFile);

			// Si l'utilisateur a bien été récupéré
			if (user != null) {
				// Sauvegarde de l'objet
				allUsers.add(user);
			}
		}

		return allUsers;
	}

	/**
	 * Extraction du fichier pour récupérer l'utilisateur correspondant. <br/>
	 * <i>Retourne <code>null</code> si un problème est rencontré</i>.
	 * 
	 * @param userFile
	 *            , Fichier XML de l'utilisateur à ectraire.
	 */
	protected User extractUser(File userFile) {
		User newUser = null;

		// Si le fichier est valide
		if (userFile != null && userFile.exists() && userFile.getName().endsWith(Constants.USER_FILE_EXTENSION)) {
			// Lecture du fichier pour récupérer le bean xml
			UserXml xmlUser = JaxbReader.readUser(userFile.getAbsolutePath());

			// Si le fichier a bien été lu
			if (xmlUser != null) {
				// Conversion vers l'objet du modèle
				newUser = XmlbeanDatamodelConverter.convertAsModelUser(xmlUser);
			}
		}

		return newUser;
	}

	/**
	 * Retourne la liste des fichiers de type 'Utilisateur' parmis la liste des
	 * fichiers donnés.
	 * 
	 * @param allFiles
	 *            , Liste complète des fichiers.
	 */
	protected Set<File> getUserFiles(Set<File> allFiles) {
		return this.getSpecificFiles(allFiles, Constants.USER_FILE_EXTENSION);
	}

	/**
	 * Retourne la liste des fichiers de type 'Twit' parmis la liste des
	 * fichiers donnés.
	 * 
	 * @param allFiles
	 *            , Liste complète des fichiers.
	 */
	protected Set<File> getTwitFiles(Set<File> allFiles) {
		return this.getSpecificFiles(allFiles, Constants.TWIT_FILE_EXTENSION);
	}

	/**
	 * Retourne la liste des fichiers ayant une extension particulière parmis la
	 * liste des fichiers donnés.
	 * 
	 * @param allFiles
	 *            , Liste complète des fichiers.
	 * @param extension
	 *            , Extension des fichiers à récupérer.
	 */
	protected Set<File> getSpecificFiles(Set<File> allFiles, String extension) {
		Set<File> specificFiles = new HashSet<File>();

		// Parcours de tous les fichiers donnés
		for (File file : allFiles) {
			// Si le fichier est un fichier ayant l'extension voulue
			if (file.getName().endsWith(extension)) {
				specificFiles.add(file);
			}
		}

		return specificFiles;
	}

	/**
	 * Configure le chemin d'accès au répertoire d'échange.
	 * 
	 * @param directoryPath
	 */
	public void setExchangeDirectory(String directoryPath) {
		this.mDirectoryPath = directoryPath;
	}

	/**
	 * Génération du fichier correspondant au twit.
	 * 
	 * @param twit
	 */
	public void sendTwit(Twit twit) {
		if (mDirectoryPath != null) {
			// Récupération du chemin pour le fichier à générer
			String twitFileName = this.getFileName(twit.getUuid(), Constants.TWIT_FILE_EXTENSION);

			// Conversion en structure XML
			TwitXml xmlTwit = XmlbeanDatamodelConverter.convertAsXmlTwit(twit);

			// Génération du fichier XML
			JaxbWriter.writeTwitFile(xmlTwit, twitFileName);
		} else {
			throw new RuntimeException("Le répertoire d'échange n'est pas configuré");
		}
	}

	/**
	 * Génération du fichier correspondant à l'utilisateur.
	 * 
	 * @param user
	 */
	public void sendUser(User user) {
		if (mDirectoryPath != null) {
			// Récupération du chemin pour le fichier à générer
			String userFileName = this.getFileName(user.getUuid(), Constants.USER_FILE_EXTENSION);

			// Conversion en structure XML
			UserXml xmlUser = XmlbeanDatamodelConverter.convertAsXmlUser(user);

			// Génération du fichier XML
			JaxbWriter.writeUserFile(xmlUser, userFileName);
		}
	}

	/**
	 * Retourne un chemin d'accès au fichier pour l'uuid et l'extension donnés.
	 * 
	 * @param objectUuid
	 * @param fileExtension
	 */
	protected String getFileName(UUID objectUuid, String fileExtension) {
		return mDirectoryPath + Constants.SYSTEM_FILE_SEPARATOR + objectUuid + "." + fileExtension;
	}
}
