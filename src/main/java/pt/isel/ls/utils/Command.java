package pt.isel.ls.utils;

public class Command {
    private String method;
    private String[] path;
    private Parameters parameters;

    public Command(String method, String path) {
        this(method, path, new Parameters());
    }

    public Command(String method, String path, Parameters parameters) {
        this.method = method;
        this.path = path.split("/", 0);
        this.parameters = parameters;
    }

    public String getMethod() {
        return this.method;
    }

    public String[] getPath() {
        return this.path;
    }

    public boolean equals(Command command) {
        if (!this.method.equals(command.getMethod()))
            return false;

        String[] aux = command.getPath();
        if (this.path.length != aux.length)
            return false;

        for (int i = 0; i < this.path.length; i++) {
            if (path[i].isEmpty() || aux[i].isEmpty()) {
                continue;
            }

            if (path[i].charAt(0) == '{' || aux[i].charAt(0) == '{') {
                continue;
            }

            if (!path[i].equals(aux[i])) {
                return false;
            }
        }

        return true;
    }
}
