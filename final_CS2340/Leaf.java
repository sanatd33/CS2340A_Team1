public class Leaf implements Component {
    private String identity;

    public Leaf(String identity) {
        this.identity = identity;
    }
    @Override
    public void execute() {
        System.out.println("Displaying: " + identity);
    }
}
