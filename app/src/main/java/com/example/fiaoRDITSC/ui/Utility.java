package com.example.fiaoRDITSC.ui;

import android.os.Build;
import android.util.Pair;

import androidx.annotation.RequiresApi;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Utility {

    public static String encodeForFirebaseKey(String s) {
        return s
                .replace("_", "__")
                .replace(".", "_P")
                .replace("$", "_D")
                .replace("#", "_H")
                .replace("[", "_O")
                .replace("]", "_C")
                .replace("/", "_S")
                ;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String FormatToSha(String cadena) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                cadena.getBytes(StandardCharsets.UTF_8));
        String cadReturn = bytesToHex(encodedhash);
        return cadReturn;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static Pair<String,Boolean> validarClave(String cadena) {
        String mensaje = "";
        Boolean valor = false;
            if (cadena.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*]).{8,}$")) {
                valor = true;
            } else if (cadena.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$")) {
                mensaje = "La contraseña debe contener caracteres especiales";
            } else if (cadena.matches("^(?=.*[a-z])(?=.*[0-9]).{8,}$")) {
                mensaje = "La contraseña debe contener letras mayusculas y caracteres especiales";
            } else if (cadena.matches("^(?=.*[A-Z])(?=.*[0-9]).{8,}$")) {
                mensaje = "La contraseña debe contener letras minusculas y caracteres especiales";
            } else if (cadena.matches("^(?=.*[0-9]).{8,}$")) {
                mensaje = "La contraseña debe contener letras minusculas y mayusculas y caracteres especiales";
            } else if (cadena.matches("^(?=.*[a-z]).{8,}$")) {
                mensaje = "La contraseña debe contener numeros, letras mayusculas y caracteres especiales";
            } else {
                mensaje = "La contraseña debe tener un minimo de 8 caracteres, numeros, letras minusculas y mayusculas y caracteres especiales ";
            }
        return new Pair<String,Boolean>(mensaje,valor);

    }

    public static String decodeFromFirebaseKey(String s) {
        int i = 0;
        int ni;
        String res = "";
        while ((ni = s.indexOf("_", i)) != -1) {
            res += s.substring(i, ni);
            if (ni + 1 < s.length()) {
                char nc = s.charAt(ni + 1);
                if (nc == '_') {
                    res += '_';
                } else if (nc == 'P') {
                    res += '.';
                } else if (nc == 'D') {
                    res += '$';
                } else if (nc == 'H') {
                    res += '#';
                } else if (nc == 'O') {
                    res += '[';
                } else if (nc == 'C') {
                    res += ']';
                } else if (nc == 'S') {
                    res += '/';
                } else {
                    // this case is due to bad encoding
                }
                i = ni + 2;
            } else {
                // this case is due to bad encoding
                break;
            }
        }
        res += s.substring(i);
        return res;
    }

}
