package Controller;

import View.ConnectSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Quyen_Controller {
    public static DefaultTableModel LaydulieutuDB() {
        DefaultTableModel model = new DefaultTableModel();
        try {
            Connection conn = ConnectSQL.connectDB();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Quyen";
            ResultSet rs = stmt.executeQuery(query);

            model.addColumn("Mã Quyền");
            model.addColumn("Tên Quyền");
            model.addColumn("Nhóm Quyền");
            while (rs.next()) {
                int id = rs.getInt("Maquyen");
                String name = rs.getString("Tenquyen");
                String groupName = rs.getString("Nhomquyen");
                model.addRow(new Object[]{id, name, groupName});
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }
    public static void ThemDulieuvaoDB(Integer Maquyen, String Tenquyen, String Nhomquyen) {
        try {
            Connection conn = ConnectSQL.connectDB();
            String query = "INSERT INTO Quyen (Maquyen, Tenquyen, Nhomquyen) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Maquyen); // Sửa chỉ số từ 0 thành 1
            pstmt.setString(2, Tenquyen);
            pstmt.setString(3, Nhomquyen);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void CapnhatDulieutrongDB(int Maquyen, String Tenquyen, String Nhomquyen) {
        try {
            Connection conn = ConnectSQL.connectDB();
            String query = "UPDATE Quyen SET Tenquyen = ?, Nhomquyen = ? WHERE Maquyen = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Tenquyen);
            pstmt.setString(2, Nhomquyen);
            pstmt.setInt(3, Maquyen);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void XoadulieutuDB(int Maquyen) {
        try {
            Connection conn = ConnectSQL.connectDB();
            String query = "DELETE FROM Quyen WHERE Maquyen = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Maquyen);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean KiemTraMaquyenTonTai(int Maquyen) {
        try {
            Connection conn = ConnectSQL.connectDB();
            String query = "SELECT COUNT(*) AS count FROM Quyen WHERE Maquyen = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, Maquyen);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt("count");
            rs.close();
            pstmt.close();
            conn.close();
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static DefaultTableModel TimKiemDulieu(String maQuyen, String tenQuyen, String nhomQuyen) {
        DefaultTableModel model = new DefaultTableModel();
        try {
            Connection conn = ConnectSQL.connectDB();
            String query = "SELECT * FROM Quyen WHERE Maquyen LIKE ? AND Tenquyen LIKE ?";
            // Nếu nhóm quyền không phải là "Chọn nhóm", thêm điều kiện vào truy vấn SQL
            if (!nhomQuyen.equals("Chọn nhóm")) {
                query += " AND Nhomquyen LIKE ?";
            }
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + maQuyen + "%");
            pstmt.setString(2, "%" + tenQuyen + "%");
            // Nếu nhóm quyền không phải là "Chọn nhóm", gán giá trị cho tham số thứ ba
            if (!nhomQuyen.equals("Chọn nhóm")) {
                pstmt.setString(3, "%" + nhomQuyen + "%");
            }
            ResultSet rs = pstmt.executeQuery();

            model.addColumn("Mã Quyền");
            model.addColumn("Tên Quyền");
            model.addColumn("Nhóm Quyền");

            while (rs.next()) {
                int id = rs.getInt("Maquyen");
                String name = rs.getString("Tenquyen");
                String groupName = rs.getString("Nhomquyen");
                model.addRow(new Object[]{id, name, groupName});
            }
            pstmt.close();
            rs.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        }
        return model;
    }



}
