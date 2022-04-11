import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KeyTest {

	
	@Test
	//	Tests the get & set functions for primary
	void testPrimary() {
		Key testKey = new Key("bird", "what", new String[] {"avian", "fowl", "birdie"});
		
		assertEquals("bird", testKey.getPrimary());
		assertNotNull(testKey.getPrimary());
		
		testKey.setPrimary("dinosaur");
		assertEquals("dinosaur", testKey.getPrimary());
		assertNotNull(testKey.getPrimary());
		
		testKey.setSecondary(null);
		assertEquals("dinosaur", testKey.getPrimary());
		assertNotNull(testKey.getPrimary());
		
		testKey.setSynonyms(new String[] {""});
		assertEquals("dinosaur", testKey.getPrimary());
		assertNotNull(testKey.getPrimary());
	}
	
	
	@Test
	//	Tests the get & set functions for secondary
	void testSecondary() {
		Key testKey = new Key("bird", "what", new String[] {"avian", "fowl", "birdie"});
		
		assertEquals("what", testKey.getSecondary());
		assertNotNull(testKey.getSecondary());
		
		testKey.setSecondary("who");
		assertEquals("who", testKey.getSecondary());
		assertNotNull(testKey.getSecondary());
		
		testKey.setPrimary(null);
		assertEquals("who", testKey.getSecondary());
		assertNotNull(testKey.getSecondary());
		
		testKey.setSynonyms(new String[] {""});
		assertEquals("who", testKey.getSecondary());
		assertNotNull(testKey.getSecondary());
	}
	
	
	@Test
	//	Tests the get & set functions for secondary
	void testSynonym() {
		Key testKey = new Key("bird", "what", new String[] {"avian", "fowl", "birdie"});
		
		
		String[] fowl = new String[] {"avian", "fowl", "birdie"};
		for(int i = 0; i < fowl.length; i++) {
			assertEquals(fowl[i], testKey.getSynonyms()[i]);
			assertNotNull(testKey.getSynonyms());
		};
		
		
		String[] raptor = new String[] {"passerine", "raptor"};
		testKey.setSynonyms(new String[] {"passerine", "raptor"});
		for(int i = 0; i < raptor.length; i++) {
			assertEquals(raptor[i], testKey.getSynonyms()[i]);
			assertNotNull(testKey.getSynonyms());
		};
		
		testKey.setPrimary(null);
		for(int i = 0; i < raptor.length; i++) {
			assertEquals(raptor[i], testKey.getSynonyms()[i]);
			assertNotNull(testKey.getSynonyms());
		};
		
		testKey.setSecondary(null);
		for(int i = 0; i < raptor.length; i++) {
			assertEquals(raptor[i], testKey.getSynonyms()[i]);
			assertNotNull(testKey.getSynonyms());
		};
	}
	
	

}
