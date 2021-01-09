package Controllers;

import Services.ItemServices;
import Util.ControllerUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * addItem的Controller类 ：添加项目功能
 */
public class addItem {
    @FXML
    private Button cancel_button;

    @FXML
    private Button confirm_button;

    @FXML
    private TextField item_leader;

    @FXML
    private TextField item_name;

    @FXML
    private TextField item_fund;

    /**
     * 确定按钮触发的事件。先判断给定的项目负责人、项目名称、项目资金是否符合要求：
     *          1. 项目负责人、项目名称、项目资金不可以为空
     *          2. 项目负责人必须是当前用户
     * 如果符合要求，将该项目信息添加到数据库items文件中，并且更新主界面（将该项目信息显示在主界面中），同时
     *      弹出一个提示框，内容为"项目申请成功!"；
     * 如果不符合要求，则弹出警告提示框"项目申请失败!"。
     *
     * @param event
     */

    @FXML
    void applyItem(ActionEvent event) {
        //定义itemLeader、itemName、itemFund分别用于存储项目负责人、项目名称、项目资金
        String itemLeader = item_leader.getText();
        String itemName = item_name.getText();
        String itemFund = item_fund.getText();

        //调用ItemServices类中的judgeItem方法用于判断当前项目信息是否符合要求
        //如果符合要求，就返回true，否则返回false。
        ItemServices itemServices = new ItemServices();
        boolean result = itemServices.judgeItem(itemLeader,itemName,itemFund);

        if(result){
            //调用itemServices的addItem方法用于添加当前项目信息至数据库items文件中
            itemServices.addItem(itemLeader,itemName,itemFund);

            //更新主界面
            MainPageController mainPage = ControllerUtil.getmainPage();
            mainPage.initialize();

            //弹出信息窗口
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "项目申请成功!");
            alert.show();
        }else{
            //弹出警示窗口
            Alert alert = new Alert(Alert.AlertType.WARNING, "项目申请失败!");
            alert.show();
        }

    }

    /**
     * 取消按钮触发的事件 ：取消申请本次项目
     * 直接关闭申请项目的窗口。
     * @param event
     */

    @FXML
    void cancelApplyItem(ActionEvent event) {
        //关闭申请项目的窗口。
        Stage primaryStage = (Stage) cancel_button.getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    void initialize() {
        assert cancel_button != null : "fx:id=\"cancel_button\" was not injected: check your FXML file 'addItem.fxml'.";
        assert confirm_button != null : "fx:id=\"confirm_button\" was not injected: check your FXML file 'addItem.fxml'.";
        assert item_leader != null : "fx:id=\"item_leader\" was not injected: check your FXML file 'addItem.fxml'.";
        assert item_name != null : "fx:id=\"item_name\" was not injected: check your FXML file 'addItem.fxml'.";
        assert item_fund != null : "fx:id=\"item_fund\" was not injected: check your FXML file 'addItem.fxml'.";
    }
}
