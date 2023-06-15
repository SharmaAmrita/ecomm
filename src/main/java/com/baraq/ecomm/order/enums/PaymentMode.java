package com.baraq.ecomm.order.enums;

public enum PaymentMode {
    PREPAID, CASH;

    public static PaymentMode getPaymentMode(String paymentMode) {
        return switch (paymentMode) {
            case "prepaid" -> PREPAID;
            case "cod" -> CASH;
            default -> null;
        };
    }
}
