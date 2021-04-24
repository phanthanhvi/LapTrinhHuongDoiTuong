/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaiTapLon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 *
 * @author acer
 */
public class Test {
    public static void main(String[] args) throws SQLException, ParseException, Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/baitaplon","root","1851050187");
        PreparedStatement preparedStatement = null;
        int chon;
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        QLNhanVien ql = new QLNhanVien();
        QLBan qlb = new QLBan();
        QLSanPham qlsp = new QLSanPham();
        DatBan db = new DatBan();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n===============QUAN LY QUAN CA PHE===============");
        System.out.print("\n1.  Xem danh sach nhan vien.");
        System.out.print("\n2.  Tra cuu nhan vien.");
        System.out.print("\n3.  Them nhan vien.");
        System.out.print("\n4.  Cap nhat nhan vien.");
        System.out.print("\n5.  Xoa nhan vien.");
        System.out.print("\n6.  Xem danh sach ban trong.");
        System.out.print("\n7.  Tim kiem ban theo suc chua.");
        System.out.print("\n8.  Tim kiem thuc an, thuc uong theo ten.");
        System.out.print("\n9.  Tim kiem thuc an, thuc uong theo khoang gia.");
        System.out.print("\n10. Sap xep thuc an, thuc uong tang theo khoang gia.");
        System.out.print("\n11. Sap xep thuc an, thuc uong giam theo khoang gia.");
        System.out.print("\n12. Sinh nhat nhan vien.");
        System.out.print("\n13. Dat ban.");
        System.out.print("\n14. In hoa don.");
        System.out.print("\n15. Thong ke doanh thu theo thang.");
        System.out.print("\n16. Thoat.");
        System.out.print("\n=================================================");
        do{
                System.out.print("\nNhap lua chon cua ban: ");
                chon = scanner.nextInt();
                switch(chon){
                    case 1:
                        ql.hienThi(connection, preparedStatement,f);
                        break;
                    case 2:
                        scanner.nextLine();
                        ql.timNV(scanner,connection, preparedStatement,f);
                        break;
                    case 3:
                        scanner.nextLine();
                        ql.themNV(scanner,connection, preparedStatement,f);
                        break;
                    case 4:
                        scanner.nextLine();
                        ql.capNhatNV(scanner,connection, preparedStatement,f);
                        break;
                    case 5:
                        scanner.nextLine();
                        ql.xoaNV(scanner,connection, preparedStatement,f);
                        break;
                    case 6:
                        qlb.banTrong(connection, preparedStatement);
                        break;
                    case 7:
                        qlb.timBan(scanner,connection, preparedStatement);
                        break;
                    case 8:
                        scanner.nextLine();
                        qlsp.timSPTheoTen(scanner,connection, preparedStatement);
                        break;
                    case 9:
                        qlsp.timSPTheoGia(scanner,connection, preparedStatement);
                        break;
                    case 10:
                        qlsp.sapXepTang(connection, preparedStatement);
                        break;
                    case 11:
                        qlsp.sapXepGiam(connection, preparedStatement);
                        break;
                    case 12:
                        ql.sinhNhat(connection,f);
                        break;
                    case 13:
                        
                        db.datBan(scanner,connection, preparedStatement,f);
                        break;
                    case 14:
                        scanner.nextLine();
                        db.inHoaDon(scanner, connection,preparedStatement,f);
                        break;
                    case 15:
                        db.thongKe(connection, preparedStatement);
                        break;
                    case 16:
                        System.out.print("\nGoodbye......!");
                        break;
                    default:
                        System.out.print("\nGia tri chon khong hop le!");
                        break;
                }
        }while(chon!=16); 
    }
}
