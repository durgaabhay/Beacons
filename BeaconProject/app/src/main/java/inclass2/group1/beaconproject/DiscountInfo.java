package inclass2.group1.beaconproject;

import java.util.ArrayList;

public class DiscountInfo {

    ArrayList<Discounts> results;

    public DiscountInfo(ArrayList<Discounts> discountDetails) {
        this.results = discountDetails;
    }

    @Override
    public String toString() {
        return "DiscountInfo{" +
                "discountDetails=" + results +
                '}';
    }

    public ArrayList<Discounts> getDiscountDetails() {
        return results;
    }

    public void setDiscountDetails(ArrayList<Discounts> discountDetails) {
        this.results = discountDetails;
    }
}
