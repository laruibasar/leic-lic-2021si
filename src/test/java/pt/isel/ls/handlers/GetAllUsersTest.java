package pt.isel.ls.handlers;

import org.junit.Test;
import pt.isel.ls.config.AppConfig;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.utils.Command;
import pt.isel.ls.results.CommandResult;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Parameters;
import pt.isel.ls.utils.Path;

//import static org.junit.Assert.assertEquals;

public class GetAllUsersTest {

    @Test
    public void select_all_users() throws HandlerException {
        AppConfig.setup();

        Command cmd = new Command(Method.GET, new Path("/users/"), new Parameters());
        GetAllUsersHandler handler = new GetAllUsersHandler();
        CommandResult rs = handler.execute(cmd);

        /*for (Model test : rs) {
            User testUser = (User) test;
            if (testUser.getId() == 1) {
                assertEquals("Mike Albuquerque", testUser.getName());
            }
        }*/
    }
}
