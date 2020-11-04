package pt.isel.ls.utils;

import java.util.ArrayList;

public class Path {
    private ArrayList<String> path;

    public Path() {
        path = new ArrayList<>();
    }

    public Path(String path) {
        this();
        for (String str : path.split("/")) {
            if (!str.isEmpty()) {
                this.path.add(str);
            }
        }
    }

    public ArrayList<String> getPath() {
        return path;
    }

    public boolean equals(Path cmp) {
        if (path.size() != cmp.getPath().size()) {
            return false;
        }

        for (int i = 0; i < path.size(); i++) {
            if (path.get(i).isEmpty() || cmp.getPath().get(i).isEmpty()) {
                continue;
            }

            if (path.get(i).charAt(0) == '{' || cmp.getPath().get(i).charAt(0) == '{') {
                continue;
            }

            if (!path.get(i).equals(cmp.getPath().get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("/");
        for (String s : path) {
            str.append(s).append("/");
        }
        str.deleteCharAt(str.length() - 1); // cut last "/"

        return str.toString();
    }
}
