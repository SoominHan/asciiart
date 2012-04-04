package org.alcibiade.asciiart.widget.model;

public interface TextWidgetModel {

    void addModelUpdateListener(ModelUpdateListener listener);

    void removeModelUpdateListener(ModelUpdateListener listener);
}
