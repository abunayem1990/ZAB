package com.example.zab.ui.login;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    String ip="203.83.171.61";
    String db="ZABDB";
    String classs="net.sourceforge.jtds.jdbc.Driver";
    String user="zabssms";
    String pass="(sql@s3rv3r.l0g1n)";

    @SuppressLint("NewApi")
    public Connection Conn(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn=null;
        String connURL=null;
        try{
            Class.forName(classs).newInstance();
            connURL="jdbc:jtds:sqlserver://"+ip+";"
                    +"databaseName="+db+";user="+user+";password="+pass+";";
            conn= DriverManager.getConnection(connURL);
        }
        catch (SQLException se) {
            Log.e("ERROR1", se.getMessage());
        }
        catch (ClassNotFoundException e) {
            Log.e("ERROR2", e.getMessage());
        }  catch (Exception ex) {
            Log.e("ERROR3", ex.getMessage());
        }

        return conn;
    }
}
