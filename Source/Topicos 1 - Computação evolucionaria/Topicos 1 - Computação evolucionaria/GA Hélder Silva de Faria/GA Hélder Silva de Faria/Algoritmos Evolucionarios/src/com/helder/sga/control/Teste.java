package com.helder.sga.control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que trata os testes
 */
public class Teste {

    private int numeroExecucoes;
    private int numeroDeBits;// numero de bits dos individuos
    private double probabilidadeDeCruzamento;
    private double probabilidadeDeMutacao;
    private int geracoes;
    private int numeroPopulacao;
    private Selecao selecao;
    private Cruzamento cruzamento;
    private Funcao funcao;
    private String[] nome; // array de string que contem o nome do teste

    /**
     * Cosntrutor para a classe Teste
     *
     * @param numeroExecucoes
     * @param numeroDeBits
     * @param geracoes
     * @param numeroPopulacao
     * @param probabilidadeDeCruzamento
     * @param probabilidadeDeMutacao
     * @param nome
     * @param selecao
     * @param cruzamento
     * @param funcao
     */
    public Teste(int numeroExecucoes, int numeroDeBits, int geracoes, int numeroPopulacao,
            double probabilidadeDeCruzamento, double probabilidadeDeMutacao,
            String[] nome, Selecao selecao, Cruzamento cruzamento, Funcao funcao) {

        this.nome = nome;
        this.numeroDeBits = numeroDeBits;
        this.numeroExecucoes = numeroExecucoes;
        this.probabilidadeDeCruzamento = probabilidadeDeCruzamento;
        this.probabilidadeDeMutacao = probabilidadeDeMutacao;
        this.geracoes = geracoes;
        this.numeroPopulacao = numeroPopulacao;
        this.selecao = selecao;
        this.cruzamento = cruzamento;
        this.funcao = funcao;
    }

    /**
     * Metodo que executa o teste
     *
     * @param nomeDir : nome do diretorio que será salvo os testes
     * @param nomeTeste : nome do teste
     */
    public void executaTeste(String nomeDir, String nomeTeste) {

        File f = new File(nomeDir);
        f.mkdir();

        PrintWriter pw = null;
        try {
            //gravacao de arquivo auxiliar
            pw = new PrintWriter(new FileWriter(nomeDir + "\\" + nomeTeste + ".txt"));
            pw.println(nomeTeste + " em " + f.getPath());
            pw.println();
            pw.println("Com os seguintes dados:");
            pw.println();
            for (String aux : nome) {
                pw.println(aux);
            }
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }

        //Repeticao que executa os testes
        for (int i = 0; i < this.numeroExecucoes; i++) {
            SGA sga = new SGA(this.getDados());
            sga.executa(nomeDir, "T " + i + ".jpg");
        }
    }

    /**
     * Metodo que monta os dados que serao passado para a classe SGA
     *
     * @return um objeto da classe dados
     */
    private Dados getDados() {
        return new Dados(numeroDeBits, geracoes, numeroPopulacao, probabilidadeDeCruzamento, probabilidadeDeMutacao, selecao, cruzamento, funcao);
    }
}