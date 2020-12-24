package pt.isel.ls.utils;

import java.util.LinkedHashMap;

public class Header {
    /* to store header */
    private LinkedHashMap<String, String> userHeader;

    public Header(String header) {
        this();
        setValues(header);
    }

    public Header() {
        userHeader = new LinkedHashMap<>();

        //Default values
        userHeader.put("accept","text/plain");
        userHeader.put("file-name", "");

    }

    public void setValues(String header) {
        String[] splitHeader = header.split("\\|");
        for (int i = 0; i < splitHeader.length; i++) {
            String[] pair = splitHeader[i].split(":");
            switch (pair[0]) {
                case "accept":
                    if (pair.length == 2) {
                        userHeader.replace(
                                pair[0],
                                pair[1].equals("text/html") ?  "text/html" : "text/plain");
                        break;
                    }
                    break;
                case "file-name":
                    userHeader.replace(
                            pair[0],
                            pair.length < 2 ? "" : pair[1]);
                    break;
                default:
                    // non used header elements
                    break;
            }
        }
    }

    public LinkedHashMap<String, String> getHeaders() {
        return userHeader;
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
