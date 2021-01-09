package test;

import Entity.Item;
import Util.DataBaseUtil;
import com.mysql.cj.jdbc.ConnectionImpl;
import org.junit.Test;

import javax.swing.text.html.ListView;
import java.sql.Connection;
import java.util.List;

/**
 * 查看所有项目信息功能测试
 */

public class ItemDemo {

    @Test
    public void test01(){
        Connection connection = DataBaseUtil.getConnection();

        String sql ="select item_id as id,item_name as itemName,item_leader as itemLeader,item_fund as itemFund from items";

        List list = DataBaseUtil.select(Item.class, connection , sql);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }


}
