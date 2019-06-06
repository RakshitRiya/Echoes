package com.example.Translate;

import java.util.HashMap;

public class Mapping {

    HashMap<String, String> hmap;

    Mapping(){
        hmap = new HashMap<String, String>();
    }
    void putData(){
        hmap.put("i","me");
        hmap.put("mine","me");
        hmap.put("my","me");

        hmap.put("your","you");
        hmap.put("yourself","you");
        hmap.put("yours","you");


        hmap.put("her","she");

        hmap.put("his","he");
        hmap.put("him","he");


        hmap.put("i","me");
        hmap.put("mine","me");
        hmap.put("my","me");



    }
    String getData(String key)
    {
        return hmap.get(key);
    }

}
