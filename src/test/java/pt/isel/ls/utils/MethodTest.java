package pt.isel.ls.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MethodTest {
    @Test
    public void method_get_set() {
        Method method = Method.getMethod("GET");
        assertEquals(Method.GET, method);
    }

    @Test
    public void method_post_set() {
        Method method = Method.getMethod("POST");
        assertEquals(Method.POST, method);
    }

    @Test
    public void method_put_set() {
        Method method = Method.getMethod("PUT");
        assertEquals(Method.PUT, method);
    }

    @Test
    public void method_null_set() {
        Method method = Method.getMethod("bla bla");
        assertEquals(null, method);
    }
}
