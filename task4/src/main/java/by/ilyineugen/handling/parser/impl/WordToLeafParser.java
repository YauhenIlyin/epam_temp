package by.ilyineugen.handling.parser.impl;

import by.ilyineugen.handling.entity.TextComponent;
import by.ilyineugen.handling.entity.TextLeaf;
import by.ilyineugen.handling.evidence.TextComponentType;
import by.ilyineugen.handling.parser.ChainOfResponsibility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordToLeafParser implements ChainOfResponsibility {
    private final static String LETTER_REGEX = "[A-Za-zА-Яа-я]";
    private final static String HYPHEN_REGEX = "\\-";

    @Override
    public TextComponent handleRequest(TextComponent wordComponent, String wordTextData) {
        String strContainer;
        int wordLength = wordTextData.length();
        TextComponentType textComponentType = null;
        Pattern letterPattern = Pattern.compile(LETTER_REGEX);
        Pattern hyphenPattern = Pattern.compile(HYPHEN_REGEX);
        for (int index = 0; index < wordLength; index++) {
            strContainer = wordTextData.substring(index, index + 1);
            Matcher matchLetter = letterPattern.matcher(strContainer);
            Matcher matchHyphen = hyphenPattern.matcher(strContainer);
            if (matchLetter.lookingAt()) {
                textComponentType = TextComponentType.LETTER;
            } else if (matchHyphen.lookingAt()) {
                textComponentType = TextComponentType.INNER_SEPARATOR_SIGN;
            } else {
                textComponentType = TextComponentType.OTHER_CHARACTER;
            }
            TextComponent textLeaf = new TextLeaf(strContainer.charAt(0), textComponentType);
            wordComponent.add(textLeaf);
        }
        return wordComponent;
    }

}
