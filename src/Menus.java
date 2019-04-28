import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
i
public class Menus {

    //First menu which allows several options to be picked
    public JFrame mainMenu () {
        JFrame mainMenu = new JFrame();
        mainMenu.setBounds(500, 300, 150, 300);
        mainMenu.setVisible(true);
        mainMenu.setResizable(false);
        JButton exitGame = new JButton("Exit Game");
        exitGame.setVisible(true);
        exitGame.setBounds(15, 60, 120, 20);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton showGame = new JButton("New Game");
        showGame.setVisible(true);
        showGame.setBounds(15, 30, 120, 20);
        showGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mainMenu.setVisible(false);
            }
        });
        mainMenu.add(showGame);
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mainMenu.add(exitGame);
        return mainMenu;
    }
    /*
    class SetEasyDifficulity implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            createBoard(12);

        }
    }
    class SetMediumDifficulity implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Board mainBoard = new Board(12);
            mainBoard.gameBoard();
        }
    }
    class SetHardDifficulity implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Board mainBoard = new Board(12);
            mainBoard.gameBoard();
        }
    }

     public JFrame difficulity () {
        JFrame difficulity = new JFrame("Select difficulity");
        JPanel selectDifficulity = new JPanel();
        SetEasyDifficulity setEasy = new SetEasyDifficulity();
        SetMediumDifficulity setMedium = new SetMediumDifficulity();
        SetHardDifficulity setHard = new SetHardDifficulity();
        difficulity.add(selectDifficulity);
        difficulity.setBounds(500, 300, 300, 150);
        difficulity.setVisible(true);
        difficulity.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton easy = new JButton("Easy");
        JButton medium = new JButton("Medium");
        JButton hard = new JButton("Hard");
        easy.addActionListener(setEasy);
        medium.addActionListener(setMedium);
        hard.addActionListener(setHard);
        selectDifficulity.add(easy);
        selectDifficulity.add(medium);
        selectDifficulity.add(hard);
        return difficulity;
    }*/
}
