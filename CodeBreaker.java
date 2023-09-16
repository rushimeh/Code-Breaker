import java.util.Scanner;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.Random;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class CodeBreaker extends Canvas implements ActionListener{
	static JLabel title = new JLabel("WELCOME TO CODE BREAKER"); //title
	static JLabel inst1 = new JLabel("Enter your guess. The letter options are G, P, Y, O, B, R. Black means right letter, right spot. White means right letter, wrong spot. Gray means nothing."); //instructions
	static JLabel inst2 = new JLabel("This setting allows you to enter the feedback and let the computer guess the code that you have in your mind."); //computer
	static JFrame frame = new JFrame(); //set frame
	static int codeLength; //Selected code length
	static JButton guessOptn = new JButton("Guess"); //option1
	static JButton AiOptn = new JButton("AI Guess"); //option 2
	static JPanel starterPanel = new JPanel(); //panels 
	static JPanel textPanel = new JPanel(); 
	static JPanel guessPanel = new JPanel();
	static JPanel responsePanel = new JPanel();
	static JPanel guesses[] = new JPanel[10];//guesses panel
	static JButton[] buttonArray; //array of buttons
	static JPanel responses[] = new JPanel[10]; //responses panel.
	static JButton[] responseButtonArray; ///array of response buttons
	static int worldInput = 0; //global input.
	static JPanel inputPanel = new JPanel();//panel
	static JLabel sizeLabel = new JLabel("Please enter how long you would like the AI's code to be: "); //size prompt
	static JTextField sizeText = new JTextField(20);
	static JButton sizeButton = new JButton("Submit Size");
	static JLabel guessLabel = new JLabel("Please enter your guess."); //guess prompt
	static JTextField guessText = new JTextField(20);
	static JLabel codeLengthInput = new JLabel("Please enter how long you would like the code to be: "); //size prompt v2
	static JTextField lengthText = new JTextField(20);
	static int x = 0; //global counter
	static String colorArray[] = {"B", "G", "R", "Y", "O", "P"}; //posibilities
	static JPanel inputPanel2 = new JPanel(); //panel
	static JLabel feedbackLabel = new JLabel("Please enter the feedback to the guess: "); //feedback prompt
	static JTextField feedbackText = new JTextField(20);
	static ArrayList<String[]> possibilities = new ArrayList<String[]>(); //array
	static String[] prevGuess; //holds previous guess.
	static String difficulty = ""; //global difficulty decider
	static JLabel difficultyLabel = new JLabel("Select difficulty"); //difficulty prompt.
	static JButton easyButton = new JButton("easy");
	static JButton mediumButton = new JButton("medium");
	static JButton hardButton = new JButton("hard");
	static JButton customButton = new JButton("custom");
	
	static JPanel lossPanel = new JPanel(); //panels
	static JLabel lossLabel = new JLabel("Sorry, you ran out of tries! Please close and rerun this program to try again!"); //loss prompt

	static JPanel winPanel = new JPanel(); //panels
	static JLabel winLabel = new JLabel("Yay! You guessed the correct code! You win!"); //win prompt
	
	public CodeBreaker(){//Constructor method - same name as the Class name, in order to run the CodeBreaker GUI
		AiOptn.addActionListener(this); //add action listener to all feature's buttons
		guessOptn.addActionListener(this);
		sizeButton.addActionListener(this);
		easyButton.addActionListener(this);
		mediumButton.addActionListener(this);
		hardButton.addActionListener(this);
		customButton.addActionListener(this);
	}
	/**
	 * This main method creates the main menu in the GUI where they are able to choose from the two options 
	 * of "Guess against AI" or "Let AI Guess", and depending on the action listeners set on them, 
	 * the program will run the code required for whichever button was pressed.
	 * 
	 * @param String[] args
	 * 
	 * String filepath - Holds the file path to access the music file.
	 * 
	 * @return void
	 * 
	 */
	public static void main(String args[]){
		new CodeBreaker(); //run method.
		starterPanel.add(title); //add starter gui to frame.    |
		starterPanel.add(AiOptn);// 							|
		starterPanel.add(guessOptn);// 							|
		guessPanel.setLayout(new GridLayout(10, 2, 2, 2));// 	|
		responsePanel.setLayout(new GridLayout(10, 2, 2, 2));// |
		frame.add(starterPanel);// 								|
		frame.setSize(500,500);// 								|
		frame.setVisible(true);// 								|
		frame.setLayout(new BorderLayout());// 					V
							
		

	}//end of main method.
	/**
	 * This action performed method adds functions to event listeners on many of the buttons in GUI
	 * 
	 * @param ActionEvent event - 
	 * 
	 * String command - Holds the text of the button that user clicked..
	 * JPanel textPanel - holds the panel with instructions on what the user should do.
	 * JPanel inputPanel - holds the panel that prompts to user to enter size, difficulty, and e.t.c
	 * JButton difficulty - global variable which lets the code know what difficulty the user selected.
	 * int size - holds size of the code that the user wants either for the AI Guess Code, or the Guessing Code Themselves.
	 * @return void
	 * 
	 */
	public void actionPerformed(ActionEvent event) { 
		String filepath = "soundtrack.wav";//initialize file path.			
		playMusic(filepath); //play music// 
		String command = event.getActionCommand(); //checks which button was pressed.
		if(command.equals("Guess")){ //if user opts to guess
			starterPanel.removeAll(); //remove everything in starter GUI
			frame.remove(starterPanel);
			textPanel.add(inst1); //add guessing instructions 
			textPanel.setLayout(new BorderLayout()); //set layouts and GUI for guessing
			frame.add(textPanel, BorderLayout.CENTER);
			frame.revalidate();
			frame.repaint();
			worldInput = 0; 
			guessPanel.removeAll(); //removes labels and updates GUI to prompt size for code being guessed |
			inputPanel.add(sizeLabel);//                            									   |
			inputPanel.add(sizeText);//                              									   |
			inputPanel.add(sizeButton);//                           								       |
			textPanel.add(inputPanel);//                            								       |
			frame.add(guessPanel);//                              										   |
			frame.add(responsePanel);//                         									       |
			frame.revalidate();//                             										       |
			frame.repaint();//                                    										   V
		} //end of guess selection
		else if(command.equals("AI Guess")) { //if user selects AI guess
			starterPanel.removeAll(); //updates GUI to prompt for size   |
			frame.remove(starterPanel); //							     |
			textPanel.add(inst2); // 							         |
			textPanel.setLayout(new BorderLayout()); // 				 |
			frame.add(textPanel, BorderLayout.CENTER); // 			     |
			frame.revalidate(); // 							             |
			frame.repaint(); // 							             |
			worldInput = 0; // 							   		         |
			inputPanel.add(codeLengthInput); // 					     |
			inputPanel.add(lengthText); // 							     |
			JButton enterSize = new JButton("Submit Size of Code");//    |
			inputPanel.add(enterSize); // 							     |
			textPanel.add(inputPanel); // 							     |
			frame.revalidate(); // 							             |
			frame.repaint(); // 							             V
			
			enterSize.addActionListener(new ActionListener() { //adds event listener towards size prompt.
					public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
						
						int size = Integer.parseInt(lengthText.getText()); //parses size entered by user.
						codeLength = size; //sets global codeLength to size.
						buttonArray = new JButton[codeLength]; //create buttonArray to match that size.
						responseButtonArray = new JButton[codeLength]; //create responseButtonArray to match the size.
						prevGuess = new String[codeLength]; //initialize prevGuess.
						textPanel.removeAll(); //Update GUI to now play against AI |
						frame.remove(textPanel);//                                 |
						frame.revalidate();//                               	   |
						frame.repaint();//                                         V
						String option = ""; //options to put into recursive array.
						int counter = 0; //counter for recursive array.
						poolMaker(codeLength, colorArray, counter, option); //run method to create all possible options with given size.
						
						
						firstGuess(size); //run firstGuess algorithm
					}
			});
		}
		else if(command.equals("Submit Size")) { //if user selects size for guessing against AI.
			inputPanel.remove(sizeLabel); //update GUI into selection for difficulty of answer. |
			inputPanel.remove(sizeText);//														|
			inputPanel.remove(sizeButton);//													|
			inputPanel.add(difficultyLabel);//													|
			inputPanel.add(easyButton);//														|
			inputPanel.add(mediumButton);//														|
			inputPanel.add(hardButton);//														|
			inputPanel.add(customButton);//														|
			textPanel.add(inputPanel);//														|
			frame.revalidate();//																|
			frame.repaint();//																	V
			int size = Integer.parseInt(sizeText.getText()); // initialize given size 
			codeLength = size; //set global variable to selected size.
			
		}//end of event.
		else if(command.equals("easy") || command.equals("medium") || command.equals("hard")|| command.equals("custom")){ //if user selects any of the difficulties.
			difficulty = command; //set global difficulty to whatever user selected.
			inputPanel.remove(difficultyLabel); //update GUI into guessing AI CODE |
			inputPanel.remove(easyButton);//									   |
			inputPanel.remove(mediumButton);//									   |
			inputPanel.remove(hardButton);//									   |								   
			inputPanel.add(guessLabel);//									  	   |
			inputPanel.add(guessText);//									       V
			guessCode();//Begin guess code method.
		}//end of event.
	}//end of event listener method.
	/**
	 * This AIGuessCode method is the method that runs when the user selects to select a code and let the algorithm guess.
	 * This method creates a "pool" of all possible permutations based on the 6 colors, and whatever size the user selected.
	 * It randomly chooses an answer from the pool, and then runs the check code AI for each answer in the pool. If it matches 
	 * the user's response it is kept in the filtered and more smaller pool. Those that do not match the feedback are removed. It eventually
	 * guesses the right answer.
	 * @param String[] userFeedBack, holds the users feedback from the first guess.
	 * 
	 * ArrayList<String[]> possibilities - Holds all the permutations possible.
	 * ArrayList<String[]> newPool - Holds the filtered amount of options, and updates possibilities..
	 * String[] compFeedback - has the response of the answer in the pool that is being checked in the for loop. Is used to see if it is the same as user feedback.
	 * String[] userFeedBack - used to check and filter the options in the pool to help narrow down the answer.
	 * int b1, b2, w1, w2 - holds the number of whites and blacks in both the userFeedback, and the computerFeedback of whatever answer it is checking in the pool.
	 * 						used to check if they are equal.
	 * @return void
	 * 
	 */
	public static void AiGuessCode(String[] userFeedback) {//AI guessing code Algorithm.
		frame.revalidate(); //update frame.
		frame.repaint(); 
		ArrayList<String[]> newPool = new ArrayList<String[]>(); //initialize new pool.
		for (int i = 0; i < possibilities.size(); i++) { //run method as size of pool.
	        ArrayList<String> computerFeedback = checkCode(prevGuess, possibilities.get(i)); //check if random code generated from list of code matches same response as user given.
	        String[] compFeedback = new String[computerFeedback.size()]; //initialize array to response.
	        for (int j = 0; j < computerFeedback.size(); j++) { 
	          if(computerFeedback.isEmpty()){
	            compFeedback[j] = "X"; //if empty replace array with all X
	          }else{
	            compFeedback[j] = computerFeedback.get(j); //take response into array from arraylist.
	          }
	          
	        }
	        int b1 = 0; //black counters
	        int w1 = 0; //white counters
	        int b2 = 0;
	        int w2 = 0;
	        for (int j = 0; j < userFeedback.length; j++) { //check how many black and white there are in user's reponse.
	          if (userFeedback[j].equals("B")) {
	            b1++;
	          } else if (userFeedback[j].equals("W")) {
	            w1++;
	          }
	        }
	        String comppFeedback = String.join("", computerFeedback);//check how many black and white there are in computers option from pool.
	        if(comppFeedback.equals("WWWW")){ 
	        }
	        for (int j = 0; j < compFeedback.length; j++) {
	          if (compFeedback[j].equals("B")) {
	            b2++;
	          } else if (compFeedback[j].equals("W")) {
	            w2++;
	          }
	        }
	        
	        if ((b1 == b2) && (w1 == w2)) { //if both user response and computer response match for random answer.
	          newPool.add(possibilities.get(i)); //put answer into new pool.
	        }

	      }
	      possibilities = newPool; //possibilities is new pool.
	      Random rn = new Random(); //guess random option.
		  int guessIndex = rn.nextInt(possibilities.size());
		  prevGuess = possibilities.get(guessIndex); //random option prompted
		  addToGuess(); //add to guesses method run.
		
	}	
	public static void firstGuess(int size) {
		Random rn = new Random();
	    int guessIndex = rn.nextInt(possibilities.size());
	    prevGuess = possibilities.get(guessIndex);
	    x = 0;
	    for(int i = 0; i < 10; i++) {
			guesses[i] = new JPanel();
			responses[i] = new JPanel();
	    }
	    
		addToGuess();
		
		inputPanel2.add(feedbackLabel);
		inputPanel2.add(feedbackText);
		JButton feedbackInput = new JButton("Enter feedback");
		inputPanel2.add(feedbackInput);
		frame.add(inputPanel2, BorderLayout.SOUTH);
		frame.revalidate();
		frame.repaint();
		feedbackInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userFeedback = feedbackText.getText();
				String userFeedbackArr[] = userFeedback.split("");
				
				
				for(int j = 0; j < userFeedbackArr.length; j ++){
					responseButtonArray[j] = new JButton();
					responseButtonArray[j].setPreferredSize(new Dimension(40, 40));
					responseButtonArray[j].setBackground(Color.GRAY);
					responseButtonArray[j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
					
					if(userFeedbackArr[j].equals("B")){
						responseButtonArray[j].setBackground(Color.BLACK);
						
					}
					if(userFeedbackArr[j].equals("W")){
						responseButtonArray[j].setBackground(Color.WHITE);
						
					}
					responses[x].add(responseButtonArray[j]);
					
				}
				responsePanel.add(responses[x]);
				frame.add(responsePanel, BorderLayout.EAST);
				x++;
				
				boolean correctCode = false;
				if(userFeedbackArr.length == codeLength) {
					correctCode = true;
					for(int i = 0; i < userFeedbackArr.length; i++) {
						if(!userFeedbackArr[i].equals("B")) {
							correctCode = false;
						}
					}
					

				}
				if(correctCode) {
					System.out.println("Won");
					frame.remove(guessPanel);
					frame.remove(responsePanel);
					frame.remove(inputPanel2);
					winPanel.add(winLabel);
					frame.add(winPanel);
					frame.revalidate();
					frame.repaint();
				}
				else if(x == 10) {
					frame.remove(guessPanel);
					frame.remove(responsePanel);
					frame.remove(inputPanel2);
					lossPanel.add(lossLabel);
					frame.add(lossPanel);
					frame.revalidate();
					frame.repaint();
					
				}
				else {
					AiGuessCode(userFeedbackArr);
				}
			}
		});
	}
	
	
	public static void addToGuess() {
		for(int j = 0; j < buttonArray.length; j ++){
			buttonArray[j] = new JButton();
			buttonArray[j].setPreferredSize(new Dimension(40, 40));
			if(prevGuess[j].equals("G")){
				
				buttonArray[j].setBackground(Color.GREEN);

			}
			else if(prevGuess[j].equals("R")){
				buttonArray[j].setBackground(Color.RED);

			}
			else if(prevGuess[j].equals("B")){
				buttonArray[j].setBackground(Color.BLUE);

			}
			else if(prevGuess[j].equals("O")){
				buttonArray[j].setBackground(Color.ORANGE);

			}
			else if(prevGuess[j].equals("P")){
				buttonArray[j].setBackground(Color.PINK);

			}
			else if(prevGuess[j].equals("Y")){
				buttonArray[j].setBackground(Color.YELLOW);

			}
			guesses[x].add(buttonArray[j]);
		}
		guessPanel.add(guesses[x]);
		frame.add(guessPanel);
		frame.repaint();
		frame.revalidate();
	}
	/**
	 * This guessCode method is the method that runs when the user selects to guess the AI's code.
	 * This method randomly creates an answer based on the difficulty the user selected beforehand.
	 * It checks at each guess if the user's response matches, and if not gives user hints with white and black responses. 
	 * It ends when user guesses the right answer, or runs out of guesses which is 10.
	 * @param none
	 * 
	 * JButton[] buttonArray - Holds all the color GUI of the current guess.
	 * JButton[] responseButtonArray - Holds all the color GUI of the computer's hint.
	 * JPanel[] guesses - holds all guesses from the user, which is max 10.
	 * JPanel[] responses - holds all responses from the computer, which is max 10.
	 * JButton[] submit - button that allows user to submit their guess. Has action listener, that helps print the guess in the GUI.
	 * int x - represents the current guess of the user.
	 * boolean correctCode - checks if the user got the right answer. If they did the code ends.
	 * @return void
	 * 
	 */
	public static void guessCode(){
		buttonArray = new JButton[codeLength]; //initialize array of buttons
		responseButtonArray = new JButton[codeLength];//initialize array of response buttons
		
		
	frame.revalidate();//update GUI
		frame.repaint();
		
		String answer[] = createCodeAI(); //create answer for user to guess.
		
		
		for(int i = 0; i < 10; i++){
			guesses[i] = new JPanel(); //create all input panels.
			responses[i] = new JPanel(); //create all response panels.
		}
			JButton submit = new JButton("Submit Guess"); //initialize button to allow user to submit guess.
			inputPanel.add(submit); //add to panel.
			frame.revalidate(); //update panel.
			frame.repaint();
			submit.addActionListener(new ActionListener() {  //add action listener to submit btn.    	
				@Override
				public void actionPerformed(ActionEvent e) {					
					frame.add(textPanel, BorderLayout.SOUTH); //add textPanel to GUI.
					
					String inputCode = guessText.getText(); //enter user's input.
					String[] codeInput = inputCode.split(""); //split the users input into array
					for(int j = 0; j < buttonArray.length; j ++){
						buttonArray[j] = new JButton(); //create a button
						buttonArray[j].setPreferredSize(new Dimension(40, 40)); //set size
						if(codeInput[j].equals("G")){//if input letter was green print green button.
							buttonArray[j].setBackground(Color.GREEN);
						}
						else if(codeInput[j].equals("R")){//if input letter was red print green button.
							buttonArray[j].setBackground(Color.RED);

						}
						else if(codeInput[j].equals("B")){//if input letter was blue print green button.
							buttonArray[j].setBackground(Color.BLUE);

						}
						else if(codeInput[j].equals("O")){//if input letter was orange print green button.
							buttonArray[j].setBackground(Color.ORANGE);

						}
						else if(codeInput[j].equals("P")){//if input letter was pink print green button.
							buttonArray[j].setBackground(Color.PINK);

						}
						else if(codeInput[j].equals("Y")){//if input letter was yellow print green button.
							buttonArray[j].setBackground(Color.YELLOW);

						}
						guesses[x].add(buttonArray[j]);//add all input for this guess, into the guess panel.
					}
					
					String[] tempAnswer = answer; //create a copy of answer;
					ArrayList<String> checker = checkCode(codeInput, tempAnswer); //check if user input is right.
					for(int j = 0; j < checker.size(); j ++){
						responseButtonArray[j] = new JButton(); //create response button.
						responseButtonArray[j].setPreferredSize(new Dimension(40, 40)); //set size of response
						responseButtonArray[j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
						
						if(checker.get(j).equals("B")){ //if checker says there's a black, print black button.
							responseButtonArray[j].setBackground(Color.BLACK);
							
						}
						if(checker.get(j).equals("W")){ //if checker says there's a white, print white button.
							responseButtonArray[j].setBackground(Color.WHITE);
							
						}
						responses[x].add(responseButtonArray[j]);//add  output into output panel.
						
					}
					for(int j = checker.size(); j < codeLength ; j ++){ //if none of it was right, print blanks.
						responseButtonArray[j] = new JButton(); //create response button.
						responseButtonArray[j].setPreferredSize(new Dimension(40, 40));
						responseButtonArray[j].setBackground(Color.GRAY);
						responseButtonArray[j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
						responses[x].add(responseButtonArray[j]);//add  output into output panel.
					}
					guessPanel.add(guesses[x]); //add guesses to panel.
					responsePanel.add(responses[x]); //add responses to panel.
					frame.add(guessPanel); //add to frame.
					frame.add(responsePanel, BorderLayout.EAST);//add to frame.
					frame.repaint(); //update frame.
					frame.revalidate();

					boolean correctCode = false; //variable that checks if game is finished.
					if(checker.size() == codeLength) { //if checker == codeLength
						correctCode = true; //set to true.
						for(int i = 0; i < codeLength; i++) {
							if(!checker.get(i).equals("B")) {//change back to false if there is non BLACK in the ouput.
								correctCode = false;
							}
						}
						

					}
	
					x++; //update x (number of guesses)
					if(correctCode) {//if correct code
						System.out.println("Won"); //print YOU WON GUI |
						frame.remove(guessPanel);// 				   |
						frame.remove(responsePanel);// 				   |
						frame.remove(inputPanel2);// 				   |
						winPanel.add(winLabel);// 				  	   |
						frame.add(winPanel);// 				  		   |
						frame.revalidate();// 				           |
						frame.repaint();// 				  			   V
					}
					else if(x == 10) { //if 10 guesses already done.
						frame.remove(guessPanel);//print YOU LOST GUI |
						frame.remove(responsePanel);// 				  |
						frame.remove(inputPanel2);// 				  |
						lossPanel.add(lossLabel);// 				  |
						frame.add(lossPanel);// 				      |
						frame.revalidate();// 				   		  |
						frame.repaint();// 				  			  V
						
					}
					
				}
	        });
	}//end of method.
	/**
	 * This createCodeAI creates the answer for the user to guess depending on the difficulty they selected.
	 * Easy means it'll have the same letter, medium will try to avoid repetition, hard will randomize, and custom will read file.
	 * @param none
	 * 
	 * ArrayList<String> colors - Holds all the possible colors that can be in the answer..
	 * String difficulty - is used to check the difficulty of the code that the user selected.
	 * String answerCode[] - holds the answer that the computer randomized.
	 * String singleColor - holds the singleColor that was randomized if the user selected easy.
	 *
	 * @return String[] answerCode - returns the answer the computer has selected.
	 * 
	 */
	public static String[] createCodeAI(){ //create answer method
		ArrayList<String> colors = new ArrayList<String>(); //array list of colors.
		colors.add("G");//add colors to options.			   |
		colors.add("P");// 				  					   |
		colors.add("O");// 				  					   |
		colors.add("Y");// 									   |
		colors.add("B");// 									   |
		colors.add("R");// 									   V
		String answerCode[] = new String[codeLength]; //create answer to be length that user selected
		if(difficulty.equals("easy")) { //if easy difficulty
			String singleColor = colors.get((int) (Math.random()*6));
			for(int i = 0; i < answerCode.length; i++){
				answerCode[i] = singleColor;//input the same color for the whole code.
			}
		}
		else if(difficulty.equals("medium")) {//if medium difficulty.
			for(int i = 0; i < answerCode.length; i++){
				int randomNum = (int) (Math.random()*6);
				answerCode[i] = colors.get(randomNum);//try to avoid repetition.
				colors.remove(randomNum);
			}
		}
		else if(difficulty.equals("hard")) {//if hard difficulty
			for(int i = 0; i < answerCode.length; i++){
				answerCode[i] = colors.get((int) (Math.random()*6));//Completely randomized code.
			}
		}
		else if(difficulty.equals("custom")) {//if custom difficulty
			for(int i = 0; i < answerCode.length; i++){
				
			}
		}
		return answerCode; //return answer.
	}
	public static ArrayList<String> checkCode(String[] codeInput, String[] answer){	
		String[] tempInput = new String[codeInput.length]; //create copy.
		String[] tempAnswer = new String[answer.length];//create copy.
		for(int i=0; i < codeInput.length; i++){//make copy.
			tempInput[i] = codeInput[i];
		}
		for(int i=0; i < answer.length; i++){//make copy.
			tempAnswer[i] = answer[i];
		}
		
		
		
		ArrayList<String> feedback = new ArrayList<String>(); //create a feedback arrayList
		for(int i = 0; i < tempAnswer.length; i++){ 
			if(tempAnswer[i].equals(tempInput[i])){//if they equal each other.
				feedback.add("B");//add a b.
				tempInput[i] = "-1";//remove from input.
				tempAnswer[i] = "-1";//remove from answer.
			}

		}
		for(int i = 0; i < tempAnswer.length; i++){
			for(int j = 0; j < tempInput.length; j++){
				if((tempAnswer[i].equals(tempInput[j]) && !tempAnswer[i].equals("-1") && !tempInput[j].equals("-1"))){ //if they equal, but aren't both -1
					feedback.add("W");//add W
					tempAnswer[i] = "-1";//put -1
					tempInput[j] = "-1";//put -1.
				}
			}

		}
		

		return feedback;//return response.
		
	}//end of CheckCode method.

	public static void AIGuesser(int size){//run AIGuess method.
		String option = ""; //create option variable for method.
		int counter = 0; //create counter.
		poolMaker(size, colorArray, counter, option); //run method.
	}
	public static void poolMaker(int size, String[] colors, int counter, String option){
		counter++;//up the counter, to let code know what position you are editing.
		if(counter ==  size){//if the counter = size of the code.
			for(int i = 0; i < colors.length; i++){//run for loop, which alternates colors as it goes.
				String option1 = option + colors[i];//concat  the string to hold  the code.
				String optionArray[] = option1.split("");//split the string into an array.
				possibilities.add(optionArray);//enter the array into the pool.

			}
		}
		else{
			for(int i = 0; i < colors.length; i++){//try every color/.
				String option1 = option + colors[i];//concat into string to hold code.
				poolMaker(size, colors, counter, option1);//recurse if not at max size.
			}
		}
		
		
	}//end of method
	public static void playMusic(String location) {
		try {
			File musicPath = new File(location);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		}
		catch(Exception e) {
			System.out.println("OK");
		}
	}

}
















