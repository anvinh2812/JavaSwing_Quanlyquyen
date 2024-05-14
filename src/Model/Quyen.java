package Model;

public class Quyen {
    private Integer Maquyen;
    private String Tenquyen;
    private String Nhomquyen;
    public Quyen(){

    }

    public Quyen(Integer maquyen, String tenquyen, String nhomquyen) {
        Maquyen = maquyen;
        Tenquyen = tenquyen;
        Nhomquyen = nhomquyen;
    }

    public Integer getMaquyen() {
        return Maquyen;
    }

    public void setMaquyen(Integer maquyen) {
        Maquyen = maquyen;
    }

    public String getTenquyen() {
        return Tenquyen;
    }

    public void setTenquyen(String tenquyen) {
        Tenquyen = tenquyen;
    }

    public String getNhomquyen() {
        return Nhomquyen;
    }

    public void setNhomquyen(String nhomquyen) {
        Nhomquyen = nhomquyen;
    }
}
