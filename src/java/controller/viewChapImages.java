package controller;

import dal.ChapDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import model.Chap;

public class viewChapImages extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet viewChapImages</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet viewChapImages at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ChapDAO chapDAO = new ChapDAO();
        Chap chap = chapDAO.getChapById(id); // Lấy thông tin chap từ CSDL
        
        // Tạo đường dẫn đến thư mục chứa ảnh
        String path = getServletContext().getRealPath("/") + chap.getLink(); // Sử dụng chap.getLink()

        File dir = new File(path);
        File[] files = dir.listFiles();

        // Tiếp tục xử lý để gửi danh sách ảnh về JSP
        request.setAttribute("chap", chap);
        request.setAttribute("imageList", files != null ? files : new File[0]); // Gửi danh sách file về JSP
        
        request.getRequestDispatcher("viewChapImages.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
