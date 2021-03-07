package com.iup.tp.twitup.ihm.dashboard;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import com.iup.tp.twitup.ihm.components.Divider;
import com.iup.tp.twitup.ihm.twit.create.CreateTwitComponent;
import com.iup.tp.twitup.ihm.twittimeline.TwitTimelineComponent;

public class DashboardComponent extends JPanel
{
  private static final long serialVersionUID = -4871680982633431617L;

  protected CreateTwitComponent createTwitComponent;
  protected TwitTimelineComponent twitTimelineComponent;
  protected JPanel contentPane;

  public DashboardComponent(CreateTwitComponent createTwitComponent, TwitTimelineComponent twitTimelineComponent)
  {
    this.createTwitComponent = createTwitComponent;
    this.twitTimelineComponent = twitTimelineComponent;

    this.initComponent();
  }

  protected void initComponent()
  {
    this.contentPane = new JPanel(new GridBagLayout());
    this.setLayout(new GridBagLayout());

    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    this.placeComponents();
  }

  protected void placeComponents()
  {
    this.contentPane.add(this.createTwitComponent, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

    this.contentPane.add(new Divider(false, 15), new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

    JScrollPane scrollPane = new JScrollPane(this.twitTimelineComponent);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setBorder(UIManager.getBorder("Label.border"));
    this.contentPane.add(scrollPane, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
  }

}
