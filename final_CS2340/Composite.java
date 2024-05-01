import java.util.ArrayList;
public class Composite implements Component {
    private ArrayList<Component> children;

    public Composite() {
        children = new ArrayList<Component>();
    }

    public void addChild(Component c) {
        children.add(c);
    }

    public void removeChild(Component c) {
        children.remove(c);
    }

    public ArrayList<Component> getChildren() {
        return new ArrayList<Component>(children);
    }

    @Override
    public void execute() {
        for (Component c : children) {
            c.execute();
        }
    }
}
