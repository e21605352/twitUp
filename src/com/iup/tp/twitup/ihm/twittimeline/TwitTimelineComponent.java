package com.iup.tp.twitup.ihm.twittimeline;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.components.Divider;
import com.iup.tp.twitup.ihm.twit.read.ReadTwitComponent;

public class TwitTimelineComponent extends JPanel implements ITwitTimelineModelObserver
{
  private static final long serialVersionUID = -6830816855006492544L;

  protected final TwitTimelineModel twitTimelineModel;
  protected final Map<Twit, ReadTwitComponent> twitComponentsMap;

  protected JPanel contentPane;

  public TwitTimelineComponent(TwitTimelineModel twitTimelineModel)
  {
    this.twitTimelineModel = twitTimelineModel;
    this.twitComponentsMap = new HashMap<>();

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

  protected void placeComponents()
  {
    Set<Twit> twits = this.database.getTwits();
    List<Twit> twitss = new ArrayList<Twit>(twits);

    Collections.sort(twitss, new Comparator<Twit>()
    {
      @Override
      public int compare(Twit o1, Twit o2)
      {
        Long d1 = o1.getEmissionDate();
        Long d2 = o2.getEmissionDate();
        return (d1 > d2 ? -1 : (d1 == d2 ? 0 : 1));
      }
    });

    int counter = 0;
    for (int i = 0; i < twitss.size(); i++)
    {
      if (i == twitss.size() - 1)
      {
        this.contentPane.add(new ReadTwitComponent(twitss.get(i)), new GridBagConstraints(0, counter++, 1, 1, 1, 1,
            GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
      }
      else
      {
        this.contentPane.add(new ReadTwitComponent(twitss.get(i)), new GridBagConstraints(0, counter++, 1, 1, 1, 0,
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
  public void notifyTwitDeleted(Twit deletedTwit)
  {
    this.placeComponents();
    this.repaintContentPane();
  }

  @Override
  public void notifyTwitModified(Twit modifiedTwit)
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

  @Override
  public void notifyUserAdded(User addedUser)
  {
    // NOT IMPLEMENTED
  }

  @Override
  public void notifyUserDeleted(User deletedUser)
  {
    // NOT IMPLEMENTED
  }

  @Override
  public void notifyUserModified(User modifiedUser)
  {
    // NOT IMPLEMENTED
  }
}
