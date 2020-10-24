package pt.isel.ls.utils;

import java.util.Hashtable;

public class Parameters {
    private Hashtable<String, String> parameters;

    public Parameters() {
        parameters = new Hashtable<>();
    }

    public Parameters(String params) {
        this();
        String[] param = params.split("&");
        for (int i = 0; i < param.length; i++) {
            String[] pair = param[i].split("=");
            parameters.put(pair[0], pair[1]);
        }
    }
}