package com.mahakim.app.utils;

import java.util.Set;
import java.util.TreeSet;

public abstract class ReturnTwoTypes<T> {
    public abstract T runProgram();

}

 class ReturnSetType extends ReturnTwoTypes<Set<String>> {

    @Override
    public Set<String> runProgram(){
        return new TreeSet<>();
    }
}