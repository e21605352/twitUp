package com.iup.tp.twitup.core;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.ihm.TwitupConsoleTest;
import com.iup.tp.twitup.ihm.TwitupController;
import com.iup.tp.twitup.ihm.TwitupMainView;
import com.iup.tp.twitup.ihm.TwitupMock;

/**
 * Classe principale l'application.
 * 
 * @author S.Lucas
 */
public class Twitup
{
  /**
   * Base de données.
   */
  protected IDatabase mDatabase;

  /**
   * Gestionnaire des entités contenu de la base de données.
   */
  protected EntityManager mEntityManager;

  /**
   * Contrôleur principal de l'application.
   */
  protected TwitupController mMainController;

  /**
   * Vue principale de l'application.
   */
  protected TwitupMainView mMainView;

  /**
   * Classe de surveillance de répertoire
   */
  protected IWatchableDirectory mWatchableDirectory;

  /**
   * Répertoire d'échange de l'application.
   */
  protected String mExchangeDirectoryPath;

  /**
   * Idnique si le mode bouchoné est activé.
   */
  protected boolean mIsMockEnabled = false;

  /**
   * Nom de la classe de l'UI.
   */
  protected String mUiClassName;

  /**
   * IHM Console Test.
   */
  protected TwitupConsoleTest mTwitupConsoleTest;

  /**
   * Constructeur.
   */
  public Twitup()
  {
    // Init du look and feel de l'application
    this.initLookAndFeel();

    // Initialisation de la base de données
    this.initDatabase();

    if (this.mIsMockEnabled)
    {
      // Initialisation du bouchon de travail
      this.initMock();
    }

    // Initialisation de l'IHM
    this.initGui();

    // Initialisation du répertoire d'échange
    this.initDirectory();

    // Initialisation IHM Console
    this.initConsoleTest();
  }

  /**
   * Initialisation du look and feel de l'application.
   */
  // TODO : TryCatch à faire
  protected void initLookAndFeel()
  {
    try
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Initialisation de l'interface graphique.
   */
  protected void initGui()
  {
    this.mMainController = new TwitupController(this.mDatabase, this.mEntityManager);
    this.mMainView = new TwitupMainView(mMainController);
    this.mMainController.setTwitupView(mMainView);
  }

  /**
   * Initialisation du répertoire d'échange (depuis la conf ou depuis un file chooser). <br/>
   * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de pouvoir utiliser l'application</b>
   * 
   * FIXME : FileChooser pas au bon endroit !!
   */
  protected void initDirectory()
  {
    if (this.mExchangeDirectoryPath == null)
    {
      JFileChooser chooser = new JFileChooser();
      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int returnVal = chooser.showOpenDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION)
      {
        this.mExchangeDirectoryPath = chooser.getSelectedFile().getAbsolutePath();
        this.initDirectory(this.mExchangeDirectoryPath);
        this.mMainView.openWindow();
      }
      else
      {
        this.mMainView.dispose();
      }
    }
  }

  /**
   * Indique si le fichier donné est valide pour servire de répertoire d'échange
   * 
   * @param directory
   *          , Répertoire à tester.
   */
  protected boolean isValideExchangeDirectory(File directory)
  {
    // Valide si répertoire disponible en lecture et écriture
    return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
        && directory.canWrite();
  }

  /**
   * Initialisation du mode bouchoné de l'application
   */
  protected void initMock()
  {
    TwitupMock mock = new TwitupMock(this.mDatabase, this.mEntityManager);
    mock.showGUI();
  }

  /**
   * Initialisation de la base de données
   */
  protected void initDatabase()
  {
    mDatabase = new Database();
    mEntityManager = new EntityManager(mDatabase);
  }

  /**
   * Initialisation du répertoire d'échange.
   * 
   * @param directoryPath
   */
  public void initDirectory(String directoryPath)
  {
    mExchangeDirectoryPath = directoryPath;
    mWatchableDirectory = new WatchableDirectory(directoryPath);
    mEntityManager.setExchangeDirectory(directoryPath);

    mWatchableDirectory.initWatching();
    mWatchableDirectory.addObserver(mEntityManager);
  }

  protected void initConsoleTest()
  {
    this.mTwitupConsoleTest = new TwitupConsoleTest();
    this.mDatabase.addObserver(this.mTwitupConsoleTest);
  }

  public void show()
  {
    this.mMainController.start();
  }
}
