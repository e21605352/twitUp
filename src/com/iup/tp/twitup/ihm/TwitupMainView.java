package com.iup.tp.twitup.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitupMainView extends JFrame
{
  private static final long serialVersionUID = -8343902931017961107L;

  protected TwitupController twitupController;

  /**
   * Contenu de la fenêtre d'application.
   */
  protected JPanel contentPane;

  public TwitupMainView(TwitupController twitupController)
  {
    this.twitupController = twitupController;
    this.initGui();
  }

  public void openWindow()
  {
    this.setVisible(true);
    this.setFocusable(true);
    this.requestFocusInWindow();
  }

  /**
   * Affiche la vue envoyée en paramètre dans la fenêtre de l'application.
   * 
   * @param panel
   *          Vue à afficher.
   */
  public void showView(JPanel panel)
  {
    this.contentPane.removeAll();
    this.contentPane.add(panel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    this.contentPane.repaint();
    this.contentPane.revalidate();
  }

  protected void initGui()
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Initialisation paramètres fenêtre.
    ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("logoIUP_50.jpg")); // FIXME : Image brut
    this.setIconImage(icon.getImage());
    this.setTitle("TwitUp"); // FIXME : Texte brut
    this.setSize(screenSize.width * 50 / 100, screenSize.height * 75 / 100);
    this.setMinimumSize(new Dimension(screenSize.width * 20 / 100, screenSize.height * 25 / 100));
    this.setLocation((screenSize.width - this.getWidth()) / 6, (screenSize.height - this.getHeight()) / 4);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // Initialisation contenu fenêtre
    this.setJMenuBar(this.createMenuBar());
    this.contentPane = new JPanel(new GridBagLayout());
    this.setContentPane(this.contentPane);
  }

  /**
   * Initialise la barre de menu de l'application.
   */
  protected JMenuBar createMenuBar()
  {
    JMenuBar menu = new JMenuBar();

    // Catégories menu
    JMenu file = new JMenu("Fichier"); // FIXME : Texte brut
    menu.add(file);
    JMenu help = new JMenu("?"); // FIXME : Texte brut
    menu.add(help);

    // Sous-catégories menu
    JMenuItem leave = new JMenuItem("Quitter"); // FIXME : Texte brut
    leave.setIcon(new ImageIcon(getClass().getClassLoader().getResource("exitIcon_20.png"))); // FIXME : Image brut
    leave.addActionListener(e -> this.clickLeave());
    file.add(leave);

    JMenuItem about = new JMenuItem("A propos"); // FIXME : Texte brut
    about.setIcon(new ImageIcon(getClass().getClassLoader().getResource("aboutIcon_20.png"))); // FIXME : Image brut
    about.addActionListener(e -> this.clickAbout());
    help.add(about);

    return menu;
  }

  protected void clickAbout()
  {
    this.twitupController.showAbout();
  }

  protected void clickLeave()
  {
    this.twitupController.exitTwitup();
  }
}
