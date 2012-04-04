package org.alcibiade.asciiart.device;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.alcibiade.asciiart.raster.CharacterRaster;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.TextPanel;

public class Screen implements TextDevice {

    private static final char ESC = 27;
    private static final String CLEAR = ESC + "[2J";
    private TextPanel rootPanel;
    private Writer deviceWriter;

    public Screen() {
        this(System.out);
    }

    public Screen(OutputStream outputStream) {
        this(new OutputStreamWriter(outputStream));
    }

    public Screen(Writer writer) {
        rootPanel = new TextPanel();
        deviceWriter = writer;
    }

    @Override
    public void close() throws DeviceFailureException {
        try {
            deviceWriter.close();
        } catch (IOException e) {
            throw new DeviceFailureException(
                    "IO Exception while closing screen", e);
        }
    }

    @Override
    public TextPanel getRootPanel() {
        return rootPanel;
    }

    @Override
    public void update() throws DeviceFailureException {
        try {
            CharacterRaster raster = new ExtensibleCharacterRaster();
            rootPanel.render(new RasterContext(raster));

            deviceWriter.write(CLEAR);
            deviceWriter.write(raster.toString());
            deviceWriter.flush();
        } catch (IOException e) {
            throw new DeviceFailureException(
                    "IO Exception while updating screen", e);
        }
    }
}
