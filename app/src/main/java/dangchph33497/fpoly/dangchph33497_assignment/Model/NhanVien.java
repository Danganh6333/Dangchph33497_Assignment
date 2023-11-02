package dangchph33497.fpoly.dangchph33497_assignment.Model;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private int maNV;
    private String hoTen;
    private String phongBan;

    public NhanVien() {
    }

    public NhanVien(int maNV, String hoTen, String phongBan) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.phongBan = phongBan;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }
}
