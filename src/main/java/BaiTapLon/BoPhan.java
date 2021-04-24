/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaiTapLon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class BoPhan {
    private static int dem = 0;
    private int maBP = ++dem;
    private String tenBP;


    public void nhapBoPhan(Scanner scanner, Connection connection, PreparedStatement stm) throws SQLException{
        System.out.print("\nNhap ten bo phan: ");
        this.tenBP = scanner.nextLine();
        String query = "INSERT INTO bophan(tenBP) VALUES (?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, this.tenBP);
        preparedStatement.executeUpdate();
        System.out.println("Them bo phan thanh cong!");
    }

    @Override
    public String toString(){
        return String.format("%s",this.tenBP);
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

    /**
     * @return the tenBP
     */
    public String getTenBP() {
        return tenBP;
    }

    /**
     * @param tenBP the tenBP to set
     */
    public void setTenBP(String tenBP) {
        this.tenBP = tenBP;
    }

    
}
