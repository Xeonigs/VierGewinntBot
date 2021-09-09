import javax.swing.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Dave
 * @created 06/04/2021 - 19:14
 * @project VierGewinnt
 */

public class VierGewinnt {
    Player[] players;
    Table table;
    ConsoleWriter consoleWriter;

    public VierGewinnt() {
        table = new Table();
        JList list = new JList(new String[] {"1 vs. COM", "1 vs. 2"});
        JOptionPane.showMessageDialog(
                null, list, "Choose gamemode", JOptionPane.PLAIN_MESSAGE);
        if (list.getSelectedValue() == "1 vs. COM") {
            players = new Player[] {new Player(new HumanMove()), new Player(new ComputerMove())};
        }
        else {
            players = new Player[] {new Player(new HumanMove()), new Player(new HumanMove())};
        }
    }

    public void play() {
        while (table.checkForWinner() == PlaceState.open) {
            table.setTableAt(players[0].move(table), PlaceState.player1);
            consoleWriter.printTable(table);
            if (table.checkForWinner() != PlaceState.open) break;

            long start = System.currentTimeMillis();

            // Move
            table.setTableAt(players[1].move(table), PlaceState.player2);

            long elapsed = System.currentTimeMillis() - start;
            System.out.println(elapsed);
            long hour = elapsed / 3600000;
            long min = elapsed / 60000 - hour * 60;
            long sec = elapsed / 1000 - min * 60 - hour * 60;
            long milisec = elapsed % 1000;
            System.out.println("Time: " + hour + ":" + min + ":" + sec + ":" + milisec);
            consoleWriter.printTable(table);
        }

        if (table.checkForWinner() == PlaceState.player1) {
            JOptionPane.showMessageDialog(null,"Player 1 has won!");
        } else if (table.checkForWinner() == PlaceState.player2) {
            JOptionPane.showMessageDialog(null,"Player 2 has won!");
        }
    }
}
