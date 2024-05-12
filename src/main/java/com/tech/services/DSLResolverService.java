package com.tech.services;

public interface DSLResolverService {
    String getResolverKeyword();
    Object resolveValue(String keyword);
}
