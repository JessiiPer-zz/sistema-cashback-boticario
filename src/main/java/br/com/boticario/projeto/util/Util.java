package br.com.boticario.projeto.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.MaskFormatter;

public class Util {

	public static String criptografarSenha(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// Usando SHA-2
		MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
		byte messageDigest[] = algorithm.digest(s.getBytes("UTF-8"));

		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}
		String senha = hexString.toString();
		return senha;
	}

	public static String converterDataParaString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String strDate = formatter.format(date);
		return strDate;
	}
	
	public static String formataCnpj(String cpf) throws ParseException {
        MaskFormatter mask = new MaskFormatter("###.###.###-##");
        mask.setValueContainsLiteralCharacters(false);
	        
	    return mask.valueToString(cpf);
	}

}
