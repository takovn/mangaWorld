package dal;

import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountDAO extends DBContext {

    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM account";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setUid(rs.getInt("uid"));
                account.setUser(rs.getString("username"));
                account.setPass(rs.getString("password"));
                account.setEmail(rs.getString("email"));
                account.setAccountType(rs.getString("accountType"));

                // Lấy danh sách manga follow
                String sqlFollow = "SELECT manga_id FROM user_follow WHERE user_id = ?";
                try (PreparedStatement followStm = connection.prepareStatement(sqlFollow)) {
                    followStm.setInt(1, account.getUid());
                    ResultSet followRs = followStm.executeQuery();
                    List<Integer> followList = new ArrayList<>();
                    while (followRs.next()) {
                        followList.add(followRs.getInt("manga_id"));
                    }
                    account.setFollow(followList);
                }

                // Lấy danh sách manga owner
                String sqlOwner = "SELECT manga_id FROM user_owner WHERE user_id = ?";
                try (PreparedStatement ownerStm = connection.prepareStatement(sqlOwner)) {
                    ownerStm.setInt(1, account.getUid());
                    ResultSet ownerRs = ownerStm.executeQuery();
                    List<Integer> ownerList = new ArrayList<>();
                    while (ownerRs.next()) {
                        ownerList.add(ownerRs.getInt("manga_id"));
                    }
                    account.setOwner(ownerList);
                }
                list.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

    public Account login(String user, String pass) {
        try {
            String sql = "SELECT * FROM account where username = ? and password = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUid(rs.getInt(1));
                a.setUser(rs.getString(2));
                a.setPass(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setAccountType(rs.getString(5));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkAccountExist(String username) {
        String sql = "SELECT COUNT(*) FROM account WHERE username = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu có tài khoản tồn tại
            }
        } catch (SQLException e) {
            System.out.println(e); // In lỗi nếu có
        }
        return false; // Trả về false nếu không có tài khoản tồn tại
    }

    public boolean checkEmailExist(String email) {
        String sql = "SELECT COUNT(*) FROM account WHERE email = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Trả về true nếu có tài khoản tồn tại
            }
        } catch (SQLException e) {
            System.out.println(e); // In lỗi nếu có
        }
        return false; // Trả về false nếu không có tài khoản tồn tại
    }

    public void addAccount(Account account) {
        String sql = "INSERT INTO account (username, password, email) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, account.getUser());
            stmt.setString(2, account.getPass());
            stmt.setString(3, account.getEmail());
            stmt.executeUpdate(); // Thực thi câu lệnh INSERT
        } catch (SQLException e) {
            System.out.println(e); // In ra lỗi nếu có
        }
    }

    public boolean updateAccount(String username, String password) {
        try {
            String sql = "UPDATE account SET password = ? WHERE username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, password);
            stm.setString(2, username);

            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu có ít nhất 1 bản ghi được cập nhật
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    public void updateAccountType(String username, String newType) {
        String query = "UPDATE account SET accountType = ? WHERE username = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, newType);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccountByUsername(String username) {
        Account account = null;
        String query = "SELECT * FROM account WHERE username = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                account = new Account();
                account.setUid(rs.getInt("uid")); // Lấy uid nếu cần
                account.setUser(rs.getString("username"));
                account.setPass(rs.getString("password")); // Nếu cần mật khẩu (không nên trả về cho người dùng)
                account.setEmail(rs.getString("email")); // Nếu cần email
                account.setAccountType(rs.getString("accountType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    public boolean updateAccountAdmin(int uid, String password, String email) {
        String sql = "UPDATE account SET password = ?, email = ? WHERE uid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, password);
            ps.setString(2, email);
            ps.setInt(3, uid);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAccount(int uid) {
        String deleteSql = "CREATE TABLE MangaToDelete (manga_id INT); "
                + "INSERT INTO MangaToDelete (manga_id) SELECT manga_id FROM user_owner WHERE user_id = ?;"
                + "DELETE FROM chap WHERE manga_id IN (SELECT manga_id FROM MangaToDelete); "
                + "DELETE FROM user_follow  WHERE manga_id IN (SELECT manga_id FROM MangaToDelete); "
                + "DELETE FROM user_owner  WHERE user_id = ?; "
                + "DELETE FROM user_follow  WHERE user_id = ?; "
                + "DELETE FROM manga_type WHERE manga_id IN (SELECT manga_id FROM MangaToDelete); "
                + "DELETE FROM manga WHERE id IN (SELECT manga_id FROM MangaToDelete); "
                + "DELETE FROM account WHERE uid = ?; "
                + "DROP TABLE MangaToDelete";
        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSql)) {
            deleteStmt.setInt(1, uid);
            deleteStmt.setInt(2, uid);
            deleteStmt.setInt(3, uid);
            deleteStmt.setInt(4, uid);
            deleteStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }
}
