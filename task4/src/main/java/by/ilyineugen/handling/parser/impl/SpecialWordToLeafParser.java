package by.ilyineugen.handling.parser.impl;

import by.ilyineugen.handling.entity.TextComponent;
import by.ilyineugen.handling.entity.TextLeaf;
import by.ilyineugen.handling.evidence.TextComponentType;
import by.ilyineugen.handling.parser.ChainOfResponsibility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialWordToLeafParser implements ChainOfResponsibility {
    private final String LETTER_REGEX = "[A-Za-zА-Яа-я]";
    private final String DOT_REGEX = "\\.";

    @Override
    public TextComponent handleRequest(TextComponent specialWordComponent, String specialWordTextData) {
        String strContainer;
        int specialWordLength = specialWordTextData.length();
        TextComponentType textComponentType;
        Pattern letterPattern = Pattern.compile(LETTER_REGEX);
        Pattern dotPattern = Pattern.compile(DOT_REGEX);
        for (int index = 0; index < specialWordLength; index++) {
            strContainer = specialWordTextData.substring(index, index + 1);
            Matcher matchLetter = letterPattern.matcher(strContainer);
            Matcher matchDot = dotPattern.matcher(strContainer);
            if (matchLetter.lookingAt()) {
                textComponentType = TextComponentType.LETTER;
            } else if (matchDot.lookingAt()) {
                textComponentType = TextComponentType.INNER_SEPARATOR_SIGN;
            } else {
                textComponentType = TextComponentType.OTHER_CHARACTER;
            }
            TextComponent textLeaf = new TextLeaf(strContainer.charAt(0), textComponentType);
            specialWordComponent.add(textLeaf);
        }
        return specialWordComponent;
    }

}
