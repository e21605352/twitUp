package com.iup.tp.twitup.ihm;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

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

  /**
   * JDialog actuellement affiché.
   */
  protected JDialog jDialog;

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
    this.pack();
  }

  protected void initGui()
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Initialisation paramètres graphiques fenêtre.
    // TODO : Set logo
    this.setTitle("TwitUp"); // TODO : Texte dans fichier config
    this.setLocation((screenSize.width - this.getWidth()) / 6, (screenSize.height - this.getHeight()) / 4);

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

    JMenu file = new JMenu("Fichier");
    menu.add(file);

    JMenu help = new JMenu("?");
    menu.add(help);

    help.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        clickAbout();
      }
    });
    // Item leave du JMenuFile.
    JMenuItem leave = new JMenuItem("Quitter");
    leave.setIcon(new ImageIcon(getClass().getClassLoader().getResource("exitIcon_20.png"))); // FIXME : Brut
    file.add(leave);

    leave.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        clickLeave();
      }
    });

    JMenuItem about = new JMenuItem("A propos");
    about.setIcon(new ImageIcon(getClass().getClassLoader().getResource("aboutIcon_20.png"))); // FIXME : Brut
    help.add(about);

    about.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        clickAbout();
      }
    });

    return menu;
  }

  protected void clickAbout()
  {
    this.twitupController.showAbout();
  }

  protected void clickLeave()
  {
    this.twitupController.closeWindow();
  }
}
