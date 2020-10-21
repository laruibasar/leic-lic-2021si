package pt.isel.ls.services;


/***
 * Interface responsible for taking any kind of command
 * @param <T>
 */
public interface CommandHandler <T> {

    int execute(T command);
}
