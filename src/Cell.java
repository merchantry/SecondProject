import javax.swing.*;


//class for cells displayed on the field
public class Cell {
    char letter;
    JButton button;
    boolean isOverwritten;
    char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    //constructor that gives each button a random letter
    Cell () {
        this.letter = letters[(int)(Math.random() * letters.length)];
        this.button = new JButton("" + this.letter);
    }

}