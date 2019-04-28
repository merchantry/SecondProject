//Class that creates indiviual words and sets their orientation on the field and position
public class Words {String word;
    int orientationArr[] = {0, 1, 2};
    static String[] givenWords = {"BOTTLE", "SCREEN", "MOUSE", "BOARD", "LASER", "CHARGE", "LIGHT"};
    int orientation;
    int positionX;
    int positionY;
    boolean isObstructed;
    boolean isLetterObstructed;
    int numberOfRowsAndColumns = Main.square;


    //Constructor that sets random orientation and a starting position depending on orientation
    Words(int index) {
        this.word = givenWords[index];
        this.orientation = orientationArr[(int)(Math.random() * orientationArr.length)];
        this.isObstructed = true;


        //Sets the position of the word and checks if it obstructs any other words
        while (this.isObstructed) {
            this.isLetterObstructed = false;
            //if orientation is horizontal
            if (this.orientation == 0) {
                this.positionX = (int) (Math.random() * (Main.mainBoard.numberOfRowsAndColumns - this.word.length()));
                this.positionY = (int) (Math.random() * (Main.mainBoard.numberOfRowsAndColumns));
                for (int i = 0; i < this.word.length(); i++) {
                    if (((Main.mainBoard.cells[this.positionY][this.positionX + i].isOverwritten) && !(Main.mainBoard.cells[this.positionY][this.positionX + i].button.getText().equals("" + this.word.charAt(i))))) {
                        this.isLetterObstructed = true;
                    }
                }
                this.isObstructed = this.isLetterObstructed;
            }

            //vertical
            else if (this.orientation == 1) {
                this.positionY = (int) (Math.random() * (Main.mainBoard.numberOfRowsAndColumns - this.word.length()));
                this.positionX = (int) (Math.random() * (Main.mainBoard.numberOfRowsAndColumns));
                for (int i = 0; i < this.word.length(); i++) {
                    if (((Main.mainBoard.cells[this.positionY + i][this.positionX].isOverwritten) && !(Main.mainBoard.cells[this.positionY + i][this.positionX].button.getText().equals("" + this.word.charAt(i))))) {
                        this.isLetterObstructed = true;
                    }
                }
                this.isObstructed = this.isLetterObstructed;
            }

            //diagonal
            else {
                this.positionY = (int) (Math.random() * (Main.mainBoard.numberOfRowsAndColumns - this.word.length()));
                this.positionX = (int) (Math.random() * (Main.mainBoard.numberOfRowsAndColumns - this.word.length()));
                for (int i = 0; i < this.word.length(); i++) {
                    if (((Main.mainBoard.cells[this.positionY + i][this.positionX + i].isOverwritten) && !(Main.mainBoard.cells[this.positionY + i][this.positionX + i].button.getText().equals("" + this.word.charAt(i))))) {
                        this.isLetterObstructed = true;
                    }
                }
                this.isObstructed = this.isLetterObstructed;
            }
        }
    }
    void reserveSpot () {
        for (int j = 0; j < this.word.length(); j++) {
            if (this.orientation == 0) {
                Main.mainBoard.cells[this.positionY][this.positionX + j].isOverwritten = true;
            } else if (this.orientation == 1) {
                Main.mainBoard.cells[this.positionY + j][this.positionX].isOverwritten = true;
            } else if (this.orientation == 2) {
                Main.mainBoard.cells[this.positionY + j][this.positionX + j].isOverwritten = true;
            }
        }
    }
}
