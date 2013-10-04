package com.helder.sga.control;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe para auxiliar o acesso aos metodos de cruzamento
 */
public class MetodosCruzamento {//TESTADO

    public static Cruzamento geraCruzamento(String nomeCruzamento) {
        if (nomeCruzamento.equals("variavel")) {
            return new CruzamentoVariavel();
        } else if (nomeCruzamento.equals("ponto_fixo")) {
            return new CruzamentoPontoFixo();
        }
        return null;
    }
}

/**
 * Classe abstrata que define como serão as classe que implementaram metodos de
 * cruzamento
 *
 * @author helder
 */
abstract class Cruzamento {//TESTADO

    ArrayList cruzAleatorio; // ArrayList  contendo os casais selecionados para cruzar
    int[] npp;
    int[] npm;
    int[] pai;
    int[] mae;
    int numeroVariaveis;
    int numeroBits;
    char[][] populacao;
    char[][] novaPopulacao;
    String NOME_CRUZAMENTO;

    /**
     * Metodo abstrato
     *
     * @param dc : dados de cruzamento
     */
    public abstract void cruzar(DadosCruzamento dc);

    /**
     * Metodo que adiciona os dados do cruzamento que foram passados como
     * parametro para o objeto this
     *
     * @param dc : dados de cruzamento passados por parametro
     */
    public void getDadosCruzamento(DadosCruzamento dc) {
        cruzAleatorio = dc.getCruzAleatorio();
        npp = dc.getNpp();
        npm = dc.getNpm();
        pai = dc.getPai();
        mae = dc.getMae();
        numeroVariaveis = dc.getNumeroVariaveis();
        numeroBits = dc.getNumeroBits();
        populacao = dc.getPopulacao();
        novaPopulacao = dc.getNovaPopulacao();
    }
}

/**
 * Classe que define o cruzamento por variavel
 */
class CruzamentoVariavel extends Cruzamento {

    {
        NOME_CRUZAMENTO = "Variável";
    }

    @Override
    public void cruzar(DadosCruzamento dc) {
        // acesso os dados de cruzamento
        this.getDadosCruzamento(dc);

        // vejo quantos bits por variavel serao
        int nbitspvariavel = this.numeroBits / this.numeroVariaveis;


        for (Object o : cruzAleatorio) {

            // posicao do casal que ira cruzar
            int posCasal = (Integer) o;

            // adquiro a posicao atual do casal 
            int pPop = pai[posCasal], mPop = mae[posCasal];
            // adquiro a nova posicao do casal na nova matriz
            int p = npp[posCasal], m = npm[posCasal];

            // eh realizada a troca neste item 

            boolean troca = new Random().nextBoolean();

            for (int nv = 0; nv > numeroVariaveis; nv++) {
                if (troca) {
                    int pos = nv * nbitspvariavel;
                    System.arraycopy(populacao[mPop], pos, novaPopulacao[p], pos, nbitspvariavel);
                    System.arraycopy(populacao[pPop], pos, novaPopulacao[m], pos, nbitspvariavel);
                    troca = false;
                } else {
                    troca = true;
                }
            }
        }
    }
}

/**
 * Classe que define como é o metodo de cruzamento em ponto fixo
 */
class CruzamentoPontoFixo extends Cruzamento {

    {
        NOME_CRUZAMENTO = "Ponto Fixo";
    }

    @Override
    public void cruzar(DadosCruzamento dc) {

        // pega os dados passsado como parametro e coloca em this
        this.getDadosCruzamento(dc);

        //realiza o cruzamento de todos que foram selecionados para cruzar
        for (Object o : cruzAleatorio) {

            // acesso a posicao do casal que ira cruzar
            int posCasal = (Integer) o;
            // seleciono um locus randomicamente
            int locus = new Random().nextInt(numeroBits);

            // adquiro a posicao atual do casal 
            int pPop = pai[posCasal];
            int mPop = mae[posCasal];
            // adquiro a nova posicao do casal na nova matriz
            int p = npp[posCasal];
            int m = npm[posCasal];

            // faz a troca de material genetico
            System.arraycopy(populacao[mPop], locus, novaPopulacao[p], locus, numeroBits - locus);
            System.arraycopy(populacao[pPop], locus, novaPopulacao[m], locus, numeroBits - locus);
        }
    }
}
