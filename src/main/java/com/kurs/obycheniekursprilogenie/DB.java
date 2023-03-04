package com.kurs.obycheniekursprilogenie;

import java.sql.*;

public class DB
{
    private final String HOST = "localhost";
    private final String PORT = "3307";
    private final String DBNAME = "kurs-prilogenie";
    private final String LOGIN = "root";
    private final String PASS = "";

    private Connection dbConn = null;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException
    {
        String connectionString = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DBNAME;
        dbConn = DriverManager.getConnection(connectionString, LOGIN, PASS);
        return dbConn;
    }

    public void isConnected () throws SQLException, ClassNotFoundException
    {
        dbConn = getDbConnection();
        System.out.println(dbConn.isValid(1000));
    }

    public ResultSet getArticles()
    {
        String sql = "SELECT * FROM `ssilki`";
        Statement statement = null;
        try
        {
            statement = getDbConnection().createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException | ClassNotFoundException e) {e.printStackTrace();}

        return null;
    }

    public void addArticle(String full_art, String small_art)
    {
        String sql = "INSERT INTO `ssilki` (`fullart`, `smallart`) VALUES(?,?)";

        try
        {
            PreparedStatement prst = getDbConnection().prepareStatement(sql);
            prst.setString(1, full_art);
            prst.setString(2, small_art);
            prst.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {e.printStackTrace();}
    }

    public boolean checkArticle(String small_art)
    {
        String sql = "SELECT `smallart` FROM `ssilki` WHERE `smallart` = ?";

        try
        {
            PreparedStatement prst = getDbConnection().prepareStatement(sql);
            prst.setString(1, small_art);
            ResultSet res = prst.executeQuery();
            return res.next();
        } catch (SQLException | ClassNotFoundException e) {e.printStackTrace();}
        return false;
    }
}
