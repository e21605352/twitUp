package com.iup.tp.twitup.ihm.components.filechooser;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageFileChooserComponent extends JPanel
{
  private static final long serialVersionUID = 7631311134373662375L;

  protected JPanel contentPane;
  protected JLabel selectedPath;
  protected JFileChooser fileChooser;

  protected final Set<IImageFileChooserObserver> observers;

  public ImageFileChooserComponent()
  {
    this.observers = new HashSet<>();
    this.initComponent();
  }

  protected void initComponent()
  {
    this.setLayout(new GridBagLayout());
    this.contentPane = new JPanel(new GridBagLayout());
    this.add(this.contentPane, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

    this.selectedPath = new JLabel("Aucune image sélectionnée");

    this.initJFileChooser();
    this.placeComponents();
  }

  protected void initJFileChooser()
  {
    this.fileChooser = new JFileChooser();
    this.fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"));
  }

  protected void placeComponents()
  {
    JButton browseButton = new JButton("Parcourir");
    browseButton.addActionListener(e -> this.doBrowse());
    this.contentPane.add(browseButton, new GridBagConstraints(0, 0, 1, 1, 0, 1, GridBagConstraints.NORTH,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));

    this.contentPane.add(this.selectedPath, new GridBagConstraints(1, 0, 1, 1, 0, 1, GridBagConstraints.NORTH,
        GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
  }

  protected void doBrowse()
  {
    int response = this.fileChooser.showOpenDialog(null);

    if (response == JFileChooser.APPROVE_OPTION)
    {
      File selectedFile = this.fileChooser.getSelectedFile();
      this.selectedPath.setText(selectedFile.getAbsolutePath());

      for (IImageFileChooserObserver observer : this.observers)
      {
        observer.notifyImageSelected(this.fileChooser.getSelectedFile());
      }
    }
  }

  // ================================================================================
  // Gestion observeurs
  // ================================================================================

  public void addObserver(IImageFileChooserObserver observer)
  {
    this.observers.add(observer);
  }

  public void deleteObserver(IImageFileChooserObserver observer)
  {
    this.observers.remove(observer);
  }
}
