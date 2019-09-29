/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.AccountService;
import beans.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 652343
 */
public class LoginServlet extends HttpServlet
  {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
      {
        String out = request.getParameter("logout");
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        
        if(out == null)
          {
            if(u != null)
              {
                response.sendRedirect("/home");
              }
            else
              {
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
              }
          }
        else if (out.equals(""))
          {
            session.invalidate();
            request.setAttribute("message", "You have logged out succesfully.");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
          }
        
        
    
        
        
      }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
      {
        
        
        String name = request.getParameter("username");
        String pass = request.getParameter("password");
        
        AccountService as = new AccountService();
        User user = as.login(name, pass);
        
        if(user==null)
          {
            request.setAttribute("message", "Invalid Username or Password");
            request.setAttribute("un", name);
            request.setAttribute("pw", pass);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
          }
        if(user!=null)
          {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("/home");
          }
      }

  }
