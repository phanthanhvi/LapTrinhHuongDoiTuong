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
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class Ban {
    private static int dem = 0;
    private String maBan;
    private int sucChua;
    private String tinhTrang;
    
    //Khối khởi động tạo mã bàn tự động
    {
        this.maBan = String.format("B%03d", ++dem);
    }
    
//    public Ban(){    
//    }
//    public Ban(int sucChua, String tinhTrang){
//        this.sucChua = sucChua;
//        this.tinhTrang = tinhTrang;
//    }

   public void nhapThongTin(Scanner scanner,Connection connection, PreparedStatement stm) throws SQLException{
        //Lấy số thứ tự mã bàn tự động tăng ở mySQL gán lên cho mã bàn. Rồi từ mã bàn gán ngược lại cột maBan ở mySQL
        //Vì maBan kiểu char không thể tự động tăng
        try{
            String s1 = " SELECT * FROM ban ";
            stm = connection.prepareStatement(s1);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                int id = rs.getInt("STT");
                this.maBan =  String.format("B%03d", ++id);
            }
            String s = "INSERT INTO ban(maBan, sucChua, tinhTrang) VALUES (?,?,?)";
            stm = connection.prepareStatement(s);
            System.out.print("\nNhap suc chua: ");
            this.sucChua = scanner.nextInt();
            scanner.nextLine();
            System.out.print("\nNhap tinh trang: ");
            this.tinhTrang = scanner.nextLine();
            stm.setString(1,this.maBan);
            stm.setInt(2, this.sucChua);
            stm.setString(3, this.tinhTrang);
            stm.executeUpdate();
        }catch(SQLException | InputMismatchException ex){
             System.err.print("Nhap gia tri khong hop le!");
        }
   }
    
//    {
//    this.maBan = "" + (++dem);
//        int kt = 3;
//        int test = kt - this.maBan.length();
//        for(int i = 0; i < test; i++){
//            this.maBan = "0" + this.maBan;
//        }
//        this.maBan = "B" + this.maBan;
//    }
//    public void hienThi(){        
//        System.out.printf("\nMa ban: %s\nSuc chua: %d\nTinh trang: %s",this.maBan,this.sucChua,this.tinhTrang);
//    }
//
//    @Override
//    public String toString() {
//        return String.format("\nMa ban: %s\nSuc chua: %d\nTinh trang: %s",this.maBan,this.sucChua,this.tinhTrang); //To change body of generated methods, choose Tools | Templates.
//    }
    


    /**
     * @return the maBan
     */
    public String getMaBan() {
        return maBan;
    }

    /**
     * @param maBan the maBan to set
     */
    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    /**
     * @return the sucChua
     */
    public int getSucChua() {
        return sucChua;
    }

    /**
     * @param sucChua the sucChua to set
     */
    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    /**
     * @return the tinhTrang
     */
    public String getTinhTrang() {
        return tinhTrang;
    }

    /**
     * @param tinhTrang the tinhTrang to set
     */
    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

   
}
