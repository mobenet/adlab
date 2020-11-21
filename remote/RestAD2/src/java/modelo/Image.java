
package modelo;


public class Image {

    
    
    private String title;
    private int id;
    private String C_date;
    private String Auth;
    private String Kwords;
    private String Filename;
    private String Description;
    private byte[] rawData;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getC_date() {
        return C_date;
    }

    public void setC_date(String C_date) {
        this.C_date = C_date;
    }

    public String getAuth() {
        return Auth;
    }

    public void setAuth(String Auth) {
        this.Auth = Auth;
    }

    public String getKwords() {
        return Kwords;
    }

    public void setKwords(String Kwords) {
        this.Kwords = Kwords;
    }

    public String getFilename() {
        return Filename;
    }

    public void setFilename(String Filename) {
        this.Filename = Filename;
    }

    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }
}
