//package com.mrm.typer.model;
//
//import java.sql.*;
//import java.util.ArrayList;
//
//public class DB {
//    final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
//    final String URL = "jdbc:derby:typoshooterDB;create=true";
//
//    Connection conn = null;
//    Statement createStatement = null;
//    DatabaseMetaData dbmd = null;
//
//    public DB() {
//        //Megpróbáljuk életre kelteni
//        try {
//            conn = DriverManager.getConnection(URL);
//            System.out.println("A híd létrejött");
//        } catch (SQLException ex) {
//            System.out.println("Valami baj van a connection (híd) létrehozásakor.");
//            System.out.println(""+ex);
//        }
//
//        //Ha életre kelt, csinálunk egy megpakolható teherautót
//        if (conn != null){
//            try {
//                createStatement = conn.createStatement();
//            } catch (SQLException ex) {
//                System.out.println("Valami baj van van a createStatament (teherautó) létrehozásakor.");
//                System.out.println(""+ex);
//            }
//        }
//
//        //Megnézzük, hogy üres-e az adatbázis? Megnézzük, létezik-e az adott adattábla.
//        try {
//            dbmd = conn.getMetaData();
//        } catch (SQLException ex) {
//            System.out.println("Valami baj van a DatabaseMetaData (adatbázis leírása) létrehozásakor..");
//            System.out.println(""+ex);
//        }
//
//        try {
//            ResultSet rs = dbmd.getTables(null, "APP", "results", null);
//            if(!rs.next())
//            {
//                createStatement
//                        .execute("create table results(name varchar(20), score VARCHAR (20))");
//            }
//        } catch (SQLException ex) {
//            System.out.println("Valami baj van az adattáblák létrehozásakor.");
//            System.out.println(""+ex);
//        }
//    }
//
//
//    public ArrayList<Result> getAllResults(){
//        String sql = "select * from results ";
//        ArrayList<Result> users = null;
//        try {
//            ResultSet rs = createStatement.executeQuery(sql);
//            users = new ArrayList<>();
//
//            while (rs.next()){
//                Result actualResult = new Result(rs.getString("name"),rs.getString("score"));
//                users.add(actualResult);
//            }
//        } catch (SQLException ex) {
//            System.out.println("Valami baj van a userek kiolvasásakor");
//            System.out.println(""+ex);
//        }
//        return users;
//    }
//
//    public void addResult(Result Result){
//        try {
//            String sql = "insert into results (name, score) values (?,?)";
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setString(1, Result.getName());
//            preparedStatement.setString(2, Result.getScore());
//            preparedStatement.execute();
//        } catch (SQLException ex) {
//            System.out.println("Valami baj van a contact hozzáadásakor");
//            System.out.println(""+ex);
//        }
//    }
//
//
//
//
//}
