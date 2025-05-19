package view;

import controller.Controller;
import controller.Csv;
import model.Deck;
import model.Path;
import model.Player;
import model.cards.Card;
import model.findings.Finding;
import model.pawns.Pawn;
import model.position.FindingPosition;
import model.position.Position;
import model.utilities.Image_reader;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

import static controller.Controller.deck;

/**
 * The type View.
 */
public class View extends JFrame {
    private final Player[] players = Controller.players;
    private JFrame frame;
    public static JLayeredPane player_pane1 = new JLayeredPane();
    public static JLayeredPane player_pane2 = new JLayeredPane();
    private JTextArea card_info;
    static JLayeredPane board = new JLayeredPane();
    private JLabel player1_findings;
    private JLabel player2_findings;
    private JLabel player1_score;
    private JLabel player2_score;

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Initializes the components of the view
     */
    public void initialize_components(){
        frame = new JFrame("Αναζητώντας τα Χαμένα Μινωικά Ανάκτορα");
        frame.setSize(1000, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        board.setBounds(0, 120, 980, 450);


        add_background();
        create_menu_bar();
        add_board_area();

        add_player_area();
        add_score_info();
        add_image_button();


        frame.add(board);
        frame.setVisible(true);
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Adds the background image to the frame
     */
    private void add_background() {
        ImageIcon background_icon = Image_reader.get_Image("background");
        assert background_icon != null;

        Image original_image = background_icon.getImage();
        Image scaled_image = original_image.getScaledInstance(board.getWidth(), board.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaled_icon = new ImageIcon(scaled_image);

        JLabel background_label = new JLabel(scaled_icon);
        background_label.setBounds(0, 0, board.getWidth(), board.getHeight());
        board.add(background_label, JLayeredPane.DEFAULT_LAYER);
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Creates the menu bar of the frame
     */
    private void create_menu_bar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);

        JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(_ -> Controller.saveGame("savegame.ser"));

        JMenuItem loadGame = new JMenuItem("Load Game");
        loadGame.addActionListener(_ -> Controller.loadGame("savegame.ser"));

        menu.add(saveGame);
        menu.add(loadGame);

        frame.setJMenuBar(menuBar);
    }


    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Adds the player areas to the frame
     */
    private void add_player_area() {
        player_pane1.setBounds(0, 0, 985, 125);
        player_pane1.setBorder(new LineBorder(Color.RED, 3));

        add_cards_to_player_pane(player_pane1, 0);
        add_labels_to_player_pane(player_pane1);
        frame.add(player_pane1);

        player_pane2.setBounds(0, 567, 985, 122);
        player_pane2.setBorder(new LineBorder(Color.GREEN, 3));

        add_cards_to_player_pane(player_pane2, 1);
        add_labels_to_player_pane(player_pane2);
        frame.add(player_pane2);
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Adds the labels to the player pane
     * @param player_pane The player pane to add the labels
     */
    private void add_labels_to_player_pane(JLayeredPane player_pane) {
        String[] names = {"Κνωσσός", "Μάλια", "Φαιστός", "Ζάκρος"};
        Color[] border_colors = {Color.RED, Color.YELLOW, Color.GRAY, Color.BLUE};

        for (int i = 0; i < names.length; i++) {
            JLabel label = new JLabel(names[i]);
            label.setBounds(520 + i * 70, 20, 55, 80);
            label.setBackground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 10));
            label.setBorder(new LineBorder(border_colors[i], 3));
            label.setOpaque(true);

            player_pane.add(label);
        }
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Adds the board area to the frame
     */
    private void add_board_area() {
        JLayeredPane board = new JLayeredPane();
        board.setBounds(10, 120, 980, 450);
        JLabel[] path_points = new JLabel[9];
        add_paths_to_board(board, path_points);
        add_deck_area();
        add_end_turn_button(board);
        add_excavate_button(board);
        add_show_findings_button(board);

        frame.add(board);
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Adds the deck area to the frame
     */
    private void add_deck_area() {
        Deck deck = Controller.deck;
        deck.setBounds(30, 250, 60, 80);

        deck.addActionListener(_ -> handle_draw_card(Controller.get_turn()));

        board.add(deck, JLayeredPane.PALETTE_LAYER);

        card_info = new JTextArea("Available Cards: " + deck.get_size() +
                "\nCheck Points: 0" +
                "\nTurn: " + Controller.get_turn().get_name());
        card_info.setBounds(10, 350, 120, 45);
        card_info.setFont(new Font("Arial", Font.BOLD, 10));
        card_info.setBackground(Color.WHITE);
        card_info.setEditable(false);
        card_info.setBorder(new LineBorder(Color.BLACK, 2));

        board.add(card_info, JLayeredPane.PALETTE_LAYER);
    }


    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Adds the paths to the board
     * @param board The board to add the paths
     * @param path_points The path points to add
     */
    private static void add_paths_to_board(JLayeredPane board, JLabel[] path_points) {
        int[] points = {-20, -15, -10, -5, 10, 15, 30, 35, 50};
        String[] icon_names = {"knossos", "malia", "phaistos", "zakros"};
        JLabel[][] paths = new JLabel[9][4];

        for (int i = 0; i < 9; i++) {
            path_points[i] = new JLabel(points[i] + " points");
            path_points[i].setBounds(185 + i * 80, 10, 185, 20);
            board.add(path_points[i]);

            for (int j = 0; j < 4; j++) {
                paths[i][j] = new JLabel();
                paths[i][j].setBounds(180 + i * 80, 50 + j * 100, 70, 60);

                ImageIcon icon;
                if (i % 2 == 0) {
                    icon = Image_reader.get_Image(icon_names[j], "paths");
                } else {
                    icon = Image_reader.get_Image(icon_names[j] + "2", "paths");
                }

                assert icon != null;
                Image resized_image = icon.getImage().getScaledInstance(70, 60, Image.SCALE_SMOOTH);
                ImageIcon resized_icon = new ImageIcon(resized_image);
                paths[i][j].setIcon(resized_icon);
                board.add(paths[i][j]);
                paths[8][j] = new JLabel(" Larger Path " + (8 + 1));
                paths[8][j].setBounds(200 + 8 * 120, 50 + j * 100, 100, 100);
                paths[8][j] = new JLabel();
                paths[8][j].setBounds(180 + 8 * 80, 45 + j * 100, 90, 70);
                icon = Image_reader.get_Image(icon_names[j] + "Palace", "paths");
                assert icon != null;
                resized_image = icon.getImage().getScaledInstance(90, 70, Image.SCALE_SMOOTH);
                resized_icon = new ImageIcon(resized_image);
                paths[8][j].setIcon(resized_icon);
                board.add(paths[8][j]);

                switch (icon_names[j]) {
                    case "knossos":
                        Controller.knossos_path.set_label(i, paths[i][j]);
                        break;
                    case "malia":
                        Controller.malia_path.set_label(i, paths[i][j]);
                        break;
                    case "phaistos":
                        Controller.phaistos_path.set_label(i, paths[i][j]);
                        break;
                    case "zakros":
                        Controller.zakros_path.set_label(i, paths[i][j]);
                        break;
                }
            }
        }

    }


    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Adds the show findings button to the board
     * @param board The board to add the button
     */
    private void add_show_findings_button(JLayeredPane board) {
        JButton showFindingsButton = new JButton("Findings");
        showFindingsButton.setBounds(15, 120, 100, 30);

        showFindingsButton.addActionListener(_ -> display_findings());

        board.add(showFindingsButton, JLayeredPane.PALETTE_LAYER);
    }


    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Adds the score info to the frame
     */
    private void add_score_info(){
        player1_score = new JLabel("Player 1 Score: 0 Points");
        player1_score.setBounds(820, 8, 200, 20);
        frame.add(player1_score);

        JLabel player1_info = new JLabel("Παίκτης 1 - Διαθέσιμα Πιόνια: 3 Αρχαιολόγοι και 1 Θησέας");
        player1_info.setBounds(10, 100, 400, 20);
        frame.add(player1_info);

        player1_findings = new JLabel("Αγαλματάκια: 0");
        player1_findings.setBounds(820, 80, 200, 40);
        ImageIcon icon = Image_reader.get_Image("snakes", "findings");
        assert icon != null;
        Image resized_image = icon.getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        ImageIcon resized_icon = new ImageIcon(resized_image);
        player1_findings.setIcon(resized_icon);
        frame.add(player1_findings);

        player2_score = new JLabel("Player 2 Score: 0 Points");
        player2_score.setBounds(820, 570, 200, 20);
        frame.add(player2_score);

        JLabel player2_info = new JLabel("Παίκτης 2 - Διαθέσιμα Πιόνια: 3 Αρχαιολόγοι και 1 Θησέας");
        player2_info.setBounds(10, 665, 400, 20);
        frame.add(player2_info);

        player2_findings = new JLabel("Αγαλματάκια: 0");
        player2_findings.setBounds(820, 640, 200, 40);
        icon = Image_reader.get_Image("snakes", "findings");
        assert icon != null;
        resized_image = icon.getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        resized_icon = new ImageIcon(resized_image);
        player2_findings.setIcon(resized_icon);


        frame.add(player2_findings);
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Adds the image button to the frame
     */
    private void add_image_button(){
        add_image_button(50, 1);
        add_image_button(610, 2);
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Adds the image button to the frame
     * @param y The y coordinate of the button
     * @param index The index of the player
     */
    private void add_image_button(int y, int index) {
        JButton image_button = new JButton("Show Toixografies");
        image_button.setBounds(820, y, 150, 30);

        image_button.addActionListener(_ -> {
            if (index == 1) {
                display_player1_frescos();
            } else {
                display_player2_frescos();
            }
        });

        frame.add(image_button);
    }



    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Adds the end turn button to the board
     * @param board The board to add the button
     */
    private void add_end_turn_button(JLayeredPane board) {
        JButton end_turn = new JButton("End Turn");
        end_turn.setBounds(15, 200, 100, 30);
        end_turn.addActionListener(_ -> {
            Controller.end_turn();
            update_turn_indicator();
        });


        board.add(end_turn, JLayeredPane.PALETTE_LAYER);
    }


    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Adds the excavate button to the board
     * @param board The board to add the button
     */
    private void add_excavate_button(JLayeredPane board) {
        JButton excavateButton = new JButton("Excavate");
        excavateButton.setBounds(15, 160, 100, 30);

        excavateButton.addActionListener(_ -> handle_excavation());

        board.add(excavateButton, JLayeredPane.PALETTE_LAYER);
    }


    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Adds the cards to the player pane
     * @param player_pane The player pane to add the cards
     * @param player_index The index of the player
     */
    private void add_cards_to_player_pane(JLayeredPane player_pane, int player_index) {
        ArrayList<Card> player_cards = players[player_index].get_hand();

        for (int i = 0; i < player_cards.size(); i++) {
            Card card = player_cards.get(i);

            JButton card_button = new JButton();
            card_button.setBounds(10 + i * 60, 20, 50, 80);

            ImageIcon image = card.get_card_image();
            Image scaled_image = image.getImage().getScaledInstance(50, 80, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaled_image);
            card_button.setIcon(icon);
            card_button.setBackground(Color.WHITE);

            card_button.addActionListener(_ -> handle_card_click(players[player_index], card));

            player_pane.add(card_button);

            JButton discard_button = new JButton("Dis");
            discard_button.setBounds(10 + i * 60, 5, 50, 20);
            discard_button.setFont(new Font("Arial", Font.PLAIN, 10));

            discard_button.addActionListener(_ -> handle_card_discard(players[player_index], card));

            player_pane.add(discard_button);
        }
    }


    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Displays the findings of the player
     */
    private void display_findings() {
        Player currentPlayer = Controller.get_turn();
        ArrayList<Finding> findingsList = currentPlayer.get_findings_list();

        if (findingsList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You have no findings yet.",
                    "Findings", JOptionPane.INFORMATION_MESSAGE);
            return;
        }


        StringBuilder findingsText = new StringBuilder("Your Findings:\n");
        for (Finding finding : findingsList) {
            findingsText.append("- ").append(finding.get_description()).append("\n");
        }

        JOptionPane.showMessageDialog(null, findingsText.toString(),
                "Findings", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Displays the frescos of the player
     */
    private void display_player1_frescos() {
        Player player1 = Controller.players[0];
        display_frescos_for_player(player1);
    }


    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Displays the frescos of the player
     */
    private void display_player2_frescos() {
        Player player2 = Controller.players[1];
        display_frescos_for_player(player2);
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Displays the frescos of the player
     * @param player The player to display the frescos
     */
    private void display_frescos_for_player(Player player) {
        if (player == null) {
            JOptionPane.showMessageDialog(null, "Player not found.",
                    "Toixografies", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<Finding> findingsList = player.get_findings_list();


        ArrayList<Finding> frescos = new ArrayList<>();
        for (Finding finding : findingsList) {
            if (finding instanceof model.findings.Fresco) {
                frescos.add(finding);
            }
        }

        if (frescos.isEmpty()) {
            JOptionPane.showMessageDialog(null, player.get_name() + " has no Frescos yet.",
                    "Toixografies", JOptionPane.INFORMATION_MESSAGE);
            return;
        }


        JFrame frescoFrame = new JFrame(player.get_name() + "'s Toixografies");
        frescoFrame.setSize(500, 400);
        frescoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frescoFrame.setLayout(new FlowLayout());

        for (Finding fresco : frescos) {
            JLabel imageLabel = new JLabel();
            ImageIcon frescoImage = fresco.get_image();
            Image scaledImage = frescoImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
            frescoFrame.add(imageLabel);
        }

        frescoFrame.setVisible(true);
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Handles the card discard
     * @param player The player to discard the card
     * @param card The card to discard
     */
    private void handle_card_discard(Player player, Card card) {
        if (!player.equals(Controller.get_turn())) {
            JOptionPane.showMessageDialog(null, "It's not your turn. You cannot discard cards now!",
                    "Invalid Action", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null,
                "Are you sure you want to discard this card?",
                "Discard Card",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            player.get_hand().remove(card);

            refresh_player_hand(player);

            JOptionPane.showMessageDialog(null,
                    "Card discarded successfully.",
                    "Discard Successful",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }


    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Handles the draw card
     * @param player The player to draw the card
     */
    private void handle_draw_card(Player player) {
        if (player.get_hand().size() >= 8) {
            JOptionPane.showMessageDialog(null, "Your hand is full! You cannot draw more cards.",
                    "Hand Full", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (deck.get_size() > 0) {
            Card drawnCard = deck.draw_card();
            player.get_hand().add(drawnCard);
            String name = drawnCard.get_name();
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
            drawnCard.set_pawn(player.get_pawns().get(index));
            JOptionPane.showMessageDialog(null, "You drew: " + drawnCard,
                    "Card Drawn", JOptionPane.INFORMATION_MESSAGE);

            refresh_player_hand(player);
        } else {
            JOptionPane.showMessageDialog(null, "The deck is empty!",
                    "Cannot Draw", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Handles the excavation
     */
    private void handle_excavation() {
        Player currentPlayer = Controller.get_turn();
        boolean found = false;

        for (Pawn pawn : currentPlayer.get_pawns()) {
            Position position = pawn.get_pawn_position();
            if (position instanceof FindingPosition findingPosition) {
                found = true;

                Finding finding = findingPosition.get_finding();
                if (finding != null) {
                    currentPlayer.add_findings(finding);
                    if (finding.get_description().equals("snakes")) {
                        currentPlayer.add_agalmatakia();
                    }
                    findingPosition.set_finding(null);

                    JOptionPane.showMessageDialog(null, "Excavated: " + finding.get_description(),
                            "Excavation Successful", JOptionPane.INFORMATION_MESSAGE);

                    String historicalInfo = Csv.getHistoricalInfo(finding.get_description());
                    JOptionPane.showMessageDialog(null, historicalInfo,
                            "Historical Information", JOptionPane.INFORMATION_MESSAGE);


                    refresh_player_findings(currentPlayer);
                    refresh_player_score(currentPlayer);
                    refresh_agalmatakia_count(currentPlayer);

                } else {
                    JOptionPane.showMessageDialog(null, "No finding at this position.",
                            "Excavation Failed", JOptionPane.WARNING_MESSAGE);
                }

                break;
            }
            Controller.checkGameEnd();
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "No FindingPosition under any of your pawns.",
                    "Excavation Not Possible", JOptionPane.WARNING_MESSAGE);
        }
    }


    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Refreshes the player findings
     * @param player The player to refresh the findings
     */
    private void refresh_player_findings(Player player) {
        JLabel findingsLabel = (player.get_name().equals("Player 1")) ? player1_findings : player2_findings;
        JLabel scoreLabel = (player.get_name().equals("Player 1")) ? player1_score : player2_score;

        if (findingsLabel == null || scoreLabel == null) {
            System.err.println("Findings or score label for " + player.get_name() + " is not initialized.");
            return;
        }

        long agalmatakiaCount = player.get_findings_list().stream()
                .filter(finding -> finding.get_description().equals("Snake Goddess"))
                .count();

        int totalScore = player.get_findings_list().stream()
                .mapToInt(Finding::get_score)
                .sum();

        findingsLabel.setText("Αγαλματάκια: " + agalmatakiaCount);
        scoreLabel.setText(player.get_name() + " Score: " + totalScore + " Points");

        findingsLabel.repaint();
        findingsLabel.revalidate();
        scoreLabel.repaint();
        scoreLabel.revalidate();
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Refreshes the player score
     * @param player The player to refresh the score
     */
    private void refresh_player_score(Player player) {
        JLabel scoreLabel = (player.get_name().equals("Player 1")) ? player1_findings : player2_findings;

        if (scoreLabel == null) {
            System.err.println("Score label for " + player.get_name() + " is not initialized.");
            return;
        }

        int totalScore = player.get_findings_list().stream()
                .mapToInt(Finding::get_score)
                .sum();

        scoreLabel.setText("Score: " + totalScore + " Points");
        scoreLabel.repaint();
        scoreLabel.revalidate();
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Refreshes the agalmatakia count
     * @param player The player to refresh the agalmatakia count
     */
    private void refresh_agalmatakia_count(Player player) {
        if (player.get_name().equals("Player 1")) {
            player1_findings.setText("Αγαλματάκια: " + player.get_agalmatakia());
            player1_findings.repaint();
            player1_findings.revalidate();
        } else {
            player2_findings.setText("Αγαλματάκια: " + player.get_agalmatakia());
            player2_findings.repaint();
            player2_findings.revalidate();
        }
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Handles the card click
     * @param player The player to handle the card click
     * @param card The card to handle the click
     */
    private void handle_card_click(Player player, Card card) {
        Player current_player = Controller.get_turn();
        if (!player.equals(current_player)) {
            JOptionPane.showMessageDialog(null, "It is not your turn!", "Invalid Move", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Pawn pawn = card.get_pawn();
        if (pawn == null) {
            System.err.println("Pawn is not set for card: " + card.get_name());
            return;
        }
        Path path = pawn.get_path();

        boolean move = Controller.check_move(player, card, path, pawn);

        if (move) {
            player.get_hand().remove(card);
            refresh_player_hand(player);
            Controller.end_turn();
            update_turn_indicator();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid move or card cannot be played.",
                    "Invalid Move", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Refreshes the player hand
     * @param player The player to refresh the hand
     */
    private void refresh_player_hand(Player player) {
        JLayeredPane playerPane = get_player_pane(player);
        playerPane.removeAll();

        int playerIndex = player.get_name().equals("Player 1") ? 0 : 1;

        add_cards_to_player_pane(playerPane, playerIndex);
        add_labels_to_player_pane(playerPane);

        playerPane.repaint();
        playerPane.revalidate();
    }


    /**
     * <b>Accessor</b>
     * <b>Postcondition</b>: Returns the player pane
     * @param player The player to get the pane
     * @return The player pane
     */
    public static JLayeredPane get_player_pane(Player player) {
        if (player.get_name().equals("Player 1")) {
            return player_pane1;
        } else {
            return player_pane2;
        }
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Updates the player position in the UI
     * @param pawn The pawn to update the position
     */
    public static void update_player_position_in_UI(Pawn pawn) {
        Position currentPosition = pawn.get_pawn_position();
        if (currentPosition != null) {
            Path path = pawn.get_path();
            int index = path.get_index_of_position(currentPosition);
            JLabel pathLabel = path.get_label(index);

            if (pathLabel != null) {
                ImageIcon pawnIcon = pawn.get_pawn_image();
                Image scaledImage = pawnIcon.getImage().getScaledInstance(50, 70, Image.SCALE_SMOOTH);
                pathLabel.setIcon(new ImageIcon(scaledImage));
            }
        }
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Moves the pawn
     * @param pawn The pawn to move
     * @param newPosition The new position of the pawn
     */
    public void move_pawn(Pawn pawn, Position newPosition) {
        pawn.set_pawn_position(newPosition);

        clear_previous_position(pawn);

        update_player_position_in_UI(pawn);
    }


    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Clears the opponent pawns
     * @param currentPlayer The current player
     */
    public static void clear_opponent_pawns(Player currentPlayer) {
        for (Player player : Controller.players) {
            if (!player.equals(currentPlayer)) {
                for (Pawn pawn : player.get_pawns()) {
                    Position position = pawn.get_pawn_position();
                    if (position != null) {
                        Path path = pawn.get_path();
                        int index = path.get_index_of_position(position);
                        JLabel pathLabel = path.get_label(index);

                        if (pathLabel != null) {
                            if (path.get_name().contains("knossos")) {
                                if (position instanceof FindingPosition) {
                                    pathLabel.setIcon(Image_reader.get_Image("knossos2", "paths"));
                                } else {
                                    pathLabel.setIcon(Image_reader.get_Image("knossos", "paths"));
                                }

                            } else if (path.get_name().contains("malia")) {
                                if (position instanceof FindingPosition) {
                                    pathLabel.setIcon(Image_reader.get_Image("malia2", "paths"));
                                } else {
                                    pathLabel.setIcon(Image_reader.get_Image("malia", "paths"));
                                }

                            } else if (path.get_name().contains("phaistos")) {
                                if (position instanceof FindingPosition) {
                                    pathLabel.setIcon(Image_reader.get_Image("phaistos2", "paths"));
                                } else {
                                    pathLabel.setIcon(Image_reader.get_Image("phaistos", "paths"));
                                }

                            } else {
                                if (position instanceof FindingPosition) {
                                    pathLabel.setIcon(Image_reader.get_Image("zakros2", "paths"));
                                } else {
                                    pathLabel.setIcon(Image_reader.get_Image("zakros", "paths"));
                                }
                            }
                            ImageIcon path_icon = (ImageIcon) pathLabel.getIcon();
                            Image scaledImage = path_icon.getImage().getScaledInstance(70, 60, Image.SCALE_SMOOTH);
                            ImageIcon scaledIcon = new ImageIcon(scaledImage);
                            pathLabel.setIcon(scaledIcon);
                        }
                    }
                }
            }
        }
        force_board_refresh();
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Clears the previous position of the pawn
     * @param pawn The pawn to clear the previous position
     */
    public static void clear_previous_position(Pawn pawn) {
        Position previousPosition = pawn.get_previous_position();
        if (previousPosition != null) {
            Path path = pawn.get_path();
            int index = path.get_index_of_position(previousPosition);
            JLabel pathLabel = path.get_label(index);

            if (pathLabel != null) {
                ImageIcon originalIcon = path.get_original_icon(index);
                pathLabel.setIcon(originalIcon);
            }
        }
    }


    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Forces the board refresh
     */
    public static void force_board_refresh() {
        board.repaint();
        board.revalidate();
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Updates the turn indicator
     */
    public void update_turn_indicator() {
        card_info.setText("Available Cards: " + deck.get_size() +
                "\nCheck Points: 0" +
                "\nTurn: " + Controller.get_turn().get_name());
    }

    /**
     * <b>Transformer</b>
     * <b>Postcondition</b>: Refreshes the player areas
     */
    public void refreshPlayerAreas() {
        refresh_player_hand(players[0]);
        refresh_player_findings(players[0]);
        refresh_player_score(players[0]);
        refresh_agalmatakia_count(players[0]);

        refresh_player_hand(players[1]);
        refresh_player_findings(players[1]);
        refresh_player_score(players[1]);
        refresh_agalmatakia_count(players[1]);

        player_pane1.repaint();
        player_pane1.revalidate();

        player_pane2.repaint();
        player_pane2.revalidate();
    }

    /**
     * <b>Constructor</b>
     * <b>Postcondition</b>: Creates a new View
     */
    public View(){
        initialize_components();
    }
}