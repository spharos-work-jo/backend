package com.workjo.pointapp.auth;


import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class CertUtils {

	public static String makeSendCertSmsSignature(String timestamp, String serviceId, String accessKey, String secretKey)
		throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		String space = " ";                    // one space
		String newLine = "\n";                    // new line
		String method = "POST";                    // method
		String url = "/sms/v2/services/" + serviceId + "/messages";    // url (include query string)

		String message = new StringBuilder()
			.append(method)
			.append(space)
			.append(url)
			.append(newLine)
			.append(timestamp)
			.append(newLine)
			.append(accessKey)
			.toString();

		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(signingKey);

		byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
		String encodeBase64String = Base64.encodeBase64String(rawHmac);

		return encodeBase64String;
	}


	public static String makeRandomNumberString() {
		Random r = new Random();
		int randomNum = r.nextInt(888888) + 111111;
		return Integer.toString(randomNum);
	}

}
