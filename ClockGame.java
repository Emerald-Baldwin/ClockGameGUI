/*
Name: Emerald Baldwin and Nicholas Hester
Assignment: Lab 09
Title: Clock Game
Course: CSCE 144
Class section: 1 (Emerald)  2 (Nicholas)
Lab Section: 2
Semester: Spring 2013
Instructor: Laurie Murphy
Date: 5/7/13
Sources consulted: http://themushroomkingdom.net/ for the audio files.
Known Bugs: none
Program description: This program displays a GUI of a game that involves 
guessing a number between 1 and 1000
Creativity: Added a timer to the game to stop the game when 30 seconds have 
passed. Also added audio to increase fun!
Instructions: Run the program. It takes a moment to throw up the GUI. To start a
new game, click "New Game". Input your 
choices and press enter. Make sure the volume is on.
*/

import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.io.*;
import java.net.*;

public class ClockGame extends JFrame {
	
	// Declare private variables
	private JLabel title;
	private JLabel guessLabel;
	private JTextField guessTextField;
	private JButton newGameButton;
	private JLabel resultLabel;
	private Random randomNumbers;
	private int num;
	private ImageIcon clockImage;
	private long startTime;
	private int numGuesses;
	private Timer timer, timer2, timer3;
	private final int TIME_DELAY = 30000;
	private DecimalFormat formatter = new DecimalFormat("#0.00");
	private Audio audio;
	private boolean winCondition;
	private int game = 0;
	
	public ClockGame() throws MalformedURLException{
		//build GUI
		super("Clock Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//create the main panel
		JPanel mainPanel = new JPanel();
		
		// create the random number object and the audio object
		randomNumbers = new Random();
		audio = new Audio();
		
		// create components and place in panel
		clockImage = new ImageIcon("clock.jpg");
		title = new JLabel("The Clock Game", clockImage, SwingConstants.CENTER);
		title.setFont(new Font("Serif", Font.BOLD, 24));
		
		guessLabel = new JLabel("   Guess: ");
		guessTextField = new JTextField(25);
		guessTextField.setEditable(false);
		newGameButton = new JButton(" New Game ");
		resultLabel = new JLabel("Click New Game to Begin");
		resultLabel.setOpaque(true);
		resultLabel.setBackground(Color.yellow);
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		newGameButton.addActionListener(new newGameListener());
		guessTextField.addActionListener(new guessListener());
		
		mainPanel.add(title);
		mainPanel.add(guessLabel);
		mainPanel.add(guessTextField);
		mainPanel.add(newGameButton);
		this.add(resultLabel, BorderLayout.SOUTH);
		
		//add panel to this JFrame
		this.add(mainPanel, BorderLayout.CENTER);
		
		// size this JFrame so that it is just big enough to hold 
		// the components
		this.setSize(375, 250);
		
		// Make this JFrame visible on the screen
		this.setVisible(true);
	
	}
	
	private class newGameListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			audio.mainThemeStop();
			if (game > 0)
			{
				timer.stop();
				timer2.stop();
				timer3.stop();
			}
			game++;
			winCondition = false;
			num = randomNumbers.nextInt(1000) + 1;
			resultLabel.setText("The price is between $1 and " +
				"$1000, begin.");
			resultLabel.setBackground(Color.green);
			resultLabel.setForeground(Color.black);
			guessTextField.setEditable(true);
			guessTextField.requestFocus();
			startTime = System.currentTimeMillis();
			numGuesses = 0;
			
			// create timers
			timer = new Timer(TIME_DELAY, new timerListener());
			timer.start();
			timer2 = new Timer(20000, new timer2Listener());
			timer2.start();
			timer3 = new Timer(24000, new timer3Listener());
			timer3.start();
			//begin main theme
			audio.mainThemeStart();
		}
	}
	
	private class timerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!winCondition)
			{
				audio.mainThemeStop();
				audio.gameOverStart();
				resultLabel.setText("You lose! Too much time has elapsed. Try again.");
				resultLabel.setBackground(Color.red);
				resultLabel.setForeground(Color.black);
				guessTextField.setEditable(false);
				timer.stop();
				timer2.stop();
				timer3.stop();
			}
		}
	}
	
	private class timer2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!winCondition)
			{
				audio.mainThemeStop();
				audio.timeWarningStart();
			}
		}
	}
	
	private class timer3Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!winCondition)
			{
				audio.mainThemeStart();
			}
		}
	}
	
	private class guessListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int guess = Integer.parseInt(guessTextField.getText());
			
			numGuesses++;
			
			if (guess > num)
			{
				resultLabel.setForeground(Color.black);
				resultLabel.setText("Too High");
				guessTextField.selectAll();
				resultLabel.setBackground(Color.red);
			}
			else if (guess < num)
			{
				resultLabel.setForeground(Color.white);
				resultLabel.setText("Too Low");
				guessTextField.selectAll();
				resultLabel.setBackground(Color.blue);
			}
			else
			{
				winCondition = true;
				resultLabel.setForeground(Color.black);
				audio.mainThemeStop();
				timer2.stop();
				audio.stageClearStart();
				resultLabel.setText("Correct! It took you " + 
					numGuesses + " tries, in " + 
					formatter.format(((double)(System.currentTimeMillis() 
					- startTime) / 1000)) + " seconds.");
				guessTextField.selectAll();
				resultLabel.setBackground(Color.yellow);
				timer.stop();
				timer3.stop();
			}
		}
	}
	
	public static void main(String[] args) throws IOException 
	{
		ClockGame frame = new ClockGame();
	}
	
}
	
