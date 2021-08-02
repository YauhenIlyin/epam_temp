package by.ilyineugen.handling.service;

import by.ilyineugen.handling.entity.TextComponent;
import by.ilyineugen.handling.evidence.TextComponentType;

public class CompositeTaskOperator {

    private CompositeTaskOperator() {
    }

    public static void findSentenceWithLongestWord(TextComponent textComposite) {
        TextComponent desiredSentence = null;
        TextComponent desiredWord = null;
        int desiredWordLetterCount = 0;
        int textComponentLevelSize = textComposite.getComponentSize();
        for (int index = 0; index < textComponentLevelSize; ++index) {
            TextComponent currentParagraph = textComposite.getChild(index);
            int paragraphComponentLevelSize = currentParagraph.getComponentSize();
            for (int paragraphItemIndex = 0; paragraphItemIndex < paragraphComponentLevelSize; ++paragraphItemIndex) {
                TextComponent paragraphItem = currentParagraph.getChild(paragraphItemIndex);
                if (paragraphItem.getTextComponentType().equals(TextComponentType.SENTENCE)) {
                    int sentenceComponentLevelSize = paragraphItem.getComponentSize();
                    for (int sentenceItemIndex = 0; sentenceItemIndex < sentenceComponentLevelSize; ++sentenceItemIndex) {
                        TextComponent sentenceItem = paragraphItem.getChild(sentenceItemIndex);
                        if (sentenceItem.getTextComponentType().equals(TextComponentType.LEXEME)) {
                            int lexemeComponentLevelSize = sentenceItem.getComponentSize();
                            for (int lexemeItemIndex = 0; lexemeItemIndex < lexemeComponentLevelSize; ++lexemeItemIndex) {
                                TextComponent lexemeItem = sentenceItem.getChild(lexemeItemIndex);
                                if (lexemeItem.getTextComponentType().equals(TextComponentType.WORD)) {
                                    int currentWordLetterCount = 0;
                                    int currentWordComponentLevelSize = lexemeItem.getComponentSize();
                                    TextComponent wordLeafContainer;
                                    for (int signIndex = 0; signIndex < currentWordComponentLevelSize; ++signIndex) {
                                        wordLeafContainer = lexemeItem.getChild(signIndex);
                                        if (wordLeafContainer.getTextComponentType().equals(TextComponentType.LETTER)) {
                                            ++currentWordLetterCount;
                                        }
                                    }
                                    if (currentWordLetterCount > desiredWordLetterCount) {
                                        desiredWordLetterCount = currentWordLetterCount;
                                        desiredSentence = paragraphItem;
                                        desiredWord = lexemeItem;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("desired longest word: \n" + desiredWord);
        System.out.println("desired sentence: \n" + desiredSentence);
    }

    public static void removeSentenceUpOfGivenWordCount(TextComponent textComposite, int wordLimit) {
        int textComponentLevelSize = textComposite.getComponentSize();
        for (int index = 0; index < textComponentLevelSize; ++index) {
            TextComponent currentParagraph = textComposite.getChild(index);
            int paragraphComponentLevelSize = currentParagraph.getComponentSize();
            for (int paragraphItemIndex = 0; paragraphItemIndex < paragraphComponentLevelSize; ++paragraphItemIndex) {
                TextComponent paragraphItem = currentParagraph.getChild(paragraphItemIndex);
                if (paragraphItem.getTextComponentType().equals(TextComponentType.SENTENCE)) {
                    int currentSentenceWordCount = 0;
                    int sentenceComponentLevelSize = paragraphItem.getComponentSize();
                    for (int sentenceItemIndex = 0; sentenceItemIndex < sentenceComponentLevelSize; ++sentenceItemIndex) {
                        TextComponent sentenceItem = paragraphItem.getChild(sentenceItemIndex);
                        if (sentenceItem.getTextComponentType().equals(TextComponentType.LEXEME)) {
                            int lexemeComponentLevelSize = sentenceItem.getComponentSize();
                            for (int lexemeItemIndex = 0; lexemeItemIndex < lexemeComponentLevelSize; ++lexemeItemIndex) {
                                TextComponent lexemeItem = sentenceItem.getChild(lexemeItemIndex);
                                if (lexemeItem.getTextComponentType().equals(TextComponentType.WORD)) {
                                    ++currentSentenceWordCount;
                                }
                            }
                        }
                    }
                    if (currentSentenceWordCount < wordLimit) {
                        currentParagraph.remove(paragraphItem);
                        --paragraphComponentLevelSize;
                    }
                }
            }
        }
    }
}
