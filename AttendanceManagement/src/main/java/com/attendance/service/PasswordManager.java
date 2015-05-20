package com.attendance.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;
@Service
public class PasswordManager {
	public String forwardHash;
	private String password;

	public void setForwardHash(String hash) {
		forwardHash = hash;
	}

	public String getForwardHash() {
		return forwardHash;
	}

	public String hashCreate(String pass) {
		if (pass == null || pass.length() == 0 || pass.equals("********")) {
			this.password = forwardHash;
		} else {
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			md.update(pass.getBytes());
			byte[] hash = md.digest();
			this.password = hashByte2MD5(hash);
		}
		return password;
	}

	private String hashByte2MD5(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			if ((0xff & hash[i]) < 0x10) {
				hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
			} else {
				hexString.append(Integer.toHexString(0xFF & hash[i]));
			}
		}
		return hexString.toString();
	}
}
