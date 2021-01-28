package pt.isel.ls.config;

public class HttpServletConfig {
    private static final int DEFAULT_PORT = 8080;
    private int port;

    public HttpServletConfig() {
        String getPort = System.getenv("PORT");
        if (getPort == null || Integer.parseInt(getPort) == 0) {
            port = DEFAULT_PORT;
        } else {
            port = Integer.parseInt(getPort);
        }
    }

    public int getPort() {
        return port;
    }
}
