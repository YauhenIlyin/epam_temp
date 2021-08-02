package by.ilyineugen.handling.main;

import by.ilyineugen.handling.entity.TextComponent;
import by.ilyineugen.handling.entity.TextComposite;
import by.ilyineugen.handling.evidence.TextComponentType;
import by.ilyineugen.handling.exception.CompositeAppException;
import by.ilyineugen.handling.parser.impl.TextToParagraphParser;
import by.ilyineugen.handling.reader.TextReader;
import by.ilyineugen.handling.service.CompositeTaskOperator;
import by.ilyineugen.handling.validator.TextValidator;

import java.io.File;

public class Main {

    public static void main(String[] args) throws CompositeAppException {
        String filePath = "src/main/resources/data/englishtext1.txt";
        File file = new File(filePath);
        System.out.println(TextValidator.validateTextFile(file));
        String text = TextReader.readTextFile(filePath);
        System.out.println(text);
        TextComponent textComposite = new TextComposite(TextComponentType.TEXT);
        TextToParagraphParser textToParagraphParser = new TextToParagraphParser();
        textToParagraphParser.handleRequest(textComposite, text);
        System.out.println("end parsing\n");
        System.out.println(textComposite);
        /////////////////////////////////////////
        //CompositeTaskOperator.findSentenceWithLongestWord(textComposite);
        //CompositeTaskOperator.removeSentenceUpOfGivenWordCount(textComposite, 55);
        //System.out.println(textComposite);
    }
}