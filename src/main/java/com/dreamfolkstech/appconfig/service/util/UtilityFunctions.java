/*******************************************************************************
 * Copyright (c) 2020-21 DreamFolks Technologies Pvt Ltd.
 * 
 * All Rights Reserved.
 * 
 * NOTICE: All information contained herein is, and remains the property of DreamFolks Technologies
 * Pvt Ltd. The intellectual and technical concepts contained herein may be covered by Patents,
 * patents in process, and are protected by trade secret or copyright law. Dissemination of this
 * information or reproduction of this material is strictly forbidden unless prior written
 * permission is obtained from DreamFolks Technologies Pvt Ltd.
 ******************************************************************************/
package com.dreamfolkstech.appconfig.service.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.dreamfolkstech.common.errors.ExternalBaseResponse;

@Component
public final class UtilityFunctions {
  private static final String ERROR_PREFIX = "ERROR.";
  private static MessageSource messageSource;

  private UtilityFunctions() {}

  @Autowired
  public void setMessageSource(MessageSource msgSource) {
    messageSource = msgSource;
  }

  public static String geteMsgFromCode(String code, Object... msgArgs) {
    return messageSource.getMessage(ERROR_PREFIX + code, msgArgs, Locale.getDefault());
  }

  public static String geteMsgFromCode(String code) {
    return messageSource.getMessage(ERROR_PREFIX + code, null, Locale.getDefault());
  }

  public static ExternalBaseResponse getBaseExternalResponse(String code) {
    return new ExternalBaseResponse(geteMsgFromCode(code), code);
  }

  public static ExternalBaseResponse getBaseExternalResponse(String code, Object data) {
    ExternalBaseResponse response = new ExternalBaseResponse(geteMsgFromCode(code), code);
    response.setData(data);
    return response;
  }

  public static ExternalBaseResponse getBaseExternalResponse(String code, String errorKey) {
    return new ExternalBaseResponse(geteMsgFromCode(code, errorKey), code);
  }

  /**
   * @param date instant
   * @return convert date into format like 26 Apr 2020 11:02 AM 19800 seconds is subtracted as
   *         formatting adds 5.5 hrs to time
   * @throws ParseException
   */
  public String dateFormatter(Instant date) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String formattedDate = formatter.format(Date.from(date));
    DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DateFormat outputFormat = new SimpleDateFormat("''dd MMM yyyy' 'KK:mm a");
    String finalFormattedDate = outputFormat.format(inputFormat.parse(formattedDate));
    int i = finalFormattedDate.indexOf(':');
    if(finalFormattedDate.substring(i-2, i).contains("00")) {
      StringBuilder tempString = new StringBuilder(finalFormattedDate); 
      tempString.setCharAt(i-2, '1');
      tempString.setCharAt(i-1, '2');
      finalFormattedDate = tempString.toString();
    }
    return finalFormattedDate.substring(1, finalFormattedDate.length());
  }
}
