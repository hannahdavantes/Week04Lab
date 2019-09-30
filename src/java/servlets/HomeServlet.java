/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 652343
 */
public class HomeServlet extends HttpServlet
  {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
      {
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");
        
        
        
        if(u==null)
          {
            response.sendRedirect("/login");
            
          }
        else
          {
            request.setAttribute("user", u);
            getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
          }
      }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
      {
        
      }


  }
