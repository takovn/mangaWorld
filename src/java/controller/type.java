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

public class type extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String typeName = request.getParameter("name");
        MangaDAO dao = new MangaDAO();
        List<Manga> mangaByType = dao.searchMangaByType(typeName);
        HttpSession session = request.getSession();
        session.setAttribute("mangaByType", mangaByType);
        session.setAttribute("typeName", typeName);
        String username = request.getParameter("username");
        response.sendRedirect("type.jsp");
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
    }// </editor-fold>

}
