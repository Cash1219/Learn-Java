package cglib;

public class ProducerImpl {
    public void saleProduct(Float money){
        System.out.println("收款"+money+"元，给您电脑");
    }
    public void afterSale(Float money){
        System.out.println("收款"+money+"元，给您维修");
    }
}
