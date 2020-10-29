package pt.isel.ls.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Parameters {
    /* store valid parameters for the command */
    private ArrayList<String> validParameters;

    /* to store user sent parameters */
    private LinkedHashMap<String, String> userParameters;

    public Parameters() {
        validParameters = new ArrayList<>();
        userParameters = new LinkedHashMap<>();
    }

    /* To receive the acceptable parameters from the method */
    public Parameters(String[] params) {
        this();
        for (int i = 0; i < params.length; i++) {
            validParameters.add(params[i]);
        }
    }

    public void setValues(String params) {
        String[] param = params.split("&");
        for (int i = 0; i < param.length; i++) {
            String[] pair = param[i].split("=");
            userParameters.put(pair[0], pair[1]);
        }
    }

    public boolean matches() {
        for (String str : validParameters) {
            if (!userParameters.containsKey(str)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        return userParameters.isEmpty();
    }

    @Override
    public String toString() {
        if (userParameters.isEmpty()) {
            return "";
        }

        StringBuilder str = new StringBuilder();
        userParameters.forEach((k, v) -> str
                .append(k)
                .append("=")
                .append(v)
                .append("&")
        );

        str.deleteCharAt(str.length() - 1); // cut last "&"

        return str.toString();
    }
}