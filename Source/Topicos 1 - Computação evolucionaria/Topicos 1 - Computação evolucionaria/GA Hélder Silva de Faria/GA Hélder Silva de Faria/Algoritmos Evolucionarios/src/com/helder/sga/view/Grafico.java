package com.helder.sga.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Classe que faz a geracao e gravado do grafico no disco
 */
public class Grafico {

    /**
     * Cosntrutor da classe grafico
     *
     * @param p: ArrayList contendo os valores do indidividuos mais fortes de
     * todas as geracoes
     * @param p2: Arryalist contendo os valores da media dos individuos de toda
     * a populacao que estao em todas as geracoes
     * @param end: String contendo o endereco que sera salvo os arquivos
     */
    public Grafico(ArrayList p, ArrayList p2, String end) {
        String titulo = "Força do individuo mais forte X Média de força dos individuos";

        /**
         * series com os valores que dos arrayslist
         */
        XYSeries maisfortes = new XYSeries("Individuo mais forte");
        XYSeries forcaMedia = new XYSeries("Força média dos individuos");

        for (int i = 0; i < p.size(); i++) {
            Ponto pxy = ((Ponto) p.get(i));
            maisfortes.add(new XYDataItem(pxy.y, pxy.x));
        }

        for (int i = 0; i < p2.size(); i++) {
            Ponto pxy = ((Ponto) p2.get(i));
            forcaMedia.add(new XYDataItem(pxy.y, pxy.x));
        }

        // criacao de um objeto para reunir as series 
        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(maisfortes);
        dataset.addSeries(forcaMedia);

        //criacao de um quadro com os dados, titulos, e geracoes
        JFreeChart chart = ChartFactory.createScatterPlot(titulo, "Força", "Gerações", dataset, PlotOrientation.HORIZONTAL, true, true, true);
        try {
            ChartUtilities.saveChartAsJPEG(new File(end), chart, 1024, 800);// salva o grafico no disco
        } catch (IOException ex) {
            Logger.getLogger(Grafico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
