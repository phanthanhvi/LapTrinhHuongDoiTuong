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
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author acer
 */
public class QLBan {
    private List<Ban> ql = new ArrayList<>();
    //Phương thức thêm bàn
    public void themBan(Scanner scanner,Connection connection, PreparedStatement preparedStatement) throws SQLException{
       Ban b = new Ban();
       b.nhapThongTin(scanner, connection, preparedStatement);
    }
    //Phương thức xóa bàn
    public void xoaBan(Scanner scanner,Connection connection, PreparedStatement preparedStatement) throws SQLException{
        int kq;
        try{
            System.out.print("\nNhap ma ban can xoa: ");
            String s = scanner.nextLine();
            String s1 = "DELETE FROM ban WHERE maBan LIKE CONCAT('%',?,'%')";
            preparedStatement = connection.prepareStatement(s1);
            preparedStatement.setString(1,s);
            kq = preparedStatement.executeUpdate();
            if(kq == 1)
                System.out.print("\nXoa ban thanh cong!");
            else
                System.out.print("\nKhong tim thay ma ban!");
        }catch(SQLException | InputMismatchException ex){
             System.err.print("Nhap gia tri khong hop le!");
        }
    }
    //Phương thức tìm bàn theo sức chứa
    public void timBan(Scanner scanner,Connection connection, PreparedStatement preparedStatement) throws SQLException{
        int count = 0;
        try{
            System.out.print("\nNhap suc chua can tim: ");
            int tim = scanner.nextInt();
            String s = "SELECT * FROM ban WHERE sucChua >= ?";
            preparedStatement = connection.prepareStatement(s);
            preparedStatement.setInt(1, tim);
            System.out.print("\n========DANH SACH  ========");
            System.out.printf("\nMa ban\tSuc chua\tTinh trang");
            System.out.print("\n----------------------------------------------------------");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ++count;
                String id = rs.getString("maBan");
                int sucChua = rs.getInt("sucChua");
                String tinhTrang = rs.getString("tinhTrang");
                System.out.printf("\n%s\t  %d\t  %s ",id,sucChua,tinhTrang);
            }
            if(count == 0)
                System.out.print("\nKhong tim thay suc chua phu hop!"); 
            System.out.print("\n==============================");
        }catch(SQLException | InputMismatchException ex){
             System.err.print("Nhap gia tri khong hop le!");
        }  
    }
    //Phương thức cập nhật bàn
    public void capNhat(Scanner scanner,Connection connection, PreparedStatement preparedStatement) throws SQLException{
        int kq;
        try{
            Ban b = new Ban();
            System.out.print("\nNhap ma ban can sua: ");
            String s = scanner.nextLine();
            String s1 = "UPDATE ban SET sucChua = ?, tinhTrang = ? WHERE maBan LIKE CONCAT ('%',?,'%') ";
            preparedStatement = connection.prepareStatement(s1);
            System.out.print("\nNhap suc chua: ");
            b.setSucChua(scanner.nextInt());
            scanner.nextLine();
            System.out.print("\nNhap tinh trang: ");
            b.setTinhTrang(scanner.nextLine());
            preparedStatement.setInt(1,b.getSucChua());
            preparedStatement.setString(2, b.getTinhTrang());
            preparedStatement.setString(3, s);
            kq = preparedStatement.executeUpdate();
            if(kq == 1)
                System.out.print("\nCap nhat thanh cong");
            else
                System.out.print("Cap nhat khong thanh cong!");
        }catch(SQLException | InputMismatchException ex){
             System.err.print("Nhap gia tri khong hop le!");
        } 
    }
    //Hiện danh sách bàn trống
    public void banTrong(Connection connection, PreparedStatement preparedStatement) throws SQLException{
        int count = 0;
        String s = " SELECT * FROM ban WHERE tinhTrang LIKE  'Trong' ";
        preparedStatement = connection.prepareStatement(s);
        System.out.print("\n========DANH SACH BAN TRONG ========");
        System.out.printf("\nMa ban\tSuc chua\tTinh trang");
        System.out.print("\n---------------------------------------------------------");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            ++count;
            String id = rs.getString("maBan");
            int sucChua = rs.getInt("sucChua");
            String tinhTrang = rs.getString("tinhTrang");
            System.out.printf("\n%s\t  %d\t  %s ",id,sucChua,tinhTrang);
        }
        System.out.print("\n==============================");
        if(count == 0)
            System.out.print("\nKhong con ban trong");
        
    }
    //Hiện danh sách bàn
    public void hienThi(Connection connection, PreparedStatement preparedStatement) throws SQLException{
        String s = " SELECT * FROM ban ";
        preparedStatement = connection.prepareStatement(s);
        ResultSet rs = preparedStatement.executeQuery();
        System.out.print("\n========DANH SACH BAN========");
        System.out.printf("\nMa ban\tSuc chua\tTinh trang");
        System.out.print("\n---------------------------------------------------------");
        while(rs.next()){  
            String id = rs.getString("maBan");
            int sucChua = rs.getInt("sucChua");
            String tinhTrang = rs.getString("tinhTrang");
            System.out.printf("\n%s\t  %d\t  %s ",id,sucChua,tinhTrang);
        }
        System.out.print("\n==============================");
    }
    /**
     * @return the ql
     */
    public List<Ban> getQl() {
        return ql;
    }
    
    /**
     * @param ql the ql to set
     */
    public void setQl(List<Ban> ql) {
        this.ql = ql;
    }


}
