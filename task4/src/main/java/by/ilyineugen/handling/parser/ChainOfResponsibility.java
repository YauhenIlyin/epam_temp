package by.ilyineugen.handling.parser;

import by.ilyineugen.handling.entity.TextComponent;
import by.ilyineugen.handling.exception.CompositeAppException;

public interface ChainOfResponsibility {

    public TextComponent handleRequest(TextComponent textComponent, String textData) throws CompositeAppException;

}
