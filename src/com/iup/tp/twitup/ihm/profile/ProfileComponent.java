package com.iup.tp.twitup.ihm.profile;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import com.iup.tp.twitup.ihm.components.Divider;
import com.iup.tp.twitup.ihm.profile.profileInfos.ProfileInfosComponent;
import com.iup.tp.twitup.ihm.twittimeline.TwitTimelineComponent;

public class ProfileComponent extends JPanel
{
  private static final long serialVersionUID = -4733976937847991034L;

  protected JPanel contentPane;

  public ProfileComponent(ProfileInfosComponent profileInfosComponent, TwitTimelineComponent twitTimelineComponent)
  {
    this.initComponent(profileInfosComponent, twitTimelineComponent);
  }

  protected void initComponent(ProfileInfosComponent profileInfosComponent, TwitTimelineComponent twitTimelineComponent)
  {
    this.contentPane = new JPanel(new GridBagLayout());
    this.setLayout(new GridBagLayout());

    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    this.placeComponents(profileInfosComponent, twitTimelineComponent);
  }

  protected void placeComponents(JPanel profileInfosComponent, JPanel twitTimelineComponent)
  {
    this.contentPane.add(profileInfosComponent, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

    this.contentPane.add(new Divider(false, 15), new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

    JScrollPane scrollPane = new JScrollPane(twitTimelineComponent);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setBorder(UIManager.getBorder("Label.border"));
    this.contentPane.add(scrollPane, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
  }
}
