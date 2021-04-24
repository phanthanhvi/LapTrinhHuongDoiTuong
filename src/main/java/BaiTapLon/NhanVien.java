/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaiTapLon;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class NhanVien {
    private static int dem = 0;
    private int maNV = ++dem;
    private String hoTen;
    private String gioiTinh;
    private String queQuan;
    private Date ngaySinh;
    private Date ngayVaoLam;
    private int maBP;
//    public NhanVien(){
//    }
//    public NhanVien(String hoTen, String gioiTinh, String queQuan, Date ngaySinh, Date ngayVaoLam,int maBP){
//        this.hoTen = hoTen;
//        this.gioiTinh = gioiTinh;
//        this.queQuan = queQuan;
//        this.ngaySinh = ngaySinh;
//        this.ngayVaoLam = ngayVaoLam;
//        this.maBP = maBP;
//    }

    public void nhapThongTin(Scanner scanner, Connection connection, PreparedStatement preparedStatement,SimpleDateFormat f) throws ParseException, SQLException, Exception{
        boolean check = false;
        int temp;
        java.sql.Date sql1 = null, sql2 = null;
        do{
            try{
                System.out.print("Nhap ten nhan vien: ");
                this.hoTen = scanner.nextLine();
                System.out.print("Nhap gioi tinh: ");
                this.gioiTinh = scanner.nextLine();
                System.out.print("Nhap que quan: ");
                this.queQuan = scanner.nextLine();
                System.out.print("Nhap ngay sinh: ");
                String ns = scanner.nextLine();
                f.setLenient(false);
                this.ngaySinh = f.parse(ns);
                sql1 = new java.sql.Date(this.ngaySinh.getTime());
                System.out.print("Nhap ngay vao lam: ");
                String nl = scanner.nextLine();
                f.setLenient(false);
                this.ngayVaoLam = f.parse(nl);
                sql2 = new java.sql.Date(this.ngayVaoLam.getTime());
                QLBoPhan ql = new QLBoPhan();
                ql.hienThi(connection, preparedStatement);
                do{
                    try (CallableStatement call = connection.prepareCall("{CALL countbp(?)}")) {
                        call.registerOutParameter(1, Types.INTEGER);
                        call.execute();
                        temp = call.getInt(1);
                    }
                System.out.print("\nNhap ma bo phan: ");
                this.maBP = scanner.nextInt();
                if(this.maBP > temp || this.maBP < 0)
                    System.out.print("\nMa bo phan khong ton tai!");
                }while(this.maBP>temp);
                scanner.nextLine();
                String query = "INSERT INTO nhanvien(hoTenNV,gioiTinh,queQuan,ngaySinh,ngayVaoLam,maBP) VALUES (?,?,?,?,?,?);";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,this.hoTen);
                preparedStatement.setString(2,this.gioiTinh);
                preparedStatement.setString(3,this.queQuan);
                preparedStatement.setDate(4,sql1);
                preparedStatement.setDate(5,sql2);
                preparedStatement.setInt(6,this.maBP);
                preparedStatement.executeUpdate();
                System.out.print("\nThem thanh cong nhan vien!");
                check = true;
                }catch(SQLException | InputMismatchException ex){
                    check = false;
                    System.err.print("Nhap gia tri khong hop le!Nhap lai!");
                }catch(ParseException pe){
                    check = false;
                    System.err.print("Nhap ngay khong hop le!");
                }catch(NumberFormatException nfe){
                    check = false;
                    System.err.print("Nhap ma bo phan khong hop le!");
                }
        }while(check == false);
    }

//    public void hienThi() throws SQLException{
////        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
////        System.out.printf("\nMa nhan vien:  %d\nHo ten: %s\nGioi tinh: %s\nQue quan: %s\nNgay sinh: %s\nNgay vao lam: %s", 
////                this.maNV,this.hoTen,this.gioiTinh,this.queQuan, f.format(this.ngaySinh),f.format(this.ngayVaoLam));
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/baitaplon","root","1851050187");
//        Statement stm = connection.createStatement();
//        ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien N INNER JOIN bophan B WHERE N.maBP = B.maBP");
//        while (rs.next()){
//            int id = rs.getInt("maNV");
//            String name = rs.getString("hoTenNV");
//            String gt = rs.getString("gioiTinh");
//            String qq = rs.getString("queQuan");
//            Date ns = rs.getDate("ngaySinh");
//            Date nvl = rs.getDate("ngayVaoLam");
//            String namebp = rs.getString("tenBP");
//            System.out.printf("\nMa nhan vien:  %d\nHo ten: %s\nGioi tinh: %s\nQue quan: %s\nNgay sinh: %s\nNgay vao lam: %s",
//                    id,name,gt,qq,ns,nvl,namebp);
//        }
//        
//    }
/**
    
    /**
     * @return the maNV
     */
    public int getMaNV() {
        return maNV;
    }

    /**
     * @param maNV the maNV to set
     */
    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    /**
     * @return the hoTen
     */
    public String getHoTen() {
        return hoTen;
    }

    /**
     * @param hoTen the hoTen to set
     */
    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    /**
     * @return the gioiTinh
     */
    public String getGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    /**
     * @return the queQuan
     */
    public String getQueQuan() {
        return queQuan;
    }

    /**
     * @param queQuan the queQuan to set
     */
    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    /**
     * @return the ngaySinh
     */
    public Date getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    /**
     * @return the ngayVaoLam
     */
    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    /**
     * @param ngayVaoLam the ngayVaoLam to set
     */
    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    /**
     * @return the maBP
     */
    public int getMaBP() {
        return maBP;
    }

    /**
     * @param maBP the maBP to set
     */
    public void setMaBP(int maBP) {
        this.maBP = maBP;
    }

 

}
