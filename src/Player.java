import java.awt.image.CropImageFilter;

/**
 * @author Dave
 * @created 06/04/2021 - 19:15
 * @project VierGewinnt
 */

public class Player {
    private Move move;

    public Player(Move move) {
        this.move = move;
    }

    public int move(Table table) {
        return move.makeMove(table);
    }
}
