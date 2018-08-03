package com.xy365.core.repository;
import com.xy365.core.model.ShopInfoType;

import java.util.List;

public interface ShopInfoTypeRepository extends XyRepository<ShopInfoType> {

    List<ShopInfoType> findShopInfoTypeByShopInfoId(Long shopInfoId);


}
