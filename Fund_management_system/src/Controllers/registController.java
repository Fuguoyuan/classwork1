package Controllers;

import Main.login;
import Services.userServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * regist的Controller类 ：用于实现创建新用户功能
 */
public class registController {
    @FXML
    private Button cancel_button;

    @FXML
    private Button confirm_button;

    @FXML
    private PasswordField password2;

    @FXML
    private PasswordField password1;

    @FXML
    private TextField account;

    /**
     * confirm_button按钮的触发事件 。先判断给定的用户账号、密码以及确认密码是否符合要求：
     *          1. 用户账号、密码、确认密码不可以为空
     *          2. 密码必须与确认密码一致
     * 如果符合要求，则进入登录界面，并且弹出信息框，内容为 : "新用户创建成功！";
     * 如果不符合要求，则分情况弹出以下警告提示框，内容为 : "创建失败！"：
     *
     * @param event
     */
    @FXML
    void toLoginPage(ActionEvent event) {
        //定义username用于存储用户名
        String username = account.getText();
        //定义passwordText1用于存储密码
        String passwordText1 = password1.getText();
        //定义passwordText2用于存储确认密码
        String passwordText2 = password2.getText();

        //判断创建的账号密码是否符合要求
        //如果符合要求的话，返回true，否则返回false。
        boolean result = userServices.judgeRegist(username,passwordText1,passwordText2);

        if(result){
            //已经创建好新账号，关闭注册窗口
            Stage primaryStage = (Stage) confirm_button.getScene().getWindow();
            primaryStage.close();

            //去往登陆界面
            login log_in = new login();
            Stage stage = new Stage();
            log_in.start(stage);

            //弹出信息框
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "新用户创建成功！");
            alert.show();
        }else{
            //密码输入错误
            Alert alert = new Alert(Alert.AlertType.WARNING, "创建失败！");
            alert.show();
        }
    }

    /**
     * cancel_button按钮的触发事件 ：取消本次创建新用户
     * 直接关闭申请项目的窗口，重新打开登陆界面，并且弹出信息框，内容为："没有创建新账户！"。
     * @param event
     */
    @FXML
    void toLoginPage1(ActionEvent event) {
        //已经创建好新账号
        // 关闭注册窗口
        Stage primaryStage = (Stage) confirm_button.getScene().getWindow();
        primaryStage.close();

        // 去往登陆界面
        login log_in = new login();
        Stage stage = new Stage();
        log_in.start(stage);

        //弹出信息框
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "没有创建新账户！");
        alert.show();
    }

    @FXML
    void initialize() {
        assert cancel_button != null : "fx:id=\"cancel_button\" was not injected: check your FXML file 'regist.fxml'.";
        assert confirm_button != null : "fx:id=\"confirm_button\" was not injected: check your FXML file 'regist.fxml'.";
        assert password2 != null : "fx:id=\"password2\" was not injected: check your FXML file 'regist.fxml'.";
        assert password1 != null : "fx:id=\"password1\" was not injected: check your FXML file 'regist.fxml'.";
        assert account != null : "fx:id=\"account\" was not injected: check your FXML file 'regist.fxml'.";

    }
}
