package test;

import Dao.ItemDao;
import Dao.userDao;
import Util.DataBaseUtil;
import com.sun.corba.se.impl.orbutil.CorbaResourceUtil;
import org.junit.Test;

import java.sql.Connection;


/**
 * 初始化数据库中items文件
 */

public class Default_Database {

    @Test
    public void setting_items() {
        Connection connection = DataBaseUtil.getConnection();

        for (int i = 0; i < 5000; i++) {
            StringBuilder itemName = new StringBuilder();
            StringBuilder itemLeader = new StringBuilder();
            StringBuilder itemFund = new StringBuilder();

            for (int j = 0; j < 3; j++) {
                itemName.append("赵钱孙李周吴郑王冯陈诸卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗费凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮齐康伍余元顾孟平黄下和穆萧尹姚邵何" .charAt((int) (Math.random()*100)));
            }

            switch (i%5){
                case 0: itemLeader.append("医疗设备管理");break;
                case 1: itemLeader.append("教育事业管理");break;
                case 2: itemLeader.append("交通设计管理");break;
                case 3: itemLeader.append("金融合资管理");break;
                case 4: itemLeader.append("体育比赛管理");break;

            }

            itemLeader.append("项目");

            itemFund.append((int)( Math.random()*10000));

            ItemDao.addItems(connection,itemLeader.toString(),itemName.toString(),Integer.parseInt(itemFund.toString()));

        }

        System.out.println(1);
    }


    @Test
    public void setting_users(){
        Connection conn = DataBaseUtil.getConnection();

        for (int i = 0; i < 9000; i++) {
            StringBuilder userName = new StringBuilder();

            for (int j = 0; j < 3; j++) {
                userName.append("赵钱孙李周吴郑王冯陈诸卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗费凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮齐康伍余元顾孟平黄下和穆萧尹姚邵何" .charAt((int) (Math.random()*100)));
            }

            userDao.addAccounts(conn,userName.toString());
    }

        System.out.println(1);

}
}

