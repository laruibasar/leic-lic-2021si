package pt.isel.ls.config;

import pt.isel.ls.data.common.Data;
import pt.isel.ls.data.common.DataConnection;

public class DataBaseConfig {
    private String jdbcUrl;

    public DataBaseConfig() throws EnvironmentVariableException {
        jdbcUrl = System.getenv("JDBC_DATABASE_URL");
        if (jdbcUrl == null || jdbcUrl.length() == 0) {
            throw new EnvironmentVariableException("DATABASE_URL not set");
        }
        Data.setDataConnection(new DataConnection(jdbcUrl));
    }

    public String getUrl() {
        return jdbcUrl;
    }
}
