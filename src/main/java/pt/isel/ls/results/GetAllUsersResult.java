package pt.isel.ls.results;

import pt.isel.ls.model.Model;

import java.util.LinkedList;
import java.util.List;

public class GetAllUsersResult extends CommandResult {

    private List<Model> users = new LinkedList<>();

    public GetAllUsersResult() {

    }

    public GetAllUsersResult(List<Model> users) {
        this.users = users;
    }

    @Override
    public boolean asResult() {
        return !users.isEmpty();
    }

    @Override
    public Object getResult() {
        return users;
    }

    @Override
    public int getResultId() {
        return 0;
    }
}
