
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

public class SentimentAnalysis 
{

    public static boolean isNegative(String text, StanfordCoreNLP pipeline) {

        StanfordCoreNLP stanfordCoreNLP = pipeline;

        CoreDocument coreDocument = new CoreDocument(text);

        stanfordCoreNLP.annotate(coreDocument);

        List<CoreSentence> sentences = coreDocument.sentences();

        for(CoreSentence sentence : sentences) {

            String sentiment = sentence.sentiment();

            if(sentiment.contains("Negative")) {
            	return true;
            }

        }

        return false;
    }

}