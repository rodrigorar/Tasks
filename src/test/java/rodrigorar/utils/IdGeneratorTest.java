package rodrigorar.utils;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class IdGeneratorTest {

    @Test
    public void generateTaskIdTest() {
        IdGenerator generator = IdGenerator.getInstance();
        String id = generator.generateTaskId();

        assertNotNull(id);

        String[] idParts = id.split("-");
        int intPart = Integer.valueOf(idParts[1]);

        assertTrue(intPart > 0);
    }
}
