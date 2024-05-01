public class CreditCard implements Payment {
    @Override
    public void processPayment(double amt) {
        System.out.println("A Credit Card payment of $" + amt + " was processed");
    }
}
