/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaiTapLon;



import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class QLNhanVien {
    private List<NhanVien> ds = new ArrayList<>();


    //Phương thức thêm nhân viên
    public void themNV(Scanner scanner,Connection connection, PreparedStatement stm,SimpleDateFormat f) throws SQLException, Exception{
        NhanVien nv = new NhanVien();
        nv.nhapThongTin(scanner, connection, stm,f);
    }
    //Phương thức xóa nhân viên
    public void xoaNV(Scanner scanner,Connection connection, PreparedStatement stm,SimpleDateFormat f) throws SQLException{ 
        int kq = 0;
        boolean check = false;

        hienThi(connection,stm,f);
        do{
            try{
                
                System.out.print("\nNhap ma so nhan vien can xoa: ");
                int ms = scanner.nextInt();
                String s = "DELETE FROM nhanvien WHERE maNV = ?";
                stm = connection.prepareStatement(s);
                stm.setInt(1,ms);
                kq = stm.executeUpdate();
                if(kq == 1)
                    System.out.print("Xoa thanh cong nhan vien!");
                else
                    System.out.print("Khong tim thay nhan vien!");
                check = true;
            }catch(SQLException |InputMismatchException ex){
                check = false;
                System.out.print("Nhap ma so khong hop le!Nhap lai!");
                scanner.nextLine();
            }
        }while(check == false);
    }
    //Phương thức cập nhật nhân viên
    public void capNhatNV(Scanner scanner,Connection connection,PreparedStatement preparedStatement,SimpleDateFormat f) throws SQLException, ParseException{
        String s;
        boolean check = false;
        int tt = 0;
        int kq = 0;
        hienThi(connection,preparedStatement,f);
        NhanVien nv = new NhanVien();
        do{
            try{
                System.out.print("\nNhap ma nhan vien can cap nhat: ");
                int ms = scanner.nextInt();
                do{
                System.out.print("\nChon gia tri can cap nhat:\n1.Ho ten \n2.Gioi tinh \n3.Que quan \n4.Ngay sinh \n5.Ngay vao lam \n6.Ma bo phan");
                System.out.print("\nLua chon cua ban: ");
                int chon = scanner.nextInt();
                scanner.nextLine();
                switch(chon){
                    case 1:
                        System.out.print("\nBan chon cap nhat ten nhan vien");
                        s = "UPDATE nhanvien SET hoTenNV = ? WHERE maNV = ?";
                        System.out.print("\nNhap ten nhan vien: ");
                        nv.setHoTen(scanner.nextLine());
                        preparedStatement = connection.prepareStatement(s);
                        preparedStatement.setString(1,nv.getHoTen());
                        preparedStatement.setInt(2,ms);
                        kq = preparedStatement.executeUpdate();
                        check = true;
                        break;
                    case 2:
                        System.out.print("\nBan chon cap nhat gioi tinh");
                        s = "UPDATE nhanvien SET gioiTinh = ? WHERE maNV = ?";
                        System.out.print("\nNhap gioi tinh: ");
                        nv.setGioiTinh(scanner.nextLine());
                        preparedStatement = connection.prepareStatement(s);
                        preparedStatement.setString(1,nv.getGioiTinh());
                        preparedStatement.setInt(2,ms);
                        kq = preparedStatement.executeUpdate();
                        check = true;
                        break;
                    case 3:
                        System.out.print("\nBan chon cap nhat que quan");
                        s = "UPDATE nhanvien SET queQuan = ? WHERE maNV = ?";
                        System.out.print("\nNhap que quan: ");
                        nv.setQueQuan(scanner.nextLine());
                        preparedStatement = connection.prepareStatement(s);
                        preparedStatement.setString(1,nv.getQueQuan());
                        preparedStatement.setInt(2,ms);
                        kq = preparedStatement.executeUpdate();
                        check = true;
                        break;
                    case 4:
                        System.out.print("\nBan chon cap nhat ngay sinh");
                        s = "UPDATE nhanvien SET ngaySinh = ? WHERE maNV = ?";
                        System.out.print("\nNhap ngay sinh: ");
                        String ns = scanner.nextLine();
                        
                        f.setLenient(false);
                        nv.setNgaySinh(f.parse(ns));
                        java.sql.Date sql1 = new java.sql.Date(nv.getNgaySinh().getTime());
                         preparedStatement = connection.prepareStatement(s);
                        preparedStatement.setDate(1,sql1);
                        preparedStatement.setInt(2,ms);
                        kq = preparedStatement.executeUpdate();
                        check = true;
                        break;
                    case 5:
                        System.out.print("\nBan chon cap nhat ngay vao lam");
                        s = "UPDATE nhanvien SET ngayVaoLam = ? WHERE maNV = ?";
                        System.out.print("\nNhap ngay vao lam: ");
                        String nl = scanner.nextLine();
                        f.setLenient(false);
                        nv.setNgayVaoLam(f.parse(nl));
                        java.sql.Date sql2 = new java.sql.Date(nv.getNgayVaoLam().getTime());
                         preparedStatement = connection.prepareStatement(s);
                        preparedStatement.setDate(1,sql2);
                        preparedStatement.setInt(2,ms);
                        kq = preparedStatement.executeUpdate();
                        check = true;
                        break;
                    case 6:
                        System.out.print("\nBan chon cap nhat ma bo phan");
                        QLBoPhan qlbp = new QLBoPhan();
                        qlbp.hienThi(connection, preparedStatement);
                        s = "UPDATE nhanvien SET maBP = ? WHERE maNV = ?";
                        System.out.print("\nNhap ma bo phan: ");
                        nv.setMaBP(scanner.nextInt());
                         preparedStatement = connection.prepareStatement(s);
                        preparedStatement.setInt(1,nv.getMaBP());
                        preparedStatement.setInt(2,ms);
                        kq = preparedStatement.executeUpdate();
                        check = true;
                        break;
                }
                  if( kq == 1)
                        System.out.println("\nCap nhat thanh cong!");
                  else
                        System.out.println("\nCap nhat khong thanh cong!");
                  System.out.print("Ban co muon tiep tuc: \n1.Co \n2.Khong \n");
                  tt = scanner.nextInt();
                }while(tt == 1); 
            }catch(SQLException | InputMismatchException ex){
                    check = false;
                    System.err.print("Nhap gia tri khong hop le!");
                    scanner.nextLine();
            }catch(ParseException pe){
                    check = false;
                    System.err.print("\nNhap ngay khong hop le!");
                    
            }catch(NumberFormatException nfe){
                    check = false;
                    System.err.print("\nNhap so khong hop le!");
            }
        }while ( check == false);
        System.out.print("Chao tam biet!!!");
    }
    //Phương thức tìm nhân viên bằng mã nhân viên
    public void timNVMS(Scanner scanner,Connection connection,PreparedStatement preparedStatement) throws SQLException{
        int count = 0;
        boolean check = false;
        do{
            try{
               System.out.print("\nNhap ma so nhan vien can tim: ");
               int ms = scanner.nextInt();
               String s ="SELECT * FROM nhanvien N INNER JOIN bophan B WHERE N.maBP = B.maBP AND N.maNV = ?";
               preparedStatement = connection.prepareStatement(s);
               preparedStatement.setInt(1, ms);
               ResultSet rs = preparedStatement.executeQuery();
               System.out.print("\nMa so\tHo ten\t\tGioi tinh\tQue quan\t\tNgay sinh\t\tBo phan");
               System.out.print("\n===============================================================================================");
               while (rs.next()) {
                     ++count;
                  int id = rs.getInt("maNV");
                  String name = rs.getString("hoTenNV");
                  String gt = rs.getString("gioiTinh");
                  String qq = rs.getString("queQuan");
                  Date ns = rs.getDate("ngaySinh");
                  String namebp = rs.getString("tenBP");
                  System.out.printf("\n%d \t%s \t%s\t%s\t\t%s\t\t%s",id,name,gt,qq,ns,namebp);
                  check = true;
               }
            if(count == 0)
                  System.out.print("\nKhong tim thay nhan vien!");
            }catch(InputMismatchException nfe){
                  System.err.print("\nNhap so khong hop le!");
                  check = false;
            }
        }while(check == false);
}
    //Phương thức tìm nhân viên bằng tên hoặc quê quán
    public void timNV(Scanner scanner,Connection connection,PreparedStatement stm,SimpleDateFormat f) throws SQLException{
        int count = 0;
        try{
                
                System.out.print("\nNhap thong tin nhan vien can tim: ");
                String kw = scanner.nextLine();
                String s = "SELECT  N.*, B.tenBP FROM nhanvien N INNER JOIN bophan B  "
                    + "WHERE N.maBP = B.maBP AND N.queQuan LIKE CONCAT ('%',?,'%') OR  N.hoTenNV LIKE CONCAT ('%',?,'%') OR N.gioiTinh LIKE CONCAT ('%',?,'%') AND N.maBP = B.maBP  ";
                stm = connection.prepareStatement(s);
                stm.setString(1,kw);
                stm.setString(2,kw);
                stm.setString(3,kw);
                ResultSet rs = stm.executeQuery();
                System.out.print("\n================================ DANH SACH NHAN VIEN =================================");
                System.out.print("\nMa so  \tHo ten \t\tGioi tinh \tQue quan \tNgay sinh  \tBo phan \t\t");
                System.out.print("\n-------------------------------------------------------------------------------------------------------------------------------------------------");
                while (rs.next()){
                    ++count;
                    int id = rs.getInt("maNV");
                    String name = rs.getString("hoTenNV");
                    String gt = rs.getString("gioiTinh");
                    String qq = rs.getString("queQuan");
                    Date ns = rs.getDate("ngaySinh");
                    String namebp = rs.getString("tenBP");
                    System.out.printf("\n%d\t%s\t%s\t%s\t%s\t%s",id,name,gt,qq,f.format(ns),namebp);
                }
                System.out.print("\n========================================================================================");
            }catch(SQLException ex){
                System.err.print("\nNhap khong hop le!");
            }
            if(count == 0)
                System.out.print("\nKhong tim thay nhan vien");
        
    }
    //Phương thức tìm nhân viên cùng tháng sinh để tổ chức sinh nhật(Dùng Stored Procedure)
    public void sinhNhat(Connection connection,SimpleDateFormat f) throws SQLException{
            Date date = new Date();
            int month = date.getMonth();
            CallableStatement stm = connection.prepareCall("{CALL sinhnhat(?)}");
            stm.setInt(1, month);
            ResultSet rs = stm.executeQuery();
            System.out.print("\n================================ DANH SACH NHAN VIEN =================================");
            System.out.print("\nMa so  \tHo ten \t\tGioi tinh \tQue quan \tNgay sinh  \tBo phan \t\t");
            System.out.print("\n-------------------------------------------------------------------------------------------------------------------------------------------------");
            while (rs.next()){
                int id = rs.getInt("maNV");
                String name = rs.getString("hoTenNV");
                String gt = rs.getString("gioiTinh");
                String qq = rs.getString("queQuan");
                Date ns = rs.getDate("ngaySinh");
                String namebp = rs.getString("tenBP");
                System.out.printf("\n%d\t%s\t%s\t%s\t%s\t%s",id,name,gt,qq,f.format(ns),namebp);
        }
        System.out.print("\n========================================================================================");
    }
    //Phương thức in ra danh sách nhân viên
    public void hienThi(Connection connection,PreparedStatement preparedStatement,SimpleDateFormat f) throws SQLException{
            String s ="SELECT N.*,B.tenBP FROM nhanvien N INNER JOIN bophan B WHERE N.maBP = B.maBP";
            preparedStatement = connection.prepareStatement(s);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.print("\n================================ DANH SACH NHAN VIEN =================================");
            System.out.print("\nMa so  \tHo ten \t\tGioi tinh \tQue quan \tNgay sinh \tNgay vao lam \tBo phan \t\t");
            System.out.print("\n-------------------------------------------------------------------------------------------------------------------------------------------------");
            while (rs.next()){
                int id = rs.getInt("maNV");
                String name = rs.getString("hoTenNV");
                String gt = rs.getString("gioiTinh");
                String qq = rs.getString("queQuan");
                Date ns = rs.getDate("ngaySinh");
                Date nvl = rs.getDate("ngayVaoLam");
                String namebp = rs.getString("tenBP");
                System.out.printf("\n%d\t%s\t%s\t%s\t%s\t%s\t%s",id,name,gt,qq,f.format(ns),f.format(nvl),namebp);
            }
            System.out.print("\n========================================================================================");
    }
    /**
     * @return the ds
     */
    public List<NhanVien> getDs() {
        return ds;
    }

    /**
     * @param ds the ds to set
     */
    public void setDs(ArrayList<NhanVien> ds) {
        this.ds = ds;
    }
  
}
