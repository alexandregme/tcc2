package com.helder.sga.control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
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
        PrintWriter pw = null;
        try {

            PrintWriter pwTabela = null;
            File f = new File(nomeDir);
            f.mkdir();

            pw = new PrintWriter(new FileWriter(nomeDir + "\\" + nomeTeste + ".txt"));
            pw.println(nomeTeste + " em " + f.getPath());
            pw.println();
            pw.println("Com os seguintes dados:");
            pw.println();

            for (String aux : nome) {
                pw.println(aux);
                pw.println();
            }

            DadosVetor[][] matriz = new DadosVetor[this.numeroExecucoes + 1][this.geracoes];

            int[] restCalc = new int[1];
            restCalc[0] = 0;

            for (int i = 0; i < this.numeroExecucoes && restCalc[0] < 1000 * this.funcao.limites.length / 2; i++) {
                DadosVetor[] dados = new DadosVetor[this.geracoes];
                SGA sga = new SGA(this.getDados());
                sga.executa(nomeDir, "T " + i + ".jpg", dados, restCalc);
                matriz[i] = dados;
            }

            // gravacao do relatorio
            
            for (int j = 0; j < this.geracoes; j++) {
                double a = 0;
                double b = 0;
                for (int i = 0; i < this.numeroExecucoes && matriz[i][j] != null; i++) {
                    a += matriz[i][j].media;
                    b += matriz[i][j].maior;
                }
                matriz[this.numeroExecucoes][j] = new DadosVetor(a, b);
            }

            pw.println("Tabela sobre as forças medias e as maiores forças.");
            pw.println();
            pw.println("Cada linha significa tem relação com uma geração e cada coluna um teste realizado.");
            pw.println();
            pw.println("Na ultima linha contem as somas de cada força");
            pw.println();
            pw.println();

            for (int j = 0; j < this.geracoes; j++) {

                pw.print("Geração " + j + " ->\t");

                for (int i = 0; i < this.numeroExecucoes + 1 && matriz[i][j] != null; i++) {

                    NumberFormat nf = NumberFormat.getInstance();
                    nf.setMaximumFractionDigits(3);

                    if (i == this.numeroExecucoes) {
                        pw.print("=\t");
                    }
                    
                    pw.print("|");

                    String m = nf.format(matriz[i][j].media);
                    int tamM = m.length();

                    for (int jj = 0; jj < 6 - tamM; jj++) {
                        m += " ";
                    }

                    pw.print(m);
                    pw.print(" , ");

                    m = nf.format(matriz[i][j].maior);
                    tamM = m.length();

                    for (int jj = 0; jj < 5 - tamM; jj++) {
                        m += " ";
                    }

                    pw.print(m);
                    pw.print("|");
                    pw.print("\t");
                }
                pw.println();
                pw.println();
            }
            pw.close();
        } catch (IOException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
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
