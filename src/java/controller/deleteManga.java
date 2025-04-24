package controller;

import dal.MangaDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Manga;

public class deleteManga extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id"); 
        MangaDAO dao = new MangaDAO();
        Manga manga = dao.getMangaById(Integer.parseInt(id));
        dao.deleteManga(Integer.parseInt(id));
        String username = manga.getOwner();
        List<Manga> ownerManga = dao.getOwnerManga(username);
        HttpSession session = request.getSession();
        session.setAttribute("ownerManga", ownerManga);
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
