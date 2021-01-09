package Controllers;

import Dao.userDao;
import Services.userServices;
import Util.ControllerUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * change_userinfo的Controller类 ：修改用户信息功能
 */
public class ChangeUserInfoController {
    @FXML
    private PasswordField change_password;

    @FXML
    private Button cancel_button;

    @FXML
    private Button confirm_button;

    @FXML
    private TextField change_username;

    /**
     * 确定按钮触发的事件。先判断给定的用户账号、密码是否符合要求：
     *      1. 用户账号、密码不可以为空
     * 如果符合要求，将该用户信息更新到数据库users文件中，并且更新主界面（将该账户名称更新在主界面中），同时
     *      弹出一个提示框，内容为"账号密码修改成功!"；
     * 如果不符合要求，则弹出警告提示框"账号密码修改失败!"。
     * @param event
     */
    @FXML
    void confirmChange(ActionEvent event) {
        //定义userName、password分别用于存储用户账号、密码
        String userName = change_username.getText();
        String password = change_password.getText();

        //调用userServices类中的judgeUsernamePassword方法用于判断当前用户信息是否符合要求
        //如果符合要求，就返回true，否则返回false。
        boolean result = userServices.judgeUsernamePassword(userName,password);

        if(result){
            //调用userServices的updateAccount方法用于更新当前用户信息至数据库users文件中
            userServices.updateAccount(userName,password);

            //更新主界面
            MainPageController mainPage = ControllerUtil.getmainPage();
            mainPage.initialize();

            //关闭修改用户信息窗口
            Stage primaryStage = (Stage) cancel_button.getScene().getWindow();
            primaryStage.close();

            //弹出信息窗口
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "账号密码修改成功！");
            alert.show();
        }else{
            //修改失败，弹出警示窗口
            Alert alert = new Alert(Alert.AlertType.WARNING, "账号密码修改失败！");
            alert.show();
        }

    }

    /**
     * 取消按钮触发的事件 ：取消申请修改用户信息
     * 直接关闭修改用户信息的窗口。
     * @param event
     */
    @FXML
    void cancelChange(ActionEvent event) {
        //关闭修改用户信息窗口
        Stage primaryStage = (Stage) cancel_button.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    void initialize() {
        assert change_password != null : "fx:id=\"change_password\" was not injected: check your FXML file 'change_userinfo.fxml'.";
        assert cancel_button != null : "fx:id=\"cancel_button\" was not injected: check your FXML file 'change_userinfo.fxml'.";
        assert confirm_button != null : "fx:id=\"confirm_button\" was not injected: check your FXML file 'change_userinfo.fxml'.";
        assert change_username != null : "fx:id=\"change_username\" was not injected: check your FXML file 'change_userinfo.fxml'.";

    }
}
