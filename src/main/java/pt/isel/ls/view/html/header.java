package pt.isel.ls.view.html;

public class header extends body{

    private String header;

    public header(String header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "\t\t<h1>"+header+"</h1>\n";
    }
}
