
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.translate.Translate;
import com.google.api.services.translate.model.TranslationsListResponse;
import com.google.api.services.translate.model.TranslationsResource;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Startup {
	
	
	//	Starts the program up, then enters the primary loop
	//		Mostly loads the Dictionary into active memory
	public static void main(String args[])  throws IOException, GeneralSecurityException, ApiException, InterruptedException {
		
		// This section will have everything required for startup
		//		Which includes the loading of the Dictionary
		
		//	Reads the dictionary file and puts each entry into a HashMap
		HashMap<String, HashMap<String, String>>	dict = ReadFile.loadDict("./src/Dictionary.txt");
		
		//	Puts the keys into an ArrayList
		ArrayList<Key>				 				keys = ReadFile.loadKeys("./src/Dictionary.txt");
		
		
		//	Gets the pipleine so it doens't have to make multiple calls to it
		StanfordCoreNLP pipeline = Pipeline.getPipeline();
		
		
		//	Enters the primary loop
		mainLoop(dict, keys, pipeline);
		
	}
	
	
	//	This loop will call functions to read input and produce output
	public static void mainLoop(HashMap<String, HashMap<String, String>> dict, ArrayList<Key> keys, StanfordCoreNLP pipeline) throws IOException, GeneralSecurityException, ApiException, InterruptedException {
		
		
		//	Used to know when to exit the primary loop
		boolean go = true;
		
		
		//	Scanner temp
		Scanner scanner = new Scanner(System.in);
		
		String lang = "";
		do {
		System.out.print("OS > Enter a language (English = en, Spanish = es, French = fr, Italian = it, German = de) > ");
		lang = scanner.nextLine();
		} while (!lang.equals("en") && lang.equals("es") && lang.equals("fr") && lang.equals("it") && lang.equals("de"));
		
		int i = 0;
		
		do {
			
		System.out.print("OS > Press 1 to continue with the chat bot or 2 to activate the orbital cannon: ");
		i = scanner.nextInt();
		scanner.nextLine();
		
		
		//	The primary loop
		if (i == 1) {
			while(go) {
			
			
				//	Does some formatting
			
				System.out.print("User > ");
			
			
				//	Fetches the keys from the user's input
				String input[] = ParseInput.getInput(scanner, keys, pipeline);
			
			
				//	Fetches the appropriate response
				String response = fetchResponse(input[0], input[1], dict);

			
				String[] splitResponse = response.split(">", 2);
			

				String translatedResponse = translateLang(splitResponse[1], lang);
			
				//	Prints the response out
				System.out.println(splitResponse[0] + " > " + translatedResponse);
			
			}
		}
		
		else if (i == 2) {
			System.out.println("OS > Enter the address for the orbital cannon strike (in the form: ADRESSS, PROVINCE POSTAL CODE): ");
			String cannonTarget = scanner.nextLine();
			System.out.println("Commencing Orbital Cannon at " + calcLatLong(cannonTarget));
			System.out.println("Orbital Strike confirmed");
			System.out.println("3");
			System.out.println("2");
			System.out.println("1");
			System.out.println("BOOM!!!");
			
			
			
		}
		
		else if (i != 1 || i != 2)
			System.out.println("OS > Please enter a valid number");
		
		} while (i != 1 && i != 2);
		
		scanner.close();
	}
	
	
	//	Stitches together an input
	//		Mainly just puts the two keys into the HashMap to get a response
	//		But handles some formatting & error catching
	public static String fetchResponse(String keyOne, String keyTwo, HashMap<String, HashMap<String, String>> dict) {
		
		
		String out = "\nOS > ";
		
		if(keyOne == "generic")
			return out + "Hm, it seems I was unable to understand what you said.";
		
		if(dict.get(keyOne) == null)
			return out + "Sorry, I didn't understand \"" + keyOne + "\".";
		
		if(dict.get(keyOne).get(keyTwo) == null)
			return out + "Sorry, I didn't understand \"" + keyTwo + "\" in this context.";
		
		return out + dict.get(keyOne).get(keyTwo);
	}
	
	
	//translate the input string into the desired language

public static String translateLang (String chatbot, String lang) throws IOException, GeneralSecurityException {
	
	String response = "";
	Translate translator = new Translate.Builder(
			GoogleNetHttpTransport.newTrustedTransport(),
			GsonFactory.getDefaultInstance(), null).setApplicationName("ChatBotTranslator").build();
	
	Translate.Translations.List inputList = translator.new Translations().list(
            Arrays.asList(chatbot),lang);
	
	inputList.setKey("AIzaSyCdvLqC7S8Q8X4rfTaj6y2bnQoL-4irfEY");
	TranslationsListResponse output = inputList.execute();
	for (TranslationsResource translationsResource : output.getTranslations()) {
		response = translationsResource.getTranslatedText();
	}
	
	return response;
	
}
	//converts an address to Lattitude and Longitude.

public static String calcLatLong (String address) throws IOException, ApiException, InterruptedException {

	GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyD_PH1xTY707I-jusXE0m76n68E6qrYlHA").build();
	GeocodingResult[] results =  GeocodingApi.geocode(context, address).await();
	String gresult = (results[0].toString());
	String[] splitResult = gresult.split("Geometry: ");
	String[] splitResult2 = splitResult[1].split(" ");
	String[] splitResult3 = splitResult2[0].split(",");
	String latlong = "Lattitude: " + splitResult3[0] + ", Longitude: " + splitResult3[1];
	return latlong;
}
};