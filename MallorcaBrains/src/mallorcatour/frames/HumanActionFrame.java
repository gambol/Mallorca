/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HumanActionFrame.java
 *
 * Created on 27.11.2012, 15:58:52
 */
package mallorcatour.frames;

import mallorcatour.core.game.Action;
import mallorcatour.core.game.interfaces.IPlayerGameInfo;
import mallorcatour.robot.humanadvisor.AbstractHumanAdvisor;
import mallorcatour.robot.humanadvisor.IActionHolder;

/**
 *
 * @author Andrew
 */
public class HumanActionFrame extends javax.swing.JFrame {

    private IPlayerGameInfo gameInfo;
    private IActionHolder actionHolder;

    /** Creates new form HumanActionFrame */
    public HumanActionFrame() {
        setUndecorated(true);
        initComponents();
    }

    public void setActionHolder(IActionHolder actionHolder) {
        this.actionHolder = actionHolder;
    }

    public void setGameInfo(IPlayerGameInfo gameInfo) {
        this.gameInfo = gameInfo;
        if (gameInfo.getHeroAmountToCall() == 0) {
            passiveButton.setText("Check");
            foldButton.setEnabled(false);
        } else {
            passiveButton.setText("Call");
            foldButton.setEnabled(true);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        foldButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        passiveButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        foldButton.setText("F");
        foldButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foldButtonActionPerformed(evt);
            }
        });

        jButton2.setText("All-in");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("0.5");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("1");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        passiveButton.setText("Check");
        passiveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passiveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(foldButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passiveButton)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(foldButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passiveButton)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void foldButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foldButtonActionPerformed
        actionHolder.actionGot(Action.foldAction());
    }//GEN-LAST:event_foldButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        actionHolder.actionGot(Action.allInAction());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        actionHolder.actionGot(AbstractHumanAdvisor.createHeroRaiseAction(gameInfo, 0.5));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        actionHolder.actionGot(AbstractHumanAdvisor.createHeroRaiseAction(gameInfo, 1));
    }//GEN-LAST:event_jButton4ActionPerformed

    private void passiveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passiveButtonActionPerformed
        if (gameInfo.getHeroAmountToCall() == 0) {
            actionHolder.actionGot(Action.checkAction());
        } else {
            actionHolder.actionGot(Action.callAction(gameInfo.getHeroAmountToCall()));
        }
    }//GEN-LAST:event_passiveButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton foldButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton passiveButton;
    // End of variables declaration//GEN-END:variables
}
