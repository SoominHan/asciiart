package org.alcibiade.asciiart.device;

import java.io.StringWriter;
import org.alcibiade.asciiart.widget.LabelWidget;
import org.alcibiade.asciiart.widget.TextWidget;
import org.junit.Test;

public class ScreenTest {

    @Test
    public void testScreen() throws DeviceFailureException {
        StringWriter stringWriter = new StringWriter();
        Screen screen = new Screen(stringWriter);

        TextWidget w1 = new LabelWidget("Hello");
        TextWidget w2 = new LabelWidget("World !");

        screen.getRootPanel().add(w1);
        screen.getRootPanel().add(w2);

        screen.update();
        screen.close();

        assert (stringWriter.getBuffer().length() > 0);
    }
}
