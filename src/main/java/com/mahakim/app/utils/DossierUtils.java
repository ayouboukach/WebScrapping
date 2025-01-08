package com.mahakim.app.utils;

public class DossierUtils {
    public static String genererNumeroDossier(String year, String codeDossier, String startWithNumeroDossier, int i) {
        return year.concat(codeDossier).concat(String.valueOf(Integer.parseInt(startWithNumeroDossier) + i));
    }
}
