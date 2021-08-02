package by.ilyineugen.handling.entity;

import by.ilyineugen.handling.evidence.TextComponentType;

import java.util.ArrayList;

public class TextComposite implements TextComponent {

    private ArrayList<TextComponent> textComponents = new ArrayList<>();
    private TextComponentType textComponentType;

    public TextComposite(TextComponentType textComponentType) {
        this.textComponentType = textComponentType;
    }

    @Override
    public void add(TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public TextComponentType getTextComponentType() {
        return this.textComponentType;
    }

    public ArrayList<TextComponent> getTextComponents() {
        return this.textComponents;
    }

    @Override
    public TextComponent getChild(int index) { //где проверка запрашиваемого индекса
        return textComponents.get(index);
    }

    @Override
    public int getComponentSize() {
        return this.textComponents.size();
    }

    @Override
    public void swapChildElements(int indexOne, int indexTwo) {

    }

    @Override
    public void remove(TextComponent textComponent) {
        textComponents.remove(textComponent);
    }

    @Override
    public String operation() {
        String str = "";
        int size = textComponents.size();
        for (int i = 0; i < size; i++) {
            str = str + textComponents.get(i).operation();

        }
        return str;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(1);
        if (this.textComponentType.equals(TextComponentType.WORD)) {
            sb.append("[WORD:]");
        }
        int currentCompositeLevelSize = textComponents.size();
        for (int index = 0; index < currentCompositeLevelSize; index++) {
            sb.append(textComponents.get(index).toString());
        }
        return sb.toString();
    }
}
