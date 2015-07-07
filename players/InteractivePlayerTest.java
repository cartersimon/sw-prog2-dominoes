package dominoes.players;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dominoes.Bone;
import dominoes.BoneYard;
import dominoes.CantPlayException;
import dominoes.Table;


/**
 * @author Simon Carter, Marcel Krajca, Charlene Ferguson, Hanna Laakso, Nick Harris, Richard Vernon
 *
 * Program Name: BSc Computing
 * Submission date: 16-02-14
 */
public class InteractivePlayerTest {
	
	private DominoPlayer dp;	
	ArrayList<Object> hand;
	BoneYard boneyard;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		dp = new InteractivePlayer();
		hand = new ArrayList<>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		dp = null;
	}
	
	@Test
	public void testBonesInHand() {
		BoneYard by = new BoneYard(6);
		Bone b = by.draw();
		Bone[] result =  new Bone[hand.size()];
		hand.toArray(result);
		assertEquals(hand.size(),result.length);		
	}
	
	@Test
	public void testDraw() {
		BoneYard by = new BoneYard(6);
		dp.draw(by);
		int expectedBoneYardCount = 27;
		assertEquals(expectedBoneYardCount,by.size());	
	}
	
	@Test
	public void testTakeBack() {
		int num = dp.numInHand();
		num++;
		BoneYard by = new BoneYard(6);	
		Bone b = by.draw();
		dp.takeBack(b);
		assertEquals(num, dp.numInHand());		
	}
	
	@Test
	public void testMakePlay() {
	}
	
	@Test
	public void testNewRound() {
		dp.newRound();
		assertEquals(0, dp.numInHand());
	}
	
	@Test
	public void testNumInHand() {
		assertEquals(0,dp.numInHand());
		BoneYard by = new BoneYard(6);
		dp.draw(by);		
		assertEquals(1,dp.numInHand());
	}
	
	@Test
	public void testSetPoints() {
		int s = 10;
		dp.setPoints(s);
		assertEquals(s, dp.getPoints());		
	}
	
	@Test
	public void testGetPoints() {
		dp.setPoints(10);
		assertNotNull(dp.getPoints());
	}
		
	@Test
	public void testSetName(){
		String s = "fred";
		dp.setName(s);
		assertEquals(s, dp.getName());
	}

	@Test
	public void testGetName() {
		dp.setName("fred");
		assertNotNull(dp.getName());
	}
	
	@Test(expected=CantPlayException.class)
	public void testMakePlayException() throws CantPlayException{
		dp.makePlay(new Table());
	}

}
