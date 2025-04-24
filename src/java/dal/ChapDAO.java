package dal;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Chap;

public class ChapDAO extends DBContext {

    public void addChap(int mangaId, String title, String link) {
        String sql = "INSERT INTO chap (manga_id, title, link) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, mangaId);
            pstmt.setString(2, title);
            pstmt.setString(3, link);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Chap> getChapsByMangaId(int mangaId) {
        List<Chap> chapList = new ArrayList<>();
        String sql = "SELECT * FROM chap WHERE manga_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, mangaId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int chapId = rs.getInt("id");
                String chapName = rs.getString("title");
                String link = rs.getString("link");
                Chap chap = new Chap(mangaId, chapId, chapName, link);
                chapList.add(chap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chapList;
    }

    public void deleteChap(int chapId) {
        String query = "DELETE FROM chap WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, chapId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Chap getChapById(int id) {
        String sql = "SELECT * FROM chap WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Chap chap = new Chap();
                    chap.setChapId(rs.getInt("id"));
                    chap.setMangaId(rs.getInt("manga_id"));
                    chap.setChapName(rs.getString("title"));
                    chap.setLink(rs.getString("link"));
                    return chap;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getNumberOfImages(String path) {
        File directory = new File(path);
        if (!directory.exists() || !directory.isDirectory()) {
            return 0; // Trả về 0 nếu thư mục không tồn tại
        }

        String[] images = directory.list((dir, name) -> name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg"));
        return images != null ? images.length : 0; // Trả về số lượng hình ảnh
    }

    public static List<String> loadImageList(String path) {
        List<String> imageList = new ArrayList<>();
        File directory = new File(path);
        if (directory.exists() && directory.isDirectory()) {
            String[] images = directory.list((dir, name) -> name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg"));
            if (images != null) {
                Collections.addAll(imageList, images);
            }
        }
        return imageList;
    }
}
