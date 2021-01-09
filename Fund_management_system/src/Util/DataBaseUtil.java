package Util;

import Entity.Item;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DataBaseUtil {

    /**
     * @return 获取数据库连接
     */
    public static Connection getConnection() {

        InputStream ins = DataBaseUtil.class.getClass().getResourceAsStream("/jdbc.properties");
        Properties pros = new Properties();
        try {
            pros.load(ins);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String username = (String) pros.get("jdbc.username");
        String password = (String) pros.get("jdbc.password");
        String url = (String) pros.get("jdbc.url");
        String driver = (String) pros.get("jdbc.driver");

        try {
            //注册驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            //获取链接
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }

    /**
     * 关闭数据库连接
     *
     * @param conn
     */

    public static void CloseConnection(Connection conn) {
        if (conn != null) {
            try {
                //释放资源
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.getStackTrace();
            }
        }
    }

    /**
     * 对数据库文件的增删改操作
     *
     * @param conn
     * @param sql
     * @param args
     */

    public static void synthesize(Connection conn, String sql, Object... args) {

        PreparedStatement preparedStatement = null;
        try {
            //预编译
            preparedStatement = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);

            }

            preparedStatement.execute();
        } catch (SQLException throwables) {
            // TODO Auto-generated catch block
            throwables.printStackTrace();
        }

    }

    /**
     * 数据库查询操作
     * @param clazz
     * @param conn
     * @param sql
     * @param args
     * @param <T>
     * @return
     */

    public static <T> List<T> select(Class<T> clazz, Connection conn, String sql, Object... args) {

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();

            //获取数据列数
            int columnCount = metaData.getColumnCount();

            List<T> list = new ArrayList<T>();

            //填充数据
            while (resultSet.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    //获取数据
                    Object columValue = resultSet.getObject(i + 1);
                    //获取列名
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    //获取属性
                    Field field = clazz.getDeclaredField(columnLabel);

                    field.setAccessible(true);
                    field.set(t, columValue);

                }
                list.add(t);
            }

            return list;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }



}
