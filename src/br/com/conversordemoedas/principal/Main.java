package br.com.conversordemoedas.principal;

import com.google.gson.Gson;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final ConexaoAPI api = new ConexaoAPI();
    private static final Gson gson = new Gson();
    private static TaxasDeCambioResponse response;
    private static String moedaBase;

    private static void atualizarDadosAPI(){
        String dados = api.conectaAPI();
        response = gson.fromJson(dados, TaxasDeCambioResponse.class);
        moedaBase = response.getMoedaBase();
    }

    public static void exibirMenuPrincipal() {
        System.out.println("\n-------------------------------");
        System.out.println("----- CONVERSOR DE MOEDAS -----\n");
        System.out.println("Moeda Base Atual: " + moedaBase);
        System.out.println("------------------------------");
        System.out.println("1. Converter");
        System.out.println("2. Mudar Moeda Base");
        System.out.println("3. SAIR");
        System.out.println("------------------------------");
        System.out.println("Digite o número da opção desejada:");
    }

    public static void exibirMenuCodigos() {
        System.out.println("------------------------------");
        System.out.println("CÓDIGO: ARS - Peso Argentino");
        System.out.println("CÓDIGO: BOB - Boliviano boliviano");
        System.out.println("CÓDIGO: BRL - Real brasileiro");
        System.out.println("CÓDIGO: CLP - Peso chileno");
        System.out.println("CÓDIGO: COP - Peso colombiano");
        System.out.println("CÓDIGO: USD - Dólar americano");
        System.out.println("------------------------------");
    }

    public static void converterMoeda() {
        String continuarConversao;
        do {
            try {
                atualizarDadosAPI();

                //Conversao
                System.out.println("Digite o valor a ser convertido:");
                double valorConversao = Double.parseDouble(sc.nextLine().replace(',', '.'));
                exibirMenuCodigos();

                String moedaConversao;
                do {
                    System.out.println("Digite o CÓDIGO (XXX) da moeda para a qual deseja converter:");
                    moedaConversao = sc.nextLine().toUpperCase();
                    if (!(Moeda.contem(moedaConversao))) {
                        System.out.println("Código inválido! Por favor, digite um dos códigos de 3 letras acima.");
                    }
                } while (!(Moeda.contem(moedaConversao)));

                double taxaDeCambio = response.getTaxas().get(moedaConversao);
                double valorConvertido = valorConversao * taxaDeCambio;

                System.out.println("------------------------------");
                System.out.println("Conversão realizada:");
                System.out.printf("%.2f %s = %.2f %s\n", valorConversao, moedaBase, valorConvertido, moedaConversao);
                System.out.println("------------------------------");

                System.out.println("Deseja fazer a conversão de outro valor?");
                System.out.println("1. Sim");
                System.out.println("2. Não, voltar ao Menu");
                continuarConversao = sc.nextLine();

                while (!(continuarConversao.equals("1") || continuarConversao.equals("2"))) {
                    System.out.println("Entrada inválida! Digite o número de uma opção válida!");
                    continuarConversao = sc.nextLine();
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, digite um valor numérico inteiro ou separado por vírgula. Ex: 10 / 2,50");
                sc.nextLine();
                continuarConversao = "1";
            } catch (Exception e) {
                System.out.println("Erro inesperado durante a conversão. " + e.getMessage());
                continuarConversao = "2";
            }
        }
        while (continuarConversao.equals("1"));
    }

    public static void mudarMoedaBase() {
        String novaMoedaBase;
        do {
            exibirMenuCodigos();
            System.out.println("Moeda Base atual: " + moedaBase);
            System.out.println("Digite CÓDIGO (XXX) da NOVA Moeda Base:");
            novaMoedaBase = sc.nextLine().toUpperCase();
            if (Moeda.contem(novaMoedaBase)) {
                try { // Tenta obter os novos dados com a moeda atualizada
                    api.setCurrency(novaMoedaBase); // Define a nova moeda na classe da API
                    atualizarDadosAPI();
                    System.out.println("Moeda Base (" + moedaBase + ") atualizada com sucesso!");
                    return;
                } catch (RuntimeException e) {
                    System.out.println("Não foi possível atualizar os dados da API com a nova moeda. Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida! Digite um código válido!");
            }
        } while (true);
    }

    public static void main(String[] args) {

        //Tenta obter a conexão da API antes de inciar o programa principal
        try {
            atualizarDadosAPI();
        } catch (RuntimeException e) {
            System.out.println("Erro ao conectar com a API. Verique sua conexão e tente novamente.");
            return;
        }

        String opcaoEscolhida;

        //Inicio do programa
        do {
            exibirMenuPrincipal();
            opcaoEscolhida = sc.nextLine();

            switch (opcaoEscolhida) {
                case "1":
                    converterMoeda();
                    break;
                case "2":
                    mudarMoedaBase();
                    break;
                case "3":
                    System.out.println("-------------------------------");
                    System.out.println("Programa finalizado!");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha 1, 2 ou 3.");
            }
        }
            while (!opcaoEscolhida.equals("3"));
            sc.close();
    }

}