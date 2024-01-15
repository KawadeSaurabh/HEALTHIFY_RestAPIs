package com.healthify.api;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class WhatsApp {
  // Find your Account Sid and Token at twilio.com/console
  public static final String ACCOUNT_SID = "AC3d649bc4035788eb97d6b27f311cd91b";
  public static final String AUTH_TOKEN = "5252b19affcf6b71b2e4e99fd09ba5de";

  public static void main(String[] args) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(
      new com.twilio.type.PhoneNumber("whatsapp:+919307508868"),
      new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
      "Massage from ram chadar")

    .create();

    System.out.println(message.getSid());
  }
}
