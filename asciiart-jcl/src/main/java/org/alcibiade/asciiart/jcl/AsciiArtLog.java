package org.alcibiade.asciiart.jcl;

import java.util.Collection;
import java.util.Map;
import org.alcibiade.asciiart.raster.CharacterRaster;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.widget.TableWidget;
import org.alcibiade.asciiart.widget.TextPanel;
import org.alcibiade.asciiart.widget.TextWidget;
import org.alcibiade.asciiart.widget.model.TableModel;
import org.alcibiade.asciiart.widget.model.TableModelCollectionAdapter;
import org.alcibiade.asciiart.widget.model.TableModelMapAdapter;
import org.apache.commons.logging.Log;

public class AsciiArtLog extends LogDelegator {

    public AsciiArtLog(Log logger) {
        super(logger);
    }

    /*
     * Collections
     */
    public void fatal(Collection<? extends Object> items, String title) {
        logList(Level.FATAL, items, title);
    }

    public void error(Collection<? extends Object> items, String title) {
        logList(Level.ERROR, items, title);
    }

    public void warn(Collection<? extends Object> items, String title) {
        logList(Level.WARN, items, title);
    }

    public void info(Collection<? extends Object> items, String title) {
        logList(Level.INFO, items, title);
    }

    public void debug(Collection<? extends Object> items, String title) {
        logList(Level.DEBUG, items, title);
    }

    public void trace(Collection<? extends Object> items, String title) {
        logList(Level.TRACE, items, title);
    }

    /*
     * Maps
     */
    public void fatal(Map<? extends Object, ? extends Object> items, String... titles) {
        logMap(Level.FATAL, items, titles);
    }

    public void error(Map<? extends Object, ? extends Object> items, String... titles) {
        logMap(Level.ERROR, items, titles);
    }

    public void warn(Map<? extends Object, ? extends Object> items, String... titles) {
        logMap(Level.WARN, items, titles);
    }

    public void info(Map<? extends Object, ? extends Object> items, String... titles) {
        logMap(Level.INFO, items, titles);
    }

    public void debug(Map<? extends Object, ? extends Object> items, String... titles) {
        logMap(Level.DEBUG, items, titles);
    }

    public void trace(Map<? extends Object, ? extends Object> items, String... titles) {
        logMap(Level.TRACE, items, titles);
    }

    private void logList(Level level, Collection<? extends Object> items, String title) {
        TableModel tableModel = new TableModelCollectionAdapter(items, title);
        TableWidget tableWidget = new TableWidget(tableModel);
        logWidget(level, tableWidget);
    }

    private void logMap(Level level, Map<? extends Object, ? extends Object> items, String... titles) {
        TableModel tableModel = new TableModelMapAdapter(items, titles);
        TableWidget tableWidget = new TableWidget(tableModel);
        logWidget(level, tableWidget);
    }

    private void logWidget(Level level, TextWidget widget) {
        TextPanel textPanel = new TextPanel();
        textPanel.add(widget);

        CharacterRaster raster = new ExtensibleCharacterRaster(' ');
        textPanel.render(new RasterContext(raster));

        for (String line : raster) {
            logLine(level, line);
        }
    }

    private void logLine(Level level, String line) {
        switch (level) {
            case FATAL:
                fatal(line);
                break;
            case ERROR:
                error(line);
                break;
            case WARN:
                warn(line);
                break;
            case INFO:
                info(line);
                break;
            case DEBUG:
                debug(line);
                break;
            case TRACE:
                trace(line);
                break;
        }
    }
}
