package test;

import Controllers.MainPageController;
import Dao.userDao;
import Services.userServices;
import Util.ControllerUtil;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.junit.Test;

/**
 * 修改用户信息功能测试
 */

public class ChangeUserInfoTest {

    @Test
    public void test01(){
        String username = "fgy";
        String password = "123456";
        //String password = "";

        boolean result = userServices.judgeUsernamePassword(username,password);

        if(result){
            //修改成功
            userDao.updateAccount(username,password);
            System.out.println("成功");
        }else{

            System.out.println("失败");
        }
    }
}
