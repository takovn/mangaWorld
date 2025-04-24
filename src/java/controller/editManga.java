package controller;

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
import model.Manga;
import model.Type;

public class editManga extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        MangaDAO dao = new MangaDAO();
        Manga manga = dao.getMangaById(Integer.parseInt(id));
        TypeDAO daot = new TypeDAO();
        List<Type> typeList = daot.getAll();
        HttpSession session = request.getSession();
        session.setAttribute("manga", manga);
        session.setAttribute("typeList", typeList);
        response.sendRedirect("editManga.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String imageUrl = request.getParameter("imageUrl");
        String author = request.getParameter("author");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        // Lấy danh sách thể loại được chọn (dưới dạng tên)
        String[] selectedTypeNames = request.getParameterValues("types"); // "types" là tên của input cho thể loại

        // Tạo đối tượng Manga để cập nhật
        Manga manga = new Manga();
        manga.setId(Integer.parseInt(id));
        manga.setName(name);
        manga.setImageUrl(imageUrl);
        manga.setAuthor(author);
        manga.setDate(date);
        manga.setStatus(status);

        // Tạo DAO và cập nhật manga
        MangaDAO dao = new MangaDAO();
        dao.updateManga(manga, selectedTypeNames); // Cập nhật với tên thể loại

        Manga owner = dao.getMangaById(Integer.parseInt(id));
        String username = owner.getOwner();
        List<Manga> ownerManga = dao.getOwnerManga(username);
        HttpSession session = request.getSession();
        session.setAttribute("ownerManga", ownerManga);
        response.sendRedirect("owner.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
