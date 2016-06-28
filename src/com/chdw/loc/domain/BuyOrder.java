package com.chdw.loc.domain;

import java.util.List;

/**
 * Created by CaoBin on 2016/4/25.
 */
public class BuyOrder {
    private TakeoutOrder takeoutOrder;
    private Seller seller;
    private List<Car> cars;
    private TakeoutOrderStatus takeoutOrderStatus;

    public TakeoutOrder getTakeoutOrder() {
        return takeoutOrder;
    }

    public void setTakeoutOrder(TakeoutOrder takeoutOrder) {
        this.takeoutOrder = takeoutOrder;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public TakeoutOrderStatus getTakeoutOrderStatus() {
        return takeoutOrderStatus;
    }

    public void setTakeoutOrderStatus(TakeoutOrderStatus takeoutOrderStatus) {
        this.takeoutOrderStatus = takeoutOrderStatus;
    }
}