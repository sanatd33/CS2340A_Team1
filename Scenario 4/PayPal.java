public class PayPal implements Payment {
    @Override
    public void processPayment(double amt) {
        System.out.println("A PayPal payment of $" + amt + " was processed");
    }
}
