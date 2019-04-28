import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Board{





    //Compares these values on each button press to check if a neighbouring button is pressed
    int height;
    int width;
    int previousHeight;
    int previousWidth;

    //Number of Rows and Columns is the same so the board is square
    int numberOfRowsAndColumns;
    //Creates an array of instances of Words which are later integrated into the board
    Words[] words = new Words[Words.givenWords.length];

    String memory = "";
    String previousMemory = "";
    Words tmpWord;
    String tmpString;
    int currentOrientation;
    JLabel tmp;


    Cell cells[][];//Matrix of cells

    //Labels displaying words the player needs to find
    JLabel[] wordLabel = new JLabel[Words.givenWords.length];
    JLabel[] wordsHistory = new JLabel[5];

    //If this integer reaches 0 all words are found and the game has been won
    int winCondition = Words.givenWords.length;

    Board (int numberOfRowsAndColumns) {
        this.numberOfRowsAndColumns = numberOfRowsAndColumns;
        this.cells = new Cell[this.numberOfRowsAndColumns][this.numberOfRowsAndColumns];
    }





    //Method creating the frame in which the board is displayed
    public JFrame gameBoard () {
        JFrame mainFrame = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setVisible(true);
        mainFrame.add(this.letterCells(), BorderLayout.CENTER);
        mainFrame.add(this.wordsPanel(), BorderLayout.NORTH);
        mainFrame.add(this.hintButton(), BorderLayout.WEST);
        mainFrame.add(this.history(), BorderLayout.EAST);
        mainFrame.setBounds(500, 200, 750, 600);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return mainFrame;
    }


    JPanel letterCells () {

        JPanel mainPanel = new JPanel();
        mainPanel.setVisible(true);
        for (int rows = 0; rows < this.numberOfRowsAndColumns; rows++) {
            for (int columns = 0; columns < this.numberOfRowsAndColumns; columns++) {
                cells[rows][columns] = new Cell();
                cells[rows][columns].button.setBackground(Color.LIGHT_GRAY);
                mainPanel.add(this.cells[rows][columns].button);
                cells[rows][columns].button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        outerloop:
                        for (int i = 0; i < Main.mainBoard.numberOfRowsAndColumns; i++) {
                            for (int j = 0; j < Main.mainBoard.numberOfRowsAndColumns; j++) {
                                if (e.getSource().equals(cells[i][j].button)) {
                                    height = i;
                                    width = j;
                                    break outerloop;
                                }
                            }
                        }
                        if (memory == "") {
                            memory += cells[height][width].button.getText();
                            cells[height][width].button.setBackground(Color.WHITE);
                            previousHeight = height;
                            previousWidth = width;
                            System.out.println(memory);
                        }
                        else {
                            if (((height == previousHeight) && (width - previousWidth == 1) && ((currentOrientation == 1) || (currentOrientation == 2))) || ((height - previousHeight == 1) && (width == previousWidth) && ((currentOrientation == 0) || (currentOrientation == 2))) || ((height - previousHeight == 1) && (width - previousWidth == 1) && ((currentOrientation == 0) || (currentOrientation == 1))) || (Math.abs(height - previousHeight) > 1) || (Math.abs(width - previousWidth) > 1) || (height < previousHeight) || (width < previousWidth)) {
                                currentOrientation = -1;
                                memory = cells[height][width].button.getText();
                                for (int i = 0; i < numberOfRowsAndColumns; i++) {
                                    for (int j = 0; j < numberOfRowsAndColumns; j++) {
                                        cells[i][j].button.setBackground(Color.LIGHT_GRAY);
                                    }
                                }
                                cells[height][width].button.setBackground(Color.WHITE);
                                previousHeight = height;
                                previousWidth = width;
                            }
                            else if ((height == previousHeight) && (width - previousWidth == 1)) {
                                memory += cells[height][width].button.getText();
                                cells[height][width].button.setBackground(Color.WHITE);
                                previousHeight = height;
                                previousWidth = width;
                                currentOrientation = 0;
                                System.out.println(memory);
                            }
                            else if ((height - previousHeight == 1) && (width == previousWidth)) {
                                memory += cells[height][width].button.getText();
                                cells[height][width].button.setBackground(Color.WHITE);
                                previousHeight = height;
                                previousWidth = width;
                                currentOrientation = 1;
                                System.out.println(memory);
                            }
                            else if ((height - previousHeight == 1) && (width - previousWidth == 1)) {
                                memory += cells[height][width].button.getText();
                                cells[height][width].button.setBackground(Color.WHITE);
                                previousHeight = height;
                                previousWidth = width;
                                currentOrientation = 2;
                                System.out.println(memory);
                            }
                        }
                        for (int checkIndex = 0; checkIndex < winCondition; checkIndex++) {
                            if (memory.equals(wordLabel[checkIndex].getText())) {
                                wordLabel[checkIndex].setForeground(Color.GREEN);
                                tmp = wordLabel[winCondition - 1];
                                wordLabel[winCondition - 1] = wordLabel[checkIndex];
                                wordLabel[checkIndex] = tmp;
                                winCondition--;
                            }
                        }
                        if (winCondition == 0) {
                            JOptionPane.showMessageDialog(null, "You've won");

                        }
                        if (memory.length() < previousMemory.length()) {
                            for (int i = 0; i < wordsHistory.length - 1; i++) {
                                wordsHistory[i].setText(wordsHistory[i + 1].getText());
                            }
                            wordsHistory[wordsHistory.length - 1].setText(previousMemory);
                            previousMemory = memory;
                        }
                        else {
                            previousMemory = memory;
                        }

                    }
                });
            }
        }
        for (int i = 0; i < words.length; i++) {
            words[i] = new Words(i);
            words[i].reserveSpot();
            for (int j = 0; j < words[i].word.length(); j++) {
                if (words[i].orientation == 0) {
                    this.cells[words[i].positionY][words[i].positionX + j].button.setText("" + words[i].word.charAt(j));
                } else if (words[i].orientation == 1) {
                    this.cells[words[i].positionY + j][words[i].positionX].button.setText("" + words[i].word.charAt(j));
                } else if (words[i].orientation == 2) {
                    this.cells[words[i].positionY + j][words[i].positionX + j].button.setText("" + words[i].word.charAt(j));
                }
            }
        }

        mainPanel.setLayout(new GridLayout(this.numberOfRowsAndColumns, this.numberOfRowsAndColumns));
        return mainPanel;
    }
    JPanel wordsPanel () {
        JPanel wordsPanel = new JPanel();

        wordsPanel.setSize(600, 300);
        for (int wordNo = 0; wordNo < words.length; wordNo++) {
            wordLabel[wordNo] = new JLabel(words[wordNo].word);
            wordsPanel.add(wordLabel[wordNo]);
        }
        return wordsPanel;
    }

    JPanel history() {
        JPanel history = new JPanel();
        history.setVisible(true);
        history.setSize(150, 600);
        history.add(new JLabel("History"), BorderLayout.NORTH);
        for (int i = 0; i < wordsHistory.length; i++) {
            wordsHistory[i] = new JLabel();
            history.add(wordsHistory[i]);
        }
        history.setLayout(new GridLayout(wordsHistory.length, 1));
        return history;
    }
    JPanel hintButton (){
        JPanel hint = new JPanel();
        JButton hintButton = new JButton("Hint");
        hint.setVisible(true);
        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tmpString = wordLabel[(int)(Math.random() * winCondition)].getText();
                for (int i = 0; i < words.length; i++) {
                    if (tmpString.equals(words[i].word)) {
                        tmpWord = words[i];
                    }
                }
                Main.mainBoard.cells[tmpWord.positionY][tmpWord.positionX].button.setBackground(Color.GREEN);

            }
        });
        hint.add(hintButton);
        return hint;
    }
}
