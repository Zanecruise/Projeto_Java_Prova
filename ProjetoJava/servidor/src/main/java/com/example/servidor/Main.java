package com.example.servidor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {

            URL url = URI.create("http://127.0.0.1:5000/processar").toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            conn.setReadTimeout(120000);

            String jsonInput = """
                {
                    "valor": 50000.00,
                    "dataInicio": "02/04/2021",
                    "dataFim": "15/10/2024",
                    "indice": "IGP-M"
                }
                """;

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                String jsonResponse = scanner.useDelimiter("\\A").next();

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                objectMapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH));

                ResultadoDTO resultado = objectMapper.readValue(jsonResponse, ResultadoDTO.class);
                imprimirResultado(resultado);
            }

        } catch (Exception e) {
            System.err.println("Erro na requisição: " + e.getMessage());
        }
    }

    // Função para imprimir os dados formatados
    private static void imprimirResultado(ResultadoDTO resultado) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Resultado:");
        System.out.println("- Boleano: " + resultado.isSuccess());
        System.out.printf("- Numérico (2 casas decimais): %.2f%n", resultado.getValor_original());
        System.out.println("- Data Início: " + dateFormat.format(resultado.getData_inicio()));
        System.out.println("- Data Fim: " + dateFormat.format(resultado.getData_fim()));
        System.out.println("- Texto: " + resultado.getIndice());
        System.out.printf("- Numérico (2 casas decimais): %.2f%n", resultado.getValor_atualizado());
        System.out.printf("- Numérico (4 casas decimais): %s%%%n", resultado.getPercentual_final().replace(".", ","));
        System.out.printf("- Numérico (6 casas decimais): %.6f%n", resultado.getFator_multiplicacao());
        System.out.println("- Lista (Texto):");
        List<List<String>> percentuais = resultado.getPercentuais_mensais();
        for (List<String> percentual : percentuais) {
            String mesAno = percentual.get(0);
            String valor = percentual.get(1).replace(".", ","); // 🔥 Troca ponto por vírgula na exibição
            System.out.printf("  -- %s = %s%%%n", mesAno, valor);
        }
        System.out.printf("- Menor Percentual: %s%n", resultado.getMenor_percentual().replace(".", ","));
        System.out.printf("- Maior Percentual: %s%n", resultado.getMaior_percentual().replace(".", ","));
        System.out.println("- Mensagem: " + resultado.getMensagem());
    }
}
