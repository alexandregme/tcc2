package com.helder.sga.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para a geracao de Testes
 *
 */
public class GeradorTeste {

    private int n_exe;
    private int n_bit;
    private ArrayList<Double> pm;//array de probablidades de mutacao
    private ArrayList<Double> pc;//array de probabilidades de cruzamento
    private ArrayList<Integer> n_gen;// array de numero de geracoes
    private ArrayList<Integer> n_pop;// array de numero de populacao
    private ArrayList<Selecao> selecoes;// array contendo objetos de selecoes
    private ArrayList<Cruzamento> cruzamentos;// array contendo objetos de cruzamentos 
    private ArrayList<Funcao> funcoes;// array contendo objetos de funcoes
    private ArrayList<Teste> testes;// array contendo todos os testes montados apartir da combinacao
    public boolean possoTestar;

    {
        possoTestar = true;
        pm = new ArrayList();
        pc = new ArrayList();
        n_gen = new ArrayList();
        n_pop = new ArrayList();
        selecoes = new ArrayList();
        cruzamentos = new ArrayList();
        funcoes = new ArrayList();
        testes = new ArrayList();
    }

    public GeradorTeste(String nomeArq) {

        BufferedReader br = null;
        try {

            br = new BufferedReader(new FileReader(nomeArq));

            String linha = br.readLine();

            comentario:
            while (linha != null) {

                StringTokenizer st = new StringTokenizer(linha);
                String tokenAtual;
                while (st.hasMoreTokens()) {

                    tokenAtual = st.nextToken();
                    if (tokenAtual.equals("n_exe")) {
                        n_exe = Integer.parseInt(br.readLine());
                    } else if (tokenAtual.equals("n_bit")) {
                        n_bit = Integer.parseInt(br.readLine());
                    } else if (tokenAtual.equals("pm")) {
                        st = new StringTokenizer(br.readLine());
                        while (st.hasMoreTokens()) {
                            String str = st.nextToken();
                            if (str.equals("%") || str.charAt(0) == '%') {
                                break;
                            }
                            pm.add(Double.parseDouble(str));
                        }
                    } else if (tokenAtual.equals("pc")) {
                        st = new StringTokenizer(br.readLine());
                        while (st.hasMoreTokens()) {
                            String str = st.nextToken();
                            if (str.equals("%") || str.charAt(0) == '%') {
                                break;
                            }
                            pc.add(Double.parseDouble(str));
                        }
                    } else if (tokenAtual.equals("n_gen")) {
                        st = new StringTokenizer(br.readLine());
                        while (st.hasMoreTokens()) {
                            String str = st.nextToken();
                            if (str.equals("%") || str.charAt(0) == '%') {
                                break;
                            }
                            n_gen.add(Integer.parseInt(str));
                        }
                    } else if (tokenAtual.equals("n_pop")) {
                        st = new StringTokenizer(br.readLine());
                        while (st.hasMoreTokens()) {
                            String str = st.nextToken();
                            if (str.equals("%") || str.charAt(0) == '%') {
                                break;
                            }
                            n_pop.add(Integer.parseInt(str));
                        }
                    } else if (tokenAtual.equals("m_selecao")) {
                        st = new StringTokenizer(br.readLine());
                        while (st.hasMoreTokens()) {
                            String str = st.nextToken();
                            if (str.equals("%") || str.charAt(0) == '%') {
                                break;
                            }
                            selecoes.add(MetodosSelecao.geraSelecao(str));
                        }
                    } else if (tokenAtual.equals("m_cruzamento")) {
                        st = new StringTokenizer(br.readLine());
                        while (st.hasMoreTokens()) {
                            String str = st.nextToken();
                            if (str.equals("%") || str.charAt(0) == '%') {
                                break;
                            }
                            cruzamentos.add(MetodosCruzamento.geraCruzamento(str));
                        }
                    } else if (tokenAtual.equals("funcao_teste")) {
                        String nomeFuncao = new StringTokenizer(br.readLine()).nextToken();
                        do {
                            st = new StringTokenizer(br.readLine());
                            int tokens = st.countTokens();
                            double[] lim = new double[tokens];

                            for (int i = 0; i < tokens; i++) {
                                String str = st.nextToken();
                                if (str.equals("%") || str.charAt(0) == '%') {
                                    break;
                                }
                                lim[i] = Double.parseDouble(i % 2 == 0 ? str.replace('[', ' ')
                                        : str.replace(']', ' '));
                            }

                            Funcao f = FuncoesDesempenho.geraFuncao(nomeFuncao, lim);

                            if (f != null && f.verificaRestricoes()) {
                                funcoes.add(f);
                            }

                            br.readLine();
                            linha = br.readLine();
                            if (linha != null) {
                                nomeFuncao = new StringTokenizer(linha).nextToken();
                            } else {
                                break;
                            }
                        } while (FuncoesDesempenho.hasNextFuncao(nomeFuncao));

                        this.possoTestar = this.funcoes.size() > 0 ? true : false;

                        continue comentario;
                    } else if (tokenAtual.equals("%") || tokenAtual.charAt(0) == '%') {
                        linha = br.readLine();
                        continue comentario;
                    }
                }
                linha = br.readLine();
            }

            if (this.possoTestar) {

                for (Double alpm : pm) {
                    for (Double alpc : pc) {
                        for (Integer alng : n_gen) {
                            for (Integer alnp : n_pop) {
                                for (Selecao als : selecoes) {
                                    for (Cruzamento alc : cruzamentos) {
                                        for (Funcao alf : funcoes) {

                                            String v = "Limites das variaveis: ";
                                            for (int j = 0; j < alf.limites.length; j++) {
                                                v += j % 2 == 0 ? "[ " + alf.limites[j] : " , " + alf.limites[j] + " ]";
                                            }
                                            v += "";
                                            String[] nome = {"\nnumero de bits: " + this.n_bit + "\n",
                                                "\nnumero de geracoes: " + alng + "\n",
                                                "\nnumero de populacao: " + alnp + "\n",
                                                "\nprobabilidade de cruzamento: " + alpc + "\n",
                                                "\nprobabilidade de mutacao: " + alpm + "\n",
                                                "\nmetodo selecao: " + als.NOME_SELECAO + "\n",
                                                "\nmetodo cruzamento: " + alc.NOME_CRUZAMENTO + "\n",
                                                "\nfuncao: " + alf.NOME_FUNCAO + "\n",
                                                v};
                                            testes.add(new Teste(this.n_exe, this.n_bit, alng, alnp, alpc, alpm, nome, als, alc, alf));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(GeradorTeste.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(GeradorTeste.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<Teste> getTestes() {
        return testes;
    }
}