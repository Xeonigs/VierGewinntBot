import java.util.Arrays;

/**
 * @author Dave
 * @created 06/04/2021 - 19:15
 * @project VierGewinnt
 */

// Calculation for computers move
public class ComputerMove implements Move {

    // Implemented method for moves
    @Override
    public int makeMove(Table table) {
        // Normal method to run MiniMax-calculation
        return valuate(new Table(table.getPlaceStates()), 8);
    }

    // Starting method of Minimax:
    // Needs to be seperate because it doesn't return max or min value,
    // instead it returns the index of column which has the best valuations
    private int valuate(Table table, int counter) {

        // Initialize Array for valuation results
        float[] valuations = new float[Table.ROW];

        // for-loop to get all values for each column
        for (int count = 0; count < valuations.length; count++) {
            // skip full columns
            if (!table.checkIfFull(count)) {
                // simulate a move to valuate
                table.setTableAt(count, PlaceState.player2);
                // recursive method
                valuations[count] = minValue(table, counter - 1);
                // deleting previous move to go to the next move
                table.deleteTableAt(count);
            }
        }

        // Takeout the full columns to not place one there
        for (int count = 0; count < Table.ROW; count++) {
            if (table.checkIfFull(count))
                valuations[count] = -1;
        }

        // find out index of max value
        int maxIndex = 0;
        for (int i = 0; i < valuations.length; i++) {
            if (valuations[i] > valuations[maxIndex]) maxIndex = i;
        }
        return maxIndex;
    }

    private float maxValue(Table table, int counter) {
        // you literally cannot calculate all moves of Connect-4, so you need to stop at a specific depth
        if (counter == 0)
            return 0.5f;

        // check if someone won
        if (table.checkForWinner() == PlaceState.open) {
            // initialize array with all value 0 because we need to search for good values
            float[] valuations = new float[Table.ROW];
            Arrays.fill(valuations, 0.5f);

            // fill array with values
            for (int count = 0; count < valuations.length; count++) {
                // skip if the table is full
                if (!table.checkIfFull(count)) {
                    // simulate a own move
                    table.setTableAt(count, PlaceState.player2);
                    // recursive function to go one deeper
                    valuations[count] = minValue(table, counter - 1);
                    // remove previous move to set the next move
                    table.deleteTableAt(count);

                    // early return if the value cant be smaller to safe some calculations
                    if (valuations[count] == 1)
                        return 1;
                }
                else {
                    // This value will never return because there are other maxValues
                    valuations[count] = -1;
                }
            }

            float maxValue = valuations[0];
            for (int count = 0; count < valuations.length; count++) {
                if (maxValue < valuations[count])
                    maxValue = valuations[count];
            }
            return maxValue;
        }
        // if someone won check who won and return good or bad value
        else if (table.checkForWinner() == PlaceState.player2) {
            return 1;
        } else {
            return 0;
        }
    }

    private float minValue(Table table, int counter) {
        // you literally cannot calculate all moves of Connect-4 so you need to stop at a specific depth
        if (counter == 0)
            return 0.5f;

        // check if someone won
        if (table.checkForWinner() == PlaceState.open) {
            // initialize array with all value 1 because we need to search for bad values
            float[] valuations = new float[Table.ROW];
            Arrays.fill(valuations, 0.5f);

            // fill array with values
            for (int count = 0; count < valuations.length; count++) {
                // skip if the table is full
                if (!table.checkIfFull(count)) {
                    // simulate a enemy move
                    table.setTableAt(count, PlaceState.player1);
                    // recursive function to go one deeper
                    valuations[count] = maxValue(table, counter - 1);
                    // remove previous move to set the next move
                    table.deleteTableAt(count);

                    // early return if the value cant be smaller to safe some calculations
                    if (valuations[count] == 0)
                        return 0;
                }
                else {
                    // This value will never return because there are other minValues
                    valuations[count] = 2;
                }
            }
            if (counter == 7)
                System.out.println("awdawd");

            float minValue = valuations[0];
            for (int count = 0; count < valuations.length; count++) {
                if (minValue > valuations[count])
                    minValue = valuations[count];
            }
            return minValue;
        }
        // if someone won check who won and return good or bad value
        else if (table.checkForWinner() == PlaceState.player2) {
            return 1;
        } else {
            return 0;
        }
    }
}
