package pt.isel.ls.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeaderTest {
    @Test
    public void header_equals() {
        Header header = new Header("accept:text/html|file-name:test.txt");
        assertEquals("accept:text/html|file-name:test.txt", header.toString());
    }

    @Test
    public void header_split_fields_equals() {
        Header header = new Header("accept:text/html|file-name:test.txt");

        assertEquals("text/html", header.getValue("accept"));
        assertEquals("test.txt", header.getValue("file-name"));
    }

    @Test
    public void header_absent_accept() {
        Header header = new Header("file-name:test.txt");
        assertEquals("text/plain", header.getValue("accept"));
        assertEquals("test.txt", header.getValue("file-name"));
    }

    @Test
    public void header_absent_filename() {
        Header header = new Header("accept:text/html");
        assertEquals("text/html", header.getValue("accept"));
        assertEquals("", header.getValue("file-name"));
    }

    @Test
    public void header_doble_accept() {
        Header header = new Header("accept:text/html|accept:text/plain");
        assertEquals("text/plain", header.getValue("accept"));
        assertEquals("", header.getValue("file-name"));
    }

    @Test
    public void header_invalid_accept() {
        Header header = new Header("accept:|file-name:text.txt");
        assertEquals("text/plain", header.getValue("accept"));
        assertEquals("text.txt", header.getValue("file-name"));
    }

    @Test
    public void header_another_invalid_accept() {
        Header header = new Header("accept:text/css|file-name:text.txt");
        assertEquals("text/plain", header.getValue("accept"));
        assertEquals("text.txt", header.getValue("file-name"));
    }

    @Test
    public void header_invalid_filename() {
        Header header = new Header("accept:text/plain|file-name:");
        assertEquals("text/plain", header.getValue("accept"));
        assertEquals("", header.getValue("file-name"));
    }

    @Test
    public void header_invalid_filename_accept() {
        Header header = new Header("accept:|file-name:");
        assertEquals("text/plain", header.getValue("accept"));
        assertEquals("", header.getValue("file-name"));
    }

    @Test
    public void header_absent_accept_filename() {
        Header header = new Header();
        assertEquals("text/plain", header.getValue("accept"));
        assertEquals("", header.getValue("file-name"));
    }
}
