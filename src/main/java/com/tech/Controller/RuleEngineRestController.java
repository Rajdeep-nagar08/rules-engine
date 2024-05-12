package com.tech.Controller;

import com.google.common.base.Enums;
import com.tech.services.ruleEngine.RuleEngine;
import com.tech.payloadData.Rule;
import com.tech.services.getRuleService;
import com.tech.services.rulesImpl.InsuranceInferenceEngine;
import com.tech.payloadData.InsuranceDetails;
import com.tech.payloadData.PolicyHolderDetails;
import com.tech.payloadData.LoanDetails;
import com.tech.services.rulesImpl.LoanInferenceEngine;
import com.tech.payloadData.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//https://medium.com/@er.rameshkatiyar/what-is-rule-engine-86ea759ad97d


//https://medium.com/@er.rameshkatiyar/implement-your-own-rule-engine-java8-springboot-mvel-5928474e1ba5


@Slf4j
@RestController
public class RuleEngineRestController {
    @Autowired
    private getRuleService knowledgeBaseService;
    @Autowired
    private RuleEngine ruleEngine;
    @Autowired
    private LoanInferenceEngine loanInferenceEngine;
    @Autowired
    private InsuranceInferenceEngine insuranceInferenceEngine;

    @GetMapping(value = "/get-all-rules/{ruleNamespace}")
    public ResponseEntity<?> getRulesByNamespace(@PathVariable("ruleNamespace") String ruleNamespace) {
        RuleNamespace namespace = Enums.getIfPresent(RuleNamespace.class, ruleNamespace.toUpperCase()).or(RuleNamespace.DEFAULT);
        // if ruleNamespac present in enums then it's ok otherwise set it as DEFAULT

        List<Rule> allRules = knowledgeBaseService.getAllRuleByNamespace(namespace.toString());

        return ResponseEntity.ok(allRules);

    }

    @GetMapping(value = "/get-all-rules")
    public ResponseEntity<?> getAllRules() {
        List<Rule> allRules = knowledgeBaseService.getAllRules();
        return ResponseEntity.ok(allRules);
    }

    @PostMapping(value = "/loan")
    public ResponseEntity<?> postUserLoanDetails(@RequestBody UserDetails userDetails) {
        LoanDetails result = (LoanDetails) ruleEngine.run(loanInferenceEngine, userDetails);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/insurance")
    public ResponseEntity<?> postCarLoanDetails(@RequestBody PolicyHolderDetails policyHolderDetails) {
        InsuranceDetails result = (InsuranceDetails) ruleEngine.run(insuranceInferenceEngine, policyHolderDetails);
        return ResponseEntity.ok(result);
    }
}
