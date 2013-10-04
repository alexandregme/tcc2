package com.helder.sga.control;

import com.helder.sga.view.Grafico;
import com.helder.sga.view.Ponto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Classe para Algoritmo Genético
 */
public class SGA {

    /**
     * Dados que sao usados no algoritmo genetico
     */
    private Dados dados;
    private int[] restCalc;

    /**
     * Construtor para a classe Algoritmo genetico
     *
     * @param dados : Teste que sera executado no algoritmo genetico
     */
    public SGA(Dados dados) {
        this.dados = dados;
    }

    /**
     * Metodo que cria a populacao de forma totalmente aleatoria, inserindo
     * caracteres que tem o valor de '0' ou '1'
     */
    private void criaPopulacao() {
        for (int i = 0; i < this.dados.numeroIndividuos; i++) {
            for (int j = 0; j < this.dados.numeroBits; j++) {
                this.dados.populacao[i][j] = Character.forDigit(new Random().nextInt(2), 2);
            }
        }
    }

    /**
     * Metodo que realiza a interpolacao
     *
     * @param x : valor real que sera interpolado
     * @return Retorna o valor real equivale a bits do intervalo
     */
    private double funcaoInterpolacao(double x, double limiteSuperior, double limiteInferior) {
        double l = Math.pow(2, this.dados.numeroBits / this.dados.numeroVariaveis) - 1;
        return (x * limiteSuperior - x * limiteInferior + limiteInferior * l) / l;
    }

    /**
     * Metodo que realiza a decodificacao de real para bits e salva os dados da
     * variavel na matriz resultadoDecodificacao
     */
    private void decodificar() {

        for (int i = 0; i < this.dados.numeroIndividuos; i++) {
            for (int k = 0; k < this.dados.numeroVariaveis * this.dados.nBitsVariavel; k += this.dados.nBitsVariavel) {

                int posAux = k / this.dados.nBitsVariavel;

                String str = String.valueOf(this.dados.populacao[i]);

                long l = Long.parseLong(str.substring(k, k + this.dados.nBitsVariavel), 2);

                this.dados.resultadoDecodificacao[i][posAux] = this.funcaoInterpolacao(l, this.dados.fx.limites[posAux], this.dados.fx.limites[posAux + 1]);

            }
        }
    }

    /**
     * Realiza os calculos de funcao objetivo e guarda os resultados no vetor
     * resultado Avaliacao
     */
    private void avaliarPopulacao() {

        for (int i = 0; i < this.dados.numeroIndividuos && this.restCalc[0] <= 1000 * this.dados.numeroVariaveis; i++) {
            this.restCalc[0]++;
            this.dados.resultadoAvaliacao[i] = this.dados.fx.executa(this.dados.resultadoDecodificacao[i]);
        }
    }

    /**
     * Realiza a selecao dos individuos da populavao
     *
     * @param geracaoAtual
     */
    private void selecionarIndividuos(int geracaoAtual) {
        dados.selecao.selecionar(new DadosSelecao(dados.resultadoAvaliacao, dados.vetorMaiorForca, dados.vetorMediaForca, geracaoAtual, dados.pai, dados.mae));
    }

    /**
     * Copia o conteudo da matriz populacao para a nova populacao de acordo com
     * os casais formados pelos vetores mae e pai
     *
     * @param npp : vetor com as posicoes dos novos pais (forma de realizar um
     * mapeamento de posicoes)
     * @param npm : vetor com as posicoes das novas maes (forma de realizar um
     * mapeamento de posicoes)
     */
    private void copiaCasais(int[] npp, int[] npm) {
        for (int i = 0; i < this.dados.pai.length; i++) {
            System.arraycopy(this.dados.populacao[this.dados.pai[i]], 0, this.dados.novaPopulacao[2 * i], 0, this.dados.numeroBits);
            System.arraycopy(this.dados.populacao[this.dados.mae[i]], 0, this.dados.novaPopulacao[2 * i + 1], 0, this.dados.numeroBits);
            npp[i] = 2 * i;
            npm[i] = 2 * i + 1;
        }
    }

    /**
     * Metodo que realiza o cruzamento
     */
    private void cruzarPopulacao() {

        int[] npp = new int[this.dados.pai.length];
        int[] npm = new int[this.dados.pai.length];

        this.copiaCasais(npp, npm);

        double pvaicruza = Math.round(this.dados.pai.length * this.dados.probabilidadeCruzamento);
        ArrayList cruzAleatorio = new ArrayList();
        Random r = new Random();

        //realiza a selecao dos casais que vao cruzar
        for (int j = 0; j < pvaicruza; j++) {
            int c = r.nextInt(this.dados.pai.length);
            while (cruzAleatorio.contains(c)) {
                c = r.nextInt(this.dados.pai.length);
            }
            cruzAleatorio.add(c);
        }

        // realiza a chamada do metodo de cruzamento 
        this.dados.cruzamento.cruzar(new DadosCruzamento(cruzAleatorio, npp, npm, dados.pai, dados.mae, dados.fx.limites.length / 2, dados.numeroBits, dados.populacao, dados.novaPopulacao));
    }

    private void mutarPopulacao() {
        /*
         // probabilidade de mutar 
         double probMutar = Math.round(this.dados.numeroIndividuos * this.dados.numeroBits * this.dados.probabilidadeMutacao);

         Random r = new Random();

         //vetor com os pontos que ja foram mutados 
         ArrayList<PontoXY> pxy = new ArrayList();

         //selecao totalmente randomica de cada ponto na matriz nova populacao que 
         for (int i = 0; i < probMutar; i++) {
         int l = (Integer) r.nextInt(this.dados.numeroIndividuos);
         int c = (Integer) r.nextInt(this.dados.numeroBits);
         PontoXY pontoxy = new PontoXY(l, c);

         while (pxy.contains(pontoxy)) {
         l = (Integer) r.nextInt(this.dados.numeroIndividuos);
         c = (Integer) r.nextInt(this.dados.numeroBits);
         pontoxy = new PontoXY(l, c);
         }
         pxy.add(pontoxy);
         // realiza a troca do bit
         dados.novaPopulacao[l][c] = Character.compare(this.dados.novaPopulacao[l][c], '1') == 0 ? '0' : '1';
         }
         * */
    }

    /**
     * Metodo que gera o grafico
     *
     * @param nomeDir: nome do diretorio que sera gravada
     * @param nomeArq: nome do arquivo que sera gravado
     */
    private void getResultado(String nomeDir, String nomeArq) {
        new Grafico(this.dados.vetorMaiorForca, this.dados.vetorMediaForca, nomeDir + "\\" + nomeArq);
    }

    /**
     * Metodo que executa o algoritimo genetico
     *
     * @param nomeDir: nome do diretorio que sera gravado o grafico
     * @param nomeArq: nome do arquivo que sera gravadoo
     */
    public void executa(String nomeDir, String nomeArq, DadosVetor[] individuos, int[] restCalc) {

        // controle de calculos de avaliacao ate mil 
        this.restCalc = restCalc;
        this.criaPopulacao();
        for (int i = 0; i < this.dados.numeroGeracoes; i++) {

            this.decodificar();
            this.avaliarPopulacao();

            // controle de calculos de avaliacao ate mil 
            if (this.restCalc[0] > 1000 * this.dados.numeroVariaveis) {
                break;
            }

            this.selecionarIndividuos(i);
            this.cruzarPopulacao();
            this.mutarPopulacao();
            this.dados.populacao = this.dados.novaPopulacao.clone(); //atualiza a matriz

        }

        this.getResultado(nomeDir, nomeArq);

        for (int i = 0; i < dados.vetorMaiorForca.size(); i++) {
            individuos[i] = new DadosVetor(Double.valueOf(((Ponto) dados.vetorMediaForca.get(i)).y), Double.valueOf(((Ponto) dados.vetorMaiorForca.get(i)).y));
        }
    }
}
