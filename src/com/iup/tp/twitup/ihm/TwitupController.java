package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.Serializable;
import java.util.HashSet;
import java.util.UUID;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.dashboard.DashboardModule;
import com.iup.tp.twitup.ihm.signin.SignInModule;
import com.iup.tp.twitup.ihm.signup.ISignUpObserver;
import com.iup.tp.twitup.ihm.signup.SignUpModule;

public class TwitupController implements ISignUpObserver, Serializable
{
  private static final long serialVersionUID = 4092742481805901354L;

  protected final IDatabase database;
  protected final EntityManager entityManager;
  protected TwitupMainView twitupMainView;

  protected SignInModule signInModule;
  protected SignUpModule signUpModule;

  public TwitupController(IDatabase database, EntityManager entityManager)
  {
    this.database = database;
    this.entityManager = entityManager;
  }

  public void start()
  {
//    this.showSignUp();
//    this.twitupMainView.showView(new CreateTwitComponent(null));

    User user = new User(UUID.randomUUID(), "alanSmithee", "1234", "Alan", new HashSet<>(), "");
    String message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi sit amet dictum urna, eget suscipit ligula. Aliquam porttitor finibus sollicitudin.";

    this.entityManager.sendUser(user);
    for (int i = 0; i < 2; i++)
    {
      this.entityManager.sendTwit(new Twit(user, message));
    }

    this.twitupMainView.showView(new DashboardModule(database, entityManager).getDashboardComponent());
    this.twitupMainView.openWindow();
  }

  /**
   * Ferme l'application.
   */
  public void exitTwitup()
  {
    this.twitupMainView.dispose();
  }

  // ================================================================================
  // Affichage des vues
  // ================================================================================

  public void showSignUp()
  {
    if (this.signUpModule == null)
      this.signUpModule = new SignUpModule(this.database, this.entityManager);

    this.twitupMainView.showView(signUpModule.getComponent());
  }

  /**
   * Affiche la vue SignIn.
   */
  public void showSignIn()
  {
    if (this.signInModule == null)
      this.signInModule = new SignInModule(null); // FIXME : PAS BIEN !
    this.twitupMainView.showView(signInModule.getSignInComponent());
  }

  /**
   * Affiche le OptionPane "A propos".
   */
  public void showAbout()
  {
    ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("logoIUP_50.jpg")); // FIXME : Image brut

    JPanel content = new JPanel(new GridBagLayout());
    content.add(new JLabel("UBO M2-TILL", SwingConstants.CENTER), new GridBagConstraints(0, 0, 1, 1, 1, 1,
        GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0)); // FIXME : Texte brut
    content.add(new JLabel("Département Informatique", SwingConstants.CENTER), new GridBagConstraints(0, 1, 1, 1, 1, 1,
        GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0)); // FIXME : Texte brut

    JOptionPane.showMessageDialog(this.twitupMainView, content, "A propos", JOptionPane.PLAIN_MESSAGE, icon);
  }

  // ================================================================================
  // Accesseurs
  // ================================================================================

  public TwitupMainView getTwitupView()
  {
    return twitupMainView;
  }

  public void setTwitupView(TwitupMainView twitupView)
  {
    this.twitupMainView = twitupView;
  }

  @Override
  public void userCreated(User user)
  {

  }

  @Override
  public void creationCancelled()
  {

  }
}
