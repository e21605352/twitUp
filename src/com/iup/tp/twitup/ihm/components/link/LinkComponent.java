package com.iup.tp.twitup.ihm.components.link;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LinkComponent extends JPanel
{
  private static final long serialVersionUID = 7634048354650704163L;

  protected JPanel contentPane;
  protected JLabel label;

  protected final Set<ILinkListener> listeners;

  public LinkComponent(String text, int size)
  {
    this.listeners = new HashSet<>();
    this.initComponent(text, size);
  }

  protected void initComponent(String text, int size)
  {
    this.setLayout(new GridBagLayout());
    this.setOpaque(false);
    this.contentPane = new JPanel(new GridBagLayout());
    this.contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

    this.label = new JLabel(text);
    this.label.setForeground(Color.BLUE);
    this.label.setFont(new Font("Sans-serif", Font.PLAIN, size));
    this.contentPane.add(this.label, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

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

  protected void updateComponentOnHover(boolean onHover)
  {
    Font font = this.label.getFont();
    Map<TextAttribute, Object> attributes = new HashMap<>(this.label.getFont().getAttributes());

    if (onHover)
    {
      attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
    }
    else
    {
      attributes.put(TextAttribute.UNDERLINE, -1);
    }

    this.label.setFont(font.deriveFont(attributes));
    this.revalidate();
    this.repaint();
  }

  protected void doClick()
  {
    for (ILinkListener listener : this.listeners)
    {
      listener.run();
    }
  }

  // ================================================================================
  // Gestion listeners
  // ================================================================================

  public void addListener(ILinkListener listener)
  {
    this.listeners.add(listener);
  }

  public void deleteListener(ILinkListener listener)
  {
    this.listeners.remove(listener);
  }
}
