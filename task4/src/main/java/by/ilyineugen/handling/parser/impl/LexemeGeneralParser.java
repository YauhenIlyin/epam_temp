package by.ilyineugen.handling.parser.impl;

import by.ilyineugen.handling.entity.TextComponent;
import by.ilyineugen.handling.entity.TextComposite;
import by.ilyineugen.handling.entity.TextLeaf;
import by.ilyineugen.handling.evidence.TextComponentType;
import by.ilyineugen.handling.parser.ChainOfResponsibility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeGeneralParser implements ChainOfResponsibility {
    private final String WORD_REGEX = "^\\(?\"?(([A-Za-zА-Яа-я']+\\-?[A-Za-zА-Яа-я']*\\-?)+)\"?\\)?$"; //только для корректного предсказуемого текста
    private final String SPECIAL_WORD_REGEX = "([^\\t\\s]?)+[^\\.\\,\\!\\?\\:\\;\\s\\r\\n]";
    private final String PUNCTUATION_MARK_REGEX = "([\\.\\,\\!\\?\\:\\;\\-]|(\\.{3}))(\\r\\n)?$";
    private final String NEXT_LINE_REGEX = "\\r\\n";
    private final String TAB_REGEX = "^\\t";
    private final char NEXT_LINE_CHARACTER_FIRST = '\r';
    private final char NEXT_LINE_CHARACTER_SECOND = '\n';
    private SpecialWordToLeafParser specialWordToLeafParser = new SpecialWordToLeafParser();
    private WordToLeafParser wordToLeafParser = new WordToLeafParser();

    @Override
    public TextComponent handleRequest(TextComponent lexemeComponent, String lexemeTextData) {
        String strContainer;
        Pattern tabPattern = Pattern.compile(TAB_REGEX);
        Matcher matchTab = tabPattern.matcher(lexemeTextData);
        if (matchTab.find()) {
            strContainer = lexemeTextData.substring(matchTab.start(), matchTab.end());
            TextComponent tabLeafComponent = new TextLeaf(strContainer.charAt(0), TextComponentType.TEXT_FORMATTING_CHARACTER);
            lexemeTextData = lexemeTextData.substring(matchTab.end(), lexemeTextData.length());
            lexemeComponent.add(tabLeafComponent);
        }
        Pattern punctuationMarkPattern = Pattern.compile(PUNCTUATION_MARK_REGEX);
        Matcher matchPunctuationMark = punctuationMarkPattern.matcher(lexemeTextData);
        TextComponent punctuationMarkComponent = null;
        String punctuationMarkContainer = null;
        int punctuationMarkLength = 0;
        if (matchPunctuationMark.find()) {
            punctuationMarkContainer = lexemeTextData.substring(matchPunctuationMark.start(), matchPunctuationMark.end());
            punctuationMarkLength = punctuationMarkContainer.length();
        }
        Pattern nextLinePattern = Pattern.compile(NEXT_LINE_REGEX);
        Matcher matchNextLine = nextLinePattern.matcher(lexemeTextData);
        TextComponent nextLineComponentFirst = null;
        TextComponent nextLineComponentSecond = null;
        int nextLineMarksLength = 0;
        if (matchNextLine.find()) {
            nextLineMarksLength = 2;
            punctuationMarkLength -= nextLineMarksLength;
            nextLineComponentFirst = new TextLeaf(NEXT_LINE_CHARACTER_FIRST, TextComponentType.TEXT_FORMATTING_CHARACTER);
            nextLineComponentSecond = new TextLeaf(NEXT_LINE_CHARACTER_SECOND, TextComponentType.TEXT_FORMATTING_CHARACTER);
        }
        lexemeTextData = lexemeTextData.substring(0, lexemeTextData.length() - punctuationMarkLength - nextLineMarksLength);
        Pattern wordPattern = Pattern.compile(WORD_REGEX);
        Matcher matchWord = wordPattern.matcher(lexemeTextData);
        Pattern specialWordPattern = Pattern.compile(SPECIAL_WORD_REGEX);
        Matcher matchSpecialWord = specialWordPattern.matcher(lexemeTextData);
        if (matchWord.find()) {
            strContainer = lexemeTextData.substring(matchWord.start(), matchWord.end());
            TextComponent wordComponent = new TextComposite(TextComponentType.WORD);
            wordToLeafParser.handleRequest(wordComponent, strContainer);
            lexemeComponent.add(wordComponent);
        } else if (matchSpecialWord.find()) {
            strContainer = lexemeTextData.substring(matchSpecialWord.start(), matchSpecialWord.end());
            TextComponent specialWordComponent = new TextComposite(TextComponentType.SPECIAL_WORD);
            specialWordToLeafParser.handleRequest(specialWordComponent, strContainer);
            lexemeComponent.add(specialWordComponent);
        }
        if (punctuationMarkLength > 0) {
            for (int index = 0; index < punctuationMarkLength; index++) {
                char signContainer = punctuationMarkContainer.charAt(index);
                punctuationMarkComponent = new TextLeaf(signContainer, TextComponentType.PUNCTUATION_MARK);
                lexemeComponent.add(punctuationMarkComponent);
            }
        }
        if (nextLineComponentFirst != null) {
            lexemeComponent.add(nextLineComponentFirst);
            lexemeComponent.add(nextLineComponentSecond);
        }
        return lexemeComponent;
    }
}
