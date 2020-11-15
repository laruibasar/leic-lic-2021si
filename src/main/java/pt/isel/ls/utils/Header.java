package pt.isel.ls.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Header {

    /* store valid parameters for the command */
    private ArrayList<String> validHeader;

    /* to store user sent parameters */
    private LinkedHashMap<String, String> userHeader;

    public Header() {
        validHeader = new ArrayList<>();
        userHeader = new LinkedHashMap<>();
    }

    /* To receive the acceptable parameters from the method */
    public Header(String[] params) {
        this();
        for (int i = 0; i < params.length; i++) {
            validHeader.add(params[i]);
        }
    }

    public void setValues(String params) {
        String[] param = params.split("|");
        for (int i = 0; i < param.length; i++) {
            String[] pair = param[i].split(":");
            userHeader.put(
                    pair[0],
                    pair.length < 2 ? "Buggy Joe" : pair[1]);
        }
    }

    public String getValue(String key) {
        return userHeader.get(key);
    }

    private LinkedHashMap<String, String> getUserHeader() {
        return userHeader;
    }


    public boolean isValid(Header header) {
        for (String str : validHeader) {
            if (!header.getUserHeader().containsKey(str)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        return userHeader.isEmpty();
    }

    @Override
    public String toString() {
        if (userHeader.isEmpty()) {
            return "";
        }

        StringBuilder str = new StringBuilder();
        userHeader.forEach((k, v) -> str
                .append(k)
                .append(":")
                .append(v)
                .append("|")
        );

        str.deleteCharAt(str.length() - 1); // cut last "&"

        return str.toString();
    }

}
