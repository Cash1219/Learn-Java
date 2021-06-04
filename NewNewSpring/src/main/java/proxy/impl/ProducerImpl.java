package proxy.impl;

import proxy.Producer;

public class ProducerImpl implements Producer {
    public void saleProduct(Float money){
        System.out.println("收款"+money+"元，给您电脑");
    }
    public void afterSale(Float money){
        System.out.println("收款"+money+"元，给您维修");
    }
}
