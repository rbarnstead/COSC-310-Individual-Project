
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;

public class NamedEntity {

    public static boolean getNamedEntity(String text, StanfordCoreNLP pipeline)
    {

        StanfordCoreNLP stanfordCoreNLP = pipeline;

        CoreDocument coreDocument = new CoreDocument(text);

        stanfordCoreNLP.annotate(coreDocument);

        List<CoreLabel> coreLabels = coreDocument.tokens();

        for(CoreLabel coreLabel : coreLabels) {

            String ner = coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class);
            if(ner.contains("PERSON"))
            return true;
        }
        
		return false;
}

	
}