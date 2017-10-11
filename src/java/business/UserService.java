/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author 738377
 */
public class UserService {
    public boolean login(String username, String password) {
        
        boolean valid = false;
        
            if (username.equals("adam") || username.equals("betty")) {
                
                if (password.equals("password")) {
                    valid = true;
                }
                
                else {
                    valid = false;
                }
            }
            
            else {
                valid = false;
            }
            
            return valid;
    }
}
