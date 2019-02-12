package com.hash;

import java.io.IOException;
import java.io.InputStream;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

public class Md5Hashing {

	// hash code of txt quiz files
	private String hash1 = "7854fa0c5d60a108cda768d67bfb1c7c";
	private String hash2 = "7289adf9683ad34db27620ac044883fd";
	private String hash3 = "eed858f3d6d06a7d29b8c72ed70bf4ee";

	public boolean getCheckHash(String fileName) {

		boolean result = false;
		String hashCode = null;

		switch (fileName) {

		case "iqupt.txt":
			hashCode = hash1;
			break;
		case "isvb.txt":
			hashCode = hash2;
			break;
		case "kaes.txt":
			hashCode = hash3;
			break;
		}
		try {
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			if (getFileHash(md5Digest, fileName).equals(hashCode))
				result = true;

		} catch (NoSuchAlgorithmException | IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return result;
	}

	private String getFileHash(MessageDigest digest, String path) throws IOException {

		InputStream is = this.getClass().getResourceAsStream("/com/exams/" + path);

		byte[] byteArray = new byte[1024];
		int bytesCount = 0;

		while ((bytesCount = is.read(byteArray)) != -1) {
			digest.update(byteArray, 0, bytesCount);
		}
		;

		is.close();

		byte[] bytes = digest.digest();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	// public static void main(String[] args) {
	// Md5Hashing obj = new Md5Hashing();
	// try {
	// obj.convert();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

}
