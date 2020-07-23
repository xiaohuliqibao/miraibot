package top.kegurou;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.kegurou.features.Menu;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    private static final Logger log = LoggerFactory.getLogger(AppTest.class);

    @Test
    public void testslf4j() {
        System.out.println("nmd");
    }

    @Test
    public void testMune() {
        System.out.print(Menu.getMenu());
    }
}
