package br.com.conversordemoedas.principal;

public enum Moeda {
    ARS,
    BOB,
    BRL,
    CLP,
    COP,
    USD;

    public static boolean contem(String codigo) {
        for (Moeda moeda : Moeda.values()) {
            if (moeda.name().equals(codigo)) {
                return true;
            }
        }
        return false;
    }
}
