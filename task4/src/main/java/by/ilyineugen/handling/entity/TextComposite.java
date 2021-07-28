package by.ilyineugen.handling.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements Component {


    private char simpleElement;
    private Type type;
    private List<Component> components = new ArrayList<>();

    public TextComposite(Type type) {
        this.type = type;
    } bvbv b b bbb

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public Object getChild(int index) {
        return components.get(index);
    }

    @Override
    public void remove(Component component) {

    }

    @Override
    public void operation() {
        int size = components.size();
        for (int i = 0; i < size; i++) {
            components.get(i).operation();
        }
    }
}
