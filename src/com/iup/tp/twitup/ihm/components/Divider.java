package com.iup.tp.twitup.ihm.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Divider extends JPanel
{
  private static final long serialVersionUID = 3335008055845636818L;

  protected boolean vertical;
  protected int size;
  protected JPanel contentPane;

  public Divider(boolean vertical, int size)
  {
    this.vertical = vertical;
    this.size = size;
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.setColor(Color.LIGHT_GRAY);

    if (vertical)
    {
      this.setSize(new Dimension(this.size, this.getHeight()));
      this.setPreferredSize(new Dimension(this.size, this.getHeight()));
      this.setMinimumSize(new Dimension(this.size, this.getHeight()));
      g.fillRect(0, 0, this.size, this.getHeight());
    }
    else
    {
      this.setSize(new Dimension(this.getWidth(), this.size));
      this.setPreferredSize(new Dimension(this.getWidth(), this.size));
      this.setMinimumSize(new Dimension(this.getWidth(), this.size));
      g.fillRect(0, 0, this.getWidth(), this.size);
    }
  }
}
