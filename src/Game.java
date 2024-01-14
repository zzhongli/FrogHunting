import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.random.RandomGenerator;
import javax.swing.JOptionPane;



public class Game extends JFrame implements ActionListener {
    private JFrame frame;
    private JPanel buttonMatrixPanel = new JPanel();
    ImageIcon frogIcon = new ImageIcon("src/frog.jpg");
    ImageIcon greyIcon = new ImageIcon("src/grey.png");
    ImageIcon buttonFrog = new ImageIcon("src/88.png");



    private JButton[][] buttonlist = new JButton[5][5];

    private boolean[][] frogList = new boolean[5][5];  // should be list
    // private int answer;

    private int ClickerCounter = 0;
    private int FrogCounter = 0;






    public void startGame() {

        setupFrame();
        setupPanel();



        setFrogList(5,5);
        setupButtonMatrix();

        endGame();







    }




    /**
     * VIEW
     */
    public void setupFrame() {
        frame = new JFrame("color");
        // frame.add(button1, BorderLayout.CENTER);
        // frame.add(button2, BorderLayout.NORTH);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setSize(720, 480);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(buttonFrog.getImage());


    }

    /**
     * add panel   VIEW
     */
    public void setupPanel() {
        /**
         * buttonMatrix Panel
         */
        buttonMatrixPanel.setVisible(true);
        buttonMatrixPanel.setBackground(Color.lightGray);  //rgb 230,230,230
        //panel.setBounds(200,100,350,300);
        buttonMatrixPanel.setBounds(frame.getWidth() / 4, frame.getHeight() / 6, frame.getWidth() / 2, frame.getHeight() / 2);
        frame.add(buttonMatrixPanel);
        buttonMatrixPanel.setLayout(null);

        /**
         * Frog counter Panel
         */


        /**
         * Clicker times Panel
         */



    }

    public void setupButtonMatrix() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {


                JButton button = new JButton("button" + i + j);

                button.setBounds(i * (buttonMatrixPanel.getWidth() / 5), j * (buttonMatrixPanel.getHeight() / 5), buttonMatrixPanel.getWidth() / 5, buttonMatrixPanel.getHeight() / 5);
                button.setBackground(new Color(211, 211, 211));
                button.setBorder(BorderFactory.createEtchedBorder());
                button.setForeground(new Color(211, 211, 211));
                button.setFocusable(false);

                button.addActionListener(this);
                buttonlist[i][j] = button;

                buttonMatrixPanel.add(button);


            }
        }

    }






    public void setFrogList(int nrRows, int nrCols) {

        for (int i = 0; i < nrRows; i++) {
            for (int j = 0; j < nrCols; j++) {
                int m = RandomGenerator.getDefault().nextInt(0, 100);
                if (m < 50) {
                    frogList[i][j] = true;

                } else {
                    frogList[i][j] = false;
                }
            }
        }
    }


    /**
     * button matrix listener
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (e.getActionCommand().equals("button" + i + j)) {

                    if (frogList[i][j] == true) {

                        buttonlist[i][j].setBackground(new Color(76,187,23));
                        buttonlist[i][j].setText(" ");
                        buttonlist[i][j].setEnabled(false);


                        ClickerCounter++;
                        FrogCounter++;
                        System.out.println(ClickerCounter);
                        System.out.println(FrogCounter);
                        endGame();

                    } else if (frogList[i][j] == false) {

                        buttonlist[i][j].setBackground(new Color(105,105,105));
                        buttonlist[i][j].setText(" ");
                        buttonlist[i][j].setEnabled(false);


                        ClickerCounter++;
                        System.out.println(FrogCounter);
                        endGame();


                    }

                }
            }
        }
    }


    /**
     * how game will terminal
     */
    public void endGame() {
        if(ClickerCounter <= 13 && ClickerCounter > 0 && FrogCounter == 12){
            System.out.println("you win!");

            disableAllButton();

            JOptionPane.showMessageDialog(null,"You Win!","Frog",JOptionPane.PLAIN_MESSAGE);

        }
        else if (ClickerCounter >= 12 && FrogCounter < 10){
            System.out.println(" you lose");

            disableAllButton();

            String[] option ={"Play Again", "Exit"};
            int answer = JOptionPane.showOptionDialog(null,"You Lose! ;((","Frog",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,buttonFrog,option,null);

            startover(answer);
        }

    }


    public void disableAllButton(){

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5;j++){
                buttonlist[i][j].setEnabled(false);
                buttonlist[i][j].setText(" ");
            }
        }

    }


    public void deleteAllbutton(){

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5;j++){

                buttonMatrixPanel.remove(buttonlist[i][j]);

            }
        }
    }


    /**
     * JoptionPane action for when game is terminal
     */
    public void startover(int answer) {
        if (answer == 1) {
            System.exit(0);
        } else if (answer == 0) {
            resetGame();
        }
    }

    /**
     * method for restart the game
     */
    public void resetGame(){
        ClickerCounter =0;
        FrogCounter=0;
        deleteAllbutton();
        setupButtonMatrix();
    }



}