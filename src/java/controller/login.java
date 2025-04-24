package controller;

import dal.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

public class login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        Cookie[] arr = request.getCookies();
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("userC")) {
                    request.setAttribute("username", o.getValue());
                }
                if (o.getName().equals("passC")) {
                    request.setAttribute("password", o.getValue());
                }
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String r = request.getParameter("remember");
        AccountDAO adb = new AccountDAO();
        Account a = adb.login(user, pass);
        if (a == null) {
            request.setAttribute("mess", "Tên đăng nhập hoặc mật khẩu sai");
            request.setAttribute("messType", "failure");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("acc", a);
            session.setAttribute("admin", a.getAccountType());
            Cookie u = new Cookie("userC", user);
            Cookie p = new Cookie("passC", pass);
            if (r != null) {
                u.setMaxAge(60 * 60);
                p.setMaxAge(60 * 60);
            }else{
                u.setMaxAge(0);
                p.setMaxAge(0);
            }
            response.addCookie(u);
            response.addCookie(p);
            response.sendRedirect("home");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
