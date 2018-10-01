package inclass2.group1.beaconproject;

public class LifeStyle {

    String discount,name,photo,price,region;

    public LifeStyle(String discount, String name, String photo, String price, String region) {
        this.discount = discount;
        this.name = name;
        this.photo = photo;
        this.price = price;
        this.region = region;
    }

    @Override
    public String toString() {
        return "LifeStyle{" +
                "discount='" + discount + '\'' +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", price='" + price + '\'' +
                ", region='" + region + '\'' +
                '}';
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
