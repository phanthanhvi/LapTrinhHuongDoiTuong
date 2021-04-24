/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaiTapLon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class ThucAn extends SanPham{
    private String anChay;
    
    public ThucAn(String tenSP, double giaBan, String tinhTrang,String thoiDiemBan, int maDM, String anChay ){
        super(tenSP,giaBan,tinhTrang,thoiDiemBan,maDM);
        this.anChay = anChay;
    }

    public ThucAn(){
    
    }
     @Override
     public void nhapThongTin(Scanner scanner,Connection connection, PreparedStatement preparedStatement) throws SQLException{
            super.nhapThongTin(scanner,connection, preparedStatement);
            scanner.nextLine();
            try{
                System.out.print("Tinh trang an chay: ");
                this.anChay = scanner.nextLine();
                String query = "INSERT INTO sanpham(tenSP, giaBan, tinhTrang, thoiDiemBan, maDM, anChay) VALUES (?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,this.tenSP);
                preparedStatement.setDouble(2,this.giaBan);
                preparedStatement.setString(3,this.tinhTrang);
                preparedStatement.setString(4,this.thoiDiemBan);
                preparedStatement.setInt(5,this.maDM);
                preparedStatement.setString(6,this.anChay);
                 preparedStatement.executeUpdate();
                System.out.print("Them san pham thanh cong!");
            }catch(SQLException|InputMismatchException ex){
                System.err.print("Nhap gia tri khong hop le!");
                System.err.print("Them san pham khong thanh cong");
            }       
    }
//    @Override
//    public String toString() {
//        super.toString();
//        return String.format("\nTinh trang an chay: ",this.anChay);//To change body of generated methods, choose Tools | Templates.
//    }
    
    /**
     * @return the anChay
     */
    public String getAnChay() {
        return anChay;
    }

    /**
     * @param anChay the anChay to set
     */
    public void setAnChay(String anChay) {
        this.anChay = anChay;
    }

    
    
}
