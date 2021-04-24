/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaiTapLon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class QLSanPham {
    private List<SanPham> ds = new ArrayList<>();
    
    //Phương thức hiển thị sản phẩm
    public void hienThi(Connection connection, PreparedStatement stm) throws SQLException{
            String s = "SELECT S.*,D.tenDM FROM sanpham S INNER JOIN danhmuc D WHERE S.maDM = D.maDM";
            stm = connection.prepareStatement(s);
            ResultSet rs = stm.executeQuery();
            System.out.print("=======================================================DANH SACH SAN PHAM======================================================");
            System.out.print("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.print("\nMa so\tTen san pham\t\tTinh trang \t\tThoi diem ban\t\tGia ban\tTen danh muc\t\tGhi chu");
            System.out.print("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while (rs.next()){
                    int id = rs.getInt("maSP");
                    String name = rs.getString("tenSP");
                    String tt = rs.getString("tinhTrang");
                    String tdb = rs.getString("thoiDiemBan");
                    String ten = rs.getString("tenDM");
                    double gia = rs.getDouble("giaBan");
                    String nuocDa = rs.getString("nuocDa");
                    String anChay = rs.getString("anChay");
                    System.out.printf("\n%d\t%s\t\t%s\t\t%s\t\t%,.0f\t%s\t\t",
                            id,name,tt,tdb,gia,ten);
                    if(nuocDa != null)
                        System.out.printf("%s\t",nuocDa);
                   else
                        System.out.printf("%s\t",anChay);
                }
            System.out.print("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        
    }
    //Phương thức thêm sản phẩm
    public void themSP(Scanner scanner,Connection connection, PreparedStatement preparedStatement, Statement stm) throws SQLException{
        QLDanhMuc ql = new QLDanhMuc();
        ql.hienThi(connection,stm);
        System.out.print("Nhap loai danh muc muon them: ");
        int chon = scanner.nextInt();
        scanner.nextLine();
        switch (chon) {
            case 1:
                SanPham sp1 = new ThucAn();
                sp1.maDM = chon;
                sp1.nhapThongTin(scanner,connection,preparedStatement);
                break;
            case 2: 
                SanPham sp2 = new ThucUong();
                sp2.maDM = chon;
                sp2.nhapThongTin(scanner,connection,preparedStatement);
                break;
            default:
                System.out.print("Gia tri nhap khong hop le!");
        }
    }
    //Phương thức xóa sản phẩm
    public void xoaSP(Scanner scanner,Connection connection, PreparedStatement stm) throws SQLException{
        int kq;
        try{
            hienThi(connection,stm);
            System.out.printf("Nhap ma san pham muon xoa: ");
            int ms = scanner.nextInt();
            scanner.nextLine(); 
            String s = "DELETE FROM sanpham WHERE maSP = ?";
            stm = connection.prepareStatement(s);
            stm.setInt(1,ms);
            kq = stm.executeUpdate();
            if(kq == 1)
                    System.out.print("\nXoa san pham thanh cong!");
            else
                    System.out.print("\nKhong tim thay san pham!");
        }catch(SQLException | InputMismatchException ex){
            System.err.print("Nhap gia tri khong hop le!");
        }
    }
    //Phương thức tìm sản phẩm theo tên
    public void timSPTheoTen(Scanner scanner,Connection connection, PreparedStatement stm) throws SQLException{
        int count  =0;
        try{
                System.out.print("Nhap ten thuc an can tim: ");
                String kw = scanner.nextLine();
                String s = "SELECT * FROM sanpham S, danhmuc D WHERE S.tenSP LIKE CONCAT ('%',?,'%') AND S.maDM = D.maDM";
                stm = connection.prepareStatement(s);
                stm.setString(1,kw);
                System.out.print("=======================================================DANH SACH SAN PHAM======================================================");
                System.out.print("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\nMa so\tTen san pham\t\tTinh trang \t\tThoi diem ban\t\tGia ban\tTen danh muc");
                System.out.print("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    ++count;
                    int id = rs.getInt ("maSP");
                    String name = rs.getString("tenSP");
                    String tinhTrang = rs.getString("tinhTrang");
                    String thoiDiem = rs. getString("thoiDiemBan");
                    double giaBan = rs.getDouble("giaBan");
                    String danhMuc = rs.getString("tenDM");
                    System.out.printf("\n%d\t%s\t\t%s\t\t%s\t\t%,.0f\t%s",
                                    id,name,tinhTrang,thoiDiem,giaBan,danhMuc);
                }
                if(count == 0)
                        System.out.print("\nKhong tim thay san pham!");
        }catch(SQLException | InputMismatchException ex){
            System.err.print("Nhap gia tri khong hop le!Nhap lai!");
        }
    }
    //Phương thức tìm sản phẩm theo giá bán trong khoảng 
    public void timSPTheoGia(Scanner scanner,Connection connection, PreparedStatement stm) throws SQLException{
        int  count  =0;
        boolean check = false;
        do{
            try{
                System.out.print("Nhap khoang gia can tim - Tu gia: ");
                double tuGia = scanner.nextDouble();
                System.out.print("Den gia: ");
                double denGia = scanner.nextDouble();
                String s = "SELECT * FROM sanpham S INNER JOIN danhmuc D WHERE D.maDM = S.maDM AND giaBan BETWEEN ? AND ?";
                stm = connection.prepareStatement(s);
                stm.setDouble(1, tuGia);
                stm.setDouble(2, denGia);
                ResultSet rs = stm.executeQuery();
                System.out.print("=======================================================DANH SACH SAN PHAM======================================================");
                System.out.print("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("\nMa so\tTen san pham\t\tTinh trang \t\tThoi diem ban\t\tGia ban\tTen danh muc");
                System.out.print("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                while(rs.next()){
                    ++count;
                    int id = rs.getInt ("maSP");
                    String name = rs.getString("tenSP");
                    String tinhTrang = rs.getString("tinhTrang");
                    String thoiDiem = rs. getString("thoiDiemBan");
                    double giaBan = rs.getDouble("giaBan");
                    String danhMuc = rs.getString("tenDM");
                    System.out.printf("\n%d\t%s\t\t%s\t\t%s\t\t%,.0f\t%s",
                                    id,name,tinhTrang,thoiDiem,giaBan,danhMuc);
                }
                System.out.print("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                check = true;
                if(count == 0)
                          System.out.print("\nKhong tim thay san pham!");
            }catch(SQLException | InputMismatchException ex){
                 check = false;
                 System.err.print("\nNhap gia tri khong hop le!"+ex);
            }
        }while(check == false);
    }
    //Phương thức sắp xếp tăng
    public void sapXepGiam(Connection connection, PreparedStatement stm) throws SQLException{
        String s = "SELECT * FROM sanpham ORDER BY giaBan desc";
        stm = connection.prepareStatement(s);
        ResultSet rs = stm.executeQuery();
        System.out.print("=======================================================SAP XEP GIAM THEO GIA======================================================");
        System.out.print("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("\nMa so\tTen san pham\t\tTinh trang \t\tThoi diem ban\t\tGia ban");
        System.out.print("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        while (rs.next()) {
                    int id = rs.getInt ("maSP");
                    String name = rs.getString("tenSP");
                    String tinhTrang = rs.getString("tinhTrang");
                    String thoiDiem = rs. getString("thoiDiemBan");
                    double giaBan = rs.getDouble("giaBan");
                     System.out.printf("\n%d\t%s\t\t%s\t\t%s\t\t%,.0f\t",
                                    id,name,tinhTrang,thoiDiem,giaBan);
                   
        }
        System.out.print("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    }
    //Phương thức sắp xếp giảm
    public void sapXepTang(Connection connection, PreparedStatement stm) throws SQLException{
        String s = "SELECT * FROM sanpham ORDER BY giaBan ";
        stm = connection.prepareStatement(s);
        ResultSet rs = stm.executeQuery();
        System.out.print("=======================================================SAP XEP TANG THEO GIA======================================================");
        System.out.print("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.print("\nMa so\tTen san pham\t\tTinh trang \t\tThoi diem ban\t\tGia ban\tTen danh muc");
        System.out.print("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        while (rs.next()) {
                    int id = rs.getInt ("maSP");
                    String name = rs.getString("tenSP");
                    String tinhTrang = rs.getString("tinhTrang");
                    String thoiDiem = rs. getString("thoiDiemBan");
                    double giaBan = rs.getDouble("giaBan");
                     System.out.printf("\n%d\t%s\t\t%s\t\t%s\t\t%,.0f\t",
                                    id,name,tinhTrang,thoiDiem,giaBan);
        }
        System.out.print("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
       
    }
    /**
     * @return the ds
     */
    public List<SanPham> getDs() {
        return ds;
    }

    /**
     * @param ds the ds to set
     */
    public void setDs(List<SanPham> ds) {
        this.ds = ds;
    }
}
