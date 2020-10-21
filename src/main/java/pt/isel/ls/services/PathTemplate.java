package pt.isel.ls.services;

public class PathTemplate {

    private String path;

    public PathTemplate(String path) {
        String[] subdirectories = path.split("/");
        switch (subdirectories[1]) {
            case "users":
                path = "/users";
                break;
            case "movies":
                path = "/movies";
                break;
            case "tops":
                path = "/tops";
                break;
            default:
                System.out.println("Invalid path");
        }
    }
}
