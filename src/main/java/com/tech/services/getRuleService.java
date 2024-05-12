package com.tech.services;

import com.tech.modelEntity.RuleDbModel;
import com.tech.daoRepositories.RulesRepository;
import com.tech.payloadData.Rule;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class getRuleService {
    @Autowired
    private RulesRepository rulesRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Rule> getAllRules(){

        List<RuleDbModel> ruleDbModel= rulesRepository.findAll();

        List<Rule> allRules=new ArrayList<>();

        for(RuleDbModel r: ruleDbModel){
            allRules.add(modelMapper.map(r,Rule.class));
        }

        return allRules;

    }

    public List<Rule> getAllRuleByNamespace(String ruleNamespace){

        List<RuleDbModel> ruleDbModel= rulesRepository.findByRuleNamespace(ruleNamespace);

        List<Rule> allRules=new ArrayList<>();

        for(RuleDbModel r: ruleDbModel){
            allRules.add(modelMapper.map(r,Rule.class));
        }

        return allRules;

    }

}
