package by.ilyineugen.handling.entity;

import by.ilyineugen.handling.evidence.TextComponentType;

public class TextLeaf implements TextComponent {

    private char element;
    private TextComponentType leafType;

    public TextLeaf(char element, TextComponentType leafType) {
        this.element = element;
        this.leafType = leafType;
    }

    @Override
    public void add(TextComponent textComponent) {
    }

    @Override
    public TextComponentType getTextComponentType() {
        return this.leafType;
    }

    @Override
    public TextComponent getChild(int index) {
        return this;
    }

    @Override
    public int getComponentSize() {
        return 0;
    }

    @Override
    public void swapChildElements(int indexOne, int indexTwo) {

    }

    @Override
    public void remove(TextComponent textComponent) {

    }

    public char getElement() {
        return this.element;
    }

    public void setElement(char element) {
        this.element = element;
    }

    @Override
    public String operation() {
        return toString();
    }

    @Override
    public String toString() {
        return "" + this.element;
    }
}
