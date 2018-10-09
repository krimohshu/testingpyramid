package com.sdettest.service;

import com.sdettest.exceptions.ProductPriceCalculationException;

public interface IProductPriceService {

    public Double getPriceForAllProduct() throws ProductPriceCalculationException;
}
