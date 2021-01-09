package Services;

import Dao.userDao;
import Entity.User;

/**
 * userServices : 给定信息实现了Controller包中各类需要对user的操作
 */
public class userServices {
    /**
     * 给定用户名称、用户密码
     * 判断登陆时账号密码是否符合要求
     *      1.用户账号必须是已经注册过的
     *      2.用户密码必须与之前的密码匹配
     * 如果满足，返回true，否则返回false。
     *
     * @param username      用户名称
     * @param passwordText  用户密码
     * @return boolean
     */
    public boolean judgeAccount(String username, String passwordText) {
        // 判断该用户名是否已经注册过
        User user = userDao.getUserByAccount(username);

        // 用户不存在
        if (user == null) {
            return false;
        }

        // 用户账号已注册，判断密码正确性
        //注意用user的password调用equals方法，因为已保存的数据中密码不可以为null
        boolean result = user.getPassword().equals(passwordText);

        return result;
    }

    /**
     * 给定username、password
     * 用于判断账号密码是否符合要求
     *      1. 用户名称、用户密码不可以为空
     * 如果满足，返回true，否则返回false。
     *
     * @param username 用户名称
     * @param password 用户密码
     * @return boolean
     */
    public static boolean judgeUsernamePassword(String username, String password) {
        //判断用户名称、用户密码是否为空
        if ( username == null || username.length() == 0 || password == null || password.length() == 0) {
            return false;
        } else {
           return  true;
        }
    }

    /**
     * 给定username、password
     * 用于更新当前用户的账号和密码信息
     *
     * @param username 用户名称
     * @param password 用户密码
     */
    public static void updateAccount(String username, String password) {
        //调用userDao类的updateAccount方法来更新当前用户的账号和密码信息
        userDao.updateAccount(username,password);
    }


    /**
     * 给定username、passwordText1、passwordText2
     * 用于判断注册新帐号时用户名称、用户密码、确认用户密码是否符合要求
     *      1. 用户名称、用户密码、确认用户密码不为空
     *      2. 用户密码、确认用户密码必须相匹配
     * 如果不符合要求，返回false；
     * 否则将该用户的信息添加到数据库users文件中，
     *      如果添加成功返回true，否则返回false；
     *
     * @param username 用户名称
     * @param passwordText1 用户密码
     * @param passwordText2 确认用户密码
     * @return
     */
    public static boolean judgeRegist(String username,String passwordText1,String passwordText2){
        //判断用户名是否为空
        if(username == null || username.length() ==0){
            return false;
        }

        //然后判断passwordText1是否为null,如果为null则不可以使用equals方法
        if(passwordText1 == null || passwordText1.length() ==0){
            return false;
            //判断用户密码、确认用户密码是否相匹配
        }else if(passwordText1.equals(passwordText2) == true){
            //账号密码输入正确，创建新用户，把账号密码插入到数据库中
            return userDao.addAccount(username,passwordText1);
        }else{
            return false;
        }
    }


}
