package com.example.servidor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ResultadoDTO {
    private boolean success;
    private double valor_original;
    private Date data_inicio;
    private Date data_fim;
    private String indice;
    private double valor_atualizado;
    private String percentual_final;
    private BigDecimal fator_multiplicacao;
    private List<List<String>> percentuais_mensais;
    private String menor_percentual;
    private String maior_percentual;
    private String mensagem;

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public double getValor_original() { return valor_original; }
    public void setValor_original(double valor_original) { this.valor_original = valor_original; }

    public Date getData_inicio() { return data_inicio; }
    public void setData_inicio(Date data_inicio) { this.data_inicio = data_inicio; }

    public Date getData_fim() { return data_fim; }
    public void setData_fim(Date data_fim) { this.data_fim = data_fim; }

    public String getIndice() { return indice; }
    public void setIndice(String indice) { this.indice = indice; }

    public double getValor_atualizado() { return valor_atualizado; }
    public void setValor_atualizado(double valor_atualizado) { this.valor_atualizado = valor_atualizado; }

    public String getPercentual_final() { return percentual_final; } 
    public void setPercentual_final(String percentual_final) { this.percentual_final = percentual_final; }

    public BigDecimal getFator_multiplicacao() { return fator_multiplicacao; }
    public void setFator_multiplicacao(BigDecimal fator_multiplicacao) { this.fator_multiplicacao = fator_multiplicacao; }

    public List<List<String>> getPercentuais_mensais() { return percentuais_mensais; }
    public void setPercentuais_mensais(List<List<String>> percentuais_mensais) { this.percentuais_mensais = percentuais_mensais; }

    public String getMenor_percentual() { return menor_percentual; }
    public void setMenor_percentual(String menor_percentual) { this.menor_percentual = menor_percentual; }

    public String getMaior_percentual() { return maior_percentual; }
    public void setMaior_percentual(String maior_percentual) { this.maior_percentual = maior_percentual; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
}
