package org.alcibiade.asciiart.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class AsciiArtLogTest {

    @Test
    public void testDelegation() {
        Log logger = LogFactory.getLog(AsciiArtLogTest.class);
        AsciiArtLog artLogger = new AsciiArtLog(logger);
        artLogger.info("Sample info message for delegation test");
    }
}
