package com.xy365.core.repository;

import com.xy365.core.model.Type;

import java.util.List;

public interface TypeRepository extends XyRepository<Type>{

    List<Type> findTypeByIdIn(List<Long> typeIds);
}
