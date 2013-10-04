package com.helder.sga.control;

import java.util.ArrayList;

public class TelaPrincipal extends javax.swing.JFrame {

    public TelaPrincipal() {
        initComponents();
        this.setLocation(120, 120);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jCheckBox1 = new javax.swing.JCheckBox();
        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        painelResultado = new javax.swing.JTextPane();
        jInternalFrame2 = new javax.swing.JInternalFrame();
        campoTextoDir = new javax.swing.JTextField();
        campoTextoArqTeste = new javax.swing.JTextField();
        labelLugarResposta = new javax.swing.JLabel();
        labelEscolhaArqTest = new javax.swing.JLabel();
        labelDT = new javax.swing.JTextField();
        botaoExecutarTeste = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelTitulo1 = new javax.swing.JLabel();
        labelResultados = new javax.swing.JTextField();
        labelTitulo2 = new javax.swing.JLabel();
        labelTitulo3 = new javax.swing.JLabel();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        jCheckBox1.setText("jCheckBox1");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Algoritmo Genético");
        setName("Algoritmo Genético"); // NOI18N
        setResizable(false);

        painelResultado.setEditable(false);
        painelResultado.setFont(new java.awt.Font("Courier New", 1, 16)); // NOI18N
        painelResultado.setText("No Arquivo de Teste escreva desta maneira as escolhas:\n\nTipos de Metodos de seleção:   \n- Média: media            \n- Roleta: roleta\n- Torneio: torneio\n\nTipos de Metodos de cruzamento:\n- Ponto Fixo: ponto_fixo\n- Variável: variavel\n\nTipos de Funções Objetivo:\n- Sphere Quadratic: SphereQuadratic\n- Rosenbrock: Rosenbrock\n- Sphere Model: SphereModel \n- Rastrigin: Rastrigin\n- Bohachevsky: Bohachevsky\n- Shelkel's Foxholes: ShelkelsFoxholes\n\n========================================================\n\n");
        jScrollPane1.setViewportView(painelResultado);

        jInternalFrame2.setTitle("Definição dos Testes");
        jInternalFrame2.setVisible(true);

        campoTextoDir.setText("Digite aqui o diretorio e a pasta para que sejam salvos os testes...");

        campoTextoArqTeste.setText("Digite aqui o arquivo de testes...");

        labelLugarResposta.setText("Escolha um diretório já criado onde será salvo os resultados:");

        labelEscolhaArqTest.setText("Escolha um arquivo de teste:");

        labelDT.setEditable(false);
        labelDT.setFont(new java.awt.Font("Tempus Sans ITC", 1, 15)); // NOI18N
        labelDT.setText("Selecione o arquivo de teste e o local onde será salvo os resultados.");
        labelDT.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        botaoExecutarTeste.setText("OK");
        botaoExecutarTeste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExecutarTesteActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tempus Sans ITC", 1, 12)); // NOI18N
        jLabel4.setText("Executar Teste");
        jLabel4.setDebugGraphicsOptions(javax.swing.DebugGraphics.FLASH_OPTION);
        jLabel4.setName("labelExecutarTeste"); // NOI18N
        jLabel4.setOpaque(true);

        jLabel1.setText("Quando terminar os testes o painel ao lado irá informar");

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoExecutarTeste, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelEscolhaArqTest, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelLugarResposta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelDT)
                    .addComponent(campoTextoArqTeste, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoTextoDir))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(122, 122, 122))
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame2Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(labelDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(labelEscolhaArqTest, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTextoArqTeste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(labelLugarResposta, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoTextoDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoExecutarTeste)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(43, 43, 43))
        );

        jLabel4.getAccessibleContext().setAccessibleName("labelExecutarTeste");

        labelTitulo1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        labelTitulo1.setText("Trabalho de Computação Evolucionária");

        labelResultados.setEditable(false);
        labelResultados.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        labelResultados.setText("Definições:");
        labelResultados.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        labelResultados.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        labelResultados.setRequestFocusEnabled(false);

        labelTitulo2.setFont(new java.awt.Font("Tempus Sans ITC", 1, 20)); // NOI18N
        labelTitulo2.setText("Algoritmo Genético\n");

        labelTitulo3.setFont(new java.awt.Font("Tempus Sans ITC", 1, 18)); // NOI18N
        labelTitulo3.setText("Autor: Hélder Silva de Faria");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTitulo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelTitulo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelTitulo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jInternalFrame2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelResultados, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelTitulo3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jInternalFrame2)))
                .addGap(62, 62, 62))
        );

        labelTitulo1.getAccessibleContext().setAccessibleName("labelTitulo");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo que é chamado ao clicar o botao executa
     *
     * @param evt
     */
    @SuppressWarnings("empty-statement")
    private void botaoExecutarTesteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExecutarTesteActionPerformed

        //gerador de teste
        GeradorTeste gt = new GeradorTeste(this.campoTextoArqTeste.getText());
        // a todos os testes
        ArrayList<Teste> al = gt.getTestes();

        //execucao de teste por teste
        for (int i = 0; i < al.size(); i++) {
            String nomeTeste = "teste" + i;
            al.get(i).executaTeste(this.campoTextoDir.getText() + "\\" + nomeTeste, nomeTeste);
        }

        this.painelResultado.setText(this.painelResultado.getText() + "FINAL DE TESTE!!!\nVá ao diretório de resultado informado ao software.");
    }//GEN-LAST:event_botaoExecutarTesteActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoExecutarTeste;
    private javax.swing.JTextField campoTextoArqTeste;
    private javax.swing.JTextField campoTextoDir;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField labelDT;
    private javax.swing.JLabel labelEscolhaArqTest;
    private javax.swing.JLabel labelLugarResposta;
    private javax.swing.JTextField labelResultados;
    private javax.swing.JLabel labelTitulo1;
    private javax.swing.JLabel labelTitulo2;
    private javax.swing.JLabel labelTitulo3;
    private javax.swing.JTextPane painelResultado;
    // End of variables declaration//GEN-END:variables
}
