package com.iup.tp.twitup.ihm.signup;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUpComponent extends JPanel
{
  private static final long serialVersionUID = 4093329035341023921L;

  protected SignUpController signUpController;

  protected JPanel contentPane;
  protected JTextField loginInput;
  protected JTextField lastnameInput;
  protected JTextField passwordInput;

  public SignUpComponent(SignUpController signUpController)
  {
    this.signUpController = signUpController;
    this.initComponent();
  }

  /**
   * Initialise le composant.
   */
  protected void initComponent()
  {
    this.contentPane = new JPanel(new GridBagLayout());
    this.lastnameInput = new JTextField();
    this.loginInput = new JTextField();
    this.passwordInput = new JTextField();

    this.setLayout(new GridBagLayout());
    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(20, 20, 20, 20), 0, 0));

    this.placeComponents();
  }

  /**
   * Place les composants dans le panel principal.
   */
  protected void placeComponents()
  {
    JPanel formComponent = new JPanel(new GridBagLayout());

    try // FIXME : Chargement image
    {
      URL imageUrl = getClass().getClassLoader().getResource("twitupLogo.png").toURI().toURL();
      JPanel imagePane = new com.iup.tp.twitup.ihm.utils.ImagePanel(ImageIO.read(imageUrl), new Dimension(100, 100));
      formComponent.add(imagePane, new GridBagConstraints(0, 0, 2, 1, 1, 0, GridBagConstraints.NORTH,
          GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));
    }
    catch (URISyntaxException | IOException e1)
    {
      e1.printStackTrace();
    }

    JLabel title = new JLabel("<html><center>Créer un compte<br/>Twitup</center></html>");
    title.setFont(new Font("Sans-Serif", Font.BOLD, 22));
    formComponent.add(title, new GridBagConstraints(0, 1, 2, 1, 1, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE,
        new Insets(0, 0, 20, 0), 0, 0));

    formComponent.add(new JLabel("Identifiant"), new GridBagConstraints(0, 2, 2, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));

    formComponent.add(this.loginInput, new GridBagConstraints(0, 3, 2, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 10, 0), 0, 0));

    formComponent.add(new JLabel("Nom"), new GridBagConstraints(0, 4, 2, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));

    formComponent.add(this.lastnameInput, new GridBagConstraints(0, 5, 2, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 10, 0), 0, 0));

    formComponent.add(new JLabel("Mot de passe"), new GridBagConstraints(0, 6, 2, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));

    formComponent.add(this.passwordInput, new GridBagConstraints(0, 7, 2, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

    JButton connectButton = new JButton("Créer");
    connectButton.addActionListener(e -> this.clickCreate());
    formComponent.add(connectButton, new GridBagConstraints(0, 8, 1, 1, 1, 0, GridBagConstraints.NORTHEAST,
        GridBagConstraints.NONE, new Insets(40, 0, 20, 4), 0, 0));

    JButton cancelButton = new JButton("Annuler");
    cancelButton.addActionListener(e -> this.clickCancel());
    formComponent.add(cancelButton, new GridBagConstraints(1, 8, 1, 1, 1, 0, GridBagConstraints.NORTHWEST,
        GridBagConstraints.NONE, new Insets(40, 4, 20, 0), 0, 0));

    this.contentPane.add(formComponent, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
  }

  protected void clickCreate()
  {
    this.signUpController.createUser();
  }

  protected void clickCancel()
  {
    this.signUpController.cancelCreation();
  }

  public String getLogin()
  {
    return this.loginInput.getText();
  }

  public String getLastname()
  {
    return this.lastnameInput.getText();
  }

  public String getPassword()
  {
    return this.passwordInput.getText();
  }
}
