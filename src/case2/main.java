package case2;

public class main {
    public static void main(String[] args) {

        Category category1 = new Category("food");
        Category category2 = new Category("drink");
        Product apple = new Product("apple", 150.0, category1);
        Product lemon = new Product("lemon", 100.0, category1);
        Product coke = new Product("coke", 250, category2);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(apple, 3);
        cart.addItem(apple, 4);
        cart.addItem(coke,3);
        cart.addItem(lemon,1);

        Campaign c1 = new Campaign(category1, 20, 3, DiscountType.RATE);
        Campaign c2 = new Campaign(category1, 10, 3, DiscountType.RATE);
        Campaign c3 = new Campaign(category1, 50, 3, DiscountType.AMOUNT);

        cart.applyDiscounts(c1, c2, c3);

        Coupon cp1 = new Coupon(550,20,DiscountType.RATE);

        cart.applyCoupon(cp1);
        System.out.println(cart.getTotalPrice()+"-"+cart.getCampaignDiscount()+"-"+cart.getCouponDiscount());

        DeliveryCostCalculator dc = new DeliveryCostCalculator(10.0, 10.0, 2.99);
        cart.setTotalDeliveryCost(dc.calculateFor(cart));
        System.out.println(cart.getDeliveryCost());
        cart.print();
    }
}
