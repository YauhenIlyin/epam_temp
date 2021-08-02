package by.ilyineugen.handling.entity;

import by.ilyineugen.handling.evidence.TextComponentType;

public interface TextComponent {

    public void add(TextComponent textComponent);

    public TextComponentType getTextComponentType();

    public TextComponent getChild(int index);

    public int getComponentSize();

    public void swapChildElements(int indexOne, int indexTwo);

    public void remove(TextComponent textComponent);

    public String operation();

}
