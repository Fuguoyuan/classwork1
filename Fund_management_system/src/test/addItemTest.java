package test;

import Controllers.loginController;
import Services.ItemServices;
import org.junit.Test;

/**
 * 添加项目功能测试
 */

public class addItemTest {

    @Test
    public void test01(){
        loginController.localUserName = "test";

        String itemLeader = "test";
        //String itemLeader = "test1";
        String itemName = "项目管理系统";
        String itemFund = "1000";
        //String itemFund = "";

        boolean result = ItemServices.judgeItem(itemLeader,itemName,itemFund);

        if(result){
            if(ItemServices.addItem(itemLeader,itemName,itemFund)){
                System.out.println("成功");
            }else{
                System.out.println("失败");
            }
        }else{
            System.out.println("失败");
        }

    }
}
