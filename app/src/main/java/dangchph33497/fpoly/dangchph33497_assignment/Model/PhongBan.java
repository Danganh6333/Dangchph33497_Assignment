package dangchph33497.fpoly.dangchph33497_assignment.Model;

import java.io.Serializable;

public class PhongBan implements Serializable {
    private String tenPhongBan;
    public PhongBan() {
    }
    public PhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }
}
