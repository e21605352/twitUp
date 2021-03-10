package com.iup.tp.twitup.ihm.components.iconbutton;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.iup.tp.twitup.ihm.components.ImagePanel;

public class IconButton extends JPanel
{
  private static final long serialVersionUID = 1323002835505847801L;

  protected JPanel contentPane;
  protected JPanel iconImage;
  protected JPanel iconImageHover;
  protected String tooltip;

  protected final Set<IIconButtonListener> listeners;

  public IconButton(String iconPath, String iconHoverPath, String tooltip)
  {
    this.listeners = new HashSet<>();
    this.initComponent(iconPath, iconHoverPath, tooltip);
  }

  protected void initComponent(String iconPath, String iconHoverPath, String tooltip)
  {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    this.setLayout(new GridBagLayout());
    this.setOpaque(false);
    this.initContentPane();

    try // FIXME
    {
      this.iconImage = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource(iconPath).toURI().toURL()),
          new Dimension(screenSize.width * 2 / 100, screenSize.width * 2 / 100), true);

      this.iconImageHover = new ImagePanel(
          ImageIO.read(getClass().getClassLoader().getResource(iconHoverPath).toURI().toURL()),
          new Dimension(screenSize.width * 2 / 100, screenSize.width * 2 / 100), true);
    }
    catch (URISyntaxException | IOException e1)
    {
      e1.printStackTrace();
    }

    this.contentPane.add(this.iconImage, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
  }

  protected void initContentPane()
  {
    this.contentPane = new JPanel(new GridBagLayout());
    this.contentPane.setOpaque(false);
    this.contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
    this.contentPane.setToolTipText(tooltip);
    this.initMouseListener();
  }

  protected void initMouseListener()
  {
    this.contentPane.addMouseListener(new MouseAdapter()
    {
      @Override
      public void mouseEntered(MouseEvent evt)
      {
        updateComponentOnHover(true);
      }

      @Override
      public void mouseExited(MouseEvent evt)
      {
        updateComponentOnHover(false);
      }

      @Override
      public void mousePressed(MouseEvent arg0)
      {
        doClick();
      }
    });
  }

  protected void doClick()
  {
    for (IIconButtonListener listener : this.listeners)
    {
      listener.run();
    }
  }

  public void addIIconButtonListener(IIconButtonListener listener)
  {
    this.listeners.add(listener);
  }

  protected void updateComponentOnHover(boolean onHover)
  {
    this.contentPane.removeAll();

    if (onHover)
    {
      this.contentPane.add(this.iconImageHover, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
          GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    }
    else
    {
      this.contentPane.add(this.iconImage, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
          GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    }

    this.revalidate();
    this.repaint();
  }

  public void addActionListener(Object object)
  {
    // TODO Auto-generated method stub

  }
}
