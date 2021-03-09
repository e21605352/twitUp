package com.iup.tp.twitup.ihm.search.searchbar;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iup.tp.twitup.ihm.components.JTextFieldStyled;

public class SearchBarComponent extends JPanel
{
  private static final long serialVersionUID = -1110465830136432363L;

  protected JPanel contentPane;
  protected JTextField searchInput;

  protected final Set<ISearchBarComponentObserver> observers;

  public SearchBarComponent()
  {
    this.observers = new HashSet<>();
    this.initComponent();
  }

  protected void initComponent()
  {
    this.setLayout(new GridBagLayout());
    this.contentPane = new JPanel(new GridBagLayout());
    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.VERTICAL, new Insets(10, 10, 10, 10), 0, 0));

    this.placeComponents();
  }

  protected void placeComponents()
  {
    Font font = new Font("Sans-serif", Font.PLAIN, 15);
    JLabel searchLabel = new JLabel("Rechercher :");
    searchLabel.setFont(font);
    this.contentPane.add(searchLabel, new GridBagConstraints(0, 0, 1, 1, 0, 1, GridBagConstraints.NORTH,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));

    this.searchInput = new JTextFieldStyled();
    this.searchInput.setFont(font);
    this.searchInput.addKeyListener(new KeyListener()
    {
      @Override
      public void keyTyped(KeyEvent e)
      {
        // DO NOTHING
      }

      @Override
      public void keyReleased(KeyEvent e)
      {
        newSearch();
      }

      @Override
      public void keyPressed(KeyEvent e)
      {
        // DO NOTHING
      }
    });
    this.contentPane.add(this.searchInput, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
  }

  protected void newSearch()
  {
    for (ISearchBarComponentObserver observer : this.observers)
    {
      observer.notifyNewSearch(this.searchInput.getText());
    }
  }

  public String getSearch()
  {
    return this.searchInput.getText();
  }

  // ================================================================================
  // Gestion observeurs
  // ================================================================================

  public void addObserver(ISearchBarComponentObserver observer)
  {
    this.observers.add(observer);
  }

  public void deleteObserver(ISearchBarComponentObserver observer)
  {
    this.observers.remove(observer);
  }
}
