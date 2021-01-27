package pt.isel.ls.config;

public class HttpServletConfig {
    private static final int DEFAULT_PORT = 8080;
    private int port;

    public HttpServletConfig() {
        String getPort = System.getenv("PORT");
        System.out.println("DEBUG: " + getPort);
        if (getPort == null || Integer.getInteger(getPort) == 0) {
            port = DEFAULT_PORT;
        } else {
            port = Integer.getInteger(getPort);
        }
    }

    public int getPort() {
        return port;
    }
}
