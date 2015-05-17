/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mallorcatour.grandtorino.robot;

import br.com.wagnerpaz.javahook.NativeKeyboardEvent;
import br.com.wagnerpaz.javahook.NativeKeyboardListener;
import java.util.concurrent.ExecutorService;
import mallorcatour.core.bot.IGameRobot;
import mallorcatour.grandtorino.robot.recognizer.PSTableRecognizer;
import mallorcatour.hookmanager.KeyboardHookManager;
import mallorcatour.util.Alarm;
import mallorcatour.util.ExecutorUtils;
import mallorcatour.util.OnExceptionListener;
import mallorcatour.util.ThreadUtils;
import mp3player.Mp3Player;

/**
 *
 * @author Andrew
 */
class ChatTracker {

    private static final int CHECK_CHAT_EVERY = 45000;
    private final ExecutorService chatTrackerExecutor;
    private final static mp3player.Mp3Player chatAlarmPlayer;
    private final IGameRobot robot;
    private final PSTableRecognizer tableRecognizer;

    static {
        chatAlarmPlayer = new Mp3Player(Alarm.ALARM_WAV_PATH);
    }

    public ChatTracker(IGameRobot robot, PSTableRecognizer recognizer) {
        this.chatTrackerExecutor = ExecutorUtils.newSingleThreadExecutor(OnExceptionListener.EMPTY);
        this.tableRecognizer = recognizer;
        this.robot = robot;
        KeyboardHookManager.addListener(new NativeKeyboardListener() {

            public boolean keyPressed(NativeKeyboardEvent nke) {
                if (nke.getKeyCode() == ManageKeySettings.STOP_CHAT_ALARM_KEY) {
                    if (chatAlarmPlayer.isPlaying()) {
                        chatAlarmPlayer.stop();
                    }
                    return false;
                }
                return true;
            }

            public boolean keyReleased(NativeKeyboardEvent nke) {
                if (nke.getKeyCode() == ManageKeySettings.STOP_CHAT_ALARM_KEY) {
                    return false;
                }
                return true;
            }
        });
    }

    public void resume() {
        chatTrackerExecutor.submit(new Runnable() {

            public void run() {
                while (robot.isPlaying()) {
                    ThreadUtils.sleep(CHECK_CHAT_EVERY);
                    if (robot.isPlaying()) {
                        boolean result = tableRecognizer.checkChat();
                        if (!result && !chatAlarmPlayer.isPlaying()) {
                            chatAlarmPlayer.play(true);
                        }
                    }
                }
            }
        });
    }
}
