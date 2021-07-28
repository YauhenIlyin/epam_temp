package by.ilyineugen.handling.entity;

import java.util.ArrayList;

public class SimpleElement implements Component {

    private Type type = Type.SIMPLE_ELEMENT;
    private char element;

    public SimpleElement(char element) {
        this.element = element;
    }

    @Override
    public void add(Component component) {

    }

    public Type getType() {
        return this.type;
    }

    public char getElement() {
        return this.element;
    }

    public void setElement(char element) {
        this.element = element;
    }

    @Override
    public Object getChild(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component component) {

    }

    @Override
    public void operation() {

    }
}
