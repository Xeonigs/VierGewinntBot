import javax.swing.*;

/**
 * @author Dave
 * @created 06/04/2021 - 19:15
 * @project VierGewinnt
 */

public class HumanMove implements Move {
    @Override
    public int makeMove(Table table) {
        int value = Integer.MIN_VALUE;
        while (value > 6 || value < 0) {
            try {
                value = Integer.parseInt(JOptionPane.showInputDialog("Enter value from 0 to 6"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Input couldn't recognize a number!");
                value = Integer.MIN_VALUE;
                continue;
            }
            if ((value > 6 || value < 0) && value != Integer.MIN_VALUE)
                JOptionPane.showMessageDialog(null,"Input wasn't in range from 0 to 6!");
            if (value > -1 && value < 7)
                if (table.checkIfFull(value))
                    JOptionPane.showMessageDialog(null,"This row is already full!");
        }
        return value;
    }
}
