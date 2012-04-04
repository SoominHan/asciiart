package org.alcibiade.asciiart.device;

import org.alcibiade.asciiart.widget.TextPanel;

public interface TextDevice {

    TextPanel getRootPanel();

    void update() throws DeviceFailureException;

    void close() throws DeviceFailureException;
}
