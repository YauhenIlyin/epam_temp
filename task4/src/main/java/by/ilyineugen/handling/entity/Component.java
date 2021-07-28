package by.ilyineugen.handling.entity;

public interface Component {

    public void add(Component component);

    public Object getChild(int index);

    public void remove(Component component);

    public void operation();
}
