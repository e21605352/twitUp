package com.iup.tp.twitup.ihm.utils;

import java.text.DateFormat;
import java.util.Date;

public class DateFormatter
{

  private DateFormatter()
  {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Formatte la date envoyée selon la localisation.
   * 
   * @param date
   *          Date à formatter.
   * @return Date formattée.
   */
  public static String formatLocalDate(Date date)
  {
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
    return dateFormat.format(date);
  }
}
