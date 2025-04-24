package controller;

import dal.AccountDAO;
import dal.MangaDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Manga;

public class owner extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        AccountDAO adb = new AccountDAO();
        Account acc = adb.getAccountByUsername(username);
        MangaDAO dao = new MangaDAO();
        if (acc.getAccountType().equals("admin")) {
            List<Manga> ownerManga = dao.getAllManga();
            session.setAttribute("ownerManga", ownerManga);
        } else {
            List<Manga> ownerManga = dao.getOwnerManga(username);
            session.setAttribute("ownerManga", ownerManga);
        }
        response.sendRedirect("owner.jsp");
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
