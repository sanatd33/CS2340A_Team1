public class Tester {
    public static void main(String[] args) {
        Payment paypal = PaymentFactory.createPayment("PayPal");
        paypal.processPayment(100);

        Payment creditcard = PaymentFactory.createPayment("Credit Card");
        creditcard.processPayment(100);
    }
}
