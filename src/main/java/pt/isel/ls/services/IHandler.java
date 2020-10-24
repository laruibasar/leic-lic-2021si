package pt.isel.ls.services;


/***
 * Interface implemented by all handlers
 * @param <T>
 */
public interface IHandler<T> {

    int execute(T command);

    int execute();
}
