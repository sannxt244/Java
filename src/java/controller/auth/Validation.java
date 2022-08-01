/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.auth;

import dal.donghieu.UserDBContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dell
 */
public class Validation {
    public int checkGender(String gender, HttpServletRequest request, HttpServletResponse response) {
        if (gender != null && gender.length() > 0) {
           return 0;
        } else {
            request.setAttribute("genderError", "Please select your gender!");
            return 1;
        }

    }
    public int checkRoleId(String role, HttpServletRequest request, HttpServletResponse response) {
        if (role != null && role.length() > 0) {
           return 0;
        } else {
            request.setAttribute("roleError", "Please select your role!");
            return 1;
        }

    }
    public int checkFullName(String fullName,HttpServletRequest request, HttpServletResponse response){
        if(fullName != null && fullName.length() > 0){
            return 0;
        }
        request.setAttribute("nameError", "Please enter your name!");
        return 1;
    }
      public int checkEmai(String email,HttpServletRequest request, HttpServletResponse response){
        if(email != null && email.length() > 0){
            UserDBContext db = new UserDBContext();
            if(db.checkEmailExist(email)){
                request.setAttribute("emailError", "This email already in use");
                return 1;
            }
            return 0;
        }
        
        request.setAttribute("emailError", "Please enter your email!");
        return 1;
    }

    public int checkPhoneNumber(String phoneNumber, HttpServletRequest request, HttpServletResponse response) {
        if (phoneNumber != null && phoneNumber.length() > 0) {
            for (int i = 0; i < phoneNumber.length(); i++) {
                if (!Character.isDigit(phoneNumber.charAt(i))) {
                    request.setAttribute("phoneNumberError", "Phone number mustn't contain letter!");
                    return 1;
                } 
            }
            if(phoneNumber.length() == 10)
            return 0;
            
            request.setAttribute("phoneNumberError", "Phone number must have 10 digit!");
            return 1;
        }
        request.setAttribute("phoneNumberError", "Please enter your phone number!");
        return 1;
    }
    public int checkUsername(String userName, HttpServletRequest request, HttpServletResponse response){
       if(userName != null && userName.length() > 0){
            UserDBContext db = new UserDBContext();
        if(db.getUserByUsername(userName) != null){
            request.setAttribute("usernameError", "This username already in use");
            return 1;
        }
        if (userName.length() < 10 || userName.length() > 30) {
            request.setAttribute("usernameError", "Username must be greater than or equal to 10 and less than or equal to 30 character");
            return 1;
        }
        return 0;
       }
       request.setAttribute("usernameError", "You must enter username");
       return 1;
    }
    public int checkPassword(String password, HttpServletRequest request, HttpServletResponse response){
        if(password != null && password.length() > 0){
            if(password.length() < 8 || password.length() > 32){
            request.setAttribute("passwordError", "Password must be greater than or equal to 8  and less than or equal to 32 character");
            return 1;
        }
        return 0;
        }
        request.setAttribute("passwordError", "You must enter password");
        return 1;
    }
}
