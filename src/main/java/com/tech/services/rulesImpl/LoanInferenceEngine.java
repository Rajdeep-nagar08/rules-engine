package com.tech.services.rulesImpl;

import com.tech.Controller.RuleNamespace;
import com.tech.payloadData.LoanDetails;
import com.tech.payloadData.UserDetails;
import com.tech.services.ruleEngine.InferenceEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoanInferenceEngine extends InferenceEngine<UserDetails, LoanDetails> {

    @Override
    protected RuleNamespace getRuleNamespace() {
        return RuleNamespace.LOAN;
    }

    @Override
    protected LoanDetails initializeOutputResult() {
        return LoanDetails.builder().build();
    }
}
