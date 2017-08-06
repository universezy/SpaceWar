package com.example.agentzengyu.spacewar.entity.set;

import com.example.agentzengyu.spacewar.entity.single.Relevancy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agent ZengYu on 2017/7/20.
 */

/**
 * 关联库
 */
public class RelevancyLibrary {
    private List<Relevancy> relevancies = new ArrayList<>();

    public RelevancyLibrary(){

    }

    public List<Relevancy> getRelevancies() {
        return relevancies;
    }
}
