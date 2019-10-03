/*
Name = Carlos Meza
Date = 5/9/15
SID: 0422793
Lab: Lab 18
Description: Guess O Matic
 */
package gom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GOM extends JFrame implements ActionListener, KeyListener
{

   JTextField theGuess = new JTextField(10);
   JLabel bankRoll = new JLabel("100.00 Zipoids");
   JButton newPlayer = new JButton("New Player");
   JButton newNumber = new JButton("New Number");
   JTextField thePlayer = new JTextField(20);
   JTextArea theOutput = new JTextArea();
   JPanel p1 =new JPanel();
   JPanel p2 =new JPanel();
   String playerName ="";
   int theNumber = 20;
   int numTries, numGames = 0;
   double amtRemaining = 100.0;
   Random randomizer = new Random();
   Container content = this.getContentPane();
   
   
   GOM()
   {
       this.setVisible(true);
       this.setSize(500,400);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setTitle("Guess O' Matic");
       JLabel tmp = new JLabel("Make your guess");
       p1.add(tmp);
       p1.add(theGuess);
       p1.add(bankRoll);
       content.add(p1, BorderLayout.NORTH);
       p2.add(newPlayer);
       p2.add(thePlayer);
       p2.add(newNumber);
       content.add(p2, BorderLayout.SOUTH);
       
       JLabel dum1 = new JLabel("  ");
       JLabel dum2 = new JLabel("  ");
       content.add(dum1, BorderLayout.WEST);
       content.add(dum2, BorderLayout.EAST);
       JScrollPane scrollArea = new JScrollPane(theOutput);
       content.add(scrollArea);
       newPlayer.addActionListener(this);
       newNumber.addActionListener(this);
       thePlayer.addKeyListener(this);
       theGuess.addKeyListener(this);
       newPlayer();
    
   }
   public void newPlayer()
   {  
       theOutput.setText("Enter your name and press the enter key to begin");
       theOutput.setEnabled(false);
       theGuess.setEnabled(false);
       newPlayer.setEnabled(false);
       newNumber.setEnabled(false);

       theGuess.setBackground(Color.WHITE);
       thePlayer.setEnabled(true);
       thePlayer.setText("");
       thePlayer.requestFocus();

       thePlayer.setBackground(Color.YELLOW);
   }
   
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        JButton btn = (JButton) e.getSource();
        if(btn == newPlayer)
        {
            newPlayer();
        }
        else 
        {
           theGuess.setEnabled(true);
           theGuess.setBackground(Color.YELLOW);
           newGame();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
      JTextField tf = (JTextField) e.getSource();
      int key = e.getKeyCode();
      
      if(key == KeyEvent.VK_ENTER)
      {
          if(tf == thePlayer)
          {
              addPlayer();
          }
          else
          {
             newGuess(); 
          }
      }
      
    }
    public void addPlayer()
    {
        playerName = thePlayer.getText();
        if(playerName.equals(""))
        {
            theOutput.setText("You need to enter a name to play");
            
        }
        else
        {
            amtRemaining = 100.00;
            numGames = 0;
            thePlayer.setEnabled(false);
            thePlayer.setBackground(Color.white);
            newPlayer.setEnabled(true);
            newNumber.setEnabled(true);
            theGuess.setEnabled(true);
            theGuess.requestFocus();
            theGuess.setBackground(Color.YELLOW);
            newGame();
        }
    }
    private void newGame()
    {
        numGames++;
        theNumber = randomizer.nextInt(101);
        numTries = 0;
        displayInstructions();
        amtRemaining-= 1;
        updateScore();
        
   
    }
    public void displayInstructions()
    {
        theOutput.setText("I'm thinking of a number between 1 and 100\n");
        theOutput.append("If you guess the number in fewer than 9 tries you'll earn\n");
        theOutput.append("\t \t  1 try \t 2.00 Zipoids\n");
        theOutput.append("\t \t  2 tries \t 1.75 Zipoids\n");
        theOutput.append("\t \t  3 tries \t 1.50 Zipoids\n");
        theOutput.append("\t \t  4 tries \t 1.25 Zipoids\n");
        theOutput.append("\t \t  5 tries \t 1.00 Zipoids\n");
        theOutput.append("\t \t  6 tries \t 0.75 Zipoids\n");
        theOutput.append("\t \t  7 tries \t 0.50 Zipoids\n");
        theOutput.append("\t \t  8 tries \t 0.25 Zipoids\n");
    }
    public void updateScore()
    {
        thePlayer.setText(playerName + "\t"+ numGames);
        bankRoll.setText(amtRemaining + " Zipiods");
    }
    public void newGuess()
    {
        String curStr = theGuess.getText();
        int curGuess = Integer.parseInt(curStr);
        numTries++;
        theOutput.append("Guesses: " +numTries +" Current Guess " + curGuess + "\n");
        if(curGuess == theNumber)
        {
            gameWon();
        }
        else
        {
            if (curGuess > theNumber)
            {
                theOutput.append("Sorry to high. Try lower\n");
            }
            else
            {
                theOutput.append("Sorry to low. Try higher\n");
            }
            theGuess.setText("");
            theGuess.setOpaque(true);
            theGuess.setBackground(Color.YELLOW);
            theGuess.selectAll();
            theGuess.requestFocus();        
        }
    }
    public void gameWon()
    {
        double win = 0;
        theGuess.setText("");
        theOutput.setText("*******WINNER********");
        theOutput.append("The amount wagered 1 Zipoid\n");
        theOutput.append("You got it in " + numTries + " number of tries\n");
        switch(numTries)
        {
            case 1:
                win = 2.00;
                amtRemaining += win; 
                theOutput.append("Current Winnings 2.00 Zipoids\n");
                break;
            case 2:
                 win = 1.75;
                amtRemaining += win;
                theOutput.append("Current Winnings 1.75 Zipoids\n");
                break;
            case 3:
                 win = 1.50;
                amtRemaining += win;
                theOutput.append("Current Winnings 1.50 Zipoids\n");
                break;
             case 4:
                  win = 1.25;
                amtRemaining += win;
                theOutput.append("Current Winnings 1.25 Zipoids\n");
                break;
            case 5:
                 win = 1.00;
                amtRemaining += win;
                theOutput.append("Current Winnings 1.00 Zipoids\n");
                break;
             case 6:
                  win = 0.75;
                amtRemaining += win;
                theOutput.append("Current Winnings 0.75 Zipoids\n");
                break;
             case 7:
                  win = 0.50;
                amtRemaining += win;
                theOutput.append("Current Winnings 0.50 Zipoids\n");
                break;
            case 8:
                 win = 0.25;
                amtRemaining += win;
                theOutput.append("Current Winnings 0.25 Zipoids\n");
                break;
            default:
                theOutput.append("Current Winnings: None\n");
                      
        }
        theOutput.append(playerName + " Amount Remaining " + amtRemaining + "\n");
        theOutput.append("Please press the number button to play again!\n");
        updateScore();
        theGuess.setBackground(Color.WHITE);
        theGuess.setEnabled(false);
        newNumber.requestFocus();
        
            
    }
       public static void main(String[] args)
    {
      GOM gom = new GOM();
    }
    
}
