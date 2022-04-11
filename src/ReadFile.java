import java.util.*;
//HAVE TO REFERENCE THE jaw-bin.jar file. IE right click the jar file and add to build path as it is only referenced on my local machine
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.WordNetDatabase;

import java.io.*;

public class ReadFile {

	//	Given a filepath to the Dictionary, returns a hashmap of the key pair and a response
	 public static HashMap<String, HashMap<String, String>> loadDict(String filePath) {
		    try {
		    File myObj = new File(filePath);
		    Scanner myReader = new Scanner(myObj);
		      
		    //	Second HashMap necessary to determine what response the user will get based on the Key Value and the who/what/when/where/why/how and generic response
		    HashMap<String, HashMap<String, String>> hashMap2 = new HashMap<String, HashMap<String, String>>();
		      
		      
		    //	Thanks Richard for the wonderfully descriptive variable name...
		    String temptemp = "";
		    
		    
	      	String st = myReader.nextLine();
		      
		    while(myReader.hasNextLine()) {
		    	  
		    	if(st.charAt(0) != '\t') {
		    		  
		    	//	Splits the read string to separate key values and user inputs
		    	String[] split = st.split(":");
		    		  
		    	//	Stores the Key and the potential user inputs into the Hash Map
		    	temptemp = split[0];
		    		  
		    	st = myReader.nextLine();  
		    	}
		    	else {
		    		  
		    		  HashMap<String, String> responses = new HashMap<String, String>();
		    		  
		    		  //	This while loop is to fix a scope problem that previously existed
		    		  //		The inner hashmap needs to hold responses for every 'W'
		    		  //		The previous implementation re-assigned the hashmap for every 'W'
		    		  //		Thus previously, only the final 'W' was included
		    		  while(st.charAt(0) == '\t') {
		    			  
		    			  if(!myReader.hasNextLine())
		    				  break; 
		    				  
			    		  String[] split2 = st.split(":");
			    		  
			    		  String wwwyh = split2[0];
			    		  wwwyh = wwwyh.replace("\t", "");
			    		  
			    		  String response = split2[1];
			    		  
			    		  //	Removes the leading white space
			    		  response = response.substring(1);
			    		  
			    		  //	Removes the quotations marks
			    		  for(int i = 0; i < response.length(); i++)
			    			  if(response.charAt(i) == '"') {
			    				  response = response.substring(0, i) + response.substring(i + 1, response.length());
			    				  i--;
			    			  }
			    		  
			    		  //	Appends a period to the response
			    		  //		First item in the if statement is to prevent an empty string from throwing an error
			    		  if(response.length() > 1 && !(response.charAt(response.length() - 1) == '.' || response.charAt(response.length() - 1) == '!' || response.charAt(response.length() - 1) == '?'))
			    			  response += '.';
			    		  
			    		  
			    		  //	Places the response into the inner hash mao
			    		  responses.put(wwwyh, response);
			    		  
			    		  
			    		  //	Calls for the next line
			    		  st = myReader.nextLine();
		    		  }
		    		  
		    		  
		    		  //	Places the inner hash map into the outer one
		    		  hashMap2.put(temptemp, responses);
		    		  
		    		  
		    	  }
		      }
		      
		      myReader.close();
		      
			  return hashMap2;
		      
		      
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred: " + e + "\nAttempted filepath: " + filePath);
		      return null;
		    }
		    
	 }
	 
	 
	 //	Given a filepath to the Dictionary, returns a hashmap of key values and their synonyms
	 public static ArrayList<Key> loadKeys(String filePath) {
		    try {
		      File myObj = new File(filePath);
		      Scanner myReader = new Scanner(myObj);

		      
		      ArrayList<Key> keys = new ArrayList<Key>();
		      
		      
	      	  String st = myReader.nextLine();
		      
		      while(myReader.hasNextLine()) {
		    	  
		    	  String primary = "";
		    	  String[] synonyms = new String[0];
		    	  
		    	  
		    	  if(st.charAt(0) != '\t') {
		    		  
		    		  //	Splits the read string to separate key values and user inputs
		    		  String[] split = st.split(":");
		    		  
		    		  
		    		  //Loads the WordNet Database in the local folder
		    		  System.setProperty("wordnet.database.dir", "./WordNet");
		    		  WordNetDatabase database = WordNetDatabase.getFileInstance();
		    		  
		    		  //determines the numbers of synsets (Synonyms) derived from the key word
		    		  Synset[] synsets = database.getSynsets(split[0]);					
						
		    		  //Display the word for synsets retrieved
		    		  
		    		  if (synsets.length > 0)
						{
							for (int i = 0; i < synsets.length; i++)
							{
								//gets the Word Form for all the synonyms and stores them in a String Array
								String[] wordForms = synsets[i].getWordForms();
								//puts the synoynms into the original synonym string
								synonyms = wordForms;
							}
						}
		    		  
		    		  
		    		  //	Splits the second string by commas and stores values as a list
		    		  //		This method is to remove any leading whitespace that might be present
		    		  //synonyms = split[1].split(",");
		    		  //for(int i = 0; i < synonyms.length; i++)
		    		  //synonyms[i] = synonyms[i].stripLeading();
		    		  
		    		
	    			  primary = split[0];
		    		  
			    	  st = myReader.nextLine();

		    	  }
		    		  
	    		  //	This while loop is to fix a scope problem that previously existed
	    		  //		The inner hashmap needs to hold responses for every 'W'
	    		  //		The previous implementation re-assigned the hashmap for every 'W'
	    		  //		Thus previously, only the final 'W' was included
	    		  while(st.charAt(0) == '\t') {
	    			  
	    			  if(!myReader.hasNextLine())
	    				  break; 
	    				  
		    		  String[] split2 = st.split(":");
		    		  
		    		  String wwwyh = split2[0];
		    		  wwwyh = wwwyh.replace("\t", "");
		    		  

		    		  
		    		  keys.add(new Key(primary, wwwyh, synonyms));

		    		  
		    		  //	Calls for the next line
		    		  st = myReader.nextLine();
	    		  }
	    		  
		      }
		      
		      myReader.close();
		      
			  return keys;
		      
		      
		    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred: " + e + "\nAttempted filepath: " + filePath);
			      return null;
		    }
		    
	 }
}
