package com.iup.tp.twitup.ihm.signup;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.File;
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
import com.iup.tp.twitup.ihm.components.filechooser.IImageFileChooserObserver;
import com.iup.tp.twitup.ihm.components.filechooser.ImageFileChooserComponent;
import com.iup.tp.twitup.ihm.components.link.LinkComponent;

public class SignUpComponent extends JPanel implements IImageFileChooserObserver
{
  private static final long serialVersionUID = 4093329035341023921L;

  protected final ImageFileChooserComponent imageChooserComponent;

  protected JPanel contentPane;
  protected JTextField loginInput;
  protected JTextField usernameInput;
  protected String avatarPath;
  protected JTextField passwordInput;

  protected final Set<ISignUpComponentObserver> observers;

  public SignUpComponent(ImageFileChooserComponent imageChooserComponent)
  {
    this.imageChooserComponent = imageChooserComponent;
    this.avatarPath = "";
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

    this.initInputs();

    this.placeComponents();
  }

  protected void initInputs()
  {
    Font font = new Font("Sans-Serif", Font.PLAIN, 16);

    this.loginInput = new JTextFieldStyled();
    this.loginInput.setFont(font);
    this.usernameInput = new JTextFieldStyled();
    this.usernameInput.setFont(font);
    this.passwordInput = new JPasswordFieldStyled();
    this.passwordInput.setFont(font);
  }

  protected void placeComponents()
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    try // FIXME : Chargement image
    {
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

    this.contentPane.add(this.usernameInput, new GridBagConstraints(0, 2, 2, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 40, 0), 0, 0));

    this.contentPane.add(this.imageChooserComponent, new GridBagConstraints(0, 3, 2, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 40, 0), 0, 0));

    this.contentPane.add(this.passwordInput, new GridBagConstraints(0, 4, 2, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 40, 0), 0, 0));

    this.contentPane.add(this.generateCreateButton(), new GridBagConstraints(0, 5, 2, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.VERTICAL, new Insets(0, 0, 25, 0), 0, 0));

    JLabel signupLabel = new JLabel("Déjà inscrit ?");
    signupLabel.setFont(new Font("Sans-serif", Font.PLAIN, 15));
    this.contentPane.add(signupLabel, new GridBagConstraints(0, 6, 1, 1, 1, 0, GridBagConstraints.EAST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0));

    LinkComponent signinLink = new LinkComponent("Se connecter", 15);
    signinLink.addListener(this::clickSignin);
    this.contentPane.add(signinLink, new GridBagConstraints(1, 6, 1, 1, 1, 0, GridBagConstraints.WEST,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
  }

  protected JPanel generateCreateButton()
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    JPanel buttonPanel = new JPanel(new GridBagLayout());
    buttonPanel.setPreferredSize(new Dimension(screenSize.width * 15 / 100, screenSize.height * 5 / 100));
    buttonPanel.setMinimumSize(new Dimension(screenSize.width * 15 / 100, screenSize.height * 5 / 100));

    JButton connectButton = new JButtonStyled("Créer un compte");
    connectButton.addActionListener(e -> this.clickCreate());
    buttonPanel.add(connectButton, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    return buttonPanel;
  }

  protected void clickCreate()
  {
    for (ISignUpComponentObserver observer : this.observers)
    {
      System.out.println(this.avatarPath);
      observer.notifySignup(this.loginInput.getText(), this.usernameInput.getText(), this.passwordInput.getText(),
                            this.avatarPath);
    }
  }

  protected void clickSignin()
  {
    for (ISignUpComponentObserver observer : this.observers)
    {
      observer.notifySignin();
    }
  }

  @Override
  public void notifyImageSelected(File imageSelected)
  {
    this.avatarPath = imageSelected.getAbsolutePath();
  }

  public String getLogin()
  {
    return this.loginInput.getText();
  }

  public String getLastname()
  {
    return this.usernameInput.getText();
  }

  public String getPassword()
  {
    return this.passwordInput.getText();
  }

  // ================================================================================
  // Gestion observeurs
  // ================================================================================

  public void addObserver(ISignUpComponentObserver observer)
  {
    this.observers.add(observer);
  }

  public void deleteObserver(ISignUpComponentObserver observer)
  {
    this.observers.remove(observer);
  }
}
