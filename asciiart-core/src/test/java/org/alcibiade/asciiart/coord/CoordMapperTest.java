package org.alcibiade.asciiart.coord;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Test coordinate mapping.
 *
 * @author Yannick Kirschhoffer <alcibiade@alcibiade.org>
 */
public class CoordMapperTest {

    @Test
    public void testProjection() {
        TextBoxSize textBoxSize = new TextBoxSize(80, 25);
        CoordMapper mapper = new CoordMapper(textBoxSize, 0, 1, 0, 1);
        Assertions.assertThat(mapper.computeTextCoord(0, 0)).isEqualTo(new TextCoord(0, 0));
        Assertions.assertThat(mapper.computeTextCoord(0.5, 0)).isEqualTo(new TextCoord(40, 0));
        Assertions.assertThat(mapper.computeTextCoord(0.999, 0)).isEqualTo(new TextCoord(79, 0));
        Assertions.assertThat(mapper.computeTextCoord(0.5, 0.5)).isEqualTo(new TextCoord(40, 12));
        Assertions.assertThat(mapper.computeTextCoord(0.5, 0.999)).isEqualTo(new TextCoord(40, 24));
    }

    @Test
    public void testOutOfBounds() {
        TextBoxSize textBoxSize = new TextBoxSize(80, 25);
        CoordMapper mapper = new CoordMapper(textBoxSize, 0, 1, 0, 1);
        Assertions.assertThat(mapper.computeTextCoord(-1, 0)).isNull();
        Assertions.assertThat(mapper.computeTextCoord(0, 1.0)).isNull();
    }
}
