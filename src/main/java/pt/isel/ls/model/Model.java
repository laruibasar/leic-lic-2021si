package pt.isel.ls.model;

public abstract class Model {

    public final int selector;

    public Model(int selector) {
        this.selector = selector;
    }

    public Model() {
        this(0);
    }

    public abstract String toString();

}
