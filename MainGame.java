package dominoes;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;

import dominoes.players.DominoPlayer;
import dominoes.players.ComputerPlayer;
import dominoes.players.InteractivePlayer;

/**
 * 
 * @author Simon Carter, Marcel Krajca, Charlene Ferguson, Hanna Laakso, Nick Harris, Richard Vernon
 * 
 * 
 *	This is the main Class that runs the Dominoes Game from the main method
 * 
 * Program Name: BSc Computing
 * Submission date: 16-02-14
 */

/**
 * 
 * Creating the Game Application Window where the console's output is simulated
 * and written into the text area
 * 
 */
@SuppressWarnings("serial")
public class MainGame extends JFrame {
	/**
	 * The text area which is used for displaying logging information.
	 */
	public JTextArea textArea;

	/**
	 * Creating the buttons in the application
	 */
	private JButton buttonStart = new JButton("Start");
	private JButton buttonClear = new JButton("Clear");

	private PrintStream standardOut;
	protected Component frame;
	protected Icon icon;
	int MAX_BONES = 6;

	/**
	 * Main Game
	 */
	public MainGame() {
		// Game title
		super("Dominoes Game");

		// text area for the simulated console output
		textArea = new JTextArea(150, 10);
		textArea.setEditable(true);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		PrintStream printStream = new PrintStream(new CustomOutputStream(
				textArea));

		// keeps reference of standard output stream
		standardOut = System.out;

		// re-assigns standard output stream and error output stream
	
	System.setOut(printStream);
		System.setErr(printStream);

		// creates the GUI
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.anchor = GridBagConstraints.WEST;

		add(buttonStart, constraints);

		constraints.gridx = 1;
		add(buttonClear, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;

		add(new JScrollPane(textArea), constraints);

		/**
		 * adds event handler for button Start This starts the Game and wait for
		 * the user inputs
		 */
		buttonStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {

				/**
				 * Creating Game mode that user can choose from
				 */
				Object[] possibilities = { "Computer vs Computer",
						"Player vs Player", "Player vs Computer" };
				String s = (String) JOptionPane.showInputDialog(frame,
						"Please choose your game mode:", "Game Mode",
						JOptionPane.PLAIN_MESSAGE, icon, possibilities, "");

				// If a string was returned, say so.
				if ((s != null) && (s.length() > 0)) {

				
	System.out.println("Your mode is " + s + "!\n");

					DominoPlayer ip = null;
					DominoPlayer op = null;
					// Players names
					String firstName, secondName;

					// Computer vs Computer Mode
				
	if (s == possibilities[0]) {
						ip = new ComputerPlayer();
						op = new ComputerPlayer();
						// get names
						do {
							firstName = JOptionPane
									.showInputDialog("Computer 1 name?");
	
					} while (firstName == null || firstName.length() == 0);

						do {
							secondName = JOptionPane
									.showInputDialog("Computer 2 name?");
						} while (secondName == null || secondName.length() == 0);
						op.setName(secondName);
					} else if (s == possibilities[1]) {
						// Player vs Player Mode
						ip = new InteractivePlayer();
						op = new InteractivePlayer();
						do {
							firstName = JOptionPane
									.showInputDialog("Player 1 name?");
						} while (firstName == null || firstName.length() == 0);

						do {
							secondName = JOptionPane
									.showInputDialog("Player 2 name?");
						} while (secondName == null || secondName.length() == 0);
						op.setName(secondName);
					} else {
						// Player vs Computer Mode
						ip = new InteractivePlayer();
						op = new ComputerPlayer();
						do {
							firstName = JOptionPane
									.showInputDialog("Player 1 name?");
						} while (firstName == null || firstName.length() == 0);
						// set name
						op.setName("Computer");
					}

					// set name
					ip.setName(firstName);

					// get integers goal to play
					int goal = 0;
					boolean inputGoalAccepted = false;
					// Validation for entering the integer
					while (!inputGoalAccepted) {
						try {
							goal = Integer.parseInt(JOptionPane
									.showInputDialog("Please enter the goal points."));
							inputGoalAccepted = true;

						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null,
									"Must be a number");
						}
				
	}

					// instance of the game - starts the game to play
					Dominoes d = new Dominoes(new MyUI(), ip, op, goal,
							MAX_BONES);
					// Gets the winner
					DominoPlayer winner = d.play();
					// Pop up message for the winner and the overall score
					System.out.println(winner + " is the winner!\n\n"
							+ "Final score... \n\n" + ip.getName() + ":  "
							+ ip.getPoints() + "\n" + op.getName() + ":  "
							+ op.getPoints());
					JOptionPane.showMessageDialog(
							null,
							winner + " is the winner!\n\n"
									+ "Final score... \n\n" + ip.getName()
									+ ":  " + ip.getPoints() + "\n"
									+ op.getName() + ":  " + op.getPoints());

					return;
				}
			
	// If you're here, the return value was null/empty.
				System.out.println("Come on, choose your game mode!");
			}
		});

		// adds event handler for button Clear
		// Clears and delete everything from the text area/console
	
	buttonClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// clears the text area
				try {
					textArea.getDocument().remove(0,
							textArea.getDocument().getLength());
					standardOut.println("Text area cleared");
				} catch (BadLocationException ex) {
					ex.printStackTrace();
				}
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(880, 720);
		setLocationRelativeTo(null); // centers on screen
	}

	/**
	 * Runs the Dominoes Game
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainGame().setVisible(true);
			}
		});
	}
}