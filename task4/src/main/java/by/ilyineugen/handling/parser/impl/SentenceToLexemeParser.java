package by.ilyineugen.handling.parser.impl;

import by.ilyineugen.handling.entity.TextComponent;
import by.ilyineugen.handling.entity.TextComposite;
import by.ilyineugen.handling.entity.TextLeaf;
import by.ilyineugen.handling.evidence.TextComponentType;
import by.ilyineugen.handling.parser.ChainOfResponsibility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SentenceToLexemeParser implements ChainOfResponsibility {
    private final String LEXEME_REGEX = "\\t?[\\S]+?[\\S]*\\r?\\n?";
    private final char SPACE_SIGN = ' ';
    private LexemeGeneralParser lexemeGeneralParser = new LexemeGeneralParser();


    @Override
    public TextComponent handleRequest(TextComponent sentenceComponent, String sentenceTextData) {
        String strContainer;
        Pattern pattern = Pattern.compile(LEXEME_REGEX);
        Matcher matchLexeme = pattern.matcher(sentenceTextData);
        while (matchLexeme.find()) {
            strContainer = sentenceTextData.substring(matchLexeme.start(), matchLexeme.end());
            TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXEME);
            lexemeGeneralParser.handleRequest(lexemeComponent, strContainer);
            sentenceComponent.add(lexemeComponent);
            TextComponent textLeaf = new TextLeaf(SPACE_SIGN, TextComponentType.SPACE);
            sentenceComponent.add(textLeaf);
        }
        return sentenceComponent;
    }

}
