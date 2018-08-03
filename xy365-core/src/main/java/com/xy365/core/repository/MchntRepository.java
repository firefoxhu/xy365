package com.xy365.core.repository;

import com.xy365.core.model.Mchnt;

public interface MchntRepository extends XyRepository<Mchnt>{

    Mchnt findMchntByPhone(String mobile);

    Mchnt findMchntByUserId(Long userId);
}
