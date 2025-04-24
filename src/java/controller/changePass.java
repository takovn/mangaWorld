package controller;

import dal.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

public class changePass extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        Account acc = (Account) session.getAttribute("acc");
        String user = acc.getUser();
        String pass = request.getParameter("password");
        String repass = request.getParameter("repassword");
        AccountDAO adb = new AccountDAO();
        if (pass.equals(repass)) {
            adb.updateAccount(user, pass);
        } else {
            request.setAttribute("mess", "Mật khẩu mới không khớp");
            request.getRequestDispatcher("updateAccount.jsp").forward(request, response);
        }
        session.removeAttribute("acc");
        request.setAttribute("mess", "Đổi mật khẩu thành công");
        request.setAttribute("messType", "success");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
