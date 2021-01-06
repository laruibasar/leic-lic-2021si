package pt.isel.ls.utils;

public enum Method {
    GET("GET"),
    PUT("PUT"),
    POST("POST"),
    EXIT("EXIT"),
    OPTION("OPTION"),
    DELETE("DELETE"),
    LISTEN("LISTEN");

    private final String method;

    private Method(String method) {
        this.method = method;
    }

    public boolean equals(String method) {
        return this.method.equals(method);
    }

    public static Method getMethod(String method) {
        for (Method m : values()) {
            if (m.equals(method)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return method;
    }
}
