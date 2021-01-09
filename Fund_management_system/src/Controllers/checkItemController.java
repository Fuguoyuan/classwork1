package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * checkItemPage的Controller类 ：查看所有项目信息功能
 *
 */
public class checkItemController {
    @FXML
    private TextField min_item_fund;

    @FXML
    private TextField items_fund;

    @FXML
    private TextField items_count;

    @FXML
    private TextField mxa_item_fund;

    /**
     * 默认初始化查看项目信息界面
     * 定义了itemsCount、itemsFund、itemsMaxFund、itemsMinFund四个文本框分别用于显示 :
     * 总共有多少个项目、所有项目的资金总和、单个项目最高资金、单个项目最低资金。
     */
    @FXML
    void initialize() {
        assert min_item_fund != null : "fx:id=\"min_item_fund\" was not injected: check your FXML file 'checkItemPage.fxml'.";
        assert items_fund != null : "fx:id=\"items_fund\" was not injected: check your FXML file 'checkItemPage.fxml'.";
        assert items_count != null : "fx:id=\"items_count\" was not injected: check your FXML file 'checkItemPage.fxml'.";
        assert mxa_item_fund != null : "fx:id=\"mxa_item_fund\" was not injected: check your FXML file 'checkItemPage.fxml'.";

        //itemsCount记录总共有多少个项目
        Integer itemsCount = Services.ItemServices.getItemCount();
        items_count.setText(String.valueOf(itemsCount)+"个");

        //itemsFund记录了所有项目的资金总和
        Integer itemsFund = Services.ItemServices.getItemsFund();
        items_fund.setText(String.valueOf(itemsFund)+"元");

        //itemsMaxFund记录了单个项目最高资金
        Integer itemsMaxFund = Services.ItemServices.getMaxItemsFund();
        mxa_item_fund.setText(String.valueOf(itemsMaxFund)+"元");

        //itemsMinFund记录了单个项目最低资金
        Integer itemsMinFund = Services.ItemServices.getMinItemsFund();
        min_item_fund.setText(String.valueOf(itemsMinFund)+"元");
    }
}
