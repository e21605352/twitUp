package com.iup.tp.twitup.ihm.components;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPasswordField;

import com.iup.tp.twitup.ihm.utils.RoundedCornerBorder;

public class JPasswordFieldStyled extends JPasswordField
{
  private static final long serialVersionUID = -8738947018362938890L;

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
    setOpaque(false);
    setBorder(new RoundedCornerBorder());
  }
}
