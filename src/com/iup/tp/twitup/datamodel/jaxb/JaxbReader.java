package com.iup.tp.twitup.datamodel.jaxb;

import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.iup.tp.twitup.common.Constants;
import com.iup.tp.twitup.datamodel.jaxb.bean.twit.TwitXml;
import com.iup.tp.twitup.datamodel.jaxb.bean.user.UserXml;

/**
 * Classe de lecture des fichiers XML.
 * 
 * @author S.Lucas
 */
public class JaxbReader {

	protected static final String JAXB_BEAN_ROOT_PACKAGE = "com.iup.tp.twitup.datamodel.jaxb.bean";

	protected static final String JAXB_TWIT_BEAN_PACKAGE = JAXB_BEAN_ROOT_PACKAGE + "." + "twit";

	protected static final String JAXB_USER_BEAN_PACKAGE = JAXB_BEAN_ROOT_PACKAGE + "." + "user";

	/**
	 * Lecture du fichier XML pour un {@link TwitXml}
	 * 
	 * @param twitFileName
	 */
	public static TwitXml readTwit(String twitFileName) {
		TwitXml twit = null;

		if (twitFileName != null && twitFileName.endsWith(Constants.TWIT_FILE_EXTENSION)) {
			twit = (TwitXml) readFile(twitFileName, JAXB_TWIT_BEAN_PACKAGE);
		}

		return twit;
	}

	/**
	 * Lecture du fichier XML pour un {@link UserXml}
	 * 
	 * @param twitFileName
	 */
	public static UserXml readUser(String userFileName) {
		UserXml user = null;

		if (userFileName != null && userFileName.endsWith(Constants.USER_FILE_EXTENSION)) {
			user = (UserXml) readFile(userFileName, JAXB_USER_BEAN_PACKAGE);
		}

		return user;
	}

	/**
	 * Unmarshalling du fichier XML
	 * 
	 * @param xmlFileName
	 *            , Fichier XML à lire
	 * @param beanPackage
	 *            , Package contenant les bean JAXB.
	 */
	protected static Object readFile(String xmlFileName, String beanPackage) {
		Object object = null;
		try {
			JAXBContext context;
			context = JAXBContext.newInstance(beanPackage);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			object = unmarshaller.unmarshal(new FileReader(xmlFileName));
		} catch (Throwable t) {
			System.err.println("Erreur de chargement du fichier : '" + xmlFileName + "'");
			t.printStackTrace();
		}
		return object;
	}

}
