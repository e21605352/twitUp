package com.iup.tp.twitup.ihm.components.filechooser;

import java.io.File;

public interface IImageFileChooserObserver
{
  public void notifyImageSelected(File imageSelected);
}
