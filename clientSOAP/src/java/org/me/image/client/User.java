/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.image.client;

/**
 *
 * @author Samuel
 */
public class User {
    
    protected static boolean validateUsername(String username){
        
        return username!=null 
                && !username.isEmpty()
                && !username.contains(" ");
    }
    
    protected static boolean validatePassword(String password){
        
        return password!=null
                && !password.isEmpty()
                && !password.contains(" ")
                && password.length()>5;
    }
}
