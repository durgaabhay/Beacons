package inclass2.group1.beaconproject;

import java.io.Serializable;

public class Discounts implements Serializable {


    private long id;
    String discount,name,image,price,region;

    public Discounts() {
    }

    public Discounts(String discount, String image, String region, String name, String price) {
        this.discount = discount;
        this.image = image;
        this.region = region;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Discounts{" +
                " discount='" + discount + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price='" + price + '\'' +
                ", region='" + region + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String photo) {
        this.image = photo;
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
