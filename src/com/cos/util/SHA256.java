package com.cos.util;

import java.security.MessageDigest;
import java.util.Random;

//256bit ������ ��ȣ = �ؽ� = ��ȣȭ�� �ȵ�
public class SHA256 {
	// password�� ��ȣȭ�ؼ� return

	public static String getEncrypt(String rawPassword, String salt) {

		// rawPassword = "qw5678qw"
		// salt = cos
		String result = "";

		// [q , w , 5, 6, 7, 8,q ,w]

		byte[] a = rawPassword.getBytes();
		byte[] b = new byte[a.length + salt.length()]; // 11
		
		try {

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(b); // MessageDigest�� SHA-256���� ��ȣȭ�� ���� ��� ����.
			byte[] bResult = md.digest();

//			for (byte data : bResult) {
//				System.out.print(data+" ");
//			}

			StringBuffer sb = new StringBuffer();
			for (byte data : bResult) {
				sb.append(Integer.toString(data & 0xff, 16));
			}
			result = sb.toString();
//			System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
