package case2;

public class Coupon {
    private double minAmount;
    private double discountRate;
    private DiscountType discountType;

    public Coupon(double minAmount, double discountRate, DiscountType discountType){
        this.minAmount = minAmount;
        this.discountRate = discountRate;
        this.discountType = discountType;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }
}
