package controller;

import dal.ChapDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Chap;

public class deleteChap extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet deleteChap</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteChap at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mangaIdParam = request.getParameter("id");
        int mangaId = Integer.parseInt(mangaIdParam);
        ChapDAO chapDAO = new ChapDAO();
        List<Chap> chapList = chapDAO.getChapsByMangaId(mangaId);

        // Lưu mangaId và chapList vào session
        HttpSession session = request.getSession();
        session.setAttribute("chapList", chapList);
        session.setAttribute("mangaId", mangaId);

        PrintWriter out = response.getWriter();
        out.print(chapList);

        String chapIdParam = request.getParameter("chapId");
        if (chapIdParam != null) {
            int chapId = Integer.parseInt(chapIdParam);
            Chap chap = chapDAO.getChapById(chapId);
            request.setAttribute("chap", chap);
        }
        request.getRequestDispatcher("deleteChap.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String chapIdParam = request.getParameter("chapId");

        if (chapIdParam != null && !chapIdParam.trim().isEmpty()) {
            int chapId = Integer.parseInt(chapIdParam);
            ChapDAO chapDAO = new ChapDAO();

            Chap chap = chapDAO.getChapById(chapId);
            if (chap != null) {
                chapDAO.deleteChap(chapId);
            } else {
                response.getWriter().println("ID của chap không hợp lệ.");
            }
        }
        String mangaIdParam = request.getParameter("id");
        int mangaId = Integer.parseInt(mangaIdParam);
        ChapDAO chapDAO = new ChapDAO();
        List<Chap> chapList = chapDAO.getChapsByMangaId(mangaId);

        // Lưu mangaId và chapList vào session
        HttpSession session = request.getSession();
        session.setAttribute("chapList", chapList);
        session.setAttribute("mangaId", mangaId);

        PrintWriter out = response.getWriter();
        out.print(chapList);

        if (chapIdParam != null) {
            int chapId = Integer.parseInt(chapIdParam);
            Chap chap = chapDAO.getChapById(chapId);
            request.setAttribute("chap", chap);
        }
        request.getRequestDispatcher("deleteChap.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
