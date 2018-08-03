package com.xy365.core.repository;

import com.xy365.core.model.Shop;

public interface ShopRepository extends XyRepository<Shop> {

    Shop findShopByName(String name);

    Shop findShopByMchntId(Long mchntId);

}
