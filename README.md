# COSC310A2
OVERSEER chat bot 
This is the spaceship chat bot, speaking and guiding our crew aboard the Blue Dwarf.

It was created by Skyler Alderson, Robert Barnstead, Abhiek Bist, and Livia Zalilla (as well as David Brideson before they dropped the class).

# Process of Creation
We decided to create a spaceship bot that will help the passengers aboard the Blue Dwarf help navigate and live aboard the spaceship. We started by creating a dictionary .txt file that contained all the Key values that passengers might be concerned about and a set of who/what/when/where/why/how and generic responses that relate to the topic. In addition to the Key values, we also created a list of synonyms that would pair with the Key value so the bot could detect a wider range of inputs. After that we needed a way to read the dictionary file, so we created a class that reads the dictionary file and stores the values into multiple hash maps. Next, we used the hash maps we previously created to parse a simple user input from the console and give a response based on whether the bot could detect a key word and a who/what/when/where/why/how. If the bot only detected a key word and nothing else, it would then give a generic response. If nothing was detected the bot would inform the user. This is where we finished of our first iteration of the bot and concluded Assignment 2. To begin our next iteration, we expanded our dictionary to include another topic. We also created a GUI so that we were not simply using the console to get the user’s input. To make the bot’s synonym recognition more sophisticated and not simply hard coded in the dictionary we used the WordNet dictionary and JAWS API to get synonyms from the key value provided. We then integrated named entity recognition using Stanford’s Named Entity Recognition API. Our last feature we added was a sentiment analysis using Socher et al’s sentinment model. This allows the bot to detect negativity in the users input and if it is determent that the input was negative the bot will give a generic response that acknowledges the user’s frustration. Finally, we created a multitude of JUnit tests to make sure that our bot was working properly.

# Code Description
## Class: Startup
The Startup class loads the Dictionary into a HashMap, then enters the primary loops for grabbing the user's input before replying via calling functions from the classes below.


## Class: ReadFile
The ReadFile class loads a text file. Depending on the function called, it will either return a nested HashMap containing the replies the OVERSER can make, or a list of keys with their synonyms (used for parsing user input) in the form of an ArrayList of Keys. The text file is custom formatted to make reading it easier.


## Class: ParseInput
The ParseInput class contains several functions used in gathering, cleaning, and extracting the keys from the user's input. After gathering a String from the user, it will deliminate it into an array of Strings where each data item is a singlular word. It will clean each item in the String array by removing problematic punctuation and whitespace. Finally, each word is compared to a list of keys; if there is a match, the key is returned.


## Class: Key
A Key contains two Strings and an ArrayList of Strings. The two Strings contain the primary and secondary keys used in the response hashmap, and the ArrayList contains all synonyms used for the primary key. The purpose of this class is to package up all information about keys into one place to make parsing text within ParseInput easier.

## Synonynm Recognition
Using the WordNet lexicon created by Princeton as well JAWS (Java API for WordNet Searching) allows for synonym recognition by searching the database and finding all synonyms related to a word (In this case a key value). This allowed us to use a wider variety of synonyms in relation to our Key value rather the ones we hard coded in.

## Class: NamedEntity
Recognizes named entities (person and company names, etc.) in text. Principally, this annotator uses machine learning sequence models to label entities. The NamedEntity class implements stanford's Named Entity Recognition API to our chatbot. This class takes the input typed by a user and looks for a person's name.

example: User > my name is Bob
         OS > hello human
         User > Where is Dave
         OS > Sorry I can not disclose their location.


## Class: SentimentAnalysis
Recognizes the sentiment of text using Socher et al’s sentiment model. This class takes input and checks if the sentiment behind our input is negative or not. If the sentiment is determined to be negative, Overseer responds with a generic response acknowledging the user's frustation.

example: User > I am angry / User > I hate this place
         OS > I understand your frustration, please let me help you.
         

## Text file: Dictionary
A custom formatted text file that contains an important word with a colon seperating it from its synonyms. The following indented lines give the sub-key (a "W" such as who, what, where...) where a colon then seperates it from the response.

## JUnit Tests
There are four classes that are specifically used for JUnit testing. The StartupTest class, ReadFileTest class, ParseInputTest class and the KeyTest class. The StartupTest class tests a variety of different cases all related to fetching responses. The ReadFileTest class was not entirely important as if the file was incorrect, it will throw an error or a FileNotFound. The ParseInputTest class takes a wide variety of potential user inputs and checks to see how the bot responds. Lastly, the KeyTest class simply tests all of the get and set functions in the Key class.

# Individual Segment

## Google Cloud Translate API
I utilized Google Cloud's translate API in order to translate the the chat bots response to the desired language. For this I chose just to use English, Spanish, French, Italian, and German as many other languages have unique characters that would appear as a ? once translated. Originally I was going to have it work in reverse as well where the user could enter a sentence in there language and then the bot would translate it and work as normal. However due to the limitation of the bot determining sentences through matching keywords, I found that the bot could almost never do so properly as the translated words rarely translated to a viable keyword. 

## Google Maps Geocode API
This was a last minute adjustment as I wanted to use the Twitter API to search for tweets from companies like NASA or SPACEX and relay that information to the chat bot. However I was unable to get a Twitter developer key as I put in an application last week and heard no response since. Therefore I chose to use the google maps geocode API. I made a rather silly implementation which prompts the user to enter an address and then a space cannon fires at the locations longitude and latitude. 
