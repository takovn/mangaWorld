package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Account;

public class manage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet manage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet manage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        List<Account> accounts = accountDAO.getAll();
        request.setAttribute("accounts", accounts);
        PrintWriter out = response.getWriter();
        out.print(accounts);
        request.getRequestDispatcher("manage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int uid = Integer.parseInt(request.getParameter("uid"));
        String action = request.getParameter("action");
        String newPassword = request.getParameter("pass");
        String newEmail = request.getParameter("email");

        AccountDAO dao = new AccountDAO();
        String message = "";

        if ("update".equals(action)) {
            boolean updated = dao.updateAccountAdmin(uid, newPassword, newEmail);
            if (updated) {
                message = "Cập nhật thành công.";
            } else {
                message = "Cập nhật thất bại.";
            }
        } else if ("delete".equals(action)) {
            boolean deleted = dao.deleteAccount(uid);
            if (deleted) {
                message = "Tài khoản đã xóa thành công.";
            } else {
                message = "Không thể xóa tài khoản này.";
            }
        }

        AccountDAO accountDAO = new AccountDAO();
        List<Account> accounts = accountDAO.getAll();
        request.setAttribute("accounts", accounts);
        request.setAttribute("mess", message);
        request.getRequestDispatcher("manage.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
