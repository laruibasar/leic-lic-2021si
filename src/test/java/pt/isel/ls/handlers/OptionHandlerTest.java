package pt.isel.ls.handlers;

import org.junit.Test;
import pt.isel.ls.config.AppConfig;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.view.results.CommandResult;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Parameters;
import pt.isel.ls.utils.Path;

public class OptionHandlerTest {
    @Test
    public void list() throws HandlerException {
        AppConfig.setup();

        Command test = new Command(Method.OPTION, new Path("/"), new Parameters());
        Handler handler = new OptionHandler();
        CommandResult rs = handler.execute(test);
        // print list of commands, we need the dev of different CommandResult
    }
}
