package com.healthify.api;
import com.healthify.api.utility.OTPGenerator;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SendSms {
  // Find your Account Sid and Token at twilio.com/console
  public static final String ACCOUNT_SID = "AC3d649bc4035788eb97d6b27f311cd91b";
  public static final String AUTH_TOKEN = "5252b19affcf6b71b2e4e99fd09ba5de";

  

  
  public static void main(String[] args) {
	  int otp = OTPGenerator.generateOtp();
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    Message message = Message.creator(
      new com.twilio.type.PhoneNumber("+919307508868"),
      new com.twilio.type.PhoneNumber("+18159823472"),
      "Your Varification Code is "+otp)
    .create();

    System.out.println(message.getSid());
  }
}