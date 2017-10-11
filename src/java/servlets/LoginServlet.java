/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import business.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
/**
 *
 * @author 738377
 */
public class LoginServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").
                    forward(request, response);
        
        String username = null;
        String action = request.getParameter("action");
        
        if (action != null && action.equals("logout")) {
            request.setAttribute("message", "Logged out!");
            
            
        }
        Cookie[] c = request.getCookies();
        
        if (c != null) {
            for (Cookie cookie : c) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    response.sendRedirect("home");
                    return;
                }
            }
        }
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remembered = request.getParameter("rememberMe");
        
        if (username == null || password == null) {
            
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").
                    forward(request, response);
            
            return;
        } 
        
        else if (username.isEmpty() || password.isEmpty()) {
            request.setAttribute("message", "Both values are required!");
           
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").
                    forward(request, response);
            
            return;
        }
        
        UserService user = new UserService();
        
        if (user.login(username, password) == true) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            request.setAttribute("username", username);
            
            if (remembered == null) {
                response.sendRedirect("home");
                
            }
            
            else if (remembered.equals("remembered")) {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(60 * 60 * 24 * 365); 
                cookie.setPath("/");
                response.addCookie(cookie);
                response.sendRedirect("home");
            }
            
            
        }
        
        else {
            request.setAttribute("message", "Invalid username or password!");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").
                        forward(request, response);
        }
        
    }
    

    // </editor-fold>

}
