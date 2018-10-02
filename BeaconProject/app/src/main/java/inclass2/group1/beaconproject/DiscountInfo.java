package inclass2.group1.beaconproject;

import java.util.ArrayList;

public class DiscountInfo {

    ArrayList<Discounts> data;

    public DiscountInfo(ArrayList<Discounts> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DiscountInfo{" +
                "discountDetails=" + data +
                '}';
    }

    public ArrayList<Discounts> getDiscountDetails() {
        return data;
    }

    public void setDiscountDetails(ArrayList<Discounts> discountDetails) {
        this.data = discountDetails;
    }
}
