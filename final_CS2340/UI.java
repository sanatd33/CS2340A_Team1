public class UI {
    public static void main(String[] args) {
        Composite dashboard = new Composite();
        Composite panel1 = new Composite();
        Composite panel2 = new Composite();
        panel1.addChild(new Leaf("Panel One Background"));
        panel1.addChild(new Leaf("Left Button"));
        panel1.addChild(new Leaf("Right Button"));
        panel2.addChild(new Leaf("Panel Two Background"));
        panel2.addChild(new Leaf("Up Button"));
        panel2.addChild(new Leaf("Down Button"));
        dashboard.addChild(panel1);
        dashboard.addChild(panel2);
        dashboard.execute();
    }
}
