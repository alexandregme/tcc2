package com.helder.sga.control;

import com.helder.sga.view.Ponto;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Classe para facilitar o acesso aos metodos de selecao
 */
public class MetodosSelecao {

    /**
     * Metodo estatico que gera um objeto selecao
     *
     * @param nomeSelecao: nome da selecao solicitada
     * @return objeto com a selecao solicitada
     */
    public static Selecao geraSelecao(String nomeSelecao) {
        if (nomeSelecao.equals("media")) {
            return new SelecaoMedia();
        } else if (nomeSelecao.equals("roleta")) {
            return new SelecaoRoleta();
        } else if (nomeSelecao.equals("torneio")) {
            return new SelecaoTorneio();
        }
        return null;
    }
}

/**
 * Classe que tratar questoes de comparacao de array de Objetos e implementa a
 * interface Comparator
 */
class ComparaObject implements Comparator {

    /**
     * Metodo que compara dois arrays de objetos e retorna um valor inteiro para
     * se referir a menor, maior ou igual
     *
     * @param o1: array de objetos
     * @param o2: array de objetos
     * @return valor inteiro
     */
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

/**
 * Classe abstrata que define como serao o metodos de selecao
 */
abstract class Selecao {

    DadosSelecao ds; // dados utilizados para realizar uma selecao 
    String NOME_SELECAO; // nome da selecao 

    /**
     * Metodo abstrato que realiza a selecao
     *
     * @param ds : dados necessarios para realizar a selecao
     */
    public abstract void selecionar(DadosSelecao ds);

    /**
     * Metodo que coloca informacoes de maior forca e media de resultado de
     * avaliacao dentro de um objeto ponto e logo apos dentro de um vetor.
     *
     * @param maiorForca: Maior forca de um individuo
     * @param mediaResultadoAvaliacao: Media da forca da populacao
     */
    public void putDadosArrayList(double maiorForca, double mediaResultadoAvaliacao) {
        ds.getVetorMaiorForca().add(new Ponto(ds.getGeracao(), maiorForca));
        ds.getVetorMediaForca().add(new Ponto(ds.getGeracao(), mediaResultadoAvaliacao));
    }

    /**
     * Metodo que pega o individuo de maior forca
     *
     * @return um vetor de Object contendo a maior forca e a posicao dele
     */
    public Object[] getMaiorForca() {
        Object[] x = {(Double.MIN_VALUE), new Integer(-1)};// menor forca possivel 
        for (Integer i = 0; i < ds.getResultadoAvaliacao().length; i++) {
            if (((Double) x[0]) < ds.getResultadoAvaliacao()[i]) {
                x[0] = (Double) ds.getResultadoAvaliacao()[i];
                x[1] = i;
            }
        }
        return x;
    }

    /**
     * Metodo que realiza a soma de todos as forcas da populacao
     *
     * @return retorn um valor real de todas as forcas
     */
    public double soma() {
        double soma = 0.0;
        for (int i = 0; i < ds.getResultadoAvaliacao().length; i++) {
            soma += ds.getResultadoAvaliacao()[i];
        }
        return soma;
    }

    /**
     * Metodo que realiza o casamento de individuos
     *
     * @param MaiorForca: array de object contendo o individuo com a maior forca
     * @param al: vetor com arrays de object selecionados para formar casais
     */
    public void formarCasais(Object[] MaiorForca, ArrayList<Object[]> al) {

        int fortes = al.size();// numero de individuos selecionados
        Random r = new Random();

        //fila de prioridades do maior para o menor
        PriorityQueue<Object[]> pq = new PriorityQueue(ds.getResultadoAvaliacao().length, new ComparaObject());

        // elitismo, é colocado o indice do individuo de maior valor
        pq.add(MaiorForca);

        // repeticao que monta os casais
        for (int i = 0; i < ds.getResultadoAvaliacao().length / 2; i++) {

            //Coloca individuos selecionados na fila de prioridades
            pq.add(al.get(r.nextInt(fortes)));
            pq.add(al.get(r.nextInt(fortes)));

            // forma o casal  
            ds.getPai()[i] = (Integer) ((Object[]) pq.poll())[1];
            ds.getMae()[i] = (Integer) ((Object[]) pq.poll())[1];
        }
    }
}

/**
 * Classe para o metodo de selecao da media
 */
class SelecaoMedia extends Selecao {

    /**
     * Construtor para classe SelecaoMedia
     */
    public SelecaoMedia() {
        NOME_SELECAO = "Media";
    }

    /**
     * Metodo que realiza a selecao
     *
     * @param dss : dados necessarios para realizar a selecao
     */
    @Override
    public void selecionar(DadosSelecao dss) {

        //Aquisicao de informacoes
        this.ds = dss;

        // calculo da media
        double mediaResultadoAvaliacao = soma() / ds.getResultadoAvaliacao().length;

        // obtem a maior forca
        Object[] x = this.getMaiorForca();

        //eh colocado no array os dados
        this.putDadosArrayList(((Double) x[0]), mediaResultadoAvaliacao);

        /**
         * Selecao dos individuos maiores que a media
         */
        ArrayList<Object[]> al = new ArrayList();
        for (int i = 0; i < ds.getResultadoAvaliacao().length; i++) {
            if (ds.getResultadoAvaliacao()[i] > mediaResultadoAvaliacao) {
                Object[] aux = {((Double) ds.getResultadoAvaliacao()[i]), ((Integer) i)};
                al.add(aux);
            }
        }
        //forma casais
        this.formarCasais(x, al);
    }
}

/**
 * Classe que implementa a selecao por roleta
 */
class SelecaoRoleta extends Selecao {

    /**
     * Construtor para a classe SelecaoRoleta
     */
    public SelecaoRoleta() {
        NOME_SELECAO = "Roleta";
    }

    /**
     * Metodo privado que realiza a roleta
     *
     * @param universo: universo de casos que podem cair e assim selecionar um
     * individuo
     * @param d: melhor individo (elitismo)
     * @return retorna um array com os selecionados
     */
    private ArrayList<Object[]> roleta(ArrayList<Object[]> universo, Object[] d) {
        ArrayList<Object[]> selecionados = new ArrayList();
        //elitismo
        selecionados.add(d);
        int xxx = ds.getResultadoAvaliacao().length - 1;
        int s = universo.size();
        Random r = new Random();

        while (selecionados.size() <= xxx) {
            selecionados.add(universo.get(r.nextInt(s))); //escolha de um individuo de forma totalmente randomica
        }
        return selecionados;
    }

    /**
     * Metodo que preenche o vetor de universo
     *
     * @param indComPorcetagem: vetor com os individuos e suas porcentagens
     * @return retorna vetor de universo preenchido
     */
    public ArrayList<Object[]> preencheUniverso(ArrayList<Object[]> indComPorcetagem) {

        ArrayList<Object[]> universo = new ArrayList();

        for (Object[] d : indComPorcetagem) {

            // adquire a porcentagem do individuo
            Double auxD = ((Double) d[0]);
            String str = ("" + auxD);

            // acha o index do ponto na string
            int auxPos = str.indexOf(".");

            /**
             * aqui ocorre uma verificacao no individuo para saber se o mesmo
             * esta entre 0 ou 1 ou maior que 1
             */
            CharSequence cs = str.subSequence(0, auxPos);

            String auxStr = cs.toString();

            Double aux = Double.parseDouble(auxStr);

            Integer i = -1;

            if (aux > 0) {
                int pos = str.indexOf(".");
                i = Double.parseDouble(str.substring(pos + 2)) >= 0.5
                        ? Integer.parseInt(str.subSequence(0, pos).toString()) + 1
                        : Integer.parseInt(str.subSequence(0, pos).toString());
            } else {
                int pos = str.indexOf(".") + 2;
                i = Double.parseDouble(str.substring(pos)) >= 0.5 ? 1 : 0;
                /**
                 * é feito isso para dar chance de o individuo ser escolhido,
                 * isto é preciso por pode haver a situação q todos os
                 * individuos tem chances menores q 1
                 */
            }

            //apartir do inteiro adquirido anterioremente eh colocado o individo n vez 
            for (int j = 0; j < i; j++) {
                universo.add(d);
            }
        }
        return universo;
    }

    /**
     * Metodo que realiza o calculoo da porcentagem
     *
     * @return o individuo com a porcentagens
     */
    public ArrayList<Object[]> calculoPorcentagem() {

        ArrayList<Object[]> indComPorcetagem = new ArrayList();
        for (int i = 0; i < ds.getResultadoAvaliacao().length; i++) {
            Object[] aux = {((Double) (100 * ds.getResultadoAvaliacao()[i]) / this.soma()), ((Integer) i)};
            indComPorcetagem.add(aux);
        }
        return indComPorcetagem;
    }

    @Override
    public void selecionar(DadosSelecao dss) {
        this.ds = dss;

        double[] resultadoAvaliacao = ds.getResultadoAvaliacao();
        //eh adquirito a soma de valores 
        double somaValores = soma();

        //adquiri o individuo de maior forca
        Object[] x = this.getMaiorForca();

        //eh colocado no array os dados
        this.putDadosArrayList(((Double) x[0]), somaValores / resultadoAvaliacao.length);

        // forma casais
        this.formarCasais(x, this.roleta(this.preencheUniverso(this.calculoPorcentagem()), x));
    }
}

/**
 * Classe que implementa a selecao por torneio e herda a selecaoroleta
 */
class SelecaoTorneio extends SelecaoRoleta {

    /**
     * Construtor para a classe
     */
    public SelecaoTorneio() {
        NOME_SELECAO = "Torneio";
    }

    /**
     * Metodo que forma casais
     *
     * @param universo : universo de chances de ser escolhido para formar casal
     */
    public void formaCasais(ArrayList<Object[]> universo) {

        for (int i = 0; i < this.ds.getPai().length; i++) {
            // escolhas randomicas
            Random r = new Random();
            Object[] rp = universo.get(r.nextInt(universo.size()));
            Object[] rp1 = universo.get(r.nextInt(universo.size()));
            Object[] rm = universo.get(r.nextInt(universo.size()));
            Object[] rm1 = universo.get(r.nextInt(universo.size()));

            // formacao de casais ganhando sempre o mais forte
            ds.getPai()[i] = (((Double) rp[0]) < ((Double) rp1[0]) ? (Integer) rp1[1] : (Integer) rp[1]);
            ds.getMae()[i] = (((Double) rm[0]) < ((Double) rm1[0]) ? (Integer) rm1[1] : (Integer) rp[1]);
        }
    }

    /**
     * Metodo que realiza a selecao
     *
     * @param dss : dados necessarios para realizar a selecao
     */
    @Override
    public void selecionar(DadosSelecao dss) {
        //aquisicao de dados
        this.ds = dss;

        // soma os valores 
        double somaValores = soma();

        // adquire o individuo de maior forca
        Object[] x = this.getMaiorForca();

        //coloca os dados sobre a forca do melhor individuo e a forca media da populacao
        this.putDadosArrayList(((Double) x[0]), somaValores / ds.getResultadoAvaliacao().length);

        // formacao de casais
        this.formaCasais(this.preencheUniverso(this.calculoPorcentagem()));
    }
}