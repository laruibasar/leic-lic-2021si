package pt.isel.ls.results;

import pt.isel.ls.config.Router;

public class OptionResult extends CommandResult {

    private Router router;

    public OptionResult() {
        router = new Router();
    }

    public OptionResult(Router router) {
        this.router = router;
    }

    @Override
    public boolean asResult() {
        return false;
    }

    @Override
    public Object getResult() {
        return router;
    }

    @Override
    public int getResultId() {
        return 0;
    }
}
