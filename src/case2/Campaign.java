package case2;

public class Campaign {
    private Category category;
    private double discountRate;
    private int quantity;
    private DiscountType discountType;

    public Campaign(Category category, double discountRate, int quantity, DiscountType discountType){
        this.category = category;
        this.discountRate = discountRate;
        this.quantity = quantity;
        this.discountType = discountType;
    }

    public Category getCategory() {
        return category;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public int getQuantity() {
        return quantity;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }
}
