package com.iup.tp.twitup.datamodel.converter;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.datamodel.jaxb.bean.twit.TwitXml;
import com.iup.tp.twitup.datamodel.jaxb.bean.user.UserXml;

/**
 * Classe de gestion des conversion des objets entre le datamodle et les bean
 * XML.
 * 
 * @author S.Lucas
 */
public class XmlbeanDatamodelConverter {

	/**
	 * Conversion du modèle de donnée vers le bean du fichier XML.
	 * 
	 * @param twitToConvert
	 */
	public static TwitXml convertAsXmlTwit(Twit twitToConvert) {
		TwitXml twitXml = new TwitXml();
		twitXml.setID(twitToConvert.getUuid().toString());
		twitXml.setTwiter(twitToConvert.getTwiter().getUuid().toString());
		twitXml.setEmissionDate(twitToConvert.getEmissionDate());
		twitXml.setText(twitToConvert.getText());

		return twitXml;
	}

	/**
	 * Conversion du fichier XML vers le modèle de donnée.<br/>
	 * <i>NB, La map doit au moins contenir l'utilisateur inconnu</i>
	 * 
	 * @param twitToConvert
	 *            , Twit à convertir.
	 * @param userMap
	 *            , Map contenant les utilisateurs enregistrés en fonction de
	 *            leur UUID.
	 */
	public static Twit convertAsModelTwit(TwitXml twitToConvert, Map<UUID, User> userMap) {
		UUID twitUuid = UUID.fromString(twitToConvert.getID());

		// Récupération de l'utilisateur source du twit
		User twitUser = userMap.get(UUID.fromString(twitToConvert.getTwiter()));
		if (twitUser == null) {
			twitUser = userMap.get(Constants.UNKNONWN_USER_UUID);
		}

		return new Twit(twitUuid, twitUser, twitToConvert.getEmissionDate(), twitToConvert.getText());
	}

	/**
	 * Conversion du modèle de donnée vers le bean du fichier XML.
	 * 
	 * @param userToConvert
	 */
	public static UserXml convertAsXmlUser(User userToConvert) {
		UserXml userXml = new UserXml();
		userXml.setID(userToConvert.getUuid().toString());
		userXml.setUserTag(userToConvert.getUserTag());
		userXml.setUserPassword(userToConvert.getUserPassword());
		userXml.setName(userToConvert.getName());
		userXml.setAvatarPath(userToConvert.getAvatarPath());

		for (String followedTag : userToConvert.getFollows()) {
			userXml.getFollows().add(followedTag);
		}

		return userXml;
	}

	/**
	 * Conversion du fichier XML vers le modèle de donnée.
	 * 
	 * @param userToConvert
	 */
	public static User convertAsModelUser(UserXml userToConvert) {
		UUID userUuid = UUID.fromString(userToConvert.getID());
		Set<String> follows = new HashSet<String>(userToConvert.getFollows());

		return new User(userUuid, userToConvert.getUserTag(), userToConvert.getUserPassword(), userToConvert.getName(),
				follows, userToConvert.getAvatarPath());
	}

}
