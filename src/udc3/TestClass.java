/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udc3;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Factory Model 
 */
public abstract class TestClass {
    private final HashMap<String, BaseUser> users = new HashMap<>(); 
    
    public TestClass(){
        users.putAll(getUsers()); 
    }
    
    abstract protected Map getUsers(); 
}

class UserTestClass extends TestClass{

    @Override
    protected Map getUsers() {
        Map<String, User> test1 = new HashMap<>(); 
        return test1; 
    }
    
}

class RepTestClass extends TestClass{

    @Override
    protected Map getUsers() {
        Map<String, Rep> test2 = new HashMap<>();
        return test2;   
    }
    
}