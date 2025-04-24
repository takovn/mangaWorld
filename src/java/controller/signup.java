package controller;

import dal.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

public class signup extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("username");
        String email = request.getParameter("email"); // Nếu bạn cần sử dụng email
        String pass = request.getParameter("password");
        String repass = request.getParameter("repassword");

        // Kiểm tra xem mật khẩu có trùng khớp không
        if (!pass.equals(repass)) {
            request.setAttribute("mess", "Mật khẩu không khớp!");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return; // Thoát khỏi phương thức sau khi forward
        }

        AccountDAO adb = new AccountDAO();

        // Kiểm tra xem tài khoản có tồn tại không
        if (!adb.checkAccountExist(user)) {
            // Tạo đối tượng tài khoản mới và lưu vào cơ sở dữ liệu
            Account newAccount = new Account();
            newAccount.setUser(user);
            newAccount.setEmail(email); // Nếu bạn muốn lưu email
            newAccount.setPass(pass);
            adb.addAccount(newAccount);

            request.setAttribute("mess", "Đăng kí thành công");
            request.setAttribute("messType", "success");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // Nếu tài khoản đã tồn tại, hiển thị thông báo lỗi
            request.setAttribute("mess", "Tài khoản đã tồn tại");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
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
