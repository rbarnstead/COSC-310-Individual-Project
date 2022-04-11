import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

class ParseInputTest {

	
	@Test
	void testParseInput() {
		//	Grabs a pipeline
		StanfordCoreNLP pipeline = Pipeline.getPipeline();
		
		
		//	Creates an ArrayList of test keys
		ArrayList<Key> testKeys = new ArrayList<Key>();
		testKeys.add(new Key("bird", "what", new String[] {"avian", "fowl", "birdie"}));
		
		
		//	Tests with the keys existing in the input
		String[] getTestCaseOne = ParseInput.parseInput("Hey OS, what is a bird?", testKeys, pipeline);
		String[] testCaseOne = new String[] {"bird", "what"};
		for(int i = 0; i < testCaseOne.length; i++)
			assertEquals(testCaseOne[i], getTestCaseOne[i]);
		
		
		//	Test with a synonym within the input
		getTestCaseOne = ParseInput.parseInput("Fowl creatures, what are they?", testKeys, pipeline);
		for(int i = 0; i < testCaseOne.length; i++)
			assertEquals(testCaseOne[i], getTestCaseOne[i]);

		
		//	Test with whacky casing and punctuation
		getTestCaseOne = ParseInput.parseInput("WHAT. IS. AN. AVIAN, OS?", testKeys, pipeline);
		for(int i = 0; i < testCaseOne.length; i++)
			assertEquals(testCaseOne[i], getTestCaseOne[i]);
	}
	
	@Test
	void testMatches() {
		
		//	A case where it pases
		assertTrue(ParseInput.matches("hello", "hello"));
		
		
		//	A bunch of cases where it should fail
		assertFalse(ParseInput.matches("hello", "goodbye"));
		assertFalse(ParseInput.matches("hello", "hello "));
		assertFalse(ParseInput.matches("h ello", "hello "));
		assertFalse(ParseInput.matches("hello and goodbye", "hello "));
	}
	
	@Test
	void testMatchesPrimary() {		
		//	Creates a testing key
		Key testKey = new Key("bird", "what", new String[] {"avian", "fowl", "birdie"});
		
		
		//	Pass cases
		//		Checks the primary key matches
		assertTrue(ParseInput.matchesPrimary("bird", testKey));
		
		//		Matches to the synonyms keys
		assertTrue(ParseInput.matchesPrimary("avian", testKey));
		assertTrue(ParseInput.matchesPrimary("fowl", testKey));
		assertTrue(ParseInput.matchesPrimary("birdie", testKey));
		
		
		//	Fails cases
		assertFalse(ParseInput.matchesPrimary("what", testKey));
		assertFalse(ParseInput.matchesPrimary("bord", testKey));
		assertFalse(ParseInput.matchesPrimary("foul", testKey));
		assertFalse(ParseInput.matchesPrimary("goose", testKey));
		assertFalse(ParseInput.matchesPrimary("", testKey));
		
	}
	
	@Test
	void testMatchesSecondary() {		
		//	Creates a testing key
		Key testKey = new Key("bird", "what", new String[] {"avian", "fowl", "birdie"});
		
		//	Pass Cases
		//		Checks the secondary
		assertTrue(ParseInput.matchesSecondary("what", testKey));
		
		
		//	Fail cases
		assertFalse(ParseInput.matchesSecondary("who", testKey));
		assertFalse(ParseInput.matchesSecondary("bird", testKey));
		assertFalse(ParseInput.matchesSecondary("avian", testKey));
		assertFalse(ParseInput.matchesSecondary("goose", testKey));
		assertFalse(ParseInput.matchesSecondary("", testKey));
	}
	
	@Test
	void testCleanString() {
		
		//	Pass cases
		//		Tests the toLowerCase
		assertEquals("birds are cool", ParseInput.cleanString("Birds are cool"));
		assertEquals("please do not yell", ParseInput.cleanString("PLEASE DO NOT YELL"));
		
		//		Tests splitting around the 'bad characters'
		assertEquals("this ll showcase splits  splits  what are they  they replace the some chars  with space    ", ParseInput.cleanString("this'll showcase splits. splits! what are they? they replace the some chars, with space ' '"));
		
		//		Puts them both together
		assertEquals("here  we will combine both punctuation  such as      as casing to make sure they match ", ParseInput.cleanString("Here, we will combine both punctuation (such as '?') as CASING to make sure they match."));
		
		//		A String that shouldn't change
		assertEquals("a simple test", ParseInput.cleanString("a simple test"));
	}

}
