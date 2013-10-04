
package com.helder.sga.control;

import java.util.ArrayList;

/**
 * Esta classe define os dados que os metodos cruzamento irao utilizar
  */
public class DadosCruzamento {

    private ArrayList cruzAleatorio;// arraylist contendo os casais que foram selecionados aleatoriamente para cruzar
    private int[] npp; // vetor com as novas posicoes dos pais
    private int[] npm; // vetor com as novas posicoes das maes
    private int[] pai; // vetor com as posicoes dos pais 
    private int[] mae; // vetor com as posicoes das maes
    private int numeroVariaveis;
    private int numeroBits;
    private char[][] populacao;
    private char[][] novaPopulacao;

    /**
     * Construtor para a classe Dados Cruzamento
     * @param cruzAleatorio
     * @param npp
     * @param npm
     * @param pai
     * @param mae
     * @param numeroVariaveis
     * @param numeroBits
     * @param populacao
     * @param novaPopulacao 
     */
    public DadosCruzamento(ArrayList cruzAleatorio, int[] npp, int[] npm, int[] pai, int[] mae,
            int numeroVariaveis, int numeroBits,
            char[][] populacao, char[][] novaPopulacao) {

        this.cruzAleatorio = cruzAleatorio;
        this.npp = npp;
        this.npm = npm;
        this.pai = pai;
        this.mae = mae;
        this.numeroVariaveis = numeroVariaveis;
        this.numeroBits = numeroBits;
        this.populacao = populacao;
        this.novaPopulacao = novaPopulacao;
    }

    /**
     * ABAIXO HÁ APENAS OS METODOS DE ACESSO AS VARIAVEIS
     */
    
    
    /**
     * @return the cruzAleatorio
     */
    public ArrayList getCruzAleatorio() {
        return cruzAleatorio;
    }

    /**
     * @param cruzAleatorio the cruzAleatorio to set
     */
    public void setCruzAleatorio(ArrayList cruzAleatorio) {
        this.cruzAleatorio = cruzAleatorio;
    }

    /**
     * @return the npp
     */
    public int[] getNpp() {
        return npp;
    }

    /**
     * @param npp the npp to set
     */
    public void setNpp(int[] npp) {
        this.npp = npp;
    }

    /**
     * @return the npm
     */
    public int[] getNpm() {
        return npm;
    }

    /**
     * @param npm the npm to set
     */
    public void setNpm(int[] npm) {
        this.npm = npm;
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

    /**
     * @return the numeroVariaveis
     */
    public int getNumeroVariaveis() {
        return numeroVariaveis;
    }

    /**
     * @param numeroVariaveis the numeroVariaveis to set
     */
    public void setNumeroVariaveis(int numeroVariaveis) {
        this.numeroVariaveis = numeroVariaveis;
    }

    /**
     * @return the numeroBits
     */
    public int getNumeroBits() {
        return numeroBits;
    }

    /**
     * @param numeroBits the numeroBits to set
     */
    public void setNumeroBits(int numeroBits) {
        this.numeroBits = numeroBits;
    }

    /**
     * @return the populacao
     */
    public char[][] getPopulacao() {
        return populacao;
    }

    /**
     * @param populacao the populacao to set
     */
    public void setPopulacao(char[][] populacao) {
        this.populacao = populacao;
    }

    /**
     * @return the novaPopulacao
     */
    public char[][] getNovaPopulacao() {
        return novaPopulacao;
    }

    /**
     * @param novaPopulacao the novaPopulacao to set
     */
    public void setNovaPopulacao(char[][] novaPopulacao) {
        this.novaPopulacao = novaPopulacao;
    }
}
