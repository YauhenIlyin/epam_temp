package by.ilyineugen.handling.parser.impl;

import by.ilyineugen.handling.entity.TextComponent;
import by.ilyineugen.handling.entity.TextComposite;
import by.ilyineugen.handling.evidence.TextComponentType;
import by.ilyineugen.handling.parser.ChainOfResponsibility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphToSentenceParser implements ChainOfResponsibility {
    private static final String SENTENCE_REGEX = "\\t?[A-ZА-Я].+?(\\.{3}|[\\.\\!\\?])((\\r\\n)|\\s)";
    private SentenceToLexemeParser sentenceToLexemeParser = new SentenceToLexemeParser();

    @Override
    public TextComponent handleRequest(TextComponent paragraphComponent, String paragraphTextData) {
        String strContainer;
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matchSentence = pattern.matcher(paragraphTextData);
        while (matchSentence.find()) {
            strContainer = paragraphTextData.substring(matchSentence.start(), matchSentence.end());
            TextComponent sentenceComponent = new TextComposite(TextComponentType.SENTENCE);
            sentenceToLexemeParser.handleRequest(sentenceComponent, strContainer);
            paragraphComponent.add(sentenceComponent);
        }
        return paragraphComponent;
    }

}
