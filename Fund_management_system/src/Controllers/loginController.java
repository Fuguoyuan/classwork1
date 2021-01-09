package Controllers;

import Main.MainPage;
import Main.regist;
import Services.userServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * login的Conroller类 ：实现用户登录的功能
 */
public class loginController {
    //定义String类型的localUserName,加入用户登录成功,则用于记录该用户的账号名称
    public static String localUserName = null;

    @FXML
    private PasswordField password;

    @FXML
    private Button login_button;

    @FXML
    private Button register_button;

    @FXML
    private TextField account;

    /**
     * login_button按钮的触发事件。先判断给定的用户账号、密码是否符合要求：
     *          1. 用户账号、密码不可以为空
     *          2. 用户账号、密码必须是数据库users文件中已经注册过的用户
     * 如果符合要求，则进入主功能界面;
     * 如果不符合要求，则分情况弹出以下警告提示框：
     *          1. 用户账号为空 ："请输入你的账号!"
     *          2. 用户密码为空 ："请输入正确的账号密码！"
     *          3. 用户账号正确，但是密码和账号不匹配  ："账号密码不匹配！"
     * @param event
     */
    @FXML
    void toMainPage(ActionEvent event) {
        //定义username用于存储用户账号
        String username = account.getText();
        //定义passwordText用于存储密码
        String passwordText = password.getText();

        //判断账号是否为空
        if (username == null || username.length() == 0) {
            //用户账号为空 ：弹出警示框"请输入你的账号!"
            Alert alert = new Alert(Alert.AlertType.WARNING, "请输入你的账号!");
            alert.show();
        } else {
            //判断用户密码是否为空
            if (passwordText != null && passwordText.length() > 0) {
                //调用userServices类中的方法judgeAccount，根据用户账号来判断用户密码是否匹配
                userServices services = new userServices();
                boolean result = services.judgeAccount(username, passwordText);

                if (result) {
                    //用户账号密码均符合要求，则保存当前账号名
                    loginController.localUserName = username;

                    // 关闭登录窗口
                    Stage primaryStage = (Stage) login_button.getScene().getWindow();
                    primaryStage.close();

                    // 进入主功能界面
                    MainPage mainPage = new MainPage();
                    Stage stage = new Stage();
                    mainPage.start(stage);
                } else {
                    // 用户名和密码不匹配导致的失败
                    Alert alert = new Alert(Alert.AlertType.WARNING, "账号密码不匹配！");
                    alert.show();
                }
            } else {
                //用户密码为空导致的失败
                Alert alert = new Alert(Alert.AlertType.WARNING, "请输入正确的账号密码！");
                alert.show();
            }
        }
    }

    /**
     * register_button按钮触发的事件 ：用于创建新用户
     * 打开创建新用户的界面
     * @param event
     */
    @FXML
    void toRegistPage(ActionEvent event) {
        // 关闭登录窗口
        Stage primaryStage = (Stage) login_button.getScene().getWindow();
        primaryStage.close();

        //打开创建新用户界面
        regist res = new regist();
        Stage stage = new Stage();
        res.start(stage);
    }

    @FXML
    void initialize() {
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'login.fxml'.";
        assert login_button != null : "fx:id=\"login_button\" was not injected: check your FXML file 'login.fxml'.";
        assert register_button != null : "fx:id=\"register_button\" was not injected: check your FXML file 'login.fxml'.";
        assert account != null : "fx:id=\"account\" was not injected: check your FXML file 'login.fxml'.";

    }


}
