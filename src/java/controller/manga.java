package controller;

import dal.ChapDAO;
import dal.MangaDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Chap;
import model.Manga;

public class manga extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String mangaIdParam = request.getParameter("id");
        HttpSession session = request.getSession();
        int id = Integer.parseInt(mangaIdParam);
        MangaDAO dao = new MangaDAO();
        Manga manga = dao.getMangaById(id);
        session.setAttribute("manga", manga);

        List<String> typeNames = manga.getTypeNames();

        ChapDAO chapDAO = new ChapDAO();
        List<Chap> chapList = chapDAO.getChapsByMangaId(id);
        session.setAttribute("chapList", chapList);

        boolean isFollowing = dao.checkFollowStatus(username, id);
        session.setAttribute("isFollowing", isFollowing);

        List<Manga> relatedManga = dao.getRelatedManga(id, typeNames);
        session.setAttribute("relatedManga", relatedManga);
        
        // Chuyển hướng đến trang manga.jsp
        request.getRequestDispatcher("manga.jsp").forward(request, response);
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
