import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ReadFileTest {

	//	There are so few test cases as there isn't much to test
	//		If something goes wrong, it will either throw an error (not JUnit needed to find that) or a FileNotFound
	//		Both of which are covered
	
	@Test
	void testLoadDict() {
		//	Tests whether a good path returns null or not, and for a bad path 
		//		Loads the standard Dictionary path
		assertNotNull(ReadFile.loadDict("./src/Dictionary.txt"));
		assertNull(ReadFile.loadDict("this is not a file path!"));
	}
	
	@Test
	void testLoadKeys() {
		//	Tests whether a good path returns null or not, and for a bad path 
		//		Loads the standard Dictionary path
		assertNotNull(ReadFile.loadKeys("./src/Dictionary.txt"));
		assertNull(ReadFile.loadKeys("this is not a file path!"));
	}

}
