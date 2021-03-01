package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class AboutView extends JPanel
{
  private static final long serialVersionUID = -5274840194207921295L;

  public AboutView()
  {
    this.initView();
  }

  protected void initView()
  {
    this.setLayout(new GridBagLayout());
    JPanel contentPane = new JPanel(new GridBagLayout());

    this.add(contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
        new Insets(10, 10, 10, 10), 0, 0));
  }
}
