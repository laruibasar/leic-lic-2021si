package pt.isel.ls.services;

public class GetUsersHandler implements CommandHandler {

    private int id = 0;

    public GetUsersHandler() { }

    public GetUsersHandler(int id) {
        this.id = id;
    }

    @Override
    public int execute(Object command) {
        if (id == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
