/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaiTapLon;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class QLDanhMuc {
    private List<DanhMuc> ql = new ArrayList<>();

    
    public void themDM(DanhMuc dm){
        this.ql.add(dm);
    }
    public void hienThi(Connection connection, Statement stm) throws SQLException{
//        this.ql.forEach(dm -> dm.hienThi());
        stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM danhmuc ");
        while (rs.next()){
            int id = rs.getInt("maDM");
            String name = rs.getString("tenDM");
            System.out.printf("%d. %s \n", id,name);
        }
    }

    /**
     * @return the ql
     */
    public List<DanhMuc> getQl() {
        return ql;
    }

    /**
     * @param ql the ql to set
     */
    public void setQl(List<DanhMuc> ql) {
        this.ql = ql;
    }
}
