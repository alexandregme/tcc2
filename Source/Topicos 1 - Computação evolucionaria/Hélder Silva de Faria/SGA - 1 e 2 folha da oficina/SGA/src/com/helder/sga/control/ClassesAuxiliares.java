package com.helder.sga.control;

import java.util.ArrayList;
import java.util.Comparator;

public class ClassesAuxiliares {
}

class ComparaObject implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Object[] aux = (Object[]) o1;
        Object[] aux2 = (Object[]) o2;

        Double i1 = (Double) aux[0];
        Double i2 = (Double) aux2[0];

        if (i1 > i2) {
            return -1;
        } else if (i1 < i2) {
            return 1;
        }
        return 0;
    }
}

class ComparaObject2 implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Object[] aux = (Object[]) o1;
        Object[] aux2 = (Object[]) o2;

        Double i1 = (Double) aux[0];
        Double i2 = (Double) aux2[0];

        if (i1 < i2) {
            return -1;
        } else if (i1 > i2) {
            return 1;
        }
        return 0;
    }
}


class Dados {

    int numeroIndividuos; // numero da população
    int numeroBits;// numero total de bits para o individuo 
    int numeroGeracoes;
    int numeroVariaveis;
    int nBitsVariavel;
    int[] pai;
    int[] mae;
    double probabilidadeCruzamento;
    double probabilidadeMutacao;// probabilidade de mutacao
    char[][] populacao;// matriz com a populacao
    char[][] novaPopulacao;// matriz com a nova populacao
    Funcao fx;// Objeto com a funcao objetivo
    Cruzamento cruzamento; //Objeto com o metodo de cruzamento
    Selecao selecao;// Objeto com o metodo de selecao
    ArrayList vetorMaiorForca;// vetor com os maiores resultados de cada geracao
    ArrayList vetorMediaForca;// vetor com a forca media de cada geracao
    double[][] resultadoDecodificacao;// matriz com a decodificacao das variaveis
    double[] resultadoAvaliacao;// vetor com os resultados da avaliacao

    public Dados(int numeroDeBits, int geracoes, int numeroPopulacao,
            double probabilidadeDeCruzamento, double probabilidadeDeMutacao,
            Selecao selecao, Cruzamento cruzamento, Funcao funcao) {

        this.nBitsVariavel = numeroDeBits;

        this.fx = funcao;
        this.numeroVariaveis = this.fx.limites.length / 2;// calculo do numero de variaveis

        this.numeroBits = this.nBitsVariavel * this.numeroVariaveis;

        this.numeroIndividuos = numeroPopulacao;
        this.probabilidadeCruzamento = probabilidadeDeCruzamento;
        this.probabilidadeMutacao = probabilidadeDeMutacao;
        this.numeroGeracoes = geracoes;

        vetorMaiorForca = new ArrayList();
        vetorMediaForca = new ArrayList();

        this.selecao = selecao;
        this.cruzamento = cruzamento;

        this.novaPopulacao = new char[this.numeroIndividuos][this.numeroBits];
        this.populacao = new char[this.numeroIndividuos][this.numeroBits];
        // vetores que significaram os pais, cada posicao tem-se referencia a um casal
        this.pai = new int[this.numeroIndividuos / 2];
        this.mae = new int[this.numeroIndividuos / 2];

        this.resultadoDecodificacao = new double[this.numeroIndividuos][this.numeroVariaveis];
        this.resultadoAvaliacao = new double[this.numeroIndividuos];
    }
}

class DadosSelecao {

    private double[] resultadoAvaliacao;// vetor com os resultados de avaliacoes
    private ArrayList vetorMaiorForca; // vetor com as maiores forcas de cada geracao
    private ArrayList vetorMediaForca;// vetor com as media forcas de cada geracao
    private int geracao;// geracao atual 
    private int[] pai;
    private int[] mae;

    public DadosSelecao(double[] resultadoAvaliacao, ArrayList vetorMaiorForca, ArrayList vetorMediaForca, int geracao, int[] pai, int[] mae) {
        this.resultadoAvaliacao = resultadoAvaliacao;
        this.geracao = geracao;
        this.mae = mae;
        this.pai = pai;
        this.vetorMaiorForca = vetorMaiorForca;
        this.vetorMediaForca = vetorMediaForca;
    }

    public double[] getResultadoAvaliacao() {
        return resultadoAvaliacao;
    }

    public void setResultadoAvaliacao(double[] resultadoAvaliacao) {
        this.resultadoAvaliacao = resultadoAvaliacao;
    }

    public ArrayList getVetorMaiorForca() {
        return vetorMaiorForca;
    }

    public void setVetorMaiorForca(ArrayList vetorMaiorForca) {
        this.vetorMaiorForca = vetorMaiorForca;
    }

    public ArrayList getVetorMediaForca() {
        return vetorMediaForca;
    }

    public void setVetorMediaForca(ArrayList vetorMediaForca) {
        this.vetorMediaForca = vetorMediaForca;
    }

    public int getGeracao() {
        return geracao;
    }

    public void setGeracao(int geracao) {
        this.geracao = geracao;
    }

    public int[] getPai() {
        return pai;
    }

    public void setPai(int[] pai) {
        this.pai = pai;
    }

    public int[] getMae() {
        return mae;
    }

    public void setMae(int[] mae) {
        this.mae = mae;
    }
}

class DadosCruzamento {

    private ArrayList cruzAleatorio;// arraylist contendo os casais que foram selecionados aleatoriamente para cruzar
    private int[] npp; // vetor com as novas posicoes dos pais
    private int[] npm; // vetor com as novas posicoes das maes
    private int[] pai; // vetor com as posicoes dos pais 
    private int[] mae; // vetor com as posicoes das maes
    private int numeroVariaveis;
    private int numeroBits;
    private char[][] populacao;
    private char[][] novaPopulacao;

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

    public ArrayList getCruzAleatorio() {
        return cruzAleatorio;
    }

    public void setCruzAleatorio(ArrayList cruzAleatorio) {
        this.cruzAleatorio = cruzAleatorio;
    }

    public int[] getNpp() {
        return npp;
    }

    public void setNpp(int[] npp) {
        this.npp = npp;
    }

    public int[] getNpm() {
        return npm;
    }

    public void setNpm(int[] npm) {
        this.npm = npm;
    }

    public int[] getPai() {
        return pai;
    }

    public void setPai(int[] pai) {
        this.pai = pai;
    }

    public int[] getMae() {
        return mae;
    }

    public void setMae(int[] mae) {
        this.mae = mae;
    }

    public int getNumeroVariaveis() {
        return numeroVariaveis;
    }

    public void setNumeroVariaveis(int numeroVariaveis) {
        this.numeroVariaveis = numeroVariaveis;
    }

    public int getNumeroBits() {
        return numeroBits;
    }

    public void setNumeroBits(int numeroBits) {
        this.numeroBits = numeroBits;
    }

    public char[][] getPopulacao() {
        return populacao;
    }

    public void setPopulacao(char[][] populacao) {
        this.populacao = populacao;
    }

    public char[][] getNovaPopulacao() {
        return novaPopulacao;
    }

    public void setNovaPopulacao(char[][] novaPopulacao) {
        this.novaPopulacao = novaPopulacao;
    }
}

class DadosVetor {

    double media;
    double maior;

    public DadosVetor(double media, double maior) {
        this.maior = maior;
        this.media = media;
    }
}

class PontoXY {

    private int x;
    private int y;

    public PontoXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
