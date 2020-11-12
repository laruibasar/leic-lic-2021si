package pt.isel.ls.services;

import org.junit.Test;
import pt.isel.ls.config.AppConfig;
import pt.isel.ls.data.IUserData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.mockdata.MockUserData;
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

public class CreateUserTest {

    @Test
    public void insert_user_test() throws HandlerException {
        AppConfig.setup();

        IUserData mock = new MockUserData();

        Parameters params = new Parameters();
        params.setValues("name=John+Smith&email=hi@example.com");
        Command cmd = new Command(Method.POST, new Path("/users/"), params);
        CreateUserHandler handler = new CreateUserHandler();
        
        handler.setUserData(mock);
        CommandResult rs = handler.execute(cmd);

        assertEquals(1, rs.getStatus());
        for (Model test : rs) {
            User testUser = (User) test;
            assertEquals("John Smith", testUser.getName());
            assertEquals("hi@example.com", testUser.getEmail());
        }
    }

    @Test
    public void insert_user_with_empty_name() throws HandlerException {
        AppConfig.setup();

        Parameters params = new Parameters();
        params.setValues("name=&email=hi@example.net");
        Command cmd = new Command(Method.POST, new Path("/users/"), params);
        CreateUserHandler handler = new CreateUserHandler();
        CommandResult rs = handler.execute(cmd);

        assertEquals(1, rs.getStatus());
        for (Model test : rs) {
            User testUser = (User) test;
            assertEquals("Buggy Joe", testUser.getName());
            assertEquals("hi@example.net", testUser.getEmail());
        }
    }
}
