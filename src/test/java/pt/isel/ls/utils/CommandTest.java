package pt.isel.ls.utils;

import org.junit.Test;

public class CommandTest {

    @Test
    public void commandTest() {
        Command cmd1 = new Command("POST /users name=Daniel+Azevedo&email=azevedo_daniel1994");
        Command cmd2 = new Command("POST /movies/240/ratings rating=8");
        Command cmd3 = new Command("POST /movies/{mid}/reviews uid=546&reviewSummary=bad&review=horriblemoviemustnotbeseen&rating=0");
        Command cmd4 = new Command("GET /movies/155/reviews");
        Command cmd5 = new Command("GET /movies/156/reviews/513");
    }

}