package com.iup.tp.twitup.ihm.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Gestion de l'affichage d'une image.
 * 
 * @author Edispa2020
 *
 */
public class ImagePanel extends JPanel
{
  private static final long serialVersionUID = -1464284450621878030L;

  protected Image image;
  protected BufferedImage buffImage;

  /**
   * Instanciation de l'image ainsi que la dimension.
   * 
   * @param image
   *          Image à afficher.
   * @param dimension
   *          Dimension de l'image.
   */
  public ImagePanel(Image image, Dimension dimension)
  {
    this.image = image;

    try
    {
      this.load(dimension);
    }
    catch (Exception e)
    {
      this.buffImage = new BufferedImage(1, 1, 1);
      e.printStackTrace();
    }
  }

  /**
   * Chargement de l'image.
   * 
   * @param dimension
   */
  protected void load(Dimension dimension)
  {

    this.buffImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

    // Resize de l'image
    if (this.buffImage.getWidth() != dimension.getWidth() || this.buffImage.getHeight() != dimension.getHeight())
    {
      this.buffImage = this.resizeImage(this.buffImage, dimension);
    }
    this.setOpaque(false);
    this.setSize(new Dimension(this.buffImage.getWidth(), this.buffImage.getHeight()));
    this.setPreferredSize(new Dimension(this.buffImage.getWidth(), this.buffImage.getHeight()));
    this.setMinimumSize(this.getSize());

  }

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.drawImage(buffImage, 0, 0, buffImage.getWidth(), buffImage.getHeight(), null);
  }

  /**
   * Redimensionnement de l'image.
   * 
   * @param original
   * @param dimension
   * @return
   */
  public BufferedImage resizeImage(BufferedImage original, Dimension dimension)
  {

    double widthFactor = dimension.getWidth() / original.getWidth();
    double heightFactor = dimension.getHeight() / original.getHeight();

    double factor = Math.min(widthFactor, heightFactor);
    int newWidth = (int) (factor * original.getWidth());
    int newHeight = (int) (factor * original.getHeight());

    BufferedImage newImage = new BufferedImage(newWidth, newHeight, original.getType());

    Graphics2D g = newImage.createGraphics();

    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g.drawImage(image, 0, 0, newWidth, newHeight, null);
    g.dispose();

    return newImage;
  }
}
