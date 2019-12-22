package dd.union.perception;

import dd.union.perception.notification.Perception;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PerceptionTest {

    @Test
    public void cycleTest() {
        Perception perception = new Perception();
        perception.cycle();
        String text = perception.getTexts().poll();
        assertNotNull(text);
        assertTrue(text.length() > 0);
    }
}
