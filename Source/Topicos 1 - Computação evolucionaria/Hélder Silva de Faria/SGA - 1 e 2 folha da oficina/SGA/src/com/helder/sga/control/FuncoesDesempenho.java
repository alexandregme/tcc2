package com.helder.sga.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FuncoesDesempenho {

    public static Funcao geraFuncao(String nomeFuncao, double[] limites) {

        if (nomeFuncao.equals("Sapateiro")) {
            return new FuncaoSapateiro(limites);
        } else if (nomeFuncao.equals("Cintaria")) {
            return new FuncaoCintaria(limites);
        } else if (nomeFuncao.equals("Fabricap1p2")) {
            return new FuncaoFabricap1p2(limites);
        } else if (nomeFuncao.equals("Dieta")) {
            return new FuncaoDieta(limites);
        } else if (nomeFuncao.equals("FabricaMoveis")) {
            return new FuncaoFabricaMoveis(limites);
        } else if (nomeFuncao.equals("Metalurgica")) {
            return new FuncaoMetalurgica(limites);
        } else if (nomeFuncao.equals("Refinaria")) {
            return new FuncaoRefinaria(limites);
        } else if (nomeFuncao.equals("Transporte")) {
            return new FuncaoRefinaria(limites);
        } else {
            return null;
        }
    }

    public static boolean hasNextFuncao(String nomeFuncao) {
        if (nomeFuncao.equals("Sapateiro")) {
            return true;
        } else if (nomeFuncao.equals("Cintaria")) {
            return true;
        } else if (nomeFuncao.equals("Fabricap1p2")) {
            return true;
        } else if (nomeFuncao.equals("Dieta")) {
            return true;
        } else if (nomeFuncao.equals("FabricaMoveis")) {
            return true;
        } else if (nomeFuncao.equals("Metalurgica")) {
            return true;
        } else if (nomeFuncao.equals("Refinaria")) {
            return true;
        } else if (nomeFuncao.equals("Transporte")) {
            return true;
        } else {
            return false;
        }
    }
}

abstract class Funcao {

    // vetor com os limites da variavel, posicao par é o limite inferior 
    // e posicao impar é limite superior
    double[] limites;
    String NOME_FUNCAO;
    // metodo para verificar as restricoes das funcoes
    public abstract boolean verificaRestricoes();

    public abstract double executa(double[] v);
}

class FuncaoSapateiro extends Funcao {

    public FuncaoSapateiro(double[] limites) {
        this.limites = limites;
        NOME_FUNCAO = "Sapateiro";
    }

    @Override
    public double executa(double[] v) {
        Double retorno = 5 * v[0] + 2 * v[1];
        ArrayList<Integer> al = new ArrayList();

        // adiciona punicoes
        al.add((v[0] / 6) + (v[1] / 5) <= 1 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));
        al.add(2 * v[0] + v[1] <= 6 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));

        // escolhe a punicao com o menor valor entre 0 e 'retorno''
        return Collections.min(al);

    }

    @Override
    public boolean verificaRestricoes() {

        // eh preciso ter duas variaveis
        if (limites.length / 2 != 2) {
            return false;
        }
        // verifica se o menor valor esta a esquerda e se eh diferente o menor e o maior valor para limites
        for (int i = 0; i < limites.length; i += 2) {
            if (limites[i] >= limites[i + 1]) {
                return false;
            }
        }

        // condicao da funcao
        if (limites[0] >= 0 && limites[2] >= 0) {
            return true;
        }

        return false;
    }
}

class FuncaoCintaria extends Funcao {

    public FuncaoCintaria(double[] limites) {
        this.limites = limites;
        NOME_FUNCAO = "Cintaria";


    }

    @Override
    public boolean verificaRestricoes() {
        // eh preciso ter duas variaveis
        if (limites.length / 2 != 2) {
            return false;
        }

        // verifica se o limite superior eh maior que o inferior
        for (int i = 0; i < limites.length; i += 2) {
            if (limites[i] >= limites[i + 1]) {
                return false;
            }
        }

        // variavel x maior do que 0
        if (limites[0] >= 0 && limites[2] >= 0) {
            return true;
        }

        return false;
    }

    @Override
    public double executa(double[] v) {
        ArrayList<Integer> al = new ArrayList();
        Double retorno = 0.0;

        /*
         * Como esta punicao é individual eu tirei 10 por cento 
         * do valor original 
         */
        if (v[0] > 400 && v[1] > 700) {
            retorno = 4 * (v[0] * 0.10) + 3 * (v[1] * 0.10);
        } else if (v[0] > 400) {
            retorno = 4 * (v[0] * 0.10) + 3 * v[1];
        } else if (v[1] > 700) {
            retorno = 4 * v[0] + 3 * (v[1] * 0.10);
        } else {
            retorno = 4 * v[0] + 3 * v[1];
        }

        al.add(2 * v[0] + v[1] <= 1000 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));
        al.add(v[0] + v[1] <= 800 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));

        return Collections.min(al);
    }
}

class FuncaoFabricap1p2 extends Funcao {

    public FuncaoFabricap1p2(double[] limites) {
        this.limites = limites;
        NOME_FUNCAO = "Fabricap1p2";
    }

    @Override
    public boolean verificaRestricoes() {
        // eh preciso ter duas variaveis
        if (limites.length / 2 != 2) {
            return false;
        }

        // verifica se o limite superior eh maior que o inferior
        for (int i = 0; i < limites.length; i += 2) {
            if (limites[i] >= limites[i + 1]) {
                return false;
            }
        }

        // variavel x maior do que 0
        if (limites[0] >= 0 && limites[2] >= 0) {
            return true;
        }

        return false;
    }

    @Override
    public double executa(double[] v) {

        Double retorno = 0.0;

        if (v[0] <= 40 & v[1] <= 50) {
            retorno = 1000 * (v[0] * 0.10) + 2000 * (v[1] * 0.10);
        } else if (v[0] <= 40) {
            retorno = 1000 * (v[0] * 0.10) + 2000 * v[1];
        } else if (v[1] <= 50) {
            retorno = 1000 * v[0] + 2000 * (v[1] * 0.10);
        }

        return 20 * v[0] + 40 * v[1] <= 1200 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1 < 0 ? 1 : retorno.intValue() - 1);
    }
}

class FuncaoDieta extends Funcao {

    public FuncaoDieta(double[] limites) {
        this.limites = limites;
        NOME_FUNCAO = "Dieta";
    }

    @Override
    public boolean verificaRestricoes() {
        // eh preciso ter duas variaveis
        if (limites.length / 2 != 4) {
            return false;
        }

        // verifica se o limite superior eh maior que o inferior
        for (int i = 0; i < limites.length; i += 2) {
            if (limites[i] >= limites[i + 1]) {
                return false;
            }
        }

        // variavel x maior do que 0
        if (limites[0] >= 0 && limites[2] >= 0 && limites[4] >= 0 && limites[6] >= 0) {
            return true;
        }

        return false;
    }

    @Override
    public double executa(double[] v) {

        ArrayList<Integer> al = new ArrayList();
        Double retorno = 2 * v[0] + 4 * v[1] + 1.5 * v[2] + v[3];

        al.add(2 * v[0] + 2 * v[1] + 10 * v[2] + 420 * v[3] >= 11 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));
        al.add(30 * v[0] + 20 * v[1] + 10 * v[2] + 30 * v[3] >= 250 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));

        return Collections.min(al);
    }
}

class FuncaoFabricaMoveis extends Funcao {

    public FuncaoFabricaMoveis(double[] limites) {
        this.limites = limites;
        NOME_FUNCAO = "FabricaMoveis";
    }

    @Override
    public boolean verificaRestricoes() {
        // eh preciso ter duas variaveis
        if (limites.length / 2 != 2) {
            return false;
        }

        // verifica se o limite superior eh maior que o inferior
        for (int i = 0; i < limites.length; i += 2) {
            if (limites[i] >= limites[i + 1]) {
                return false;
            }
        }

        // variavel x maior do que 0
        if (limites[0] >= 0 && limites[2] >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public double executa(double[] v) {
        ArrayList<Integer> al = new ArrayList();
        Double retorno = 200 * v[0] + 100 * v[1] - (10 * v[0] + 8 * v[1]) - (60 * v[0] + 40 * v[1]);

        al.add(0.05 * v[0] + 0.04 * v[1] <= 1 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));
        al.add(0.33 * v[0] + 0.025 * v[1] <= 1 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));
        al.add(0.05 * v[0] + 0.05 * v[1] <= 1 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));
        al.add(6 * v[0] + 4 * v[1] <= 120 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));

        return Collections.min(al);
    }
}

class FuncaoMetalurgica extends Funcao {

    public FuncaoMetalurgica(double[] limites) {
        this.limites = limites;
        NOME_FUNCAO = "Metalurgica";
    }

    @Override
    public boolean verificaRestricoes() {
        // eh preciso ter duas variaveis
        if (limites.length / 2 != 2) {
            return false;
        }

        // verifica se o limite superior eh maior que o inferior
        for (int i = 0; i < limites.length; i += 2) {
            if (limites[i] >= limites[i + 1]) {
                return false;
            }
        }

        // variavel x maior do que 0
        if (limites[0] >= 0 && limites[2] >= 0) {
            return true;
        }

        return false;
    }

    @Override
    public double executa(double[] v) {

        ArrayList<Integer> al = new ArrayList();
        Double retorno = 3000 * v[0] + 5000 * v[1];

        al.add(0.5 * v[1] + 0.2 * v[1] <= 16 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));
        al.add(0.25 * v[0] + 0.5 * v[1] <= 11 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));
        al.add(0.25 * v[0] + 0.5 * v[1] <= 15 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));

        return Collections.min(al);
    }
}

class FuncaoRefinaria extends Funcao {

    public FuncaoRefinaria(double[] limites) {
        this.limites = limites;
        NOME_FUNCAO = "Refinaria";
    }

    @Override
    public boolean verificaRestricoes() {
        if (limites.length / 2 != 3) {
            return false;
        }

        for (int i = 0; i < limites.length; i += 2) {
            if (limites[i] >= limites[i + 1]) {
                return false;
            }
        }

        if (limites[0] >= 0 && limites[2] >= 0 && limites[2] <= 600000 && limites[4] >= 0) {
            return true;
        }

        return false;
    }

    @Override
    public double executa(double[] v) {
        ArrayList<Integer> al = new ArrayList();
        Double retorno = 0.30 * v[0] + 0.25 * v[1] + 0.20 * v[2];

        al.add(0.22 * v[1] + 0.52 * v[1] + 0.74 * v[2] <= 9600000 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));
        al.add(0.50 * v[0] + 0.34 * v[1] + 0.20 * v[2] <= 4800000 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));
        al.add(0.28 * v[0] + 0.14 * v[1] + 0.06 * v[2] <= 2200000 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));

        return v[2] >= 16 * v[0] ? 0 : Collections.min(al);
    }
}

class FuncaoTransporte extends Funcao {

    public FuncaoTransporte(double[] limites) {
        this.limites = limites;
        NOME_FUNCAO = "Transporte";
    }

    @Override
    public boolean verificaRestricoes() {

        if (limites.length / 2 != 6) {
            return false;
        }

        for (int i = 0; i < limites.length; i += 2) {
            if (limites[i] >= limites[i + 1]) {
                return false;
            }
        }

        if (limites[0] >= 0 && limites[2] >= 0 && limites[4] >= 0 && limites[6] >= 0 && limites[8] >= 0 && limites[10] >= 0) {
            return true;
        }

        return false;
    }

    @Override
    public double executa(double[] v) {
        ArrayList<Integer> al = new ArrayList();
        Double retorno = 7 * v[0] + 4 * v[1] + 3 * v[2] + 3 * v[3] + v[4] + 2 * v[5];

        al.add(v[0] + v[1] + v[2] == 100 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));
        al.add(v[3] + v[4] + v[5] == 50 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));
        al.add(v[0] + v[3] == 80 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));
        al.add(v[1] + v[4] == 30 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));
        al.add(v[2] + v[5] == 40 ? retorno.intValue() : new Random().nextInt(retorno.intValue() - 1));

        retorno = ((Integer) Collections.min(al)).doubleValue();
        return 1 / (retorno + 0.0001);
    }
}
