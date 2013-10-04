package com.helder.sga.view;

/**
 * Classe que representa um ponto
 */
public class Ponto {

    int x;// inteiro referente a geracao
    double y;// real referente ao valor do individuo / valor medio da populacao

    /**
     * Construtor para a classe ponto
     *
     * @param x: inteiro referente a geracao 
     * @param y: real referente ao valor do individuo/valor medio da populacao
     */
    public Ponto(int x, double y) {
        this.x = x;
        this.y = y;
    }
}
