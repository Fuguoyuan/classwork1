package Dao;

import Entity.Item;
import Util.DataBaseUtil;

import java.sql.Connection;
import java.util.List;

/**
 * ItemDao : 给定信息实现了Services包中各类 使用工具类 对数据库进行Item相关的操作
 */

public class ItemDao {

    /**
     * 读取数据库中的信息将其保存在一个以Item为元素的elist集合中，并返回给调用者
     *
     * @return list
     */
    public static List<Item> getList() {
        //获取数据库连接
        Connection connection = DataBaseUtil.getConnection();
        //sql语言，数据库操作语言
        String sql = "select item_id as id,item_name as itemName,item_leader as itemLeader,item_fund as itemFund from items";
        //调用工具类DataBaseUtil中的select方法，获取以Item为元素的list集合
        List list = DataBaseUtil.select(Item.class, connection, sql);

        return list;
    }

    /**
     * 给定itemLeader、itemName、itemFund
     * 将该项目信息添加到数据库items文件中
     *
     * @param itemLeader 项目负责人
     * @param itemName 项目名称
     * @param itemFund 项目资金
     * @return  boolean
     */
    public static boolean addItem(String itemLeader, String itemName, Integer itemFund) {
        //获取数据库连接
        Connection connection = DataBaseUtil.getConnection();
        //sql语言，数据库操作语言
        String sql = "Insert into items(item_name ,item_leader ,item_fund ) values(?,?,?)";
        //调用工具类DataBaseUtil中的synthesize方法，
        // 将itemLeader、itemName、itemFund添加到数据库items文件中
        DataBaseUtil.synthesize(connection, sql, itemName, itemLeader, itemFund);
        return true;
    }

    public static boolean addItems(Connection connection,String itemLeader, String itemName, Integer itemFund) {
        //sql语言，数据库操作语言
        String sql = "Insert into items(item_name ,item_leader ,item_fund ) values(?,?,?)";
        //调用工具类DataBaseUtil中的synthesize方法，
        // 将itemLeader、itemName、itemFund添加到数据库items文件中
        DataBaseUtil.synthesize(connection, sql, itemName, itemLeader, itemFund);
        return true;
    }

    /**
     * 给定itemName
     * 先在数据库items文件中查找是否有名为itemName的项目，
     * 如果没有，返回false；
     * 如果存在，则在数据库items文件中删除名为itemName的项目，
     * 返回 true；
     *
     * @param itemName 项目名称
     * @return boolean
     */
    public static boolean judgeItemName(String itemName) {
        //获取数据库连接
        Connection connection = DataBaseUtil.getConnection();
        //sql语言，数据库操作语言
        String sql1 = "select item_id as id,item_name as itemName,item_leader as itemLeader,item_fund as itemFund from items where item_name =?";
        //调用工具类DataBaseUtil中的select方法，获取以Item为元素的list集合
        List<Item> list = DataBaseUtil.select(Item.class, connection, sql1, itemName);
        //判断是否名为itemName的项目
        if (list.size()==0){
            return false;
        }
        //sql语言，数据库操作语言
        String sql2 = "DELETE FROM items WHERE item_name =?";
        //调用工具类DataBaseUtil中的synthesize方法，
        //在数据库items文件中将名为itemName的项目删除
        DataBaseUtil.synthesize(connection, sql2, itemName);

        return true;
    }

    /**
     * 读取数据库信息，返回数据库中所有项目的数量
     *
     * @return Integer
     */
    public static Integer getItemCount(){
        //获取数据库连接
        Connection connection = DataBaseUtil.getConnection();
        //sql语言，数据库操作语言
        String sql1 = "select item_id as id,item_name as itemName,item_leader as itemLeader,item_fund as itemFund from items ";
        //调用工具类DataBaseUtil中的synthesize方法，
        //获取以Item为元素的list集合
        List<Item> list = DataBaseUtil.select(Item.class, connection, sql1);
        //返回集合的大小、即为项目的数量
        return list.size();
    }
}
