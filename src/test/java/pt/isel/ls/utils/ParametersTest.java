package pt.isel.ls.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParametersTest {
    @Test
    public void parameters_equals() {
        Parameters params = new Parameters("name=First+Last&email=example"
                + "@email.com");
        assertEquals("name=First+Last&email=example@email.com",
                params.toString());
    }
}
