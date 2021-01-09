package Controllers;

import java.util.List;

import Entity.Item;
import Main.*;
import Main.addItem;
import Main.endItem;
import Services.ItemServices;
import Util.ControllerUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * MainPage的Controller类 ：主功能界面控制
 */

public class MainPageController {
    @FXML
    private Label localUserName;

    @FXML
    private TableView<Item> itemInfo;

    @FXML
    private TableColumn<Item, Integer> id;

    @FXML
    private TableColumn<Item, String> itemLeader;

    @FXML
    private TableColumn<Item, Integer> itemFund;

    @FXML
    private TableColumn<Item, String> itemName;

    @FXML
    private Button apply_item_button;

    @FXML
    private Button end_item_button;

    @FXML
    private Button change_info_button;

    @FXML
    private Button item_info_button;

    //
    ObservableList<Item> itemslist = FXCollections.observableArrayList();

    /**
     * change_info_button按钮的触发事件 ：用于打开更改用户信息界面
     * @param event
     */
    @FXML
    void changeUserInfo(ActionEvent event) {
        //打开更改用户信息界面
        Main.ChangeUserInfo change_userinfo_Page = new ChangeUserInfo();
        Stage stage = new Stage();
        change_userinfo_Page.start(stage);
    }

    /**
     * apply_item_button按钮的触发事件 ：用于打开添加项目界面
     * @param event
     */
    @FXML
    void toAddItemPage(ActionEvent event) {
        //打开添加项目界面
        Main.addItem to_addItem_Page = new addItem();
        Stage stage = new Stage();
        to_addItem_Page.start(stage);
    }

    /**
     * end_item_button按钮的触发事件 ：用于打开结算项目界面
     * @param event
     */
    @FXML
    void toEndItemPage(ActionEvent event) {
        //打开结算项目界面
        Main.endItem to_endItem_Page = new endItem();
        Stage stage = new Stage();
        to_endItem_Page.start(stage);
    }

    /**
     * item_info_button按钮的触发事件 ：用于打开查看项目界面
     * @param event
     */
    @FXML
    void toCheckItemPage(ActionEvent event) {
        //打开查看项目界面
        Main.checkItem to_checkItem_Page = new checkItem();
        Stage stage = new Stage();
        to_checkItem_Page.start(stage);
    }

    @FXML
    void initialize() {
        assert itemLeader != null : "fx:id=\"itemLeader\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert itemFund != null : "fx:id=\"itemFund\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert itemName != null : "fx:id=\"itemName\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert apply_item_button != null : "fx:id=\"apply_item_button\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert end_item_button != null : "fx:id=\"end_item_button\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert itemInfo != null : "fx:id=\"itemInfo\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert change_info_button != null : "fx:id=\"change_info_button\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert item_info_button != null : "fx:id=\"item_info_button\" was not injected: check your FXML file 'MainPage.fxml'.";

        //localUserName是一个Label，赋值为当前用户名称
        localUserName.setText(loginController.localUserName);

        //定义了一个TableView<Item>，将每一个TableColumn与对应的item的属性建立联系
        id.setCellValueFactory(new PropertyValueFactory("id"));
        itemName.setCellValueFactory(new PropertyValueFactory("itemName"));//映射
        itemLeader.setCellValueFactory(new PropertyValueFactory("itemLeader"));
        itemFund.setCellValueFactory(new PropertyValueFactory("itemFund"));
        itemInfo.setItems(itemslist);

        //调用ItemServices的getItemList方法，将数据库items文件中的数据保存至一个以Item为元素的list集合中
        List<Item> list = ItemServices.getItemList();

        //先清楚itemslist中原本的数据，在将跟新后的list赋值到itemslist中
        //以达到更新界面的效果
        itemslist.clear();
        itemslist.addAll(list);

        //每一次保存当前主界面对象
        ControllerUtil.setmainPage(this);
    }
}
