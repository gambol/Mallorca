/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GrandtorinoGameFrame.java
 *
 * Created on 02.12.2012, 13:44:00
 */
package mallorcatour.frames;

import java.awt.EventQueue;

import mallorcatour.bot.interfaces.IDecisionListener;
import mallorcatour.bot.interfaces.IPlayer;
import mallorcatour.bot.modeller.VillainModel;
import mallorcatour.bot.neural.NeuralBotFactory;
import mallorcatour.bot.villainobserver.VillainStatistics;
import mallorcatour.brains.IAdvisor;
import mallorcatour.core.game.Action;
import mallorcatour.core.game.Card;
import mallorcatour.core.game.LimitType;
import mallorcatour.core.game.PlayerInfo;
import mallorcatour.core.game.PokerStreet;
import mallorcatour.core.game.engine.GameEngine.EngineListener;
import mallorcatour.core.game.engine.PredefinedGameEngine;
import mallorcatour.core.game.interfaces.IPlayerGameInfo;
import mallorcatour.robot.controller.PokerPreferences;
import mallorcatour.util.DateUtils;
import mallorcatour.util.ExecutorUtils;
import mallorcatour.util.Log;
import mallorcatour.util.OnExceptionListener;
import mallorcatour.util.SerializatorUtils;
import mallorcatour.util.frames.FrameUtils;

/**
 * Frame for playing with bot.
 * 
 * @author Andrew
 */
public class GameFrame extends javax.swing.JFrame implements IPlayer, EngineListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7429672585051881560L;
	private final Object lock = new Object();
	private final Object actionLock = new Object();
	private final ShowingSpectrumListener spectrumListener;
	private boolean useGoButton;
	private VillainModel villainModeller;
	private final String DEBUG_PATH;
	private PredefinedGameEngine engine;
	private Action lastMove;
	private IPlayer player;

	/** Creates new form GrandtorinoGameFrame */
	public GameFrame(LimitType limitType) {
		initComponents();
		spectrumListener = new ShowingSpectrumListener();
		Log.WRITE_TO_ERR = true;
		DEBUG_PATH = PokerPreferences.DEBUG_PATTERN + DateUtils.getDate(false) + ".txt";
		villainModeller = new VillainModel(limitType, DEBUG_PATH);
		NeuralBotFactory factory = new NeuralBotFactory();
		player = factory.createBot(IAdvisor.UNSUPPORTED,new ShowingSpectrumListener(), IDecisionListener.EMPTY,
				"debug.txt");
		engine = new PredefinedGameEngine(this, player, DEBUG_PATH);
		engine.player = player;
		engine.setListener(this);
		enableActionButtons(false);
		humanDealerButton.setVisible(false);
		botDealerButton.setVisible(false);

		spectrumListener.setShow(showSpectrum);
		botCardsVisibleToggleButton.getModel().setPressed(true);
	}

	private void lock() {
		synchronized (lock) {
			try {
				goButton.setEnabled(true);
				lock.wait();
			} catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
		}
		goButton.setEnabled(false);
	}

	private void enableActionButtons(boolean enable) {
		foldButton.setEnabled(enable);
		passiveButton.setEnabled(enable);
		aggressiveButton.setEnabled(enable);
	}

	private void updateUI() {
		botBetField.setText(String.valueOf(gameInfo.getVillain().bet));
		humanBetField.setText(String.valueOf(gameInfo.getHero().bet));
		potField.setText(String.valueOf(gameInfo.getPotSize()));
		boardField.setText(gameInfo.getBoard().toString());
		humanStackField.setText(String.valueOf(gameInfo.getHero().stack));
		botStackField.setText(String.valueOf(gameInfo.getVillain().stack));
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		dealButton = new javax.swing.JButton();
		foldButton = new javax.swing.JButton();
		passiveButton = new javax.swing.JButton();
		aggressiveButton = new javax.swing.JButton();
		myHoleCardsField = new javax.swing.JTextField();
		botCardsField = new javax.swing.JTextField();
		botBetField = new javax.swing.JTextField();
		potField = new javax.swing.JTextField();
		humanBetField = new javax.swing.JTextField();
		boardField = new javax.swing.JTextField();
		humanDealerButton = new javax.swing.JButton();
		botDealerButton = new javax.swing.JButton();
		botStackField = new javax.swing.JTextField();
		humanStackField = new javax.swing.JTextField();
		botActionLabel = new javax.swing.JLabel();
		goButton = new javax.swing.JButton();
		botCardsVisibleToggleButton = new javax.swing.JToggleButton();
		showSpectrumToggleButton = new javax.swing.JToggleButton();
		useGoButtonCheckBox = new javax.swing.JCheckBox();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		dealButton.setText("Deal");
		dealButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dealButtonActionPerformed(evt);
			}
		});

		foldButton.setText("Fold");
		foldButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				foldButtonActionPerformed(evt);
			}
		});

		passiveButton.setText("Check");
		passiveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				passiveButtonActionPerformed(evt);
			}
		});

		aggressiveButton.setText("Bet");
		aggressiveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				aggressiveButtonActionPerformed(evt);
			}
		});

		boardField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				boardFieldActionPerformed(evt);
			}
		});

		humanDealerButton.setText("D");

		botDealerButton.setText("D");

		goButton.setText("GO");
		goButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				goButtonActionPerformed(evt);
			}
		});

		botCardsVisibleToggleButton.setText("Make unvisible");
		botCardsVisibleToggleButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botCardsVisibleToggleButtonActionPerformed(evt);
			}
		});

		showSpectrumToggleButton.setText("Show spectrum");
		showSpectrumToggleButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				showSpectrumToggleButtonActionPerformed(evt);
			}
		});

		useGoButtonCheckBox.setText("Use GO button");
		useGoButtonCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				useGoButtonCheckBoxStateChanged(evt);
			}
		});

		jMenu1.setText("File");

		jMenuItem1.setText("Set villain...");
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem1);

		jMenuBar1.add(jMenu1);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(30, 30, 30)
								.addComponent(potField, javax.swing.GroupLayout.PREFERRED_SIZE, 49,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(39, 39, 39)
								.addComponent(boardField, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133,
										Short.MAX_VALUE).addComponent(showSpectrumToggleButton).addContainerGap())
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(19, 19, 19)
																.addComponent(dealButton)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(humanStackField,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 63,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(58, 58, 58)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING)
																												.addComponent(
																														humanDealerButton,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														47,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(
																														foldButton,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														61,
																														javax.swing.GroupLayout.PREFERRED_SIZE))
																								.addGap(18, 18, 18)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING)
																												.addComponent(
																														passiveButton,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														73,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(
																														humanBetField,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														56,
																														javax.swing.GroupLayout.PREFERRED_SIZE)))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										botDealerButton,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										47,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGap(37, 37, 37)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												false)
																												.addComponent(
																														botStackField)
																												.addComponent(
																														botBetField,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														60,
																														Short.MAX_VALUE))))))
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
																.addGroup(
																		layout.createSequentialGroup()
																				.addGap(69, 69, 69)
																				.addComponent(
																						botCardsField,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						46,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addPreferredGap(
																						javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																						50, Short.MAX_VALUE)
																				.addComponent(
																						botCardsVisibleToggleButton)
																				.addContainerGap())
																.addGroup(
																		layout.createSequentialGroup()
																				.addGap(10, 10, 10)
																				.addComponent(
																						aggressiveButton,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						70,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addContainerGap(196, Short.MAX_VALUE))
																.addGroup(
																		javax.swing.GroupLayout.Alignment.TRAILING,
																		layout.createSequentialGroup()
																				.addComponent(
																						botActionLabel,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						155,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addGap(62, 62, 62))
																.addGroup(
																		javax.swing.GroupLayout.Alignment.TRAILING,
																		layout.createSequentialGroup()
																				.addPreferredGap(
																						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(goButton)
																				.addPreferredGap(
																						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(useGoButtonCheckBox)
																				.addContainerGap()))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(72, 72, 72)
																.addComponent(myHoleCardsField,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 48,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addContainerGap()))));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGap(16, 16, 16)
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(botStackField,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(botBetField,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(botDealerButton)
																.addComponent(botCardsField,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(botCardsVisibleToggleButton)))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(botActionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(13, 13, 13)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						potField,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						boardField,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		47, Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						humanBetField,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(humanDealerButton)
																				.addComponent(
																						myHoleCardsField,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGap(18, 18, 18)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(foldButton)
																				.addComponent(aggressiveButton)
																				.addComponent(passiveButton)))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(26, 26, 26)
																.addComponent(showSpectrumToggleButton)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
								.addGroup(
										layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(31, 31, 31)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(dealButton)
																				.addComponent(goButton)
																				.addComponent(useGoButtonCheckBox)))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(18, 18, 18)
																.addComponent(humanStackField,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(19, 19, 19)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void dealButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_dealButtonActionPerformed
		dealButton.setVisible(false);
		ExecutorUtils.newSingleThreadExecutor(OnExceptionListener.EMPTY).submit(new Runnable() {

			public void run() {
				engine.deal();
			}
		});

	}// GEN-LAST:event_dealButtonActionPerformed

	private void foldButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_foldButtonActionPerformed
		enableActionButtons(false);
		botActionLabel.setText("");
		lastMove = Action.foldAction();
		synchronized (actionLock) {
			actionLock.notifyAll();
		}
	}// GEN-LAST:event_foldButtonActionPerformed

	private void passiveButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_passiveButtonActionPerformed
		botActionLabel.setText("");
		enableActionButtons(false);
		if (gameInfo.getAmountToCall() > 0) {
			lastMove = Action.callAction(gameInfo.getAmountToCall());
		} else {
			lastMove = Action.checkAction();
		}
		synchronized (actionLock) {
			actionLock.notifyAll();
		}
	}// GEN-LAST:event_passiveButtonActionPerformed

	private void aggressiveButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_aggressiveButtonActionPerformed
		botActionLabel.setText("");
		enableActionButtons(false);
		lastMove = Action.aggressive();
		synchronized (actionLock) {
			actionLock.notifyAll();
		}
	}// GEN-LAST:event_aggressiveButtonActionPerformed

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				EventQueue.invokeLater(new Runnable() {

					public void run() {
						new GameFrame(LimitType.NO_LIMIT).setVisible(true);
					}
				});
			}
		});
	}

	private void boardFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_boardFieldActionPerformed
	}// GEN-LAST:event_boardFieldActionPerformed

	private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_goButtonActionPerformed
		synchronized (lock) {
			lock.notifyAll();
			goButton.setEnabled(false);
		}
	}// GEN-LAST:event_goButtonActionPerformed

	private void botCardsVisibleToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botCardsVisibleToggleButtonActionPerformed
		if (botCardsField.isVisible()) {
			botCardsField.setVisible(false);
			botCardsVisibleToggleButton.setText("Make visible");
		} else {
			botCardsField.setVisible(true);
			botCardsVisibleToggleButton.setText("Make unvisible");
		}
	}// GEN-LAST:event_botCardsVisibleToggleButtonActionPerformed

	private boolean showSpectrum = false;

	private void showSpectrumToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_showSpectrumToggleButtonActionPerformed
		spectrumListener.setShow(!showSpectrum);
		showSpectrum = !showSpectrum;
	}// GEN-LAST:event_showSpectrumToggleButtonActionPerformed

	private void useGoButtonCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_useGoButtonCheckBoxStateChanged
		useGoButton = useGoButtonCheckBox.getModel().isSelected();
		//TODO implement use GO checkbox
//		goButton.setEnabled(useGoButton);
	}// GEN-LAST:event_useGoButtonCheckBoxStateChanged

	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem1ActionPerformed
		String path = FrameUtils.openFileChooser(this, "./villains");
		if (path == null) {
			return;
		}
		villainModeller.onVillainKnown(true);
		villainModeller.onVillainChange(SerializatorUtils.load(path, VillainStatistics.class));
	}// GEN-LAST:event_jMenuItem1ActionPerformed
		// Variables declaration - do not modify//GEN-BEGIN:variables

	private javax.swing.JButton aggressiveButton;
	private javax.swing.JTextField boardField;
	private javax.swing.JLabel botActionLabel;
	private javax.swing.JTextField botBetField;
	private javax.swing.JTextField botCardsField;
	private javax.swing.JToggleButton botCardsVisibleToggleButton;
	private javax.swing.JButton botDealerButton;
	private javax.swing.JTextField botStackField;
	private javax.swing.JButton dealButton;
	private javax.swing.JButton foldButton;
	private javax.swing.JButton goButton;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JTextField humanBetField;
	private javax.swing.JButton humanDealerButton;
	private javax.swing.JTextField myHoleCardsField;
	private javax.swing.JTextField humanStackField;
	private javax.swing.JButton passiveButton;
	private javax.swing.JTextField potField;
	private javax.swing.JToggleButton showSpectrumToggleButton;
	private javax.swing.JCheckBox useGoButtonCheckBox;

	// End of variables declaration//GEN-END:variables

	@Override
	public void onStageEvent(PokerStreet street) {
		botActionLabel.setText("");
		updateUI();
	}

	@Override
	public void onHandStarted(IPlayerGameInfo gameInfo) {
		this.gameInfo = gameInfo;
		botDealerButton.setVisible(false);
		humanDealerButton.setVisible(false);

		if (gameInfo.onButton()) {
			humanDealerButton.setVisible(true);
		} else {
			botDealerButton.setVisible(true);
		}
		updateUI();
	}

	@Override
	public void onHandEnded() {
		dealButton.setVisible(true);
		botCardsField.setVisible(true);
		new Thread() {

			@Override
			public void run() {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException ex) {
					throw new RuntimeException(ex);
				}
				botCardsField.setVisible(botCardsVisibleToggleButton.getModel().isPressed());
			}
		}.start();
		enableActionButtons(false);

		updateUI();

	}

	@Override
	public void onHoleCards(Card c1, Card c2, String villainName) {
		botActionLabel.setText("");

		botCardsField.setText("");
		myHoleCardsField.setText(c1 + " " + c2);
	}

	@Override
	public void onActed(Action action, double toCall, String name) {
		if (name.equals(player.getName())) {
			botActionLabel.setText(action.toString());
		}
	}

	private IPlayerGameInfo gameInfo;

	@Override
	public Action getAction() {
		enableActionButtons(true);
		if (gameInfo.getAmountToCall() > 0) {
			passiveButton.setText("Call");
			aggressiveButton.setText("Raise");
		} else {
			passiveButton.setText("Check");
			aggressiveButton.setText("Bet");
		}

		synchronized (actionLock) {
			try {
				actionLock.wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		return lastMove;
	}

	@Override
	public String getName() {
		return "Andrew";
	}

	@Override
	public void onPlayerActed(Action action, PlayerInfo player) {
		updateUI();
		lock();
	}

}
