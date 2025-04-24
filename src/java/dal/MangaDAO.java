package dal;

import model.Manga;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.sql.Date;

public class MangaDAO extends DBContext {

    private Manga extractMangaFromResultSet(ResultSet rs) throws SQLException {
        int mangaId = rs.getInt("id");
        Manga manga = new Manga(
                mangaId,
                rs.getString("name"),
                rs.getString("imageUrl"),
                rs.getString("author"),
                rs.getString("username"),
                new ArrayList<>(), // Tạm thời gán rỗng, sẽ thêm loại sau
                rs.getString("date"),
                rs.getString("status"),
                rs.getInt("chap"),
                rs.getInt("followNumber")
        );

        String sqlType = "SELECT t.name FROM manga_type mt "
                + "JOIN type t ON mt.type_id = t.id "
                + "WHERE mt.manga_id = ?";
        try (PreparedStatement preType = connection.prepareStatement(sqlType)) {
            preType.setInt(1, mangaId);
            try (ResultSet rsType = preType.executeQuery()) {
                List<String> typeNames = new ArrayList<>();
                while (rsType.next()) {
                    typeNames.add(rsType.getString("name"));
                }
                manga.setTypeNames(typeNames);
            }
        }

        return manga;
    }

    public List<Manga> getAllManga() {
        List<Manga> mangaList = new ArrayList<>();
        String sql = "SELECT m.*, a.username, "
                + "(SELECT COUNT(DISTINCT c.id) FROM chap c WHERE c.manga_id = m.id) AS chap, "
                + "(SELECT COUNT(DISTINCT f.user_id) FROM user_follow f WHERE f.manga_id = m.id) AS followNumber "
                + "FROM manga m "
                + "LEFT JOIN user_owner u ON m.id = u.manga_id "
                + "LEFT JOIN account a ON u.user_id = a.uid "
                + "ORDER BY m.date DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Manga manga = extractMangaFromResultSet(rs);
                mangaList.add(manga);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return mangaList;
    }

    public List<Manga> getMangaByPage(int page) {
        int pageSize = 25; // Số manga mỗi trang
        int offset = (page - 1) * pageSize; // Tính toán offset cho câu truy vấn

        List<Manga> mangaList = new ArrayList<>();
        String sql = "SELECT m.*, a.username, "
           + "(SELECT COUNT(DISTINCT c.id) FROM chap c WHERE c.manga_id = m.id) AS chap, "
           + "(SELECT COUNT(DISTINCT f.user_id) FROM user_follow f WHERE f.manga_id = m.id) AS followNumber "
           + "FROM manga m "
           + "LEFT JOIN user_owner u ON m.id = u.manga_id "
           + "LEFT JOIN account a ON u.user_id = a.uid "
           + "ORDER BY m.date DESC "
           + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, offset); // Thay thế với offset
            stmt.setInt(2, pageSize); // Số lượng bản ghi cần lấy

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Manga manga = extractMangaFromResultSet(rs);
                    mangaList.add(manga);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return mangaList;
    }

    public int getTotalMangaCount() {
        int count = 0;
        String sql = "SELECT COUNT(*) AS total FROM manga";

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return count;
    }

    public List<Manga> searchManga(String keyword) {
        List<Manga> mangaSearch = new ArrayList<>();
        String sql = "SELECT m.*, a.username, "
           + "(SELECT COUNT(DISTINCT c.id) FROM chap c WHERE c.manga_id = m.id) AS chap, "
           + "(SELECT COUNT(DISTINCT f.user_id) FROM user_follow f WHERE f.manga_id = m.id) AS followNumber "
           + "FROM manga m "
           + "LEFT JOIN user_owner u ON m.id = u.manga_id "
           + "LEFT JOIN account a ON u.user_id = a.uid "
           + "WHERE m.name LIKE ? OR m.author LIKE ? "
           + "ORDER BY m.date DESC";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            String searchKeyword = "%" + keyword + "%";
            stm.setString(1, searchKeyword);
            stm.setString(2, searchKeyword);

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    Manga manga = extractMangaFromResultSet(rs);
                    mangaSearch.add(manga);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return mangaSearch;
    }

    public List<Manga> searchMangaByType(String type) {
        List<Manga> mangaSearchType = new ArrayList<>();
        String sql = "SELECT m.*, a.username, "
           + "(SELECT COUNT(DISTINCT c.id) FROM chap c WHERE c.manga_id = m.id) AS chap, "
           + "(SELECT COUNT(DISTINCT f.user_id) FROM user_follow f WHERE f.manga_id = m.id) AS followNumber "
           + "FROM manga m "
           + "JOIN manga_type mt ON m.id = mt.manga_id "
           + "JOIN type t ON mt.type_id = t.id "
           + "LEFT JOIN user_owner u ON m.id = u.manga_id "
           + "LEFT JOIN account a ON u.user_id = a.uid "
           + "WHERE t.name = ? "
           + "ORDER BY m.date DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, type);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Manga manga = extractMangaFromResultSet(rs);
                    mangaSearchType.add(manga);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return mangaSearchType;
    }

    public List<Manga> getTop5MangaByFollow() {
        List<Manga> top5Manga = new ArrayList<>();
        String sql = "SELECT TOP 5 m.*, a.username, "
           + "(SELECT COUNT(DISTINCT c.id) FROM chap c WHERE c.manga_id = m.id) AS chap, "
           + "(SELECT COUNT(DISTINCT f.user_id) FROM user_follow f WHERE f.manga_id = m.id) AS followNumber "
           + "FROM manga m "
           + "LEFT JOIN user_owner u ON m.id = u.manga_id "
           + "LEFT JOIN account a ON u.user_id = a.uid "
           + "ORDER BY followNumber DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Manga manga = extractMangaFromResultSet(rs);
                top5Manga.add(manga);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return top5Manga;
    }

    public Manga getMangaById(int id) {
        String sql = "SELECT m.*, a.username, "
           + "(SELECT COUNT(DISTINCT c.id) FROM chap c WHERE c.manga_id = m.id) AS chap, "
           + "(SELECT COUNT(DISTINCT f.user_id) FROM user_follow f WHERE f.manga_id = m.id) AS followNumber "
           + "FROM manga m "
           + "LEFT JOIN user_owner u ON m.id = u.manga_id "
           + "LEFT JOIN account a ON u.user_id = a.uid "
           + "WHERE m.id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Manga manga = extractMangaFromResultSet(rs);
                // Lấy danh sách loại của manga
                String sqlType = "SELECT t.name FROM manga_type mt "
                        + "JOIN type t ON mt.type_id = t.id "
                        + "WHERE mt.manga_id = ?";
                try (PreparedStatement preType = connection.prepareStatement(sqlType)) {
                    preType.setInt(1, id);
                    ResultSet rsType = preType.executeQuery();

                    List<String> typeNames = new ArrayList<>();
                    while (rsType.next()) {
                        typeNames.add(rsType.getString("name"));
                    }
                    manga.setTypeNames(typeNames);
                }
                return manga;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public List<Manga> getRelatedManga(int mangaId, List<String> typeNames) {
        List<Manga> relatedManga = new ArrayList<>();

        // Tạo câu SQL để tìm kiếm các manga có type trùng với manga hiện tại
        String sql = "SELECT m.*, a.username, "
           + "(SELECT COUNT(*) FROM chap c WHERE c.manga_id = m.id) AS chap, "
           + "COUNT(DISTINCT f.user_id) AS followNumber "
           + "FROM manga m "
           + "LEFT JOIN user_owner u ON m.id = u.manga_id "
           + "LEFT JOIN account a ON u.user_id = a.uid "
           + "LEFT JOIN user_follow f ON m.id = f.manga_id "
           + "JOIN manga_type mt ON m.id = mt.manga_id "
           + "JOIN type t ON mt.type_id = t.id "
           + "WHERE t.name IN (" + typeNames.stream().map(name -> "?").collect(Collectors.joining(",")) + ") "
           + "AND m.id <> ? " // Không bao gồm manga hiện tại
           + "GROUP BY m.id, m.name, m.imageUrl, m.author, m.date, m.status, a.username "
           + "ORDER BY chap DESC, m.date DESC "
           + "OFFSET 0 ROWS FETCH NEXT 5 ROWS ONLY";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            int index = 1;
            for (String type : typeNames) {
                stmt.setString(index++, type);
            }
            stmt.setInt(index, mangaId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Manga manga = extractMangaFromResultSet(rs);
                relatedManga.add(manga);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return relatedManga;
    }

    public List<Manga> getOwnerManga(String username) {
        List<Manga> ownerManga = new ArrayList<>();
        String sql = "SELECT m.*, a.username, COUNT(DISTINCT c.id) AS chap, COUNT(DISTINCT f.user_id) AS followNumber "
           + "FROM manga m "
           + "LEFT JOIN chap c ON m.id = c.manga_id "
           + "LEFT JOIN user_follow f ON m.id = f.manga_id "
           + "LEFT JOIN user_owner u ON m.id = u.manga_id "
           + "LEFT JOIN account a ON u.user_id = a.uid "
           + "WHERE a.username = ? "
           + "GROUP BY m.id, m.name, m.imageUrl, m.author, m.date, m.status, a.username "
           + "ORDER BY m.date DESC";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Manga manga = extractMangaFromResultSet(rs);
                ownerManga.add(manga);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return ownerManga;
    }

    public void updateManga(Manga manga, String[] selectedTypeNames) {
        // Cập nhật thông tin manga trong cơ sở dữ liệu
        String sqlUpdateManga = "UPDATE manga SET name = ?, imageUrl = ?, author = ?, date = ?, status = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdateManga)) {
            pstmt.setString(1, manga.getName());
            pstmt.setString(2, manga.getImageUrl());
            pstmt.setString(3, manga.getAuthor());
            pstmt.setString(4, manga.getDate());
            pstmt.setString(5, manga.getStatus());
            pstmt.setInt(6, manga.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Để xem thông báo lỗi nếu có
        }

        // Xóa các thể loại cũ
        String sqlDeleteTypes = "DELETE FROM manga_type WHERE manga_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlDeleteTypes)) {
            pstmt.setInt(1, manga.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Để xem thông báo lỗi nếu có
        }

        // Thêm các thể loại mới
        String sqlInsertTypes = "INSERT INTO manga_type (manga_id, type_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlInsertTypes)) {
            for (String typeName : selectedTypeNames) {
                int typeId = getTypeIdByName(typeName); // Lấy ID từ tên thể loại
                pstmt.setInt(1, manga.getId());
                pstmt.setInt(2, typeId);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace(); // Để xem thông báo lỗi nếu có
        }
    }

    private int getTypeIdByName(String typeName) throws SQLException {
        String sql = "SELECT id FROM type WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, typeName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }
        throw new SQLException("Không tìm thấy thể loại: " + typeName);
    }

    public void deleteManga(int id) {
        String sql = "delete user_owner where manga_id = ? "
                + "delete user_follow where manga_id = ? "
                + "delete manga_type where manga_id = ? "
                + "delete chap where manga_id = ? "
                + "delete manga where id =  ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setInt(2, id);
            stmt.setInt(3, id);
            stmt.setInt(4, id);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Manga> getFollowManga(String username) {
        List<Manga> ownerManga = new ArrayList<>();
        String sql = "SELECT m.*, a.username, COUNT(DISTINCT c.id) AS chap, COUNT(DISTINCT f.user_id) AS followNumber "
           + "FROM manga m "
           + "LEFT JOIN user_follow f ON m.id = f.manga_id "
           + "LEFT JOIN account a ON f.user_id = a.uid "
           + "LEFT JOIN chap c ON m.id = c.manga_id "
           + "WHERE a.username = ? "
           + "GROUP BY m.id, m.name, m.imageUrl, m.author, m.date, m.status, a.username "
           + "ORDER BY m.date DESC";


        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Manga manga = extractMangaFromResultSet(rs);
                ownerManga.add(manga);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return ownerManga;
    }

    public void unfollowManga(String username, int mangaId) {
        String sql = "DELETE FROM user_follow "
                + "WHERE user_id = (SELECT uid FROM account WHERE username = ?) "
                + "AND manga_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, mangaId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public boolean checkFollowStatus(String username, int mangaId) {
        String sql = "SELECT COUNT(*) FROM user_follow WHERE user_id = (SELECT uid FROM account WHERE username = ?) AND manga_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, mangaId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu có bản ghi, trả về true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void followManga(String username, int mangaId) {
        String sql = "INSERT INTO user_follow (user_id, manga_id) "
                + "VALUES ((SELECT uid FROM account WHERE username = ? ), ? );";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, mangaId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public int add(String name, String imageUrl, String author, String date, String status) {
        String sql = "INSERT INTO manga (name, imageUrl, author, date, status) OUTPUT INSERTED.id VALUES (?, ?, ?, ?, ?)";
        int mangaId = -1; // Khởi tạo giá trị mặc định

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, imageUrl);
            pstmt.setString(3, author);
            pstmt.setDate(4, Date.valueOf(date));
            pstmt.setString(5, status);

            // Thực hiện chèn và lấy id mới
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                mangaId = rs.getInt(1); // Lấy giá trị id vừa chèn
            }
        } catch (SQLException e) {
        }
        return mangaId; // Trả về id mới
    }

    public void addUserManga(String username, int mangaId) {
        String sql = "INSERT INTO user_owner (user_id, manga_id) VALUES ((SELECT uid FROM account WHERE username = ? ), ? );";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setInt(2, mangaId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
