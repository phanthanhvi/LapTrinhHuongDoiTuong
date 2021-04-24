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
public class ThucUong extends SanPham{
    private String nuocDa;
    public ThucUong(String tenSP, double giaBan, String tinhTrang,String thoiDiemBan, int maDM,String nuocDa){
        super(tenSP,giaBan,tinhTrang,thoiDiemBan,maDM);
        this.nuocDa = nuocDa;
    }
    public ThucUong(){
    }
    @Override
     public void nhapThongTin(Scanner scanner,Connection connection, PreparedStatement preparedStatement) throws SQLException{
            super.nhapThongTin(scanner,connection, preparedStatement);
            scanner.nextLine();
            try{
                System.out.print("Tinh trang nuoc da: ");
                this.nuocDa = scanner.nextLine();
                String query = "INSERT INTO sanpham(tenSP, giaBan, tinhTrang, thoiDiemBan, maDM, anChay) VALUES (?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,this.tenSP);
                preparedStatement.setDouble(2,this.giaBan);
                preparedStatement.setString(3,this.tinhTrang);
                preparedStatement.setString(4,this.thoiDiemBan);
                preparedStatement.setInt(5,this.maDM);
                preparedStatement.setString(6,this.nuocDa);
                 preparedStatement.executeUpdate();
                System.out.print("Them san pham thanh cong!");
            }catch(SQLException|InputMismatchException ex){
                System.err.print("Nhap gia tri khong hop le!");
                System.err.print("Them san pham khong thanh cong");
            }       
    }

//    @Override
//    public String toString(){
//        super.toString();
//        return String.format("\nTinh trang da: %s",this.nuocDa);
//    }
    /**
     * @return the nuocDa
     */
    public String getNuocDa() {
        return nuocDa;
    }

    /**
     * @param nuocDa the nuocDa to set
     */
    public void setNuocDa(String nuocDa) {
        this.nuocDa = nuocDa;
    }
    
}
