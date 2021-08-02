package by.ilyineugen.handling.parser;

import by.ilyineugen.handling.entity.TextComponent;
import by.ilyineugen.handling.entity.TextComposite;
import by.ilyineugen.handling.entity.TextLeaf;
import by.ilyineugen.handling.evidence.TextComponentType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IncorrectChainResponsibilityParser {
    private static final String PARAGRAPH_REGEX = "\\t.+?\\n|\\t.+?$";
    private static final String SENTENCE_REGEX = "[A-ZА-Я].+?([\\.\\!\\?]|(\\.{3}))";
    private static final String LEXEME_REGEX = ".+?(\\s|$)";
    private static final String LEXEME_PUNCTUATION_SIGN_REGEX = "(([^\\.\\,\\!\\?\\:]+|(\\.{3})))|[\\,\\.\\!\\?]";
    private static final String WORD_REGEX = "\\(?\"?(([A-Za-zА-Яа-я]+\\-?[A-Za-zА-Яа-я]*\\-?)+)\"?\\)?";
    private static final String WORD_LEXEME_REGEX = "[^A-Za-zА-Яа-я].+[^\\.\\,\\!\\?\\:]";
    private static final String PUNCTUATION_MARK_REGEX = "[\\.\\,\\!\\?\\:]";
    private static final String OTHER_CHARACTER_SIGN_REGEX = "[^A-Za-zА-Яа-я\\.\\,\\:\\!\\?]";
    private static final String LETTER_REGEX = "[A-Za-zА-Яа-я]";
    private static final String INNER_SEPARATOR_SIGN_REGEX = "[\\.\\,\\-]";
    private String container;

    public void parseTextToComposite(String text) {
        TextComponent textComposite = new TextComposite(TextComponentType.TEXT);
        parseTextToParagraphs(textComposite, text);
    }

    private void parseTextToParagraphs(TextComponent component, String text) {
        Pattern pattern = Pattern.compile(PARAGRAPH_REGEX);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            container = text.substring(matcher.start(), matcher.end());
            TextComponent paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
            parseParagraphsToSentences(paragraphComponent, container);
            component.add(paragraphComponent);
        }
    }

    private void parseParagraphsToSentences(TextComponent component, String paragraph) {
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(paragraph);
        while (matcher.find()) {
            container = paragraph.substring(matcher.start(), matcher.end());
            TextComponent sentenceComponent = new TextComposite(TextComponentType.SENTENCE);
            parseSentencesToLexemes(sentenceComponent, container);
            component.add(sentenceComponent);
        }
    }

    private void parseSentencesToLexemes(TextComponent component, String sentence) {
        Pattern pattern = Pattern.compile(LEXEME_REGEX);
        Matcher matcher = pattern.matcher(sentence);
        while (matcher.find()) {
            container = sentence.substring(matcher.start(), matcher.end());
            TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXEME);
            parseMainLexemes(lexemeComponent, container);
            component.add(lexemeComponent);
        }
    }

    private void parseMainLexemes(TextComponent component, String mainLexeme) {
        Pattern patternWord = Pattern.compile(WORD_REGEX);
        Pattern patternWordLexeme = Pattern.compile(WORD_LEXEME_REGEX);
        Pattern patternLexemePunctuationSign = Pattern.compile(LEXEME_PUNCTUATION_SIGN_REGEX);
        Pattern patternPunctuationMark = Pattern.compile(PUNCTUATION_MARK_REGEX);
        Pattern patternOtherCharacter = Pattern.compile(OTHER_CHARACTER_SIGN_REGEX);
        Matcher matcher = patternLexemePunctuationSign.matcher(mainLexeme);
        while (matcher.find()) {
            container = mainLexeme.substring(matcher.start(), matcher.end());
            Matcher matchWord = patternWord.matcher(container);
            Matcher matchWordLexeme = patternWordLexeme.matcher(container);
            Matcher matchPunctuationMark = patternPunctuationMark.matcher(container);
            Matcher matchOtherCharacter = patternOtherCharacter.matcher(container);
            if (matchWord.lookingAt()) {
                TextComponent composite = new TextComposite(TextComponentType.WORD);
                parseWordToLeaf(composite, container);
            } else if (matchWordLexeme.lookingAt()) {
                TextComponent composite = new TextComposite(TextComponentType.SPECIAL_WORD);
                parseLexemeToLeaf(composite, container);
            } else if (container.length() == 1 && matchPunctuationMark.lookingAt()) {
                TextComponent textLeaf = new TextLeaf(container.charAt(0), TextComponentType.PUNCTUATION_MARK);
                component.add(textLeaf);
            } else if (container.length() == 1 && matchOtherCharacter.lookingAt()) {
                TextComponent textLeaf = new TextLeaf(container.charAt(0), TextComponentType.OTHER_CHARACTER);
                component.add(textLeaf);
            }
        }
    }

    private void parseWordToLeaf(TextComponent component, String word) {
        int wordLength = word.length();
        Pattern patternOtherCharacter = Pattern.compile(OTHER_CHARACTER_SIGN_REGEX);
        Pattern patternLetter = Pattern.compile(LETTER_REGEX);
        for (int i = 0; i < wordLength; ++i) {
            String container = word.substring(i, i + 1);
            Matcher matchOtherCharacter = patternOtherCharacter.matcher(container);
            Matcher matchLetter = patternLetter.matcher(container);
            if (matchLetter.lookingAt()) {
                TextLeaf textLeaf = new TextLeaf(word.charAt(i), TextComponentType.LETTER);
                component.add(textLeaf);
            } else if (matchOtherCharacter.lookingAt()) {
                TextLeaf textLeaf = new TextLeaf(word.charAt(i), TextComponentType.OTHER_CHARACTER);
                component.add(textLeaf);
            }
        }
    }

    private void parseLexemeToLeaf(TextComponent component, String wordLexeme) {
        Pattern patternOtherCharacter = Pattern.compile(OTHER_CHARACTER_SIGN_REGEX);
        Pattern patternLetter = Pattern.compile(LETTER_REGEX);
        Pattern patternInnerSeparator = Pattern.compile(INNER_SEPARATOR_SIGN_REGEX);
        int wordLexemeLength = wordLexeme.length();
        for (int i = 0; i < wordLexemeLength; ++i) {
            container = wordLexeme.substring(i, i + 1);
            Matcher matchOtherCharacter = patternOtherCharacter.matcher(container);
            Matcher matchLetter = patternLetter.matcher(container);
            Matcher matchInnerSeparator = patternInnerSeparator.matcher(container);
            TextLeaf textLeaf = new TextLeaf(wordLexeme.charAt(i), TextComponentType.OTHER_CHARACTER);
            component.add(textLeaf);
        }
    }

}
