package pt.isel.ls.handlers;

import pt.isel.ls.data.IUserData;
import pt.isel.ls.data.UserData;
import pt.isel.ls.data.common.DataConnectionException;
import pt.isel.ls.handlers.common.Handler;
import pt.isel.ls.handlers.common.HandlerException;
import pt.isel.ls.handlers.common.IHandler;
import pt.isel.ls.utils.Command;
import pt.isel.ls.utils.CommandResult;
import pt.isel.ls.utils.Parameters;

/**
 * POST /users - creates a new user, given the following parameters
 *  name - the user's name
 *  email - the user's unique email.
 */
public class CreateUserHandler extends Handler implements IHandler {
    IUserData userData;

    public CreateUserHandler() {
        super();
        userData = new UserData();
        template.setParameters(new Parameters(new String[]{"name", "email"}));
    }

    public void setUserDataConnection(IUserData userData) {
        this.userData = userData;
    }

    @Override
    public CommandResult execute(Command cmd) throws HandlerException {
        if (!template.getParameters().isValid(cmd.getParameters())) {
            StringBuilder keys = new StringBuilder("Missing ");
            for (String str : template.getParameters()) {
                if (cmd.getParameters().getValue(str) == null) {
                    keys.append("\"").append(str).append("\" ");
                }
            }
            throw new HandlerException("Handler: missing parameters: "
                    + keys.toString());
        }

        final String name = cmd
                .getParameters()
                .getValue("name")
                .replace("+", " ");
        try {
            return userData.createUser(
                    name,
                    cmd.getParameters().getValue("email"));
        } catch (DataConnectionException e) {
            throw new HandlerException(e.getMessage(), e);
        }
    }
}
