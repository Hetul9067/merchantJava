import java.util.Date;
import java.util.Random;

public class Item {



    private String name ;

    private float sellingPrice;

    private float buyingPrice;

    private float quantity;

    private int randomValue;

    public Item(String name, float price, float quantity){
        this.name = name;
        randomlySetBuyingPrice(price);
        randomlySetSellingPrice();

        this.quantity = quantity;

    }

    public void randomlySetBuyingPrice(float price){
        float maxPrice = price * 1.50f;

        if(randomValue == 1)    randomValue = 0;
        else    randomValue = 1;

        Random rand = new Random(new Date().getTime());
        float range = maxPrice - price;

        buyingPrice = (float) Math.round(rand.nextDouble() * range + price);

        // Round to 2 decimal places
        buyingPrice = (float) Math.round(buyingPrice * 100) / 100;


    }

    public void randomlySetSellingPrice(){

        // Assuming BuyingPrice is accessed via a getter method
        float maxPrice = this.getBuyingPrice() * 1.50f;

        Random rand = new Random(new Date().getTime());

        // Assuming BuyingPrice is accessed via a getter method
        float range =  maxPrice - getBuyingPrice();

        // Assuming SellingPrice is a float field
        float sellingPrice = (float) Math.round(rand.nextDouble() * range + getBuyingPrice());

        // Round to 2 decimal places
        sellingPrice = (float) Math.round(sellingPrice * 100) / 100;

        // Assuming SellingPrice is set via a setter method

        setSellingPrice(sellingPrice);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public float getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(float buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
