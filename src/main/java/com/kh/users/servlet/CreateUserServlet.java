package com.kh.users.servlet;

import com.kh.users.dao.UserDao;
import com.kh.users.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/create")
public class CreateUserServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    User newUser = User.from(req);
    new UserDao().insert(newUser);
    resp.sendRedirect("/index.html");
  }
}
