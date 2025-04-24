package controller;

import dal.ChapDAO;
import dal.MangaDAO;
import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.util.List;
import model.Manga;

@MultipartConfig
public class updateManga extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String mangaId = request.getParameter("id");
        String mangaName = request.getParameter("mangaName");
        String userType = request.getParameter("userType");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("mangaId", mangaId);
        session.setAttribute("mangaName", mangaName);
        session.setAttribute("userType", userType);
        response.sendRedirect("updateManga.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String mangaId = request.getParameter("mangaId");

// Base directory path
        String baseDir = getServletContext().getRealPath("/") + "uploads/manga_" + mangaId + "/chap_";

// Initialize a counter for the new directory
        int counter = 1;
        String uploadDir;

// Check for existing directories and increment the counter until a free name is found
        do {
            uploadDir = baseDir + title + "_" + counter; // Append counter to title
            counter++;
        } while (new File(uploadDir).exists());

// Create the new directory
        File chapDir = new File(uploadDir);
        if (!chapDir.exists()) {
            chapDir.mkdirs();
        }

// Save each file
        int pageNum = 1;
        for (Part part : request.getParts()) {
            if (part.getName().equals("images")) {
                String fileName = pageNum + ".jpg"; // Naming the files as per page number
                part.write(new File(chapDir, fileName).getAbsolutePath());
                pageNum++;
            }
        }

// Create link to the directory
        String link = "uploads/manga_" + mangaId + "/chap_" + title + "_" + (counter - 1); // Use the last counter value

// Add chap info to DB
        ChapDAO chapDAO = new ChapDAO();
        chapDAO.addChap(Integer.parseInt(mangaId), title, link);

        String username = request.getParameter("username");
        MangaDAO dao = new MangaDAO();
        List<Manga> ownerManga = dao.getOwnerManga(username);
        HttpSession session = request.getSession();
        session.setAttribute("ownerManga", ownerManga);
        response.sendRedirect("owner?username=" + username);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
