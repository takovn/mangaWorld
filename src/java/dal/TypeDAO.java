package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Type;

public class TypeDAO extends DBContext {

    public List<Type> getAll() {
        List<Type> listType = new ArrayList<>();
        String sql = "Select * from type";
        //thuc thi cau truy van
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Type st = new Type(id, name);
                listType.add(st);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listType;
    }
    public String getTypeNameById(int typeId) {
        String typeName = null;
        String sql = "SELECT name FROM type WHERE id = ?";
        
        try (PreparedStatement pre = connection.prepareStatement(sql)) {
            pre.setInt(1, typeId);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                typeName = rs.getString("name");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return typeName;
    }
}
