package com.iup.tp.twitup.ihm.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

import com.iup.tp.twitup.ihm.utils.RoundedCornerBorder;

public class JButtonStyled extends JButton
{
  private static final long serialVersionUID = 767656256427989370L;

  public JButtonStyled(String text)
  {
    super(text);
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    if (!isOpaque() && getBorder() instanceof RoundedCornerBorder)
    {
      Graphics2D g2 = (Graphics2D) g.create();
      g2.setPaint(getBackground());
      g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(0, 0, getWidth() - 1, getHeight() - 1));
      g2.dispose();
    }
    super.paintComponent(g);
  }

  @Override
  public void updateUI()
  {
    super.updateUI();
    setBackground(Color.decode("#1da1f2"));
    setCursor(new Cursor(Cursor.HAND_CURSOR));
    setFocusPainted(false);
    setContentAreaFilled(false);
    setFont(new Font("Sans-serif", Font.BOLD, 15));
    setForeground(Color.WHITE);
    setBorder(new RoundedCornerBorder(Color.decode("#1da1f2")));
  }
}
