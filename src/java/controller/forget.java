package controller;

import dal.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class forget extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("username");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String repass = request.getParameter("repassword");
        AccountDAO adb = new AccountDAO();
        if (adb.checkAccountExist(user)) {
            if (adb.checkEmailExist(email)) {
                if (pass.equals(repass)) {
                    adb.updateAccount(user,pass);
                } else {
                    request.setAttribute("mess", "Mật khẩu mới không khớp");
                    request.getRequestDispatcher("forget.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("mess", "Email không khớp");
                request.getRequestDispatcher("forget.jsp").forward(request, response);
            }
            request.setAttribute("mess", "Đổi mật khẩu thành công");
            request.setAttribute("messType", "success");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // Nếu tài khoản đã tồn tại, hiển thị thông báo lỗi
            request.setAttribute("mess", "Tài khoản không tồn tại");
            request.getRequestDispatcher("forget.jsp").forward(request, response);
        }
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
