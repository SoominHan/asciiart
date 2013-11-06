package org.alcibiade.asciiart.widget;

import java.util.Random;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.Raster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.model.ArrayTableModel;
import org.alcibiade.asciiart.widget.model.TableModel;
import org.junit.Test;

public class TableWidgetPerformanceTest {

    private static final int MODEL_SIZE = 100;

    @Test
    public void testTableWidgetPerformance() {
        Random random = new Random();
        String[][] dataArray = new String[MODEL_SIZE][MODEL_SIZE];

        for (int i = 0; i < MODEL_SIZE; i++) {
            for (int j = 0; j < MODEL_SIZE; j++) {
                double value = random.nextDouble() * 10000;
                dataArray[i][j] = Double.toString(value);
            }
        }

        TableModel tableModel = new ArrayTableModel(dataArray);
        TableWidget tableWidget = new TableWidget(tableModel);
        Raster raster = new ExtensibleCharacterRaster();

        tableWidget.render(new RasterContext(raster));
    }
}
