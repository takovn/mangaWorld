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

public class home extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        MangaDAO dao = new MangaDAO();

        // Lấy số trang hiện tại từ yêu cầu, mặc định là 1 nếu không có
        String pageParam = request.getParameter("page");
        int currentPage = (pageParam != null) ? Integer.parseInt(pageParam) : 1;

        // Lấy danh sách manga cho trang hiện tại
        List<Manga> mangaList = dao.getMangaByPage(currentPage);
        session.setAttribute("mangaList", mangaList);

        // Lấy tổng số manga để tính số trang
        int totalMangaCount = dao.getTotalMangaCount();
        int pageSize = 25; // Số manga mỗi trang
        int totalPages = (int) Math.ceil((double) totalMangaCount / pageSize);

        // Lưu số trang hiện tại và tổng số trang vào session hoặc request
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);

        // Lấy 5 manga phổ biến nhất
        List<Manga> top5Manga = dao.getTop5MangaByFollow();
        session.setAttribute("top5Manga", top5Manga);
        request.getRequestDispatcher("home.jsp").forward(request, response);
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
