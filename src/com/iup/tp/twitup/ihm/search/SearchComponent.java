package com.iup.tp.twitup.ihm.search;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import com.iup.tp.twitup.ihm.components.Divider;
import com.iup.tp.twitup.ihm.search.searchbar.SearchBarModule;
import com.iup.tp.twitup.ihm.twittimeline.TwitTimelineComponent;

public class SearchComponent extends JPanel
{
  private static final long serialVersionUID = 3998756113774886307L;

  protected JPanel contentPane;

  public SearchComponent(SearchBarModule searchBarModule, TwitTimelineComponent twitTimelineComponent)
  {
    this.initComponent(searchBarModule, twitTimelineComponent);
  }

  protected void initComponent(SearchBarModule searchBarModule, TwitTimelineComponent twitTimelineComponent)
  {
    this.contentPane = new JPanel(new GridBagLayout());
    this.setLayout(new GridBagLayout());

    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    this.placeComponents(searchBarModule.getView(), twitTimelineComponent);
  }

  protected void placeComponents(JPanel searchBarComponent, JPanel twitTimelineComponent)
  {
    this.contentPane.add(searchBarComponent, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.NORTH,
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
