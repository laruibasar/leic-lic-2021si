package pt.isel.ls.services;

public class GetUsersHandler implements CommandHandler {
    private int id;

    public GetUsersHandler() { }

    public GetUsersHandler(int id) {
        this.id = id;
    }

//    @Override
//    public int execute() {
//        if (id == null) {
//            return 1;
//        } else {
//            return 0;
//        }
//    }



    @Override
    public void execute(Object command) {
    }
}
