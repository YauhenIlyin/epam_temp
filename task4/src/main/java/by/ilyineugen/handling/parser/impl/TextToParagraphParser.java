package by.ilyineugen.handling.parser.impl;

import by.ilyineugen.handling.entity.TextComponent;
import by.ilyineugen.handling.entity.TextComposite;
import by.ilyineugen.handling.evidence.TextComponentType;
import by.ilyineugen.handling.exception.CompositeAppException;
import by.ilyineugen.handling.parser.ChainOfResponsibility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextToParagraphParser implements ChainOfResponsibility {
    private final String PARAGRAPH_REGEX = "\\t.+?(\\r\\n|$)";
    private ParagraphToSentenceParser paragraphToSentenceParser = new ParagraphToSentenceParser();

    @Override
    public TextComponent handleRequest(TextComponent textComposite, String textData) throws CompositeAppException {
        if (textComposite == null) {
            textComposite = new TextComposite(TextComponentType.TEXT);
        }
        if (textData == null) {
            throw new CompositeAppException("TextParagraphParser.class : handleRequest(TextComponent x, String y) : error. String = null");
        }
        textData = textData + " ";
        //проверка на type ? тот ли указан
        String strContainer;
        Pattern pattern = Pattern.compile(PARAGRAPH_REGEX);
        Matcher matcher = pattern.matcher(textData);
        while (matcher.find()) {
            strContainer = textData.substring(matcher.start(), matcher.end());
            TextComponent paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
            paragraphToSentenceParser.handleRequest(paragraphComponent, strContainer);
            textComposite.add(paragraphComponent);
        }
        return textComposite;
    }

}
