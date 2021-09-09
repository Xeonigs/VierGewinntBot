import javax.swing.plaf.SpinnerUI;

/**
 * @author Dave
 * @created 06/04/2021 - 19:15
 * @project VierGewinnt
 */

public class Table {
    public static final int ROW = 7;
    public static final int COL = 6;
    private PlaceState[][] placeStates = new PlaceState[ROW][COL];

    public Table() {
        for (int i = 0; i < placeStates.length; i++) {
            for (int ii = 0; ii < placeStates[i].length; ii++) {
                placeStates[i][ii] = PlaceState.open;
            }
        }
    }

    public Table(PlaceState[][] gettedPlaceStates) {
        this.placeStates = new PlaceState[ROW][COL];
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                placeStates[row][col] = gettedPlaceStates[row][col];
            }
        }
    }

    public void setTableAt(int row, PlaceState placeState) {
        for (int i = 0; i < placeStates[row].length; i++) {
            if (placeStates[row][i] == PlaceState.open) {
                placeStates[row][i] = placeState;
                break;
            }
        }
    }

    public void deleteTableAt(int row) {
        for (int i = placeStates[row].length - 1; i > -1; i--) {
            if (placeStates[row][i] != PlaceState.open) {
                placeStates[row][i] = PlaceState.open;
                break;
            }
        }
    }

    public boolean checkIfFull(int row) {
        return placeStates[row][5] != PlaceState.open;
    }

    public PlaceState checkForWinner() {
        PlaceState winner = PlaceState.open;
        for (int i = 0; i < ROW; i++) {
            for (int ii = 0; ii < COL; ii++) {
                if (placeStates[i][ii] != PlaceState.open) {
                    if (i + 3 < 7) {
                        if (placeStates[i][ii] == placeStates[i + 1][ii] && placeStates[i][ii] == placeStates[i + 2][ii] && placeStates[i][ii] == placeStates[i + 3][ii]) { return placeStates[i][ii]; }
                        if (ii - 3 > - 1) {
                            if (placeStates[i][ii] == placeStates[i + 1][ii - 1] && placeStates[i][ii] == placeStates[i + 2][ii - 2] && placeStates[i][ii] == placeStates[i + 3][ii - 3]) { return placeStates[i][ii]; }
                        }
                        if (ii + 3 < 6) {
                            if (placeStates[i][ii] == placeStates[i + 1][ii + 1] && placeStates[i][ii] == placeStates[i + 2][ii + 2] && placeStates[i][ii] == placeStates[i + 3][ii + 3]) { return placeStates[i][ii]; }
                        }
                    }
                    if (i - 3 > -1) {
                        if (placeStates[i][ii] == placeStates[i - 1][ii] && placeStates[i][ii] == placeStates[i - 2][ii] && placeStates[i][ii] == placeStates[i - 3][ii]) { return placeStates[i][ii]; }
                        if (ii - 3 > - 1) {
                            if (placeStates[i][ii] == placeStates[i - 1][ii - 1] && placeStates[i][ii] == placeStates[i - 2][ii - 2] && placeStates[i][ii] == placeStates[i - 3][ii - 3]) { return placeStates[i][ii]; }
                        }
                        if (ii + 3 < 6) {
                            if (placeStates[i][ii] == placeStates[i - 1][ii + 1] && placeStates[i][ii] == placeStates[i - 2][ii + 2] && placeStates[i][ii] == placeStates[i - 3][ii + 3]) { return placeStates[i][ii]; }
                        }
                    }
                    if (ii - 3 > - 1) {
                        if (placeStates[i][ii] == placeStates[i][ii - 1] && placeStates[i][ii] == placeStates[i][ii - 2] && placeStates[i][ii] == placeStates[i][ii - 3]) { return placeStates[i][ii]; }
                    }
                    if (ii + 3 < 6) {
                        if (placeStates[i][ii] == placeStates[i][ii + 1] && placeStates[i][ii] == placeStates[i][ii + 2] && placeStates[i][ii] == placeStates[i][ii + 3]) { return placeStates[i][ii]; }
                    }
                }
            }
        }
        return winner;
    }

    public PlaceState[][] getPlaceStates() {
        return placeStates.clone();
    }

    public int berechne(int index) {
        if (index > 1)
            return index * berechne(index - 1);
        return 1;
    }
}
