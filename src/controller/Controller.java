package controller;

import model.Deck;
import model.Path;
import model.Player;
import model.cards.Card;
import model.findings.Finding;
import model.findings.Fresco;
import model.findings.RareFinding;
import model.findings.SnakeGoddess;
import model.pawns.Archeologist;
import model.pawns.Pawn;
import model.pawns.Theseus;
import model.position.FindingPosition;
import model.position.Position;
import model.position.SimplePosition;
import model.utilities.Image_reader;
import view.View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import static java.util.Collections.shuffle;


/**
 * <b>Controller</b> is the class that controls the game. It initializes the components of the game, deals the cards to the players, checks if the move is valid, switches the turn of the player, starts the turn timer, saves the game, loads the game, plays the music, checks if the game has ended, determines the winner of the game and starts the game.
 * <p>Controller contains the following operations:</p>
 * <ul>
 *     <li>Initialize the components of the game</li>
 *     <li>Sets the pawns of the player</li>
 *     <li>Initializes the paths of the game</li>
 *     <li>Sets the path of the game</li>
 *     <li>Deals the cards to the player</li>
 *     <li>Checks if the move is valid</li>
 *     <li>Switches the turn of the player</li>
 *     <li>Starts the turn timer</li>
 *     <li>Saves the game</li>
 *     <li>Loads the game</li>
 *     <li>Returns the player whose turn it is</li>
 *     <li>Plays the music</li>
 *     <li>Checks if the game has ended</li>
 *     <li>Determines the winner of the game</li>
 *     <li>Starts the game</li>
 * </ul>
 */
public class Controller {
    public static Player[] players = new Player[2];
    private static final ArrayList<Finding> findings = new ArrayList<>(20);
    public static boolean turn;
    public static Deck deck;
    private static View view;
    public static Path knossos_path;
    public static Path malia_path;
    public static Path phaistos_path;
    public static Path zakros_path;
    private static final HashMap<Path, Integer> last_played_card_value_by_path = new HashMap<>();
    private static Timer turn_timer;
    private static Clip backgroundMusic;



    /**<b>Transformer</b>
     * <b>Postcondition</b> Initializes the components of the game
     */
    static void initialize_components() {
        players[0] = new Player("Player 1");
        players[1] = new Player("Player 2");

        last_played_card_value_by_path.put(knossos_path, -1);
        last_played_card_value_by_path.put(malia_path, -1);
        last_played_card_value_by_path.put(phaistos_path, -1);
        last_played_card_value_by_path.put(zakros_path, -1);


        findings.add(new Fresco("fresco1", Image_reader.get_Image("fresco1_20", "findings"), 20));
        findings.get(0).set_position(new FindingPosition(20, findings.get(0)));

        findings.add(new Fresco("fresco2", Image_reader.get_Image("fresco2_20", "findings"), 20));
        findings.get(1).set_position(new FindingPosition(20, findings.get(1)));

        findings.add(new Fresco("fresco3", Image_reader.get_Image("fresco3_15", "findings"), 15));
        findings.get(2).set_position(new FindingPosition(15, findings.get(2)));

        findings.add(new Fresco("fresco4", Image_reader.get_Image("fresco4_20", "findings"), 20));
        findings.get(3).set_position(new FindingPosition(20, findings.get(3)));

        findings.add(new Fresco("fresco5", Image_reader.get_Image("fresco5_15", "findings"), 15));
        findings.get(4).set_position(new FindingPosition(15, findings.get(4)));

        findings.add(new Fresco("fresco6", Image_reader.get_Image("fresco6_15", "findings"), 15));
        findings.get(5).set_position(new FindingPosition(15, findings.get(5)));

        findings.add(new RareFinding("diskos", Image_reader.get_Image("diskos", "findings"), 35));
        findings.get(6).set_position(new FindingPosition(35, findings.get(6)));

        findings.add(new RareFinding("ring", Image_reader.get_Image("ring", "findings"), 25));
        findings.get(7).set_position(new FindingPosition(25, findings.get(7)));

        findings.add(new RareFinding("kosmima", Image_reader.get_Image("kosmima", "findings"), 25));
        findings.get(8).set_position(new FindingPosition(25, findings.get(8)));

        findings.add(new RareFinding("ruto", Image_reader.get_Image("ruto", "findings"), 25));
        findings.get(9).set_position(new FindingPosition(25, findings.get(9)));

        for (int i = 0; i < 10; i++) {
            findings.add(new SnakeGoddess("snakes", Image_reader.get_Image("snakes", "findings"), 0));
            findings.get(i + 10).set_position(new FindingPosition(0, findings.get(i + 10)));
        }


        shuffle(findings);

        initialize_paths();

        set_pawn(players[0].get_pawns(), players[0]);
        set_pawn(players[1].get_pawns(), players[1]);

    }

    /**<b>Transformer</b>
     * <b>Postcondition</b> Sets the pawns of the player
     * @param pawns The pawns of the player
     * @param owner The owner of the pawns
     */
    private static void set_pawn(ArrayList<Pawn> pawns, Player owner) {
        Pawn arch1 = new Archeologist(new SimplePosition(0), Image_reader.get_Image("arch", "pionia"), knossos_path);
        arch1.set_owner(owner);
        arch1.set_pawn_position(new SimplePosition(0));
        pawns.add(arch1);

        Pawn arch2 = new Archeologist(new SimplePosition(0), Image_reader.get_Image("arch", "pionia"), malia_path);
        arch2.set_owner(owner);
        arch2.set_pawn_position(new SimplePosition(0));
        pawns.add(arch2);

        Pawn arch3 = new Archeologist(new SimplePosition(0), Image_reader.get_Image("arch", "pionia"), phaistos_path);
        arch3.set_owner(owner);
        arch3.set_pawn_position(new SimplePosition(0));
        pawns.add(arch3);

        Pawn theseus = new Theseus(new SimplePosition(0), Image_reader.get_Image("theseus", "pionia"), zakros_path);
        theseus.set_owner(owner);
        theseus.set_pawn_position(new SimplePosition(0));
        pawns.add(theseus);
    }


    /**<b>Transformer</b>
     * <b>Postcondition</b> Initializes the paths of the game
     */
    private static void initialize_paths() {
        int[] points_path = {-20, -15, -10, 5, 10, 15, 30, 35, 50};
        knossos_path = new Path("knossos", 9);
        set_path(points_path, knossos_path);

        malia_path = new Path("malia", 9);
        set_path(points_path, malia_path);

        phaistos_path = new Path("phaistos", 9);
        set_path(points_path, phaistos_path);

        zakros_path = new Path("zakros", 9);
        set_path(points_path, zakros_path);
    }

    /**<b>Transformer</b>
     * <b>Postcondition</b> Sets the path of the game
     * @param points_path The points of the path
     * @param path The path
     */
    private static void set_path(int[] points_path, Path path) {
        for (int i = 0; i < 9; i++) {
            if ((i % 2 == 1 || i == 8) && i < findings.size()) {
                FindingPosition findingPosition = new FindingPosition(findings.get(i).get_position().get_score() + points_path[i], findings.get(i));
                findingPosition.set_finding(findings.get(i));
                path.set_position(i, findingPosition);
            } else {
                path.set_position(i, new SimplePosition(points_path[i]));
            }
        }
    }

    /**<b>Transformer</b>
     * <b>Postcondition</b> Deals the cards to the player
     * @param deck The deck of the game
     * @param player The player
     */
    private void deal_cards_to_player(Deck deck, Player player) {
        if (deck.get_size() < 16) {
            System.out.println("Not enough cards in the deck to deal.");
            return;
        }
        ArrayList<Card> player_cards = player.get_hand();

        player.clear_hand();

        for (int i = 0; i < 8; i++) {
            Card card = deck.get_deck().removeFirst();
            String name = card.get_name();
            int index;
            if (name.contains("knossos")) {
                index = 0;
            } else if (name.contains("malia")) {
                index = 1;
            } else if (name.contains("phaistos")) {
                index = 2;
            } else {
                index = 3;
            }
            card.set_pawn(player.get_pawns().get(index));
            player_cards.add(card);
        }

        player.set_hand(player_cards);

        System.out.println(player.get_name() + " has " + player.get_hand().size() + " cards in hand.");
    }

    /**<b>Accessor</b>
     * <b>Postcondition</b> checks if the move is valid
     * @param player The player
     * @param card The card
     * @param pawn The pawn
     * @return true if the move is valid, false otherwise
     */
    public static boolean check_move(Player player, Card card, Path path, Pawn pawn) {
        Position current_position = pawn.get_pawn_position();
        int current_index = path.get_index_of_position(current_position);

        if (card.get_name().toLowerCase().contains("min")) {
            Player opponent = (player == players[0]) ? players[1] : players[0];
            for (Pawn opponentPawn : opponent.get_pawns()) {
                if (opponentPawn.get_path() == path) {
                    int opponent_index = path.get_index_of_position(opponentPawn.get_pawn_position());
                    int new_opponent_index = Math.max(opponent_index - 2, 0);

                    Position new_position = path.get_position(new_opponent_index);
                    opponentPawn.move_pawn(new_position);

                    System.out.println(opponent.get_name() + "'s pawn moved back to position " +
                            new_opponent_index + " on " + path.get_name());
                    return true;
                }
            }

            System.out.println("No opponent's pawn found on path " + path.get_name() + " for Minotaur card.");
            return false;
        }

        if (card.get_name().toLowerCase().contains("ari")) {
            int new_index = current_index + 2;
            if (new_index >= path.get_length()) {
                new_index = path.get_length() - 1;
            }

            Position new_position = path.get_position(new_index);
            pawn.move_pawn(new_position);

            System.out.println(player.get_name() + "'s pawn moved forward to position " +
                    new_index + " on " + path.get_name());
            return true;
        }

        int next_index = current_index + 1;

        int lastPlayedValue = last_played_card_value_by_path.getOrDefault(path, -1);

        System.out.println("Last played value for " + path.get_name() + ": " + lastPlayedValue);

        if (lastPlayedValue != -1 && card.get_number() < lastPlayedValue) {
            System.out.println(player.get_name() + " cannot play card " + card.get_number() +
                    " for path " + path.get_name() + " because it is lower than the last played card " + lastPlayedValue);
            System.out.println();
            return false;
        }

        if (next_index >= path.get_length()) {
            System.out.println(player.get_name() + " cannot move further on " + path.get_name());
            return false;
        }

        Position new_position = path.get_position(next_index);
        pawn.move_pawn(new_position);

        last_played_card_value_by_path.put(path, card.get_number());

        checkGameEnd();

        System.out.println(player.get_name() + " moved to position " + next_index + " on " + path.get_name());
        return true;
    }


    /**<b>Transformer</b>
     * <b>Postcondition</b> switched the turn of the player
     */
    public static void end_turn() {
        turn = !turn;
        Player currentPlayer = get_turn();

        startTurnTimer(currentPlayer);

        View.clear_opponent_pawns(currentPlayer);
        for (Pawn pawn : currentPlayer.get_pawns()) {
            View.update_player_position_in_UI(pawn);
        }

        View.force_board_refresh();
        checkGameEnd();
    }

    /**<b>Transformer</b>
     * <b>Postcondition</b> Starts the turn timer
     * @param currentPlayer The current player
     */
    private static void startTurnTimer(Player currentPlayer) {
        if (turn_timer != null) {
            turn_timer.stop();
        }

        turn_timer = new Timer(30000, _ -> {
            JOptionPane.showMessageDialog(null, currentPlayer.get_name() + "'s turn has been skipped due to time limit.",
                    "Time Expired", JOptionPane.WARNING_MESSAGE);
            Controller.end_turn();
            view.update_turn_indicator();
        });

        turn_timer.setRepeats(false);
        turn_timer.start();
    }

    /**<b>Transformer</b>
     * <b>Postcondition</b> Saves the game
     * @param fileName The name of the file
     */
    public static void saveGame(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(players);
            oos.writeObject(deck);
            oos.writeObject(knossos_path);
            oos.writeObject(malia_path);
            oos.writeObject(phaistos_path);
            oos.writeObject(zakros_path);
            oos.writeObject(turn);
            System.out.println("Game saved successfully to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving game: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**<b>Transformer</b>
     * <b>Postcondition</b> Loads the game
     * @param fileName The name of the file
     */
    public static void loadGame(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            players = (Player[]) ois.readObject();
            deck = (Deck) ois.readObject();
            turn = (Boolean) ois.readObject();
            knossos_path = (Path) ois.readObject();
            malia_path = (Path) ois.readObject();
            phaistos_path = (Path) ois.readObject();
            zakros_path = (Path) ois.readObject();
            JOptionPane.showMessageDialog(null, "Game loaded successfully.", "Load Game", JOptionPane.INFORMATION_MESSAGE);

            view.refreshPlayerAreas();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error loading game: " + e.getMessage(), "Load Game", JOptionPane.ERROR_MESSAGE);
        }
    }


    /**<b>Accessor</b>
     * <b>Postcondition</b> Returns the player whose turn it is
     */
    public static Player get_turn() {
        if (turn) {
            playMusic(Objects.requireNonNull(Image_reader.class.getResource("/resources/music/Player1.wav")).getPath());
            return players[0];
        } else {
            playMusic(Objects.requireNonNull(Image_reader.class.getResource("/resources/music/Player2.wav")).getPath());
            return players[1];
        }
    }

    /**<b>Transformer</b>
     * <b>Postcondition</b> Plays the music
     * @param musicFilePath The path of the music file
     */
    private static void playMusic(String musicFilePath) {
        try {
            if (backgroundMusic != null && backgroundMusic.isRunning()) {
                backgroundMusic.stop();
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(musicFilePath));
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioStream);
            backgroundMusic.start();
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println("Error playing music: " + e.getMessage());
        }
    }

    /**<b>Transformer</b>
     * <b>Postcondition</b> Checks if the game has ended
     */
    public static void checkGameEnd() {
        boolean allPawnsAtEnd = true;

        for (Player player : players) {
            for (Pawn pawn : player.get_pawns()) {
                if (!pawn.isAtEnd()) {
                    allPawnsAtEnd = false;
                    break;
                }
            }
        }

        if (allPawnsAtEnd) {
            Player winner = determineWinner();
            String message = (winner != null)
                    ? "Game Over! Winner: " + winner.get_name()
                    : "Game Over! It's a tie!";
            JOptionPane.showMessageDialog(null, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    /**<b>Accessor</b>
     * <b>Postcondition</b> Determines the winner of the game
     */
    private static Player determineWinner() {
        int score1 = players[0].get_score();
        int score2 = players[1].get_score();

        if (score1 > score2) return players[0];
        if (score2 > score1) return players[1];
        return null;
    }

    /**
     * <b>Constructor</b>
     * <b>Postcondition</b> Creates a new Controller with 2 players, a board and initializes the components.
     */
    public Controller() {
        initialize_components();
        Random random = new Random();
        turn = random.nextBoolean();

        deck = new Deck();
        deck.set_cards(deck.get_deck());

        deal_cards_to_player(deck, players[0]);
        deal_cards_to_player(deck, players[1]);
        System.out.println("Available cards: " + deck.get_size());

        view = new View();
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b> Starts the game
     * @param args The arguments
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        Csv.loadHistoricalInfo();
    }


}
