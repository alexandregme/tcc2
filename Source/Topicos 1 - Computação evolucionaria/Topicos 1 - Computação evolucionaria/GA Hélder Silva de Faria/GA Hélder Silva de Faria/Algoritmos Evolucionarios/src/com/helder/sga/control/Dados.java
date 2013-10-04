package com.helder.sga.control;

import java.util.ArrayList;

/**
 * Esta classe define os dados que o algoritmo genetico vai utilizar
 */
public class Dados {

    int numeroIndividuos; // numero da população
    int numeroBits;// numero total de bits para o individuo 
    int numeroGeracoes;// numero de gerações
    int numeroVariaveis;// numero de variaveis
    int nBitsVariavel;// numero de bits por variavel
    int[] pai;// vetor pai, a logica esta na posicao que indicara o casal
    int[] mae;// vetor mae
    double probabilidadeCruzamento;// probabilidade de cruzamento
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

    /**
     * Construtor para a classe Dados
     *
     * @param numeroDeBits
     * @param geracoes
     * @param numeroPopulacao
     * @param probabilidadeDeCruzamento
     * @param probabilidadeDeMutacao
     * @param selecao
     * @param cruzamento
     * @param funcao
     */
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
