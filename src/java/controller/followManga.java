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

public class followManga extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        int id = Integer.parseInt(request.getParameter("id"));
        String action = request.getParameter("action");
        MangaDAO dao = new MangaDAO();
        HttpSession session = request.getSession();
        boolean isFollowing = false;
        if ("unfollow".equals(action)) {
            dao.unfollowManga(username, id);
            isFollowing = false;
        } else if ("follow".equals(action)) {
            dao.followManga(username, id);
            isFollowing = true;
        }
        request.setAttribute("isFollowing", isFollowing);
        session.setAttribute("username", username);
        session.setAttribute("id", id);
        request.getRequestDispatcher("manga?id=" + id).forward(request, response);
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
