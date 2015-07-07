package dominoes.players;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import dominoes.Bone;
import dominoes.BoneYard;
import dominoes.CantPlayException;
import dominoes.Play;
import dominoes.Table;

/**
 * @author Simon Carter, Marcel Krajca, Charlene Ferguson, Hanna Laakso, Nick Harris, Richard Vernon
 *
 * Program Name: BSc Computing
 * Submission date: 16-02-14
 */
public class InteractivePlayer implements DominoPlayer {
	private String name;
	private ArrayList<Bone> hand;
	private int points;

	public InteractivePlayer() {
		hand = new ArrayList<>();
	}

	/**
	 * The bones in the player's hand
	 * 
	 * @return Current bones in players hand
	 */
	@Override
	public Bone[] bonesInHand() {
		Bone[] ba = new Bone[hand.size()];
		return hand.toArray(ba);
	}

	/**
	 * Draw a tile from the given boneyard
	 * 
	 * @param boneyard
	 * 				Boneyard tile is drawn from
	 */
	@Override
	public void draw(BoneYard boneyard) {
		Bone b = boneyard.draw();
		hand.add(b);
	}
	
	/**
	 * Tells the player to take back the specified bone
	 * 
	 * @param bone
	 * 			Bone to be taken back
	 */
	@Override
	public void takeBack(Bone bone) {
		hand.add(bone);
	}
	
	/**
	 * Picks a bone to play and where it should be played
	 * 
	 * @param table
	 * 			table bone gets played on
	 * 
	 * @return play object
	 * 
	 * @throws CantPlayException
	 */
	@Override
	public Play makePlay(Table table) throws CantPlayException {

		Play p = null;
		Component frame = null;
		Icon icon = null;
		int whichBoneToPlay = 0;
		boolean validMoveAvailable = false;
		// boolean canIplay = true;

		Bone[] bonesAvailable = bonesInHand();

		// test a valid move exists
		for (Bone b : bonesAvailable) {
			// flip
			if (b.left() == table.left() || b.right() == table.right()
					|| b.right() == table.left() || b.left() == table.right()) {
				validMoveAvailable = true;
			}
		}
		int index = 0;
		if (validMoveAvailable) {
			Object[] leftOrRight = { "Left", "Right" };

			Bone[] whatBone = bonesInHand();

			String[] whatBoneStr = new String[whatBone.length + 1];
			for (int i = 0; i < whatBone.length; i++)
				whatBoneStr[i] = "" + whatBone[i].left() + " - "
						+ whatBone[i].right();
			whatBoneStr[whatBoneStr.length - 1] = "Quit the Game";

			Object pickABone = JOptionPane
					.showInputDialog(
							frame,
							this.getName()
									+ ": You have "
									+ numInHand()
									+ " bones in your hand, which one you would like to play?",
							"Pick a bone?", JOptionPane.PLAIN_MESSAGE, icon,
							whatBoneStr, "");

			if (pickABone != null) {

				// result is the choosen object, if you need the index even so:
				for (Object o : whatBoneStr) {
					if (pickABone == o)
						break;
					index++;
				}
			}

			if (pickABone == whatBoneStr[whatBoneStr.length - 1]) {
				System.exit(0);
			}

			Bone boneToPlay = bonesAvailable[index];

			Object boneSide = JOptionPane
					.showInputDialog(
							frame,
							"Please choose what side you would like to place the bone:",
							"Game Mode", JOptionPane.PLAIN_MESSAGE, icon,
							leftOrRight, "");

			if (boneSide == "Left") {
				if (boneToPlay.right() == table.left()) {
					hand.remove(boneToPlay);
					return new Play(boneToPlay, Play.LEFT);
				}

				// flip
				if (boneToPlay.left() == table.left()) {
					boneToPlay.flip();
					hand.remove(boneToPlay);
					return new Play(boneToPlay, Play.LEFT);
				}
			} else {
				if (boneToPlay.left() == table.right()) {
					hand.remove(boneToPlay);
					return new Play(boneToPlay, Play.RIGHT);
				}

				// flip
				if (boneToPlay.right() == table.right()) {
					hand.remove(boneToPlay);
					boneToPlay.flip();
					return new Play(boneToPlay, Play.RIGHT);
				}
			}
		} else {

			JOptionPane
					.showMessageDialog(
							null,
							this.getName()
									+ ", you are not able to make a valid move with the bones you currently hold.");
			throw new CantPlayException();

		}
		JOptionPane
				.showMessageDialog(
						null,
						this.getName()
								+ ", you have made an invalid move and a new bone has been added into your hand. Please pick another bone");
		throw new CantPlayException();
	}

	/**
	 * Starts a new round and clears hand
	 */
	@Override
	public void newRound() {
		hand.clear();
	}

	/**
	 * The number of bones the player has in its hand
	 * 
	 * @return Number of bones player has in its hand
	 */
	@Override
	public int numInHand() {
		return hand.size();
	}

	/**
	 * Sets the number of points the player has
	 * 
	 * @param points
	 * 			points the player will have
	 */
	@Override
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * Gets the number of points the player has
	 * 
	 * @return points player has
	 */
	@Override
	public int getPoints() {
		return points;
	}


	/**
	 * Set the player's name 
	 * 
	 * @param name
	 * 			Name to be set
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name of the player
	 * 
	 * @return name of the player
	 */
	@Override
	public String getName() {
		return name;
	}


	/**
	 * Represents Name of player in string format
	 * 
	 * @return Name of player
	 */
	@Override
	public String toString() {
		return this.getName();
	}
}