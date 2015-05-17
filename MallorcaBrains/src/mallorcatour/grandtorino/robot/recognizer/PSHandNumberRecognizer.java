/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mallorcatour.grandtorino.robot.recognizer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import mallorcatour.grandtorino.robot.recognizer.assets.IDigitAssets;
import mallorcatour.recognizer.core.AbstractNumberRecognizer;
import mallorcatour.util.IImageProcessor;
import mallorcatour.util.RetainColorProcessor;

/**
 *
 * @author Andrew
 */
class PSHandNumberRecognizer extends AbstractNumberRecognizer {

    private static final Color DIGIT_COLOR = new Color(16, 16, 16);
    private IDigitAssets assets;

    public PSHandNumberRecognizer(IDigitAssets assets) {
        this.assets = assets;
    }

    public void changeAssets(IDigitAssets assets) {
        this.assets = assets;
    }

    public long getHandNumber(BufferedImage image) {
        return getNumberLong(image);
    }

    @Override
    protected IImageProcessor getPreprocessor() {
        return new RetainColorProcessor(DIGIT_COLOR, Color.WHITE);
    }

    public BufferedImage[] getDigitImages() {
        return assets.getDigitImages();
    }
}
