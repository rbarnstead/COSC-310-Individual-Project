import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

class StartupTest {

	
	//	Trries plenty of tests about fetching responses
	//		Namely a few regular cases, then a few generic keys / null keys
	@Test
	void testFetchResponse() {		
		
		//	Consctructs a Dictionary
		HashMap<String, HashMap<String, String>> dict = new HashMap<String, HashMap<String, String>>();
		HashMap<String, String> inner = new HashMap<String, String>();
		inner.put("generic", "Birds are cool!");
		inner.put("what", "A bird is an avian creature descended from reptiles.");
		inner.put("where", "There are no birds aboard the Blue Dwarf.");
		dict.put("bird", inner);
		
		
		
		//	Test some cases where a key is incorrect
		//		Test the generic case
		assertEquals("\nOS > Hm, it seems I was unable to understand what you said.", Startup.fetchResponse("generic", "generic", dict));
		
		
		//		Tests an undefined key one
		assertEquals("\nOS > Sorry, I didn't understand \"verisimilitude\".", Startup.fetchResponse("verisimilitude", "generic", dict));
		
		
		//		Tests an undefined key two
		assertEquals("\nOS > Sorry, I didn't understand \"sociopath\".", Startup.fetchResponse("bird", "sociopath", dict));
		
		
		//		Tests two undefined keys 
		assertEquals("\nOS > Sorry, I didn't understand \"dinosaurs\".", Startup.fetchResponse("dinosaurs", "lame", dict));
		
		
		
		//	Test a few cases where the keys are correct
		assertEquals("\nOS > Birds are cool!", Startup.fetchResponse("bird", "generic", dict));
		assertEquals("\nOS > A bird is an avian creature descended from reptiles.", Startup.fetchResponse("bird", "what", dict));
		assertEquals("\nOS > There are no birds aboard the Blue Dwarf.", Startup.fetchResponse("bird", "where", dict));
		
	}

}
