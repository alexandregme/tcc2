package com.helder.sga.control;

import java.util.ArrayList;

/**
 * Esta classe define os dados que o metodo cruzamento ira utilizar
  */
public class DadosSelecao {

    private double[] resultadoAvaliacao;// vetor com os resultados de avaliacoes
    private ArrayList vetorMaiorForca; // vetor com as maiores forcas de cada geracao
    private ArrayList vetorMediaForca;// vetor com as media forcas de cada geracao
    private int geracao;// geracao atual 
    private int[] pai;
    private int[] mae;

    /**
     * Construtor para a classe Dados selecao
     * @param resultadoAvaliacao
     * @param vetorMaiorForca
     * @param vetorMediaForca
     * @param geracao
     * @param pai
     * @param mae 
     */
    public DadosSelecao(double[] resultadoAvaliacao, ArrayList vetorMaiorForca, ArrayList vetorMediaForca, int geracao, int[] pai, int[] mae) {
        this.resultadoAvaliacao = resultadoAvaliacao;
        this.geracao = geracao;
        this.mae = mae;
        this.pai = pai;
        this.vetorMaiorForca = vetorMaiorForca;
        this.vetorMediaForca = vetorMediaForca;
    }

    /**
     * ABAIXO HA APENAS OS METODOS DE ACESSO AS VARIAVEIS
     */
    /**
     * @return the resultadoAvaliacao
     */
    public double[] getResultadoAvaliacao() {
        return resultadoAvaliacao;
    }

    /**
     * @param resultadoAvaliacao the resultadoAvaliacao to set
     */
    public void setResultadoAvaliacao(double[] resultadoAvaliacao) {
        this.resultadoAvaliacao = resultadoAvaliacao;
    }

    /**
     * @return the vetorMaiorForca
     */
    public ArrayList getVetorMaiorForca() {
        return vetorMaiorForca;
    }

    /**
     * @param vetorMaiorForca the vetorMaiorForca to set
     */
    public void setVetorMaiorForca(ArrayList vetorMaiorForca) {
        this.vetorMaiorForca = vetorMaiorForca;
    }

    /**
     * @return the vetorMediaForca
     */
    public ArrayList getVetorMediaForca() {
        return vetorMediaForca;
    }

    /**
     * @param vetorMediaForca the vetorMediaForca to set
     */
    public void setVetorMediaForca(ArrayList vetorMediaForca) {
        this.vetorMediaForca = vetorMediaForca;
    }

    /**
     * @return the geracao
     */
    public int getGeracao() {
        return geracao;
    }

    /**
     * @param geracao the geracao to set
     */
    public void setGeracao(int geracao) {
        this.geracao = geracao;
    }

    /**
     * @return the pai
     */
    public int[] getPai() {
        return pai;
    }

    /**
     * @param pai the pai to set
     */
    public void setPai(int[] pai) {
        this.pai = pai;
    }

    /**
     * @return the mae
     */
    public int[] getMae() {
        return mae;
    }

    /**
     * @param mae the mae to set
     */
    public void setMae(int[] mae) {
        this.mae = mae;
    }
}
