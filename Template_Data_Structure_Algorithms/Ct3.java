import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

class Product implements Comparable<Product> {
    private String name;
    private double price;

    Product (String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return this.name;
    }  
    public double getPrice() {
        return this.price;
    }

    public String toString(){
        return name + "  " + price;
    }

    public int compareTo(Product that){
        if( that == null) 
            throw new NullPointerException();
        return (Double.compare(this.price,that.price) == 0) ? this.name.compareTo(that.name) : Double.compare(price,that.price);
    }
}


public class Ct3{
    public static  void main(String args[]){
        List<Product> productList = new ArrayList<>();
        Random rand = new Random();

        productList.add(new Product("A",1000*rand.nextDouble()));
        productList.add(new Product("B",1000*rand.nextDouble()));
        productList.add(new Product("C",1000*rand.nextDouble()));
        productList.add(new Product("D",1000*rand.nextDouble()));
        productList.add(new Product("E",1000*rand.nextDouble()));

        productList.add(1, new Product("PDummy",1000.0));

        for(Product p: productList)
           System.out.println(p); 

        System.out.println();
        System.out.println();

        Collections.sort(productList);

        for(Product p: productList)
            System.out.println(p);
    }
}