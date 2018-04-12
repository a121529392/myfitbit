/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfitbit;

import com.mysql.jdbc.CommunicationsException;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import static java.sql.DriverManager.getConnection;
import static java.sql.DriverManager.getConnection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import models.Check_DateTime_Name_User_Time_Model;
import models.Check_DateTime_Name_User_Model;
import models.Check_DateTime_User_Time_Model;

/**
 *
 * @author LU SIN EN
 */
public class MySQLConnector {
    //login

    private config con = new config(); //configulation object

    String connStr = null;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    ResultSetMetaData metaData = null;
    StringBuffer ResultSB = new StringBuffer();

    public MySQLConnector(String inHost, String inAcc, String inPW, String inDB) {

        con.setDBname(inDB);
        con.setPwStr(inPW);
        con.setUrlstr(inHost);
        con.setUserStr(inAcc);
    }

    public static Check_DateTime_Name_User_Model Check_Fitbit_DAY(String user,String date) throws SQLException {
        config c = new config();
        Check_DateTime_Name_User_Model data = new Check_DateTime_Name_User_Model();

        MySQLConnector sql = new MySQLConnector(c.getUrlstr(), c.getUserStr(), c.getPwStr(), c.getDBname());
        sql.connectDB();
        String query = "SELECT * FROM DAY  where User_ID='"+user+"'and DateTime='"+date+"'";
        data.dateTime = sql.getdata(query, "DateTime");
        data.name = sql.getdata(query, "ID_ACTIVITY");
        data.user = sql.getdata(query, "User_ID");
        sql.close();
        return data;
    }

    public static String[] Check_devices () throws SQLException {
        config c = new config();
        String[] data;

        MySQLConnector sql = new MySQLConnector(c.getUrlstr(), c.getUserStr(), c.getPwStr(), c.getDBname());
        sql.connectDB();
        String query = "SELECT * FROM Devices";
        data = sql.getdata(query, "ID");
        sql.close();
        return data;
    }

    public static Check_DateTime_Name_User_Model Check_Fitbit_activities_heart(String user,String date) throws SQLException {
        Check_DateTime_Name_User_Model data = new Check_DateTime_Name_User_Model();
        config c = new config();
        MySQLConnector sql = new MySQLConnector(c.getUrlstr(), c.getUserStr(), c.getPwStr(), c.getDBname());
        sql.connectDB();
        String query = "SELECT * FROM activities_heart where User_ID='"+user+"' and DateTime='"+date+"'";
        data.dateTime = sql.getdata(query, "DateTime");
        data.name = sql.getdata(query, "hz_name");
        data.user = sql.getdata(query, "User_ID");
        sql.close();
        return data;

    }

    public static Check_DateTime_User_Time_Model Check_Fitbit_SLEEP_MINUTE(String user,String date) throws SQLException {
        config c = new config();
        Check_DateTime_User_Time_Model data = new Check_DateTime_User_Time_Model();
        MySQLConnector sql = new MySQLConnector(c.getUrlstr(), c.getUserStr(), c.getPwStr(), c.getDBname());
        sql.connectDB();
        String query = "SELECT * FROM SLEEP_MINUTE where User_ID='"+user+"'and DateTime='"+date+"'";
        data.dateTime = sql.getdata(query, "DateTime");
        data.user = sql.getdata(query, "User_ID");
        data.time = sql.getdata(query, "time");
        sql.close();
        return data;

    }

    public static Check_DateTime_User_Time_Model Check_Fitbit_Heartrate_Detail(String user,String date) throws SQLException {
        config c = new config();
        Check_DateTime_User_Time_Model data = new Check_DateTime_User_Time_Model();
        MySQLConnector sql = new MySQLConnector(c.getUrlstr(), c.getUserStr(), c.getPwStr(), c.getDBname());
        sql.connectDB();
        String query = "SELECT * FROM Heartrate_Detail where User_ID='"+user+"' and DateTime='"+date+"'";
        data.dateTime = sql.getdata(query, "DateTime");
        data.user = sql.getdata(query, "User_ID");
        data.time = sql.getdata(query, "Time");
        sql.close();
        return data;

    }

    public static Check_DateTime_Name_User_Time_Model Check_Fitbit_activities_minute(String user,String id) throws SQLException {
        config c = new config();
        Check_DateTime_Name_User_Time_Model data = new Check_DateTime_Name_User_Time_Model();
        MySQLConnector sql = new MySQLConnector(c.getUrlstr(), c.getUserStr(), c.getPwStr(), c.getDBname());
        sql.connectDB();
        String query = "SELECT * FROM activity_minute where User_ID='"+user+"' and ID_ACTIVITY='"+id+"'";
        data.dateTime = sql.getdata(query, "DateTime");
        data.name = sql.getdata(query, "ID_ACTIVITY");
        data.user = sql.getdata(query, "User_ID");
        data.time = sql.getdata(query, "time");
        sql.close();

        return data;
    }

    public static String[] Check_Fitbit_access() throws SQLException {
        config c = new config();
        String[] user;
        MySQLConnector sql = new MySQLConnector(c.getUrlstr(), c.getUserStr(), c.getPwStr(), c.getDBname());
        sql.connectDB();
        String query = "SELECT * FROM access";
        user = sql.getdata(query, "account");
        sql.close();
        return user;
    }

    public int connectDB() {
        try {
            //conn = (Connection) DriverManager.getConnection("jdbc:mysql://140.138.77.109/javaclass?user=root&password=tear040MARC668");
            connStr = "jdbc:mysql://" + con.getUrlstr() + "/" + con.getDBname() + "?user=" + con.getUserStr() + "&password=" + con.getPwStr();
            conn = (Connection) DriverManager.getConnection(connStr);
            //   conn.close();
            System.out.println("fitbit Databases connected!");

        } catch (SQLException ex) {

            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return -1;
        }

        return 1;

    }

    public static void insertdata(StringBuffer sb, config c) throws SQLException {//insert data to db
        //sb.append(";");
        char end=sb.charAt(sb.length()-1);
        if(end!=';'){
        sb.append(";");
        }
        
        System.out.println(sb);
        
        MySQLConnector sleep_sql = new MySQLConnector(c.getUrlstr(), c.getUserStr(), c.getPwStr(), c.getDBname());
        sleep_sql.connectDB();
        sleep_sql.updateQuery(sb.toString());
        sleep_sql.close();

    }
public static void InsertManySQL(Vector<String> sb,  config c) throws SQLException {//insert data to db
        
        MySQLConnector sleep_sql = new MySQLConnector(c.getUrlstr(), c.getUserStr(), c.getPwStr(), c.getDBname());
        sleep_sql.connectDB();
        System.out.println("Fitbit"+sb);
        sleep_sql.insert(sb, c);
        sleep_sql.close();

    }

public  void insert(Vector<String> sb, config c) throws SQLException {//insert data to db
        
try{

    Statement st = conn.createStatement();
    System.out.println(sb.size());
    for (int i = 0; i < sb.size(); i++) {
        st.addBatch(sb.get(i));
    }
    st.executeBatch();
    st.close();
    }
catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    public void doQuery(String SQLstr) {
        try {

            String SQL = SQLstr;

            ResultSB.delete(0, ResultSB.length());
            System.out.println(SQL);
            stmt = (Statement) conn.createStatement();
            rs = stmt.executeQuery(SQL);

            metaData = rs.getMetaData();
            int numCol = metaData.getColumnCount();

            for (int i = 1; i <= numCol; i++) {
                System.out.print("\t" + metaData.getColumnName(i));
                ResultSB.append("\t" + metaData.getColumnName(i));
            }
            System.out.println();
            ResultSB.append("\n");

            while (rs.next()) {
                for (int i = 1; i <= numCol; i++) {
                    System.out.print("\t" + rs.getObject(i));
                    ResultSB.append("\t" + rs.getObject(i));
                }
                System.out.println();
                ResultSB.append("\n");

            }
            rs.close();
            stmt.close();
            conn.close();

            // Now do something with the ResultSet ....
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }

    }

    public void updateQuery(String SQLstr) {
        try {

            String SQL = SQLstr;
            stmt = (Statement) conn.createStatement();

            //清空result
            ResultSB.delete(0, ResultSB.length());
            // int resultset;
            stmt.executeUpdate(SQL);
            stmt.close();
            // Now do something with the ResultSet ....
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    //      conn.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }

    }

    public String[] getdata(String query, String ans) throws SQLException {//get db data
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        int size = 0;
        while (rs.next()) {//count data size
            size++;
        }

        rs.first();//move cursor's position to the first position 
        String[] data = new String[size];

        try {
            for (int i = 0; i < size; i++) {
                data[i] = rs.getString(ans);
                rs.next();

            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        st.close();
        return data;

    }

    public StringBuffer getResultString() {
        return this.ResultSB;
    }

    public void close() throws SQLException {
        conn.close();
    }
}
