package com.tech.services.ruleEngine;

import com.tech.services.getRuleService;
import com.tech.payloadData.Rule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RuleEngine {

    @Autowired
    private getRuleService knowledgeBaseService;

    public Object run(InferenceEngine inferenceEngine, Object inputData) {
        String ruleNamespace = inferenceEngine.getRuleNamespace().toString();
        //TODO: Here for each call, we are fetching all rules from db. It should be cache.
        List<Rule> allRulesByNamespace = knowledgeBaseService.getAllRuleByNamespace(ruleNamespace);
        Object result = inferenceEngine.run(allRulesByNamespace, inputData);
        return result;
    }

}
