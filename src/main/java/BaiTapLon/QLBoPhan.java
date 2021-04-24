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
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class QLBoPhan {
    private List<BoPhan> ds = new ArrayList<>();


    
    public void themBP(BoPhan bp,Connection connection,PreparedStatement stm) throws SQLException{
        Scanner scanner = new Scanner(System.in);
        bp.nhapBoPhan(scanner,connection, stm);
    }
    public void hienThi(Connection connection, PreparedStatement stm) throws SQLException{
        stm = connection.prepareStatement("SELECT * FROM bophan");
       ResultSet rs = stm.executeQuery();
       while(rs.next()){
           int id = rs.getInt("maBP");
           String name = rs.getString("tenBP");
           System.out.printf("\n%d. %s", id,name);
       }
    }

    
    /**
     * @return the ds
     */
    public List<BoPhan> getDs() {
        return ds;
    }

    /**
     * @param ds the ds to set
     */
    public void setDs(List<BoPhan> ds) {
        this.ds = ds;
    }

   
    
}
