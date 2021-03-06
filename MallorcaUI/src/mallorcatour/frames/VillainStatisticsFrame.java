/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VillainStatisticsFrame.java
 *
 * Created on 12.12.2012, 23:24:29
 */
package mallorcatour.frames;

import java.io.File;

import javax.swing.DefaultListModel;

import mallorcatour.bot.villainobserver.VillainStatistics;
import mallorcatour.core.game.PokerStreet;
import mallorcatour.core.game.advice.Advice;
import mallorcatour.neural.core.PokerLearningExample;
import mallorcatour.neural.core.VectorInterpreter;
import mallorcatour.tools.DoubleUtils;
import mallorcatour.tools.Log;
import mallorcatour.tools.SerializatorUtils;
import mallorcatour.util.frames.FrameUtils;

/**
 *
 * @author Andrew
 */
public class VillainStatisticsFrame extends javax.swing.JFrame {

    private VillainStatistics villainStatistics;
    private DefaultListModel preflopExamplesModel;

    /** Creates new form VillainStatisticsFrame */
    public VillainStatisticsFrame() {
        initComponents();
        preflopExamplesModel = new DefaultListModel();
        preflopExamplesList.setModel(preflopExamplesModel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        aggressionFactorField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        wtsdField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        preflopExamplesList = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        handPlayedField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        aggressionFrequencyField = new javax.swing.JTextField();
        foldFrequencyField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("AF");

        jLabel2.setText("WTSD");

        preflopExamplesList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(preflopExamplesList);

        jButton1.setText("Calculate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Hands");

        jLabel4.setText("AFreq");

        jLabel5.setText("Fold Freq");

        jMenu1.setText("File");

        jMenuItem1.setText("Open...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("All villains");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(foldFrequencyField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aggressionFrequencyField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wtsdField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(aggressionFactorField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(handPlayedField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(aggressionFactorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(wtsdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(aggressionFrequencyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(foldFrequencyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(handPlayedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        String path = FrameUtils.openFileChooser(this, "./villains");
        if (path == null) {
            return;
        }
        villainStatistics = SerializatorUtils.load(path, VillainStatistics.class);
        preflopExamplesModel.clear();
        for (PokerLearningExample example : villainStatistics.getExamples()) {
            if (example.getSituation().getStreet() == PokerStreet.PREFLOP_VALUE) {
                preflopExamplesModel.addElement(example);
            }
        }
        aggressionFactorField.setText(DoubleUtils.digitsAfterComma(
                villainStatistics.getAggressionFactor(), 2) + "");
        wtsdField.setText(DoubleUtils.digitsAfterComma(villainStatistics.getWtsd(), 2) + "");
        aggressionFrequencyField.setText(DoubleUtils.digitsAfterComma(
                villainStatistics.getAggressionFrequency(), 2) + "");
        foldFrequencyField.setText(DoubleUtils.digitsAfterComma(
                villainStatistics.getFoldFrequency(), 2) + "");
        handPlayedField.setText(villainStatistics.getHandsCount() + "");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PokerLearningExample selectedExample = ((PokerLearningExample) preflopExamplesList.getSelectedValue());
        villainStatistics.getPrefloNeuralNetwork().setInput(new VectorInterpreter(false).createInput(selectedExample.getSituation()));
        villainStatistics.getPrefloNeuralNetwork().calculate();
        Advice advice = Advice.create(villainStatistics.getPrefloNeuralNetwork().getOutput());
        Log.d(advice + "");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        File dir = new File("./villains");
        for (File f : dir.listFiles()) {
            VillainStatistics villainStat = SerializatorUtils.load(f.getAbsolutePath(), VillainStatistics.class);
            Log.d("Aggression frequency: " + DoubleUtils.digitsAfterComma(villainStat.getAggressionFrequency(), 2)
                    + " Fold frequency: " + DoubleUtils.digitsAfterComma(villainStat.getFoldFrequency(), 2));
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new VillainStatisticsFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField aggressionFactorField;
    private javax.swing.JTextField aggressionFrequencyField;
    private javax.swing.JTextField foldFrequencyField;
    private javax.swing.JTextField handPlayedField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList preflopExamplesList;
    private javax.swing.JTextField wtsdField;
    // End of variables declaration//GEN-END:variables
}
