package case2;

public class DeliveryCostCalculator {
    private double costPerDelivery;
    private double costPerProduct;
    private double fixedCost;

    public DeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost){
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }

    public double getCostPerDelivery() {
        return costPerDelivery;
    }

    public double getCostPerProduct() {
        return costPerProduct;
    }

    public double getFixedCost() {
        return fixedCost;
    }

    public double calculateFor(ShoppingCart cart){
        return cart.getAddedProducts().size()*costPerProduct + cart.getCategoryMap().size()*costPerDelivery + fixedCost;
    }
}
