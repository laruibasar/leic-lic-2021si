package pt.isel.ls.services;

import org.junit.Test;
import pt.isel.ls.config.AppConfig;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.model.Model;
import pt.isel.ls.model.User;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Method;
import pt.isel.ls.utils.Parameters;
import pt.isel.ls.utils.ParametersExceptions;
import pt.isel.ls.utils.Path;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class GetAllUsersTest {

    @Test
    public void select_all_users() throws DataConnectionException,
            SQLException, ParametersExceptions, HandlerException {
        AppConfig.setup();

        Command cmd = new Command(Method.GET, new Path("/users/"), new Parameters());
        GetAllUsersHandler handler = new GetAllUsersHandler();
        CommandResult rs = handler.execute(cmd);

        for (Model test : rs) {
            User testUser = (User) test;
            if (testUser.getId() == 1) {
                assertEquals("Mike Albuquerque", testUser.getName());
            }
        }
    }
}
