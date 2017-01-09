package com.mrheadshot62.server.storage;

import com.sun.istack.internal.Nullable;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by novak on 07.01.2017.
 */
public class SQLBuilder {
    private Statement stmt;
    private ArrayList<String> query = new ArrayList<>();
    private ArrayList<String> rQuery = new ArrayList<>();
    private int iter = 0;
    private boolean sendedQuery = false;
    private ResultSet rs;
    private static SQLBuilder instance;

    public static SQLBuilder getInstance() {
        if (instance == null) {
            instance = new SQLBuilder();
            return instance;
        } else {
            return instance;
        }
    }

    private SQLBuilder() {
    }

    private void connect(){
        if (stmt == null) {
            try {
                stmt = Connector.getConnection().createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return Последний полученый результат
     */
    public ResultSet getResult() {
        return rs;
    }

    /**
     * Отсылает все запросы без результата и одно с результатом, если такое имеется
     * @return Результат запроса
     */
    @Nullable
    public ResultSet executeSingle(){
        pExecuteNoResult();
        if (rQuery.size()==1) {
            try {
                ResultSet rs = stmt.executeQuery(rQuery.get(0));
                this.rs = rs;
                reset();
                return rs;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        reset();
        return null;
    }

    private void reset(){
        rQuery=new ArrayList<>();
        query=new ArrayList<>();
        sendedQuery=false;
        stmt=null;
    }

    /**
     * Посылает запросы, которые не возвращают результат
     */
    public void executeNoResult(){
        pExecuteNoResult();
    }

    private void pExecuteNoResult(){
        if (!sendedQuery) {
            for (String s : query) {
                try {
                    stmt.execute(s);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            sendedQuery=true;
        }
    }
    /**
     * Пример кода для получения результата нескольких запросов:
     *<pre>{@code SQLBuilder sqlBuilder = new SQLBuilder().addUpdateQuery(USER_TABLE, "pass", "789", "id", "9").addSelectQuery(USER_TABLE, new String[]{"id", "login"}, "id", "8", 50).addSelectQuery(USER_TABLE, new String[]{"id", "login"}, "id", "7", 50);
    while (sqlBuilder.executeNext()){
    try {
    ResultSet r = sqlBuilder.getResult();
    while (r.next()) {
    System.out.print(r.getString("id")+" "+r.getString("login"));
    }
    } catch (SQLException e) {
    e.printStackTrace();
    }
    }
    }</pre>
     *
     */
    public boolean executeNext() {
        pExecuteNoResult();
        if (iter == rQuery.size()){
            reset();
            return false;
        }
        try {
            String q = rQuery.get(iter);
            rs = stmt.executeQuery(q);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        iter++;
        return true;
    }

    public String getQuery() {
        return toString();
    }

    /**
     * @param table Таблица, в которой следует выполнить запрос
     * @param variables Переменные, которые следует получить
     * @param whereVar переменная для WHERE (можно null)
     * @param whereData значание переменной для WHERE (можно null)
     * @param limit лимит результатов, больше нуля
     * @return this
     */
    public SQLBuilder addSelectQuery(String table, String[] variables, String whereVar, String whereData, int limit) {
        connect();
        if (limit <= 0) addToQuery("");
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ");
        if (variables[0].equals("*")) {
            builder.append("* ");
        } else {
            for (int i = 0; i < variables.length; i++) {
                String s = variables[i];
                builder.append("`").append(s).append("`");
                if (i == variables.length - 1) {
                    builder.append(" ");
                } else {
                    builder.append(",");
                }
            }
        }
        builder.append("FROM ").append(table).append(" ");
        if (whereVar != null && whereData != null) {
            builder.append("WHERE `").append(whereVar).append("` = ").append(whereData).append(" ");
        }
        builder.append("LIMIT ").append(limit).append(";");
        addToResultQuery(builder.toString());
        return this;
    }

    /**
     * Получает все колонки ряда<br>
     * {@link #addSelectQuery(String, String[], String, String, int)}
     *
     * @param table Таблица, в которой следует выполнить запрос
     * @param whereVar переменная для WHERE <b>(можно null)</b>
     * @param whereData значание переменной для WHERE <b>(можно null)</b>
     * @param limit лимит результатов, больще нуля
     * @return this
     */
    public SQLBuilder addSelectQuery(String table, String whereVar, String whereData, int limit){
        return addSelectQuery(table, new String[]{"*"}, whereVar, whereData, limit);
    }


    /**
     * Генерация запроса для обновления
     *
     * @param table Таблица, в которой следует провести обновление
     * @param var Переменная, которые следует обновить
     * @param data Новое значение
     * @param whereVar переменная для WHERE
     * @param whereData значание переменной для WHERE
     * @return this
     */
    public SQLBuilder addUpdateQuery(String table, String var, String data, String whereVar, String whereData) {
        connect();
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ").append(table).append(" SET `").append(var).append("` = `").append(data);
        if (whereData != null && whereVar != null) {
            builder.append("` WHERE `").append(whereVar).append("` = ").append(whereData).append(";");
        } else {
            builder.append("`;");
        }
        addToQuery(builder.toString());
        System.out.println(builder.toString());
        return this;
    }

    public SQLBuilder addInsertQuery(String table, String[] vars, String[] datas) {
        connect();
        if (vars.length != datas.length) addToQuery("");
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(table).append("(");
        for (int i = 0; i < vars.length; i++) {
            String s = vars[i];
            builder.append(s);
            if (!(i == vars.length - 1)) builder.append(",");
        }
        builder.append(") VALUES (");
        for (int b = 0; b < datas.length; b++) {
            String s = datas[b];
            builder.append(s);
            if (!(b == datas.length - 1)) {
                builder.append(",");
            }
        }
        builder.append(");");
        addToQuery(builder.toString());
        return this;
    }

    private void addToQuery(String str) {
        StringBuilder builder = new StringBuilder(str);
        if (!str.endsWith(";")) builder.append(";");
        query.add(builder.toString());
    }

    private void addToResultQuery(String str) {
        StringBuilder builder = new StringBuilder(str);
        if (!str.endsWith(";")) builder.append(";");
        rQuery.add(builder.toString());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : query) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    private static class Connector {
        private static final String url = "jdbc:mysql://localhost:3306/BlueBear";
        private static final String user = "root";
        private static final String password = "";
        private static Connection con;
        private static boolean isConnected=false;

        private static void connect() {
            try {
                con = DriverManager.getConnection(url, user, password);
                isConnected = true;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        static Connection getConnection(){
            if (!isConnected)connect();
            return con;
        }

//        private static void test() {
//            com.mrheadshot62.server.storage.SQLBuilder sqlBuilder = new com.mrheadshot62.server.storage.SQLBuilder().addUpdateQuery(ServerStorage.USER_TABLE, "pass", "789", "id", "9").addSelectQuery(ServerStorage.USER_TABLE, new String[]{"id", "login"}, "id", "8", 50).addSelectQuery(ServerStorage.USER_TABLE, new String[]{"id", "login"}, "id", "7", 50);
//            System.out.println(sqlBuilder.getQuery());
//            while (sqlBuilder.executeNext()) {
//                try {
//                    ResultSet r = sqlBuilder.getResult();
//                    while (r.next()) {
//                        System.out.printf("%s %s%n", r.getString("id"), r.getString("login"));
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}
