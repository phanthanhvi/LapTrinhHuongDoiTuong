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
public abstract class SanPham {
    protected String tenSP;
    protected double giaBan;
    protected String tinhTrang;
    protected String thoiDiemBan;
    protected int maDM;
    protected DanhMuc danhmuc;
    public SanPham(){
        
    }
    public SanPham(String tenSP, double giaBan, String tinhTrang, String thoiDiemBan, int maDM){
        this.tenSP = tenSP;
        this.giaBan = giaBan;
        this.tinhTrang = tinhTrang;
        this.thoiDiemBan = thoiDiemBan;
        this.maDM = maDM;
    }

    public void nhapThongTin(Scanner scanner, Connection connection, PreparedStatement preparedStatement) throws SQLException{
        try{
            System.out.print("Nhap ten san pham: ");
            this.tenSP = scanner.nextLine();
            System.out.print("Nhap tinh trang: ");
            this.tinhTrang = scanner.nextLine();
            System.out.print("Nhap thoi diem ban: ");
            this.thoiDiemBan = scanner.nextLine();
            System.out.print("Nhap gia ban: ");
            this.giaBan = scanner.nextDouble();
        }catch(InputMismatchException ex){
            System.err.print("Nhap gia tri khong hop le!Nhap lai!");
        }
    }
    
    /**
     * @return the tenSP
     */
    public String getTenSP() {
        return tenSP;
    }

    /**
     * @param tenSP the tenSP to set
     */
    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    /**
     * @return the giaBan
     */
    public double getGiaBan() {
        return giaBan;
    }

    /**
     * @param giaBan the giaBan to set
     */
    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
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

    /**
     * @return the thoiDiemBan
     */
    public String getThoiDiemBan() {
        return thoiDiemBan;
    }

    /**
     * @param thoiDiemBan the thoiDiemBan to set
     */
    public void setThoiDiemBan(String thoiDiemBan) {
        this.thoiDiemBan = thoiDiemBan;
    }

    /**
     * @return the maDM
     */
    public int getMaDM() {
        return maDM;
    }

    /**
     * @param maDM the maDM to set
     */
    public void setMaDM(int maDM) {
        this.maDM = maDM;
    }



}
