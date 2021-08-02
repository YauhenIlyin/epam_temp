package by.ilyineugen.handling.evidence;

public enum TextComponentType {

    TEXT,
    PARAGRAPH,
    SENTENCE,
    LEXEME,                 //бело-красный, //привет! //привет //~6&9|(3&4) //~6&9|(3&4)...
    WORD,                   //бело-красный //солнце //де-ре-во
    SPECIAL_WORD,           //~6&9|(3&4)
    PUNCTUATION_MARK,       // . , - : ; ! ? ...
    LETTER,                 // а б в U
    INNER_SEPARATOR_SIGN,   //String.toString() //.  // Мамин-Сибиряк // -
    TEXT_FORMATTING_CHARACTER, // \r\n \t
    OTHER_CHARACTER,             //= //> //9 //&
    SPACE

}
