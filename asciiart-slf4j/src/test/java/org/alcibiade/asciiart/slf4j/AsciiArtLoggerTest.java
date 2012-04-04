package org.alcibiade.asciiart.slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsciiArtLoggerTest {

    @Test
    public void testDelegation() {
        Logger logger = LoggerFactory.getLogger(AsciiArtLoggerTest.class);
        AsciiArtLogger artLogger = new AsciiArtLogger(logger);
        artLogger.info("Sample info message for delegation test");

        List<String> list = new ArrayList<String>();
        list.add("Hello");
        list.add("world !");

        artLogger.info(list, "Test items");

        Map<String, Double> map = new TreeMap<String, Double>();
        map.put("Paris", 12.5);
        map.put("London", 17.23);
        map.put("San Francisco", 9.);

        artLogger.info(map, "City", "Value");
    }
}
