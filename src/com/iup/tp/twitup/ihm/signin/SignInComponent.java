package com.iup.tp.twitup.ihm.signin;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Composant graphique formulaire de connexion.
 * 
 * @author KAN
 *
 */
public class SignInComponent extends JPanel
{
  private static final long serialVersionUID = 7603526814136132461L;

  protected SignInController signInController;

  protected JPanel contentPane;
  protected JTextField loginInput;
  protected JTextField passwordInput;

  public SignInComponent(SignInController signInController)
  {
    this.signInController = signInController;
    this.initComponent();
  }

  /**
   * Initialise le composant.
   */
  protected void initComponent()
  {
    this.setLayout(new GridBagLayout());
    this.contentPane = new JPanel(new GridBagLayout());

    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(10, 10, 10, 10), 0, 0));

    this.placeComponents();
  }

  protected void placeComponents()
  {
    JPanel formComponent = new JPanel(new GridBagLayout());

    formComponent.add(new JLabel("Identifiant"), new GridBagConstraints(0, 0, 2, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));

    formComponent.add(this.loginInput, new GridBagConstraints(0, 1, 2, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 10, 0), 0, 0));

    formComponent.add(new JLabel("Mot de passe"), new GridBagConstraints(0, 2, 2, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.NONE, new Insets(0, 0, 10, 0), 0, 0));

    formComponent.add(this.passwordInput, new GridBagConstraints(0, 3, 2, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

    JButton connectButton = new JButton("Se connecter");
    connectButton.addActionListener(e -> this.clickConnect());
    formComponent.add(connectButton, new GridBagConstraints(0, 4, 1, 1, 1, 0, GridBagConstraints.NORTHEAST,
        GridBagConstraints.NONE, new Insets(40, 0, 20, 4), 0, 0));

    JButton cancelButton = new JButton("Annuler");
    cancelButton.addActionListener(e -> this.clickCancel());
    formComponent.add(cancelButton, new GridBagConstraints(1, 4, 1, 1, 1, 0, GridBagConstraints.NORTHWEST,
        GridBagConstraints.NONE, new Insets(40, 4, 20, 0), 0, 0));

    this.contentPane.add(formComponent, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
  }

  protected void clickConnect()
  {
    this.signInController.connect();
  }

  protected void clickCancel()
  {
    this.signInController.cancel();
  }

  public String getLogin()
  {
    return this.loginInput.getText();
  }

  public String getPassword()
  {
    return this.passwordInput.getText();
  }
}