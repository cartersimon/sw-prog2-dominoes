package dominoes.players;

import java.util.ArrayList;

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
public class ComputerPlayer implements DominoPlayer {
	private String name;
	private ArrayList<Bone> hand;
	private int points;

	public ComputerPlayer() {
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

		Bone[] bonesAvailable = bonesInHand();	


		for (Bone b : bonesAvailable) {

			//flip
			if (b.left() == table.left()) {
				b.flip();
				hand.remove(b);		
				return new Play(b, Play.LEFT);				
			}
			//flip
			if ( b.right() == table.right()) {
				hand.remove(b);				
				return new Play(b, Play.RIGHT);			
			}			
			if (b.right() == table.left() ) {
				hand.remove(b);				
				return new Play(b, Play.LEFT);	

			} else if (b.left() == table.right()) {
				hand.remove(b);	
				return new Play(b, Play.RIGHT);
			}
		}
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
	 * Set the number of points the player has
	 * 
	 * @param points
	 * 			Points to be set
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