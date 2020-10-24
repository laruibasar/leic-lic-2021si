package pt.isel.ls.utils;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Parameters {
    private LinkedHashMap<String, String> parameters;

    public Parameters() {
        parameters = new LinkedHashMap<>();
    }

    public Parameters(String params) {
        this();
        String[] param = params.split("&");
        for (int i = 0; i < param.length; i++) {
            String[] pair = param[i].split("=");
            parameters.put(pair[0], pair[1]);
        }
    }

    @Override
    public String toString() {
        if (parameters.isEmpty()) {
            return "";
        }

        StringBuilder str = new StringBuilder();
        parameters.forEach((k, v) -> str
                .append(k)
                .append("=")
                .append(v)
                .append("&")
        );

        str.deleteCharAt(str.length() - 1); // cut last "&"

        return str.toString();
    }
}