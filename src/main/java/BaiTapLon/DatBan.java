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
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class DatBan {
    private QLBan dsBan = new QLBan();
    private QLSanPham dsSP = new QLSanPham();
    private String maBan;
    private int soLuong;
    private Date ngayDat;
    
    
    public void datBan(Scanner scanner,Connection connection,PreparedStatement stm ,SimpleDateFormat f ) throws SQLException, ParseException{
        int kq,temp,tt,msp,maHD =0;
        //Xuất ra danh sách bàn trống 
        this.dsBan.banTrong(connection, stm);
        //Xử lý giao tác, chỉ khi phương thức chạy hoàn toàn đúng không sinh ra lỗi mới có thể thêm giá trị vào bảng
            try{
            connection.setAutoCommit(false);
                scanner.nextLine();
                System.out.print("\nNhap ma ban can dat: ");
                this.maBan = scanner.nextLine();
                System.out.print("Nhap ngay dat: ");
                String ns = scanner.nextLine();
                this.ngayDat = f.parse(ns);
                java.sql.Date sql = new java.sql.Date(this.ngayDat.getTime());
                String s = "INSERT INTO hoadon(maBan,ngayDat) VALUES(?,?)";
                stm = connection.prepareStatement(s);
                stm.setString(1, this.getMaBan());
                stm.setDate(2, sql);
                stm.executeUpdate();
                String s1 = "SELECT * FROM hoadon";
                stm = connection.prepareStatement(s1);
                ResultSet rs1 = stm.executeQuery();
                while(rs1.next()){
                     maHD = rs1.getInt("maHD");
                }
                
                this.dsSP.hienThi(connection, stm);
                do{
                    do{
                        try (CallableStatement call = connection.prepareCall("{CALL countsp(?)}")) {
                            call.registerOutParameter(1, Types.INTEGER);
                            call.execute();
                            temp = call.getInt(1);
                        }
                    System.out.print("\nNhap ma san pham can dat: ");//kiểm tra người dùng nhập
                    msp = scanner.nextInt();
                    if(msp > temp || msp < 0)
                        System.out.print("\nMa san pham khong ton tai!");
                    }while(msp > temp || msp < 0);
                    System.out.print("Nhap so luong: ");
                    this.soLuong = scanner.nextInt();
                    String query ="INSERT INTO chitiethd(maHD,maSP,soLuong) VALUES(?,?,?)";
                    stm = connection.prepareStatement(query);
                    stm.setInt(1, maHD);
                    stm.setInt(2, msp);
                    stm.setInt(3, this.soLuong);
                    stm.executeUpdate();
                    System.out.print("\nBan co muon tiep tuc(1/0): ");
                    tt = scanner.nextInt();
                }while(tt==1);//Vòng lặp do - while này cho phép người dùng có thể đặt nhiều món
                //Sau khi hoàn tất công việc đặt bàn, thì bàn được đặt sẽ chuyển trừ trạng thái "Trống" sang "Đã sử dụng";
                String query2 = "UPDATE ban SET tinhTrang = 'Da su dung' WHERE maBan LIKE CONCAT ('%',?,'%') ";
                stm = connection.prepareStatement(query2);
                stm.setString(1,this.maBan);
                kq = stm.executeUpdate();
            connection.commit();
                if(kq > 0)
                    System.out.print("\nDat ban thanh cong!");
                else
                    System.out.print("\nDat ban khong thanh cong!");
            }catch(SQLException | InputMismatchException ex){
                System.err.print("Nhap gia tri khong hop le!Nhap lai!");
            }catch(ParseException pe){
                System.err.print("Nhap ngay khong hop le!");
            
        }
    }
    public void dsDatBan(Connection connection,PreparedStatement preparedStatement,SimpleDateFormat f) throws SQLException{
        String s = "SELECT * FROM hoadon";
        preparedStatement = connection.prepareStatement(s);
        ResultSet rs = preparedStatement.executeQuery();
        System.out.print("\n==================== DANH SACH ====================");
        System.out.print("\nMa hoa don \tMa ban\tNgay dat");
        while(rs.next()){
            int id = rs.getInt("maHD");
            String mb = rs.getString("maBan");
            Date ngay = rs.getDate("ngayDat");
            System.out.printf("\n%d \t%s\t%s" ,id,mb,f.format(ngay));
        }
         System.out.print("\n-----------------------------------------------------------------------------------");
    }
    public void inHoaDon(Scanner scanner,Connection connection,PreparedStatement preparedStatement,SimpleDateFormat f) throws SQLException{
        int count = 0;
        try{
            dsDatBan(connection, preparedStatement,f);
            System.out.print("\nNhap ma hoa don: ");
            int s = scanner.nextInt();
            CallableStatement stm;
            stm = connection.prepareCall("{CALL hoadon(?)}");
            stm.setInt(1,s);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                ++count;
                System.out.print("\n==================== HOA DON ====================");
                int id = rs.getInt("maHD");
                String mb = rs.getString("maBan");
                Date ngay = rs.getDate("ngayDat");
                double tc = rs.getDouble("tongTien");
                System.out.printf("\nMa hoa don: %d\nMa ban: %s\t\tNgay dat: %s" ,id,mb,f.format(ngay));
                stm = connection.prepareCall("{CALL spmoihd(?)}");
                stm.setInt(1,id);
                rs = stm.executeQuery();
                System.out.print("\n-----------------------------------------------------------------------------------");
                System.out.print("\nSan pham dat\t\tSo luong \tDon gia\tThanh tien");
                System.out.print("\n-----------------------------------------------------------------------------------");
                while(rs.next()){
                    String name = rs.getString("tenSP");
                    int slb = rs.getInt("soLuong");
                    double dg = rs.getDouble("giaBan");
                    double thanhTien = rs.getDouble("tongTien");
                    System.out.printf("\n%s\t\t%d\t%,.0f\t%,.0f", name,slb,dg,thanhTien);
                }
                System.out.print("\n-----------------------------------------------------------------------------------");
                System.out.printf("\n--> Tong tien: %,.0f",tc);
                System.out.printf("\n================================================");
            }
        }catch(SQLException | InputMismatchException ex){
            System.err.print("Nhap gia tri khong hop le!Nhap lai!");
        }
        if(count == 0)
                    System.out.print("Khong tim thay hoa don!");
    }
    public void thongKe(Connection connection,PreparedStatement preparedStatement) throws SQLException{
        CallableStatement call = connection.prepareCall("{CALL thongke}");
        ResultSet rs = call.executeQuery();
        System.out.print("\n===================== THONG KE DOANH THU THEO THANG =======================");
        System.out.print("\nThang \tNam \t Tong doanh thu ");
        System.out.print("\n-----------------------------------------------------------------------------------");
        while(rs.next()){
            int month = rs.getInt("thang");
            int year = rs.getInt("nam");
            double total = rs.getDouble("tongDT");
            System.out.printf("\n%d\t%d\t%,.0f", month, year,total);
        }
        System.out.print("\n-----------------------------------------------------------------------------------");
    }
    /**
     * @return the dsBan
     */
    public QLBan getDsBan() {
        return dsBan;
    }

    /**
     * @param dsBan the dsBan to set
     */
    public void setDsBan(QLBan dsBan) {
        this.dsBan = dsBan;
    }

    /**
     * @return the dsSP
     */
    public QLSanPham getDsSP() {
        return dsSP;
    }

    /**
     * @param dsSP the dsSP to set
     */
    public void setDsSP(QLSanPham dsSP) {
        this.dsSP = dsSP;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the ngayDat
     */
    public Date getNgayDat() {
        return ngayDat;
    }

    /**
     * @param ngayDat the ngayDat to set
     */
    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

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

   
}
