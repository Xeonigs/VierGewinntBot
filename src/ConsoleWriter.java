/**
 * @author Dave
 * @created 06/04/2021 - 19:15
 * @project VierGewinnt
 */

public class ConsoleWriter {

    public static void printTable(Table table) {
        String print = "0 1 2 3 4 5 6 \n";
        PlaceState[][] placeStates = table.getPlaceStates();
        for (int ii = Table.COL - 1; ii > -1; ii--) {
            for (int i = 0; i < Table.ROW; i++) {
                if (placeStates[i][ii] == PlaceState.open)
                    print += "- ";
                else if (placeStates[i][ii] == PlaceState.player1)
                    print += "X ";
                else if (placeStates[i][ii] == PlaceState.player2)
                    print += "O ";
            }
            print += "\n";
        }
        System.out.println(print);
    }
}
