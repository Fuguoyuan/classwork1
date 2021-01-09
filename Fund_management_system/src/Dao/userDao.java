package Dao;

import Controllers.loginController;
import Entity.User;
import Util.DataBaseUtil;

import java.sql.Connection;
import java.util.List;

/**
 * userDao : 给定信息实现了Services包中各类 使用工具类 对数据库进行user相关的操作
 */
public class userDao {

    /**
     * 通过用户名查找账号信息封装成一个User类并返回该类
     * 如果找到了，返回该User类，没有找到则返回null。
     *
     * @param username 用户名
     * @return User
     */
    public static User getUserByAccount(String username) {
        // 获取数据库连接
        Connection conn = DataBaseUtil.getConnection();
        // 编写sql语言
        String sql = "select user_id as id, user_name as username, user_password as password from users where user_name = ? ";
        //调用工具类DataBaseUtil中的select方法，
        //在数据库users文件中根据用户名查找该用户，并将其存储在一个以Item为元素的集合中
        //返回该集合的第一个元素即为要找的用户的User类给调用者
        List<User> list = DataBaseUtil.select(User.class, conn, sql, username);
        //判断是否找到了该用户
        if (list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    /**
     * 给定账号密码将其保存在数据库文件User中，并且返回true
     *
     * @param username  用户账号
     * @param password  用户密码
     * @return
     */
    public static boolean addAccount(String username, String password) {
        //获取数据库连接
        Connection conn = DataBaseUtil.getConnection();
        // 编写sql语言
        String sql = "Insert into users(user_name,user_password) values(?,?)";
        //调用工具类DataBaseUtil中的synthesize方法，
        //将username、password添加到数据库users文件中
        DataBaseUtil.synthesize(conn, sql, username, password);
        return true;
    }

    public static boolean addAccounts(Connection conn,String username) {
        // 编写sql语言
        String sql = "Insert into users(user_name,user_password) values(?,?)";
        //调用工具类DataBaseUtil中的synthesize方法，
        //将username、password添加到数据库users文件中
        DataBaseUtil.synthesize(conn, sql, username, "123456");
        return true;
    }



    /**
     * 给定username、password
     * 修改当前用户的用户账号以及用户密码
     * 并且更新当前用户名
     *
     * @param username 用户账号
     * @param password 用户密码
     */
    public static void updateAccount(String username, String password) {
        //获取数据库连接
        Connection conn = DataBaseUtil.getConnection();
        // 编写sql语言
        String sql = "UPDATE users SET user_name=?, user_password=? WHERE user_name=?";
        //调用工具类DataBaseUtil中的synthesize方法，
        //将username、password更新到数据库users文件中
        DataBaseUtil.synthesize(conn, sql, username, password, loginController.localUserName);
        //将当前用户账号名更新
        loginController.localUserName = username;
    }
}
