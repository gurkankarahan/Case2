package case2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShoppingCart {
    private HashMap<Product, Integer> addedProducts;
    //below map is added to calculate quantitiy and category price. value of hashmap contains of 2 elements. first is quantity, second is total price of category
    private HashMap<Category, Number []> categoryMap;
    private double totalPrice;
    private double discountedPrice;
    private double campaignDiscount;
    private double couponDiscount;
    private double deliveryCost;

    public ShoppingCart(){
        this.addedProducts = new HashMap<>();
        this.categoryMap = new HashMap<>();

        this.totalPrice = 0.0;
        this.discountedPrice = 0.0;
        this.campaignDiscount = 0.0;
        this.couponDiscount = 0.0;
    }

    public HashMap<Product, Integer> getAddedProducts() {
        return this.addedProducts;
    }

    public HashMap<Category, Number[]> getCategoryMap() {
        return categoryMap;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public double getCampaignDiscount() {
        return campaignDiscount;
    }

    public double getCouponDiscount() {
        return couponDiscount;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setTotalDeliveryCost(double totalDeliveryCost) {
        this.deliveryCost = totalDeliveryCost;
    }

    public void addItem(Product product, Integer quantity){
        addCategory(product, quantity);

        this.totalPrice = this.totalPrice + product.getPrice() * quantity;
        if (this.addedProducts.containsKey(product)){
            quantity = quantity + this.addedProducts.get(product);
        }
        this.addedProducts.put(product, quantity);
    }

    public void addCategory(Product product, Integer quantity){
        double price = product.getPrice() * quantity;
        if (this.categoryMap.containsKey(product.getCategory())){
            quantity = quantity + this.categoryMap.get(product.getCategory())[0].intValue();
            price = price + this.categoryMap.get(product.getCategory())[1].doubleValue();
        }
        Number[] quantityPriceArray = {quantity, price};
        this.categoryMap.put(product.getCategory(), quantityPriceArray);
    }

    public void applyDiscounts(Campaign... discounts){
        for (Campaign discount : discounts){
            if (categoryMap.containsKey(discount.getCategory()) && categoryMap.get(discount.getCategory())[0].intValue() >= discount.getQuantity()) {
                calculateDiscountByCampaign(discount);
            }
        }
    }

    public void calculateDiscountByCampaign(Campaign discount){
        if (discount.getDiscountType().equals(DiscountType.AMOUNT)){
            // here discountRate is amount not rate
            if (this.campaignDiscount<discount.getDiscountRate()){
                this.campaignDiscount = discount.getDiscountRate();
            }
        } else {
            double possibleDiscount = categoryMap.get(discount.getCategory())[1].doubleValue() * discount.getDiscountRate() / 100;
            if (this.campaignDiscount<possibleDiscount){
                this.campaignDiscount = possibleDiscount;
            }
        }
    }

    public void applyCoupon(Coupon coupon){
        if (this.totalPrice - (this.campaignDiscount + this.couponDiscount) >= coupon.getMinAmount()){
            if (coupon.getDiscountType().equals(DiscountType.AMOUNT)) {
                // here discountRate is amount not rate
                this.couponDiscount = coupon.getDiscountRate();
            } else {
                this.couponDiscount = this.totalPrice * coupon.getDiscountRate() / 100;
            }
        }
    }

    public double getTotalAmountAfterDiscounts(){
        return totalPrice - campaignDiscount - couponDiscount;
    }

    public void print(){
        System.out.println("CATEGORY_NAME | PRODUCT_NAME | QUANTITY | UNIT_PRICE | TOTAL_PRICE");
        Iterator it = this.getAddedProducts().entrySet().iterator();
        while (it.hasNext()) {
            StringBuilder sb = new StringBuilder();
            Map.Entry product = (Map.Entry)it.next();
            sb.append(((Product)product.getKey()).getCategory().getTitle() + " | ");
            sb.append(((Product)product.getKey()).getTitle() + " | ");
            sb.append(product.getValue() + " | ");
            sb.append(((Product)product.getKey()).getPrice() + " | ");
            sb.append(((Product)product.getKey()).getPrice()*(int)product.getValue());

            it.remove();
            System.out.println(sb.toString());
        }
    }

}
