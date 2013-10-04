package com.helder.sga.control;

/**
 * Classe para auxiliar o acesso as funcoes desempenho
 */
public class FuncoesDesempenho {

    public static Funcao geraFuncao(String nomeFuncao, double[] limites) {
        if (nomeFuncao.equals("SphereQuadratic")) {
            return new FuncaoSphereQuadratic(limites);
        } else if (nomeFuncao.equals("Rosenbrock")) {
            return new FuncaoRosenbrock(limites);
        } else if (nomeFuncao.equals("SphereModel")) {
            return new FuncaoSphereModel(limites);
        } else if (nomeFuncao.equals("Rastrigin")) {
            return new FuncaoRastrigin(limites);
        } else if (nomeFuncao.equals("Bohachevsky")) {
            return new FuncaoBohachevsky(limites);
        } else if (nomeFuncao.equals("ShelkelsFoxholes")) {
            return new FuncaoShelkelsFoxholes(limites);
        }
        return null;
    }

    /**
     * Funcao booleana usada na classe de geracao de teste que verifica se a
     * string passada como parametro é uma funcao
     *
     * @param nomeFuncao: string que será avaliada
     * @return retorna verdadeiro ou falso
     */
    public static boolean hasNextFuncao(String nomeFuncao) {
        if (nomeFuncao.equals("SphereQuadratic")
                || nomeFuncao.equals("Rosenbrock")
                || nomeFuncao.equals("SphereModel")
                || nomeFuncao.equals("Rastrigin")
                || nomeFuncao.equals("Bohachevsky")
                || nomeFuncao.equals("ShelkelsFoxholes")) {
            return true;
        } else {
            return false;
        }
    }
}

/**
 * Classe abstrata que define como serão as funcoes, variando apenas o metodo
 * que realiza a execucao delas.
 */
abstract class Funcao {

    /**
     * Vetor com os limites das variaveis, este vetor é percorrido de 2 em 2
     * pois o primeiro item do par é o limite inferior e o segundo item é o
     * limites superior
     */
    double[] limites;
    String NOME_FUNCAO; // nome da funcao

    /**
     * Metodo abstrato para o metodo que realiza os calculos da funcao e retorna
     * o resultado da mesma.
     *
     * @param v : vetor contendo variaveis decodificadas
     * @return O resultado da funcao
     */
    public abstract double executa(double[] v);
}

/**
 * Classe para a funcao FuncaoSphereQuadratic
 */
class FuncaoSphereQuadratic extends Funcao {

    {
        NOME_FUNCAO = "Sphere Quadratic";
    }

    /**
     * Construtor para classe FuncaoSphereQuadratic
     *
     * @param limites: vetor com os limites das variaveis
     */
    public FuncaoSphereQuadratic(double[] limites) {
        this.limites = limites;
    }

    /**
     * Metodo que executa a funcao
     *
     * @param x: vetor de variaveis decodificadas
     * @return
     */
    @Override
    public double executa(double[] x) {

        double resultado = 0.0;
        for (int i = 0; i < x.length; i++) {
            resultado += Math.pow(x[i], 2);
        }
        return resultado;
    }
}

class FuncaoRosenbrock extends Funcao {

    {
        NOME_FUNCAO = "Rosenbrock";
    }

    public FuncaoRosenbrock(double[] limites) {
        this.limites = limites;
    }

    public double executa(double[] x) {

        double resultado = 0.0;
        for (int i = 0; i < x.length; i++) {
            resultado += 100 * Math.pow(Math.pow(x[i], 2) - x[i] + 1, 2) + Math.pow(1 - x[i], 2);
        }
        return resultado;
    }
}

class FuncaoSphereModel extends Funcao {

    {
        NOME_FUNCAO = "Sphere Model";
    }

    public FuncaoSphereModel(double[] limites) {
        this.limites = limites;
    }

    @Override
    public double executa(double[] x) {
        double resultado = 0.0;
        for (int i = 0; i < x.length; i++) {
            resultado += Math.pow(x[i], 2);
        }
        return resultado;
    }
}

class FuncaoRastrigin extends Funcao {

    {
        NOME_FUNCAO = "Rastrigin";
    }

    public FuncaoRastrigin(double[] limites) {
        this.limites = limites;
    }

    @Override
    public double executa(double[] x) {

        double somatorio = 0.0;

        for (int i = 0; i < x.length; i++) {
            somatorio += Math.pow(x[i], 2) - (10 * Math.cos(2 * Math.PI * x[i]));
        }

        return 100 + somatorio;
    }
}

class FuncaoShelkelsFoxholes extends Funcao {

    {
        NOME_FUNCAO = "Shelkel's Foxholes";
    }
    int[][] a = new int[2][25];

    public FuncaoShelkelsFoxholes(double[] limites) {
        this.limites = limites;

        int[] aux = {-32, -16, 0, 16, 32, -32, -16, 0, 16, 32, -32, -16, 0, 16, 32, -32, -16, 0, 16, 32, -32, -16, 0, 16, 2};

        int[] aux2 = new int[25];

        for (int i = 0; i < aux2.length; i++) {
            if (i % 5 == 0) {
                aux2[i] = -32;
            } else if (i % 5 == 1) {
                aux2[i] = -16;
            } else if (i % 5 == 2) {
                aux2[i] = 0;
            } else if (i % 5 == 3) {
                aux2[i] = 16;
            } else if (i % 5 == 4) {
                aux2[i] = 32;
            }
        }

        a[0] = aux;
        a[1] = aux2;
    }

    @Override
    public double executa(double[] v) {

        double somatorio1 = 0.0;

        for (int i = 0; i < 25; i++) {
            double somatorio2 = 0.0;

            for (int j = 0; j < 2; j++) {
                somatorio2 += Math.pow(v[j] - a[j][i], 6);
            }

            somatorio1 += (1 / (i + somatorio2));
        }


        double retorno = (1 / ((1 / 500) + somatorio1)) - 0.9980038378;

        return retorno;
    }
}

class FuncaoBohachevsky extends Funcao {

    {
        this.NOME_FUNCAO = "Bohachevsky";
    }

    public FuncaoBohachevsky(double[] limites) {
        this.limites = limites;
    }

    @Override
    public double executa(double[] v) {

        double retorno = 0.0;

        for (int i = 0; i < v.length - 1; i++) {
            retorno += Math.pow(v[i], 2) + 2 * Math.pow(v[i + 1], 2) - 0.3 * Math.cos(3 * Math.PI * v[i]) - 0.4 * Math.cos(4 * Math.PI * v[i + 1]) + 0.7;
        }
        return retorno;

    }
}