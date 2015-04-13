package org.alcibiade.asciiart.coord;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Test projections.
 *
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */
public class CoordProjectionTest {

    @Test
    public void testDouble() {
        CoordProjection projectionDouble1 = new CoordProjection(0, 1, 0, 2);
        Assertions.assertThat(projectionDouble1.project(1)).isEqualTo(2);
        Assertions.assertThat(projectionDouble1.project(3.14)).isEqualTo(6.28);
        Assertions.assertThat(projectionDouble1.project(-12)).isEqualTo(-24);

        CoordProjection projectionDouble2 = new CoordProjection(0, 1.4, 0, 2.8);
        Assertions.assertThat(projectionDouble2.project(1)).isEqualTo(2);
    }

    @Test
    public void testInvert() {
        CoordProjection projectionInvert1 = new CoordProjection(0, 1, 0, -1);
        Assertions.assertThat(projectionInvert1.project(1)).isEqualTo(-1);
        Assertions.assertThat(projectionInvert1.project(3.14)).isEqualTo(-3.14);
        Assertions.assertThat(projectionInvert1.project(-12)).isEqualTo(12);

        CoordProjection projectionInvert2 = new CoordProjection(0, 1.4, 0, -1.4);
        Assertions.assertThat(projectionInvert2.project(1)).isEqualTo(-1);
    }

    @Test
    public void testTranslate() {
        CoordProjection projectionTranslate1 = new CoordProjection(0, 1, 10, 11);
        Assertions.assertThat(projectionTranslate1.project(1)).isEqualTo(11);
        Assertions.assertThat(projectionTranslate1.project(3.14)).isEqualTo(13.14);
        Assertions.assertThat(projectionTranslate1.project(-12)).isEqualTo(-2);

        CoordProjection projectionTranslate2 = new CoordProjection(0, 1.4, 10, 11.4);
        Assertions.assertThat(projectionTranslate2.project(1)).isEqualTo(11);
    }
}
