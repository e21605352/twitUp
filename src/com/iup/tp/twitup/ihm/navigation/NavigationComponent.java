package com.iup.tp.twitup.ihm.navigation;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JPanel;

public class NavigationComponent extends JPanel
{
  private static final long serialVersionUID = -6310964056350071397L;

  protected JPanel contentPane;

  public NavigationComponent()
  {
    this.initComponent();
  }

  protected void initComponent()
  {
    this.setLayout(new GridBagLayout());

    this.contentPane = new JPanel(new GridBagLayout());
    this.contentPane.setOpaque(false);
    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.VERTICAL, new Insets(10, 10, 10, 10), 0, 0));
  }

  public void initNavigation(List<JPanel> navigationItems)
  {
    this.placeComponents(navigationItems);
  }

  protected void placeComponents(List<JPanel> navigationItems)
  {
    for (int i = 0; i < navigationItems.size(); i++)
    {
      if (i == navigationItems.size() - 1)
      {
        this.contentPane.add(navigationItems.get(i), new GridBagConstraints(0, i, 1, 1, 1, 1, GridBagConstraints.NORTH,
            GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
      }
      else
      {
        this.contentPane.add(navigationItems.get(i), new GridBagConstraints(0, i, 1, 1, 1, 0, GridBagConstraints.NORTH,
            GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));
      }
    }
  }
}
