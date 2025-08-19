package br.com.conversordemoedas.principal;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class TaxasDeCambioResponse {

    @SerializedName("base_code")
    private String moedaBase;
    @SerializedName("conversion_rates")
    private Map<String, Double> taxas;

    public String getMoedaBase() {
        return moedaBase;
    }

    public Map<String, Double> getTaxas() {
        return taxas;
    }
}
