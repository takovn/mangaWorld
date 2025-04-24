package controller;

import dal.AccountDAO;
import dal.MangaDAO;
import dal.TypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Manga;
import model.Type;

public class add extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        TypeDAO daot = new TypeDAO();
        List<Type> typeList = daot.getAll();
        HttpSession session = request.getSession();
        session.setAttribute("typeList", typeList);
        PrintWriter out = response.getWriter();
        out.print(typeList);
        session.setAttribute("username", username);
        response.sendRedirect("add.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String imageUrl = request.getParameter("imageUrl");
        String author = request.getParameter("author");
        String date = request.getParameter("date");
        String status = request.getParameter("status");
        String[] selectedTypeNames = request.getParameterValues("types");
        String username = request.getParameter("username");

        MangaDAO dao = new MangaDAO();
        int mangaId = dao.add(name, imageUrl, author, date, status); // Thêm manga và nhận id mới

        dao.addUserManga(username, mangaId); // Thêm vào bảng user_owner

        // Tạo đối tượng Manga và cập nhật các thể loại
        Manga manga = new Manga();
        manga.setId(mangaId);
        manga.setName(name);
        manga.setImageUrl(imageUrl);
        manga.setAuthor(author);
        manga.setDate(date);
        manga.setStatus(status);

        dao.updateManga(manga, selectedTypeNames);
        
        AccountDAO adb = new AccountDAO();
        Account acc = adb.getAccountByUsername(username);
        if ("reader".equals(acc.getAccountType())) {
            adb.updateAccountType(username, "uploader");
        }

        List<Manga> ownerManga = dao.getOwnerManga(username);
        HttpSession session = request.getSession();
        session.setAttribute("ownerManga", ownerManga);
        response.sendRedirect("home");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
