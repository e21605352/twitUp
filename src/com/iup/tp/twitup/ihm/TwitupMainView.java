package com.iup.tp.twitup.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.iup.tp.twitup.ihm.components.Divider;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitupMainView extends JFrame
{
  private static final long serialVersionUID = -8343902931017961107L;

  protected JPanel contentPane;
  protected JPanel navigationComponent;

  protected final Set<IMainViewListener> listeners;

  public TwitupMainView()
  {
    this.listeners = new HashSet<>();
    this.initGui();
  }

  protected void initGui()
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Initialisation paramètres fenêtre.
    ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("logoIUP_50.jpg"));
    this.setIconImage(icon.getImage());
    this.setTitle("TwitUp");
    this.setSize(screenSize.width * 50 / 100, screenSize.height * 75 / 100);
    this.setMinimumSize(new Dimension(screenSize.width * 20 / 100, screenSize.height * 25 / 100));
    this.setLocation((screenSize.width - this.getWidth()) / 6, (screenSize.height - this.getHeight()) / 4);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    // Initialisation contenu fenêtre
    this.setJMenuBar(this.createMenuBar());
    this.contentPane = new JPanel(new GridBagLayout());
    this.setContentPane(this.contentPane);
  }

  public void openWindow()
  {
    this.setVisible(true);
    this.setFocusable(true);
    this.requestFocusInWindow();
  }

  public void showView(IModule module, boolean isNavigable)
  {
    this.contentPane.removeAll();

    if (isNavigable)
    {
      this.contentPane.add(this.navigationComponent, new GridBagConstraints(0, 0, 1, 1, 0, 1, GridBagConstraints.NORTH,
          GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));

      this.contentPane.add(new Divider(true, 2), new GridBagConstraints(1, 0, 1, 1, 0, 1, GridBagConstraints.NORTH,
          GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
    }

    this.contentPane.add(module.getView(), new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    this.contentPane.repaint();
    this.contentPane.revalidate();
  }

  public void addIMainViewListener(IMainViewListener listener)
  {
    this.listeners.add(listener);
  };

  /**
   * Initialise la barre de menu de l'application.
   */
  protected JMenuBar createMenuBar()
  {
    JMenuBar menu = new JMenuBar();

    // Catégories menu
    JMenu file = new JMenu("Fichier");
    menu.add(file);
    JMenu help = new JMenu("?");
    menu.add(help);

    // Sous-catégories menu
    JMenuItem leave = new JMenuItem("Quitter");
    leave.setIcon(new ImageIcon(getClass().getClassLoader().getResource("exitIcon_20.png")));
    leave.addActionListener(e -> this.clickLeave());
    file.add(leave);

    JMenuItem about = new JMenuItem("A propos");
    about.setIcon(new ImageIcon(getClass().getClassLoader().getResource("aboutIcon_20.png")));
    about.addActionListener(e -> this.clickAbout());
    help.add(about);

    return menu;
  }

  protected void clickAbout()
  {
    ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("logoIUP_50.jpg"));
    JPanel content = new JPanel(new GridBagLayout());
    content.add(new JLabel("UBO M2-TILL", SwingConstants.CENTER), new GridBagConstraints(0, 0, 1, 1, 1, 1,
        GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    content.add(new JLabel("Département Informatique", SwingConstants.CENTER), new GridBagConstraints(0, 1, 1, 1, 1, 1,
        GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    JOptionPane.showMessageDialog(this, content, "A propos", JOptionPane.PLAIN_MESSAGE, icon);
  }

  protected void clickLeave()
  {
    for (IMainViewListener listener : this.listeners)
    {
      listener.run();
    }
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public void setNavigationComponent(JPanel navigationComponent)
  {
    this.navigationComponent = navigationComponent;
  }
}
