package org.alcibiade.asciiart.widget;

import java.util.HashMap;
import java.util.Map;
import org.alcibiade.asciiart.coord.TextCoord;
import org.alcibiade.asciiart.raster.CharacterRaster;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.Raster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.model.ArrayTableModel;
import org.alcibiade.asciiart.widget.model.TableModel;
import org.alcibiade.asciiart.widget.model.TableModelMapAdapter;
import org.junit.Assert;
import org.junit.Test;

public class TableWidgetTest {

    @Test
    public void testTableWidget() {
        String[][] dataArray = {{"Hello", "world"}, {"!", "Glad"},
            {"to see", "you"}};
        TableModel tableModel = new ArrayTableModel(dataArray);
        new TableWidget(tableModel);
    }

    @Test
    public void testBorderless() {
        String[][] dataArray = {{"Hello", "world"}, {"!", "Glad"},
            {"to see", "you"}};
        TableModel tableModel = new ArrayTableModel(dataArray);
        TableWidget tableWidget = new TableWidget(tableModel, false);

        Raster raster = new ExtensibleCharacterRaster(' ');

        RasterContext rc = new RasterContext(raster);
        tableWidget.render(rc);
        String rasterString = raster.toString();
        Assert.assertTrue(rasterString.startsWith("Hello !"));
    }

    @Test
    public void testFormatting() {
        String[][] dataArray = {{"John", "Ringo"}, {"12", "2.4"}};
        TableModel tableModel = new ArrayTableModel(dataArray);
        TableWidget tableWidget = new TableWidget(tableModel);

        CharacterRaster raster = new ExtensibleCharacterRaster(' ');

        RasterContext rc = new RasterContext(raster);
        tableWidget.render(rc);

        // Assert that john is padded to the left
        Assert.assertEquals('J', (char) raster.getCharacter(new TextCoord(2, 1)));
        // Assert that 12 is padded to the left
        Assert.assertEquals('1', (char) raster.getCharacter(new TextCoord(10, 1)));
        // Assert that 2.4 is padded to the right
        Assert.assertEquals('2', (char) raster.getCharacter(new TextCoord(11, 2)));
    }

    @Test
    public void testTitles() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "A");
        map.put("2", "B");
        TableModel tableModel = new TableModelMapAdapter(map, "Key", "Value");
        TableWidget tableWidget = new TableWidget(tableModel);

        CharacterRaster raster = new ExtensibleCharacterRaster(' ');

        RasterContext rc = new RasterContext(raster);
        tableWidget.render(rc);

        Assert.assertEquals('K', (char) raster.getCharacter(new TextCoord(2, 1)));
        Assert.assertEquals('V', (char) raster.getCharacter(new TextCoord(8, 1)));
    }
}
