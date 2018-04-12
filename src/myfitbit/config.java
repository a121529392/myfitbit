package myfitbit;

public class config {
    
    private String  urlstr = "";
    private String userStr= "";
    private String pwStr= "";
    private String DBname ="fitbit_log"; 

   public void setDBname(String DBname) {
        this.DBname = DBname;
    }
   
   public String getDBname() {
        return DBname;
    }

    public String getUrlstr() {
        return urlstr;
    }

    public void setUrlstr(String urlstr) {
        this.urlstr = urlstr;
    }

    public void setUserStr(String userStr) {
        this.userStr = userStr;
    }

    public void setPwStr(String pwStr) {
        this.pwStr = pwStr;
    }

    public String getUserStr() {
        return userStr;
    }

    public String getPwStr() {
        return pwStr;
    }
}
