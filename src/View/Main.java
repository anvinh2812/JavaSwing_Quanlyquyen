package View;

import Controller.Quyen_Controller;
import Model.Quyen;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        JTable Bang_quanlyquyen;
        JLabel lb_Maquyen;
        JLabel lb_Tenquyen;
        JLabel lb_Nhomquyen;
        JTextField txt_TenQuyen;
        JTextField txt_Maquyen;
        JComboBox<String> cbb_Nhomquyen;
        JButton btn_Them;
        JButton btn_Capnhat;
        JButton btn_Xoa;
        JButton btn_Huy;
        JButton btn_Dong;
        JButton btn_Timkiem;
        JButton btn_Tailai;

        JFrame jFrame = new JFrame();
        jFrame.setSize(800, 600);
        jFrame.setLocation(400, 100);
        jFrame.setTitle("Quan ly quyen");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);

        // Panel Input
        JPanel panel_input = new JPanel(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        lb_Maquyen = new JLabel("Ma quyen");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipadx = 200;
        panel_input.add(lb_Maquyen, gbc);
        txt_Maquyen = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel_input.add(txt_Maquyen, gbc);

        lb_Tenquyen = new JLabel("Ten quyen");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel_input.add(lb_Tenquyen, gbc);
        txt_TenQuyen = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel_input.add(txt_TenQuyen, gbc);

        lb_Nhomquyen = new JLabel("Nhom quyen");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel_input.add(lb_Nhomquyen, gbc);
        String[] nhomquyens = {"Chọn nhóm", "Quản trị", "Bán hàng", "Kho vận", "Kế toán"};
        cbb_Nhomquyen = new JComboBox<String>(nhomquyens);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel_input.add(cbb_Nhomquyen, gbc);

        // Panel Chucnang
        JPanel panel_Chucnang = new JPanel(new GridBagLayout());
        btn_Them = new JButton("Them");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        panel_Chucnang.add(btn_Them, gbc);
        btn_Capnhat = new JButton("Cap nhat");
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel_Chucnang.add(btn_Capnhat, gbc);
        btn_Xoa = new JButton("Xoa");
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel_Chucnang.add(btn_Xoa, gbc);
        btn_Dong = new JButton("Dong");
        gbc.gridx = 3;
        gbc.gridy = 0;
        panel_Chucnang.add(btn_Dong, gbc);
        btn_Huy = new JButton("Huy");
        gbc.gridx = 4;
        gbc.gridy = 0;
        panel_Chucnang.add(btn_Huy, gbc);
        btn_Timkiem = new JButton("Tim Kiem");
        gbc.gridx = 5;
        gbc.gridy = 0;
        panel_Chucnang.add(btn_Timkiem, gbc);
        btn_Tailai = new JButton("Tai lai");
        gbc.gridx = 6;
        gbc.gridy = 0;
        panel_Chucnang.add(btn_Tailai, gbc);

        //
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        jFrame.add(panel_input, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        jFrame.add(panel_Chucnang, gbc);

        // Table
        Bang_quanlyquyen = new JTable();
        // Ngăn chặn việc chỉnh sửa ô trong bảng
        Bang_quanlyquyen.setDefaultEditor(Object.class, null);
        DefaultTableModel model = Quyen_Controller.LaydulieutuDB();
        Bang_quanlyquyen.setModel(model);
        //
        JScrollPane scrollPane = new JScrollPane(Bang_quanlyquyen);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        jFrame.add(scrollPane, gbc);

        // Sự kiện chọn vào bảng để hiển thị dữ liệu vào input
        Bang_quanlyquyen.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selectedRow = Bang_quanlyquyen.getSelectedRow();
                if (selectedRow != -1) {

                    String maQuyen = Bang_quanlyquyen.getValueAt(selectedRow, 0).toString();
                    String tenQuyen = Bang_quanlyquyen.getValueAt(selectedRow, 1).toString();
                    String nhomQuyen = Bang_quanlyquyen.getValueAt(selectedRow, 2).toString();

                    txt_Maquyen.setText(maQuyen);
                    txt_TenQuyen.setText(tenQuyen);
                    cbb_Nhomquyen.setSelectedItem(nhomQuyen);
                }
            }
        });
        // Sự kiện cho nút Thêm
        btn_Them.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Lấy thông tin từ giao diện
                    Integer maQuyen = null;
                    if (!txt_Maquyen.getText().isEmpty()) {
                        maQuyen = Integer.parseInt(txt_Maquyen.getText());
                    }
                    String tenQuyen = txt_TenQuyen.getText();
                    String nhomQuyen = cbb_Nhomquyen.getSelectedItem().toString();

                    // Kiểm tra xem các trường dữ liệu có rỗng không
                    if (maQuyen == null || tenQuyen.isEmpty() || nhomQuyen.equals("Chọn nhóm")) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
                        return; // Không thực hiện thêm nếu dữ liệu thiếu hoặc chưa chọn nhóm
                    }

                    // Kiểm tra xem Maquyen đã tồn tại chưa
                    if (Quyen_Controller.KiemTraMaquyenTonTai(maQuyen)) {
                        JOptionPane.showMessageDialog(null, "Mã quyền đã tồn tại.");
                        return; // Không thực hiện thêm nếu mã quyền đã tồn tại
                    }

                    // Thêm dữ liệu vào cơ sở dữ liệu
                    Quyen_Controller.ThemDulieuvaoDB(maQuyen, tenQuyen, nhomQuyen);
                    // Cập nhật bảng hiển thị dữ liệu
                    Bang_quanlyquyen.setModel(Quyen_Controller.LaydulieutuDB());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Mã quyền phải là một số nguyên.");
                }
            }
        });

        // Sự kiện cho nút Cập nhật
        btn_Capnhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Lấy thông tin từ giao diện
                    Integer maQuyen = null;
                    if (!txt_Maquyen.getText().isEmpty()) {
                        maQuyen = Integer.parseInt(txt_Maquyen.getText());
                    }
                    String tenQuyen = txt_TenQuyen.getText();
                    String nhomQuyen = cbb_Nhomquyen.getSelectedItem().toString();

                    // Kiểm tra xem các trường dữ liệu có rỗng không
                    if (maQuyen == null || tenQuyen.isEmpty() || nhomQuyen.equals("Chọn nhóm")) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
                        return; // Không thực hiện cập nhật nếu dữ liệu thiếu hoặc chưa chọn nhóm
                    }

                    // Kiểm tra xem Maquyen có tồn tại không
                    if (!Quyen_Controller.KiemTraMaquyenTonTai(maQuyen)) {
                        JOptionPane.showMessageDialog(null, "Mã quyền không tồn tại.");
                        return; // Không thực hiện cập nhật nếu mã quyền không tồn tại
                    }

                    // Cập nhật dữ liệu trong cơ sở dữ liệu
                    Quyen_Controller.CapnhatDulieutrongDB(maQuyen, tenQuyen, nhomQuyen);
                    // Cập nhật bảng hiển thị dữ liệu
                    Bang_quanlyquyen.setModel(Quyen_Controller.LaydulieutuDB());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Mã quyền phải là một số nguyên.");
                }
            }
        });

        // Sự kiện cho nút Xóa
        btn_Xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Lấy thông tin từ giao diện
                    Integer maQuyen = null;
                    if (!txt_Maquyen.getText().isEmpty()) {
                        maQuyen = Integer.parseInt(txt_Maquyen.getText());
                    }

                    // Kiểm tra xem Maquyen có tồn tại không
                    if (maQuyen == null || !Quyen_Controller.KiemTraMaquyenTonTai(maQuyen)) {
                        JOptionPane.showMessageDialog(null, "Mã quyền không tồn tại.");
                        return; // Không thực hiện xóa nếu mã quyền không tồn tại
                    }

                    // Xóa dữ liệu từ cơ sở dữ liệu
                    Quyen_Controller.XoadulieutuDB(maQuyen);
                    // Cập nhật bảng hiển thị dữ liệu
                    Bang_quanlyquyen.setModel(Quyen_Controller.LaydulieutuDB());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Mã quyền phải là một số nguyên.");
                }
            }
        });
        // Sự kiện cho nút Hủy
        btn_Huy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa nội dung của các text field
                txt_Maquyen.setText("");
                txt_TenQuyen.setText("");
                // Đặt lại combobox về "Chọn nhóm"
                cbb_Nhomquyen.setSelectedItem("Chọn nhóm");
            }
        });

        // Sự kiện cho nút Đóng
        btn_Dong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        // Sự kiện cho nút Tìm kiếm
        btn_Timkiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maQuyen = txt_Maquyen.getText();
                String tenQuyen = txt_TenQuyen.getText();
                String nhomQuyen = cbb_Nhomquyen.getSelectedItem().toString();

                if (!maQuyen.isEmpty() || !tenQuyen.isEmpty() || !nhomQuyen.equals("Chọn nhóm")) {
                    Bang_quanlyquyen.setModel(Quyen_Controller.TimKiemDulieu(maQuyen, tenQuyen, nhomQuyen));
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập từ khóa và chọn trường tìm kiếm.");
                }
            }
        });

        btn_Tailai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Bang_quanlyquyen.setModel(Quyen_Controller.LaydulieutuDB());
                txt_Maquyen.setText("");
                txt_TenQuyen.setText("");
                cbb_Nhomquyen.setSelectedIndex(0);
            }
        });

        jFrame.setVisible(true);
    }
}
