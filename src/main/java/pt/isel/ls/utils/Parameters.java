package pt.isel.ls.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Parameters {
    /* to store user sent parameters */
    private LinkedHashMap<String, String> userParameters;

    public Parameters() {
        userParameters = new LinkedHashMap<>();
    }

    public void setValues(String params) {
        String[] param = params.split("&");
        for (int i = 0; i < param.length; i++) {
            String[] pair = param[i].split("=");
            userParameters.put(
                    pair[0],
                    pair.length < 2 ? "" : pair[1].replace("+", " "));
        }
    }

    public String getValue(String key) {
        return userParameters.get(key);
    }

    public LinkedHashMap<String, String> getParameters() {
        return userParameters;
    }

    public boolean isValid(ArrayList<String> checklist) {
        for (String str : checklist) {
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