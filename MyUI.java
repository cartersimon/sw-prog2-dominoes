package dominoes;

import javax.swing.JOptionPane;

import dominoes.players.DominoPlayer;

/**
 * @author Simon Carter, Marcel Krajca, Charlene Ferguson, Hanna Laakso, Nick Harris, Richard Vernon
 *
 * Program Name: BSc Computing
 * Submission date: 16-02-14
 */
public class MyUI implements DominoUI {

	int roundCounter = 1;

	/**
	 * Displays the current state of the players, the table, and the boneyard as appropriate for the UI
	 * 
	 * @param players 
	 * 			The players involved in the domino game
	 * @param table
	 * 			The domino table to display
	 * @param boneyard
	 * 			The boneyard to display
	 */
	@Override
	public void display(DominoPlayer[] players, Table table, BoneYard boneyard) {
		// Display Player's name current score and bones in hand
		//MainGame displayOut = new MainGame();

		System.out.println("Round: " + roundCounter);
		for (DominoPlayer dp : players)
		{

			System.out.println("===============");
			if(dp == players[0]){
				System.out.println("Player 1: " + dp + " ");
				//displayOut.textArea.setText("Computer 1: " + dp);
			}else{
				System.out.println("Player 2: " + dp);
				//displayOut.textArea2.setText("Computer 2: " + dp);
			}
			System.out.println("Score: " + dp.getPoints() + "\n");

			Bone[] result = dp.bonesInHand();

			for (int j = 0; j < dp.numInHand() ; j++){
				for (int i = 0; i < result.length; i++)
				{	
					j = i + 1;
					//System.out.println("+---+---+");
					System.out.print("  Bone " + (j) + ": ");
					System.out.println("   " +result[i].left() + "  |  " + result[i].right() + "    ");
					//System.out.println("+---+---+");
				}
			}
			System.out.println();						
		}

		//Display the current table layout.

		System.out.println("===============");
		System.out.println("Table ");
		int numOnTable = table.layout().length;
		if(numOnTable <= 6){
			for (int i = 0; i < numOnTable ; i++)
				System.out.print("+---+---+ ");
			System.out.println();
			for (Bone x : table.layout())
				System.out.print("| " + x.left() + " | " + x.right() + " | ");
			System.out.println();
			for (int i = 0; i < numOnTable ; i++)
				System.out.print("+---+---+ ");
		}else{
			Bone[] curTable = table.layout();

			for (int i = 0; i < 3 ; i++)
				System.out.print("+---+---+ ");
			System.out.print("          ");
			for (int i = 0; i < 3 ; i++)
				System.out.print("+---+---+ ");
			System.out.println();

			for(int i = 0 ; i < 3 ; i++)
				System.out.print("| " + curTable[i].left() + " | " + curTable[i].right() + " | ");
			System.out.print(" ........ ");
			for(int i = curTable.length-3 ; i < curTable.length ; i++)
				System.out.print("| " + curTable[i].left() + " | " + curTable[i].right() + " | ");
			System.out.println();

			for (int i = 0; i < 3 ; i++)
				System.out.print("+---+---+ ");
			System.out.print("          ");
			for (int i = 0; i < 3 ; i++)
				System.out.print("+---+---+ ");
			System.out.println();
		}
		System.out.println();
		System.out.println("===============");
		System.out.println("Number of bones in the yard: " + boneyard.size());
	}

	/**
	 * Displays that the specified player made an illegal play
	 * 
	 * @param player
	 * 			The player making the illegal play
	 */
	@Override
	public void displayInvalidPlay(DominoPlayer player) {
		System.out.println("invalid play for " + player);
	}

	public void displayCurrentRound() {
		//System.out.println("invalid play for " + player);
	}

	/**
	 * Displays the winner for the round 
	 * 
	 * @param player
	 * 			the winning player for the round. <code>null</code> if there was a draw. 
	 */
	@Override
	public void displayRoundWinner(DominoPlayer player) {
		JOptionPane.showMessageDialog(null,player + " wins round "+roundCounter);
		System.out.println(player + " wins round "+roundCounter);
		roundCounter++;
	}
}
