package com.iup.tp.twitup.ihm.twittimeline;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.ihm.components.Divider;
import com.iup.tp.twitup.ihm.twit.read.ReadTwitComponent;

public class TwitTimelineComponent extends JPanel implements ITwitTimelineModelObserver
{
  private static final long serialVersionUID = -6830816855006492544L;

  protected final TwitTimelineModel twitTimelineModel;
  protected JPanel contentPane;

  public TwitTimelineComponent(TwitTimelineModel twitTimelineModel)
  {
    this.twitTimelineModel = twitTimelineModel;
    this.initComponent();
  }

  protected void initComponent()
  {
    this.setLayout(new GridBagLayout());
    this.setBackground(Color.WHITE);

    this.contentPane = new JPanel(new GridBagLayout());
    this.contentPane.setBackground(Color.WHITE);
    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    this.placeComponents();
  }

  // FIXME Création composant dans la vue ?
  protected void placeComponents()
  {
    this.contentPane.removeAll();

    List<Twit> twits = this.twitTimelineModel.getTwits();
    int counter = 0;

    for (int i = 0; i < twits.size(); i++)
    {
      if (i == twits.size() - 1)
      {
        this.contentPane.add(new ReadTwitComponent(twits.get(i)), new GridBagConstraints(0, counter++, 1, 1, 1, 1,
            GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
      }
      else
      {
        this.contentPane.add(new ReadTwitComponent(twits.get(i)), new GridBagConstraints(0, counter++, 1, 1, 1, 0,
            GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));

        this.contentPane.add(new Divider(false, 2), new GridBagConstraints(0, counter++, 1, 1, 1, 0,
            GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
      }
    }
  }

  @Override
  public void notifyTwitAdded(Twit addedTwit)
  {
    this.placeComponents();
    this.repaintContentPane();
  }

  @Override
  public void notifyTwitsAdded(Set<Twit> addedTwits)
  {
    this.placeComponents();
    this.repaintContentPane();
  }

  @Override
  public void notifyTwitDeleted(Twit deletedTwit)
  {
    this.placeComponents();
    this.repaintContentPane();
  }

  @Override
  public void notifyTwitsDeleted(Set<Twit> deletedTwits)
  {
    this.placeComponents();
    this.repaintContentPane();
  }

  /**
   * Rafraîchi le panneau principal.
   */
  protected void repaintContentPane()
  {
    this.contentPane.removeAll();
    this.placeComponents();
    this.revalidate();
    this.repaint();
  }
}
