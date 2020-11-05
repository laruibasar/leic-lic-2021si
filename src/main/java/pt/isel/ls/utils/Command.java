package pt.isel.ls.utils;

public class Command {
    private Method method;
    private Path path;
    private Parameters parameters;

    public Command(Method method, Path path) {
        this(method, path, new Parameters());
    }

    public Command(Method method, Path path, Parameters parameters) {
        this.method = method;
        this.path = path;
        this.parameters = parameters;
    }

    public Command() { }

    public Method getMethod() {
        return this.method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Path getPath() {
        return this.path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters params) {
        parameters = params;
    }

    public boolean matches(Command command) {
        if (!this.method.equals(command.getMethod())) {
            return false;
        }

        if (!this.path.matches(command.getPath())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(method.toString());
        str.append(" ");

        str.append(path.toString());

        if (!parameters.isEmpty()) {
            str.append(" ").append(parameters.toString());
        }

        return str.toString();
    }
}
