/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MFrame.java
 *
 * Created on 28.09.2012, 15:53:07
 */
package mallorcatour.hhparser;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import javax.swing.JOptionPane;
import mallorcatour.core.bot.LimitType;
import mallorcatour.game.advice.Advice;
import mallorcatour.util.FrameUtils;
import mallorcatour.game.hand.BaseHandHandler;
import mallorcatour.game.hand.BaseTournamentHandler;
import mallorcatour.game.hand.Hand;
import mallorcatour.game.hand.HandManager;
import mallorcatour.game.hand.Tournament;
import mallorcatour.game.situation.HandParser;
import mallorcatour.game.situation.NoStrengthSituationHandler;
import mallorcatour.game.situation.PotSituationHandler;
import mallorcatour.game.situation.ProfitSituationHandler;
import mallorcatour.game.situation.SituationHandler;
import mallorcatour.interfaces.ISituationHandler;
import mallorcatour.neuronetworkwrapper.PokerExamples;
import mallorcatour.neuronetworkwrapper.PokerLearningExample;
import mallorcatour.util.ExecutorUtils;
import mallorcatour.util.Log;
import mallorcatour.util.OnExceptionListener;
import mallorcatour.util.Pair;
import mallorcatour.util.ReaderUtils;
import mallorcatour.util.SerializatorUtils;

/**
 *
 * @author Andrew
 */
public class HHParserFrame extends javax.swing.JFrame {

    private ExecutorService executor;

    /** Creates new form MFrame */
    public HHParserFrame() {
        initComponents();
        executor = ExecutorUtils.newSingleThreadExecutor(OnExceptionListener.EMPTY);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton3.setText("Calculate pot changes");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("Parse situations no strength");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Process PS debug");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu1.setText("PA");

        jMenuItem1.setText("Tournaments' HH -> situations");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Handhistory -> situations");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Handhistory (no advices) -> situations");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem5.setText("Calculate statistics");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem7.setText("Calculate profit");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("PS");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem4.setText("Calculate statistics");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem6.setText("Parse and log");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(67, 67, 67)
                        .add(jButton3))
                    .add(layout.createSequentialGroup()
                        .add(56, 56, 56)
                        .add(jButton1))
                    .add(layout.createSequentialGroup()
                        .add(79, 79, 79)
                        .add(jButton2)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(7, 7, 7)
                .add(jButton2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        calculatePotChanges();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        executor.submit(new Runnable() {

            public void run() {
                parseTournamentsWithHandler(new SituationHandler(LimitType.NO_LIMIT));
            }
        });
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        executor.submit(new Runnable() {

            public void run() {
                parseHandsWithHandler(new SituationHandler(LimitType.FIXED_LIMIT));
            }
        });
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        executor.submit(new Runnable() {

            public void run() {
                parseHandsWithoutAdvices("Waterhouse", new SituationHandler(LimitType.FIXED_LIMIT));
            }
        });
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        String heroName = "grandtorino";
        showStats(heroName);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void showStats(String heroName) {
        String[] pathes = FrameUtils.openFileChooserForMultipleFiles(this, "");
        for (String path : pathes) {
            BaseHandHandler handHandler = new BaseHandHandler();
            PAHHParser.parseHandHistory(path, handHandler);
            List<Hand> hands = handHandler.buildHands();
            Log.d(path);
            Log.d("Hands: " + hands.size());
            String villainName = HandManager.getVillainName(hands.get(0), heroName);
            Log.d(heroName + ":");
            Log.d("Vpip: " + HandManager.calculateVpip(hands, heroName));
            Log.d("Pfr: " + HandManager.calculatePfr(hands, heroName));
            Log.d("Postflop AF: " + HandManager.calculatePostflopAF(hands, heroName));
            Log.d("Postflop Aggression freq.: "
                    + HandManager.calculatePostflopAggressionFrequency(hands, heroName));
            Log.d("Postflop Fold freq.: " + HandManager.calculatePostflopFoldFrequency(hands, heroName));
            Log.d("WTSD: " + HandManager.calculateWTSD(hands, heroName));
            Log.d(villainName + ":");
            Log.d("Vpip: " + HandManager.calculateVpip(hands, villainName));
            Log.d("Pfr: " + HandManager.calculatePfr(hands, villainName));
            Log.d("Postflop AF: " + HandManager.calculatePostflopAF(hands, villainName));
            Log.d("Postflop Aggression freq.: "
                    + HandManager.calculatePostflopAggressionFrequency(hands, villainName));
            Log.d("Postflop Fold freq.: "
                    + HandManager.calculatePostflopFoldFrequency(hands, villainName));
            Log.d("WTSD: " + HandManager.calculateWTSD(hands, villainName));
        }
    }
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        String heroName = "Andrew";
        showStats(heroName);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        String path = FrameUtils.openFileChooser(this, "");
        PSHHParser.parseHandHistory(path, new LoggingTournamentHandler());
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String path = FrameUtils.openFileChooser(this, "");
        if (path == null) {
            return;
        }
        BaseHandHandler handHandler = new BaseHandHandler();
        PAHHParser.parseHandHistory(path, handHandler);
        List<PokerLearningExample> examples = NNConverter.parseLocalSituationsWithoutAdvices(handHandler.buildHands(), "Andrew", new NoStrengthSituationHandler(LimitType.FIXED_LIMIT));
        path = FrameUtils.openFileChooser(this, "./");
        if (path == null) {
            return;
        }
        Log.d("Count of examples in HH file = " + examples.size());
        List<Advice> advices = AdviceReader.readAdvices(path);
        Log.d("Count of advices in advice file = " + advices.size());

        for (int i = 0; i < examples.size(); i++) {
            if (!isSame(advices.get(i), examples.get(i).getAdvice())) {
                Log.d("Error in " + i + " advice!");
                Log.d("Advice: " + advices.get(i));
                Log.d("Action: " + examples.get(i).getAdvice());
                throw new RuntimeException();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        String path = FrameUtils.openFileChooser(this, "");
        if (path == null) {
            return;
        }
        BaseHandHandler handHandler = new BaseHandHandler();
        PAHHParser.parseHandHistory(path, handHandler);
        String heroName = "Andrew";
        double IPProfit = 0, OOPProfit = 0;
        double exIPProfit = 0, exOOPProfit = 0;
        int countIP = 0, countOOP = 0;
        int i = 0;
        for (Hand hand : handHandler.buildHands()) {
            Log.d(i++ + "");
            ProfitSituationHandler handler = new ProfitSituationHandler();
            new HandParser().parse(hand, heroName, handler);
            if (handler.hasFlop()) {
                if (hand.getButtonPlayer().equals(heroName)) {
                    IPProfit += handler.getProfit();
                    exIPProfit += handler.getExpectedProfit();
                    countIP++;
                } else {
                    OOPProfit += handler.getProfit();
                    exOOPProfit += handler.getExpectedProfit();
                    countOOP++;
                }
            }
        }
        Log.d("Hands IP: " + countIP);
        Log.d("Hands OOP: " + countOOP);
        Log.d("IP profit: " + IPProfit);
        Log.d("OOP profit: " + OOPProfit);
        Log.d("IP profit by hand: " + IPProfit / countIP);
        Log.d("OOP profit by hand: " + OOPProfit / countOOP);
        Log.d("Expected IP profit: " + exIPProfit);
        Log.d("Expected OOP profit: " + exOOPProfit);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String[] pathes = FrameUtils.openFileChooserForMultipleFiles(this, "./");
        for (String path : pathes) {
            BufferedReader reader = ReaderUtils.initReader(path);
            String buffer;
            double profit = 0;
            int count = 0;
            int line = 0;
            while ((buffer = ReaderUtils.readLine(reader)) != null) {
                line++;
                if (buffer.contains("Map<Action, Profit>")) {
                    profit += findProfit(path, buffer, line);
                    count++;
                }
            }
            Log.d("-----------------------");
            Log.d("File: " + path + "\n Summary profit: " + profit
                    + " Actions: " + count + "\n Average profit: " + profit / count);
            Log.d("-----------------------");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private double findProfit(String path, String buffer, int line) {
        double result = -Double.MAX_VALUE;
        double percent = 0.05;
        int from = 0, to = 0;
        while (true) {
            from = buffer.indexOf("=", to);
            if (from == -1) {
                break;
            }
            to = buffer.indexOf(",", from);
            if (to == -1) {
                to = buffer.indexOf("}", from);
            }
            if (to == -1) {
                throw new RuntimeException();
            }
            double value = Double.parseDouble(buffer.substring(from + 1, to));
            if (value > result) {
                if (value > 0 && result > 0) {
                    if (Math.abs(value - result) / result < percent) {
                        Log.d("!!!!!!!!!!!!!!!!!!!!!!!");
                        Log.d("In file " + path + " on line " + line + " were finded sit");
                        Log.d(result + " " + value);
                        Log.d("!!!!!!!!!!!!!!!!!!!!!!!");
                    }

                }
                result = value;
            }
        }
        return result;
    }

    private boolean isSame(Advice one, Advice two) {
        if (two.getAggressivePercent() == 100 && one.getAggressivePercent() == 0) {
            return false;
        }
        if (two.getPassivePercent() == 100 && one.getPassivePercent() == 0) {
            return false;
        }
        if (two.getFoldPercent() == 100 && one.getFoldPercent() == 0) {
            return false;
        }
        return true;
    }

    private void parseTournamentsWithHandler(ISituationHandler situationHandler) {
        String[] pathes = FrameUtils.openFileChooserForMultipleFiles(this, "");
        if (pathes == null) {
            return;
        }
        List<PokerLearningExample> allExamples = new ArrayList<PokerLearningExample>();

        for (String path : pathes) {
            BaseTournamentHandler handler = new BaseTournamentHandler(new BaseHandHandler());
            PAHHParser.parseTournamentHH(path, handler);
            long start = System.currentTimeMillis();
            int count = 0;
            for (Tournament tournament : handler.buildTournaments()) {
                List<PokerLearningExample> examples = NNConverter.localSituationsToFile(
                        tournament, "./advices_sng", situationHandler);
                if (examples != null) {
                    allExamples.addAll(examples);
                    count++;
                }
            }
            String pathForSerialization = path.replace(".txt", ".pkr");
            SerializatorUtils.save(pathForSerialization, new PokerExamples(allExamples));
            String message = "There were parsed " + count + " tournaments from file "
                    + path + "\nTime: " + (System.currentTimeMillis() - start) + " ms"
                    + "\nExamples count: " + allExamples.size();
            JOptionPane.showMessageDialog(this, message);
            allExamples.clear();
            Log.d("Totally parsed " + count + " tournaments.");
            Log.d("Time " + (System.currentTimeMillis() - start) + " ms");
        }
    }

    private void parseHandsWithHandler(ISituationHandler situationHandler) {
        String[] pathes = FrameUtils.openFileChooserForMultipleFiles(this, "./../..");
        if (pathes == null) {
            return;
        }
        for (String path : pathes) {
            BaseHandHandler handler = new BaseHandHandler();
            PAHHParser.parseHandHistory(path, handler);
            long start = System.currentTimeMillis();
            List<Hand> hands = handler.buildHands();
            int count = hands.size();
            List<PokerLearningExample> examples =
                    NNConverter.localSituationsToFile(hands, "./advices_cash", situationHandler);
            if (examples == null) {
                continue;
            }

            String pathForSerialization = path.replace(".txt", ".pkr");
            SerializatorUtils.save(pathForSerialization, new PokerExamples(examples));
            String message = "There were parsed " + count + " hands from file "
                    + path + "\nTime: " + (System.currentTimeMillis() - start) + " ms"
                    + "\nExamples count: " + examples.size();
            JOptionPane.showMessageDialog(this, message);
            Log.d("Totally parsed " + count + " hands.");
            Log.d("Time: " + (System.currentTimeMillis() - start) + " ms");
        }
    }

    private void parseHandsWithoutAdvices(String heroName, ISituationHandler situationHandler) {
        String[] pathes = FrameUtils.openFileChooserForMultipleFiles(this, "./../..");
        if (pathes == null) {
            return;
        }
        for (String path : pathes) {
            BaseHandHandler handler = new BaseHandHandler();
            PAHHParser.parseHandHistory(path, handler);
            long start = System.currentTimeMillis();
            List<Hand> hands = handler.buildHands();
            int count = hands.size();
            Log.d("There is " + count + " hands");
            List<PokerLearningExample> examples =
                    NNConverter.parseLocalSituationsWithoutAdvices(hands, heroName, situationHandler);
            if (examples == null) {
                continue;
            }
            String pathForSerialization = FrameUtils.openFileChooser(this, "./../..");
            SerializatorUtils.save(pathForSerialization, new PokerExamples(examples));
            String message = "There were parsed " + count + " hands from file "
                    + path + "\nTime: " + (System.currentTimeMillis() - start) + " ms"
                    + "\nExamples count: " + examples.size();
            JOptionPane.showMessageDialog(this, message);
            Log.d("Totally parsed " + count + " hands.");
            Log.d("Time: " + (System.currentTimeMillis() - start) + " ms");
        }
    }

    private void calculatePotChanges() {
        BaseHandHandler handler = new BaseHandHandler();
//        BaseTournamentHandler tournamentHandler = new BaseTournamentHandler(handler);
        String[] pathes = FrameUtils.openFileChooserForMultipleFiles(this, "./../..");
        if (pathes == null) {
            return;
        }
        HandParser handParser = new HandParser();
        PotSituationHandler situationHandler = new PotSituationHandler();
        for (String path : pathes) {
            PAHHParser.parseHandHistory(path, handler);
            long start = System.currentTimeMillis();
            List<Hand> hands = handler.buildHands();
            for (Hand hand : hands) {
                handParser.parse(hand, "Andrew", situationHandler);
            }
            int sectionCount = 10;
            double min = 0, max = 48;
            double sectionSize = (max - min) / sectionCount;
            int[] sections = new int[sectionCount + 1];
            double averageAmount = 0, averageChange = 0;
            int count = 0;
            for (Pair<Double, Double> pots : situationHandler.getFlopTurnPots()) {
//                double temp = (pots.second - pots.first) / pots.first;
                if (pots.first < 5) {
                    continue;
                }
                count++;
                double tempAmount = (pots.second - pots.first);
                double tempChange = (pots.second - pots.first) / pots.first;
//                sections[(int) (tempAmount / sectionSize)]++;
                Log.d(tempAmount + "");
                averageAmount += tempAmount;
                averageChange += tempChange;
            }
            averageAmount /= count;
            averageChange /= count;
            Log.d("Turn average amount: " + averageAmount);
            Log.d("Turn average change: " + averageChange);
            Log.d(Arrays.toString(sections));
//            average = 0;
            count = 0;
            averageAmount = 0;
            averageChange = 0;
            for (Pair<Double, Double> pots : situationHandler.getTurnRiverPots()) {
//                double temp = (pots.second - pots.first) / pots.first;
//                if (pots.first < 5) {
//                    continue;
//                }
                count++;
                double tempAmount = (pots.second - pots.first);
                double tempChange = (pots.second - pots.first) / pots.first;
//                sections[(int) (tempAmount / sectionSize)]++;
                Log.d(tempAmount + "");
                averageAmount += tempAmount;
                averageChange += tempChange;
            }
            averageAmount /= count;
            averageChange /= count;
            Log.d("Turn average amount: " + averageAmount);
            Log.d("Turn average change: " + averageChange);
            Log.d("Totally parsed " + hands.size() + " hands.");
            Log.d("Time: " + (System.currentTimeMillis() - start) + " ms");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new HHParserFrame().setVisible(true);
            }
        });


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    // End of variables declaration//GEN-END:variables
}
