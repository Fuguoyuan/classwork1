package test;

import Controllers.MainPageController;
import Controllers.loginController;
import Services.ItemServices;
import Util.ControllerUtil;
import javafx.scene.control.Alert;
import org.junit.Test;

/**
 * 结算项目功能测试
 */

public class endItemTest {

    @Test
    public void test01(){
        loginController.localUserName = "test";

        String itemLeader = "test";
        //String itemLeader = "test1";
        String itemName = "100";
        //String itemName = "1";

        boolean result = ItemServices.judgeItem(itemLeader,itemName);

        if(result){
            System.out.println("成功");
        }else{
            System.out.println("失败");
        }
    }
}
