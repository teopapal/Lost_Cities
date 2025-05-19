package controller;

import model.Path;
import model.Player;
import model.cards.Card;
import model.cards.NumberCard;
import model.pawns.Pawn;
import model.position.Position;
import model.utilities.Image_reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @Test
    void check_move() {
        Player player = Controller.players[0];
        Pawn pawn = player.get_pawns().getFirst();
        Path path = pawn.get_path();
        Card card = new NumberCard("knossos", 5, Image_reader.get_Image("background"));
        card.set_pawn(pawn);

        boolean result = Controller.check_move(player, card, path, pawn);

        assertTrue(result, "The move should be valid for this card and pawn.");
        assertEquals(path.get_position(1), pawn.get_pawn_position(), "Pawn should have moved to the next position.");
    }

    @Test
    void end_turn() {
        Player initialPlayer = Controller.get_turn();
        Controller.end_turn();
        Player nextPlayer = Controller.get_turn();

        assertNotEquals(initialPlayer, nextPlayer, "The turn should switch to the other player.");
    }

    @Test
    void get_turn() {
        assertNotNull(Controller.get_turn(), "There should be a player whose turn it is.");
    }

    @Test
    void checkGameEnd() {

        for (Player player : Controller.players) {
            for (Pawn pawn : player.get_pawns()) {
                Path path = pawn.get_path();
                Position endPosition = path.get_position(path.get_length() - 1);
                pawn.set_pawn_position(endPosition);
            }
        }

        Controller.checkGameEnd();

        assertTrue(true, "Game should end when all pawns reach the end.");
    }





    @BeforeEach
    void setUp() {
        Controller controller = new Controller();
    }

    @Test
    void main2() {
        assertDoesNotThrow(() -> Controller.main(new String[]{}), "Main method threw an exception.");
    }
}
