package pt.isel.ls.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Header {


    /* to store header */
    private LinkedHashMap<String, String> userHeader;

    public Header(String header) {
        userHeader = new LinkedHashMap<>();
        setValues(header);

        //Default values
        userHeader.putIfAbsent("accept","text/plain");
        userHeader.putIfAbsent("file-name","standard output");
    }

    public Header() {
        userHeader = new LinkedHashMap<>();

        //Default values
        userHeader.putIfAbsent("accept","text/plain");
        userHeader.putIfAbsent("file-name","standard output");
    }

    public void setValues(String header) {
        String[] splitHeader = header.split("\\|");
        for (int i = 0; i < splitHeader.length; i++) {
            String[] pair = splitHeader[i].split(":");
            if (pair[0].equals("accept") || pair[0].equals("file-name")) {
                userHeader.put(
                        pair[0],
                        pair.length < 2 ? "Buggy Joe" : pair[1]);
            } else {
                // non used header elements
                // maybe exception
            }
        }
    }

    public String getValue(String key) {
        return userHeader.get(key);
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

        str.deleteCharAt(str.length() - 1); // cut last "|"

        return str.toString();
    }

}
