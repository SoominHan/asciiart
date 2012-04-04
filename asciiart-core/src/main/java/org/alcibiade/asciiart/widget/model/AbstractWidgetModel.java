package org.alcibiade.asciiart.widget.model;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractWidgetModel implements TextWidgetModel {

    private Set<ModelUpdateListener> listeners = new HashSet<ModelUpdateListener>();

    /*
     * (non-Javadoc)
     *
     * @see
     * org.alcibiade.asciiart.widget.model.TextWidgetModel#addModelUpdateListener
     * (org.alcibiade.asciiart.widget.model.ModelUpdateListener)
     */
    @Override
    public void addModelUpdateListener(ModelUpdateListener listener) {
        listeners.add(listener);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.alcibiade.asciiart.widget.model.TextWidgetModel#removeModelUpdateListener
     * (org.alcibiade.asciiart.widget.model.ModelUpdateListener)
     */
    @Override
    public void removeModelUpdateListener(ModelUpdateListener listener) {
        listeners.remove(listener);
    }

    protected void notifyModelUpdate() {
        for (ModelUpdateListener listener : listeners) {
            listener.modelUpdated(this);
        }
    }
}
