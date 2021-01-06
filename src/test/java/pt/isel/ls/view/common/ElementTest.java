package pt.isel.ls.view.common;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ElementTest {
    @Test
    public void print_element_text() {
        Element test = new Text("Hello World!");
        assertEquals("Hello World!", test.print());
    }

    @Test
    public void print_element_a_empty() {
        Element test = new A("This is an empty link");
        assertEquals("<a href=\"\">This is an empty link</a>\n", test.print());
    }

    @Test
    public void print_element_a_href() {
        Element test = new A("This is a normal link", "www.isel.pt");
        assertEquals("<a href=\"www.isel.pt\">This is a normal link</a>\n", test.print());
    }

    @Test
    public void print_element_a_href_element() {
        Element content = new Text("another element");
        Element test = new A(content, "www.isel.pt");
        assertEquals("<a href=\"www.isel.pt\">another element</a>\n", test.print());
    }

    @Test
    public void print_element_title() {
        Element test = new Title("Test page");
        assertEquals("<title>Test page</title>\n", test.print());
    }

    @Test
    public void print_element_head_empty() {
        Element header = new Head();
        assertEquals("<head>\n</head>\n", header.print());
    }

    @Test
    public void print_element_head_element() {
        Element header = new Head(
                new Title("Moshi Moshi!")
        );

        assertEquals(
                "<head>\n<title>Moshi Moshi!</title>\n</head>\n",
                header.print());
    }

    @Test
    public void print_element_body_elements() {
        Element test = new Body(
                new Text("Moshi Moshi!"),
                new A("Ciao", "www.isel.pt")
        );

        assertEquals(
                "<body>\nMoshi Moshi!<a href=\"www.isel.pt\">Ciao</a>\n</body>\n",
                test.print());
    }

    @Test
    public void print_element_html() {
        Element html = new Html(
                new Head(),
                new Body()
        );

        assertEquals("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "</head>\n" +
                "<body>\n" +
                "</body>\n" +
                "</html>", html.print());
    }
}
