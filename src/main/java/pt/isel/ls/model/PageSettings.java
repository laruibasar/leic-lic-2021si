package pt.isel.ls.model;

public class PageSettings extends Model{

    private int top;
    private int skip;

    public PageSettings(int top, int skip){
        this.top = top;
        this.skip = skip;
    }

    public int getSkip() {
        return skip;
    }

    public int getTop() {
        return top;
    }
}
