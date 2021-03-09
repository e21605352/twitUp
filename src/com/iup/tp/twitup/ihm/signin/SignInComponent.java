package com.iup.tp.twitup.ihm.signin;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iup.tp.twitup.ihm.components.ImagePanel;
import com.iup.tp.twitup.ihm.components.JButtonStyled;
import com.iup.tp.twitup.ihm.components.JPasswordFieldStyled;
import com.iup.tp.twitup.ihm.components.JTextFieldStyled;
import com.iup.tp.twitup.ihm.components.link.LinkComponent;

/**
 * Composant graphique formulaire de connexion.
 */
public class SignInComponent extends JPanel
{
  private static final long serialVersionUID = 7603526814136132461L;

  protected JPanel contentPane;
  protected JTextField loginInput;
  protected JTextField passwordInput;

  protected final Set<ISignInComponentObserver> observers;

  public SignInComponent()
  {
    this.observers = new HashSet<>();
    this.initComponent();
  }

  /**
   * Initialise le composant.
   */
  protected void initComponent()
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    this.setLayout(new GridBagLayout());
    this.contentPane = new JPanel(new GridBagLayout());
    this.contentPane.setPreferredSize(new Dimension(screenSize.width * 30 / 100, this.getHeight()));
    this.contentPane.setMinimumSize(new Dimension(screenSize.width * 30 / 100, this.getHeight()));
    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.VERTICAL, new Insets(10, 10, 10, 10), 0, 0));

    this.loginInput = new JTextFieldStyled();
    this.loginInput.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
    this.passwordInput = new JPasswordFieldStyled();
    this.passwordInput.setFont(new Font("Sans-Serif", Font.PLAIN, 16));

    this.placeComponents();
  }

  protected void placeComponents()
  {
    try // FIXME : Chargement image
    {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

      JPanel twitupBanner = new ImagePanel(
          ImageIO.read(getClass().getClassLoader().getResource("twitup-banner.png").toURI().toURL()),
          new Dimension(screenSize.width * 20 / 100, screenSize.width * 7 / 100));
      this.contentPane.add(twitupBanner, new GridBagConstraints(0, 0, 2, 1, 1, 0, GridBagConstraints.NORTH,
          GridBagConstraints.NONE, new Insets(0, 0, 40, 0), 0, 0));
    }
    catch (URISyntaxException | IOException e1)
    {
      e1.printStackTrace();
    }

    this.contentPane.add(this.loginInput, new GridBagConstraints(0, 1, 2, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 40, 0), 0, 0));

    this.contentPane.add(this.passwordInput, new GridBagConstraints(0, 2, 2, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 40, 0), 0, 0));

    this.contentPane.add(this.generateConnectButton(), new GridBagConstraints(0, 3, 2, 1, 1, 0,
        GridBagConstraints.NORTH, GridBagConstraints.VERTICAL, new Insets(0, 0, 25, 0), 0, 0));

    JLabel signupLabel = new JLabel("Pas encore inscrit ?");
    signupLabel.setFont(new Font("Sans-serif", Font.PLAIN, 15));
    this.contentPane.add(signupLabel, new GridBagConstraints(0, 4, 1, 1, 1, 0, GridBagConstraints.EAST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0));

    LinkComponent signupLink = new LinkComponent("Créer un compte", 15);
    signupLink.addListener(() -> this.clickSignUp());
    this.contentPane.add(signupLink, new GridBagConstraints(1, 4, 1, 1, 1, 0, GridBagConstraints.WEST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
  }

  protected JPanel generateConnectButton()
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    JPanel buttonPanel = new JPanel(new GridBagLayout());
    buttonPanel.setPreferredSize(new Dimension(screenSize.width * 15 / 100, screenSize.height * 5 / 100));
    buttonPanel.setMinimumSize(new Dimension(screenSize.width * 15 / 100, screenSize.height * 5 / 100));

    JButton connectButton = new JButtonStyled("Se connecter");
    connectButton.addActionListener(e -> this.clickConnect());
    buttonPanel.add(connectButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    return buttonPanel;
  }

  protected void clickConnect()
  {
    for (ISignInComponentObserver observer : this.observers)
    {
      observer.notifyConnect(this.loginInput.getText(), this.passwordInput.getText());
    }
  }

  protected void clickSignUp()
  {
    for (ISignInComponentObserver observer : this.observers)
    {
      observer.notifySignUp();
    }
  }

  public String getLogin()
  {
    return this.loginInput.getText();
  }

  public String getPassword()
  {
    return this.passwordInput.getText();
  }

  // ================================================================================
  // Gestion observeurs
  // ================================================================================

  public void addObserver(ISignInComponentObserver observer)
  {
    this.observers.add(observer);
  }

  public void deleteObserver(ISignInComponentObserver observer)
  {
    this.observers.remove(observer);
  }
}
