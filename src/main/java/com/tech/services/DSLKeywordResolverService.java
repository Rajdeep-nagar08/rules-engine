package com.tech.services;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@NoArgsConstructor
public class DSLKeywordResolverService {
    Map<String, DSLResolverService> dslKeywordResolverList;

    @Autowired
    public DSLKeywordResolverService(List<DSLResolverService> resolverList) {
        dslKeywordResolverList = resolverList.stream()
                .collect(Collectors.toMap(DSLResolverService::getResolverKeyword, Function.identity()));
    }

    public Map<String, DSLResolverService> getDslKeywordResolverList(){
        return dslKeywordResolverList;
    }

    public Optional<DSLResolverService> getResolver(String keyword) {
        return Optional.ofNullable(dslKeywordResolverList.get(keyword));
    }
}
