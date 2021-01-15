package pt.isel.ls.view.common.viewroute;

import org.junit.Test;
import pt.isel.ls.results.RootResult;
import pt.isel.ls.utils.Header;
import pt.isel.ls.view.common.IView;
import pt.isel.ls.view.common.ViewRouter;
import pt.isel.ls.view.html.RootHtmlView;
import pt.isel.ls.view.text.RootTextView;

import static org.junit.Assert.assertEquals;

public class ViewRouteTest {
    @Test
    public void view_html_root() throws Exception {
        ViewRouter router = new ViewRouter();
        router.addView(
                new Header("accept:text/html"),
                new RootResult(),
                new RootHtmlView());
        router.addView(
                new Header("accept:text/plain"),
                new RootResult(),
                new RootTextView());

        IView view = router.findView(new Header("accept:text/plain"), new RootResult());
        assertEquals(RootTextView.class, view.getClass());
    }
}
