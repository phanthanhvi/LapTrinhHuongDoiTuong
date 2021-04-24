/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaiTapLon;

/**
 *
 * @author acer
 */
public  class DanhMuc {
    private static int dem =0;
    private int maDM = ++dem;
    private String tenDanhMuc;
    
    
    public DanhMuc(String tenDM){
        this.tenDanhMuc = tenDM;
    
    }
    public void hienThi(){
        System.out.printf("%d. %s\n",this.maDM,this.tenDanhMuc);
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

    /**
     * @return the tenDanhMuc
     */
    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    /**
     * @param tenDanhMuc the tenDanhMuc to set
     */
    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }
    
}
