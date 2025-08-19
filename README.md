# 💱 Conversor de Moedas em Java

Este projeto é um **Conversor de moedas** desenvolvido em **Java**, que consome a **[ExchangeRate API](https://www.exchangerate-api.com/)** para realizar conversões entre diferentes moedas em tempo real.

## 🚀 Funcionalidades

* Consulta taxas de câmbio atualizadas em uma API externa
* Conversão entre diferentes moedas (ex: USD → BRL, EUR → JPY, etc.)
* Estrutura modular em classes:

  * `ConversorDeMoedas.java` → Classe principal que executa o programa
  * `ConexaoAPI.java` → Responsável pela comunicação com a API
  * `Moeda.java` → Modelo de dados para representar uma moeda
  * `TaxasDeCambioResponse.java` → Estrutura para mapear a resposta da API

## 🛠️ Tecnologias utilizadas

* **Java 17+** (ou versão compatível)
* **ExchangeRate API** (endpoint: `https://v6.exchangerate-api.com/v6/SUA_CHAVE/latest/USD`)
* **HttpClient** do Java para requisições HTTP

## 📂 Estrutura do projeto

```
.
├── ConversorDeMoedas.java
├── ConexaoAPI.java
├── Moeda.java
└── TaxasDeCambioResponse.java
```

## ▶️ Como executar

1. **Clone este repositório**

   ```bash
   git clone https://github.com/luisatriers/conversordemoedas.git
   cd conversordemoedas/src/br/com/conversordemoedas/principal
   ```

2. **Compile os arquivos Java**

   ```bash
   javac *.java
   ```

3. **Execute a aplicação**

   ```bash
   java ConversorDeMoedas.java
   ```

## ⚙️ Configuração da API

### 🔑 Obtendo a chave de API

1. Acesse o site [ExchangeRate API](https://www.exchangerate-api.com/).
2. Crie uma conta gratuita.
3. Após o cadastro, você receberá uma **chave de API pessoal**.

### 🔧 Configurando no projeto

No arquivo `ConexaoAPI.java`, substitua a chave padrão pela sua:

```java
String chaveAPI = "SUA_CHAVE_AQUI";
```

O endpoint usado será:

```
https://v6.exchangerate-api.com/v6/SUA_CHAVE/latest/USD
```

## 📖 Exemplo de uso

### Entrada

O programa solicita ao usuário:

* Moeda de origem (ex: `USD`)
* Moeda de destino (ex: `BRL`)
* Valor a ser convertido (ex: `100`)

### Saída esperada

```
------------------------------
Conversão realizada:
100.00 USD = 548.00 BRL
------------------------------
```

*(O valor varia de acordo com a cotação atual da API)*

## 📌 Próximos passos (possíveis melhorias)

* Criar interface gráfica (Swing ou JavaFX)
* Adicionar tratamento de erros mais detalhado
* Implementar cache local das taxas de câmbio
* Suporte para múltiplos idiomas

---

✍️ Desenvolvido por [Luisa Triers](https://github.com/luisatriers)
