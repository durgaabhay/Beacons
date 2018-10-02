package inclass2.group1.beaconproject;

import java.io.Serializable;

public class Discounts implements Serializable {


    private long _id;
    String discount,name,photo,price,region;

    public Discounts() {
    }

    public Discounts(String discount, String photo, String region, String name, String price) {
        this.discount = discount;
        this.photo = photo;
        this.region = region;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Discounts{" +
                " discount='" + discount + '\'' +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", price='" + price + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    public long getId() {
        return _id;
    }

    public void setId(long _id) {
        this._id = _id;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
