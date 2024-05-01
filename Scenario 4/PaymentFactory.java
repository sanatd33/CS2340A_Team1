public class PaymentFactory {
    public static Payment createPayment(String type) {
        if (type.equals("PayPal")) {
            return new PayPal();
        } else if (type.equals("Credit Card")) {
            return new CreditCard();
        }
        return null;
    }
}
