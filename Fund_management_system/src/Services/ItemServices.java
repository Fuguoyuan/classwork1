package Services;

import Controllers.loginController;
import Dao.ItemDao;
import Entity.Item;
import java.util.List;

/**
 *ItemServices : 给定信息实现了Controller包中各类需要对Item的操作
 */
public class ItemServices {

    /**
     * 调用ItemDao的getList方法 ：
     * 从数据库中读取数据，用以Item为元素的List集合保存并返回给调用者
     * @return
     */
    public static List<Item> getItemList() {
        return ItemDao.getList();
    }

    /**
     * 给定项目负责人、项目名称、项目资金
     * 用来判断项目负责人、项目名称、项目资金是否符合要求
     *      1.项目负责人、项目名称、项目资金不可以为空
     *      2.项目负责人必须是当前用户
     *  如果符合要求，返回true，否则返回false
     *
     * @param itemLeader 项目负责人
     * @param itemName  项目名称
     * @param itemFund  项目资金
     * @return boolean
     */
    public static boolean judgeItem(String itemLeader, String itemName, String itemFund) {
        //项目负责人、项目名称、项目资金不可以为空
        if (itemLeader == null || itemLeader.length() == 0 || itemName == null || itemName.length() == 0 || itemFund == null || itemFund.length() == 0) {
            return false;
        } else {
            //项目负责人必须是当前用户
            return (loginController.localUserName.equals(itemLeader));
        }
    }

    /**
     * 给定项目负责人、项目名称
     * 用来判断项目负责人、项目名称是否符合要求
     *      1.项目负责人、项目名称不可以为空
     *      2.项目负责人必须为当前用户
     * 如果不符合的话就返回false；
     * 如果符合要求则执行Dao.ItemDao中的judgeItemName方法用于删除该项目Item
     * 删除成功返回true，删除失败返回false
     *
     *
     * @param itemLeader 项目负责人
     * @param itemName   项目名称
     * @return boolean
     */
    public static boolean judgeItem(String itemLeader, String itemName) {
        //项目负责人必须为当前用户，且项目负责人不可以为空
        if (!itemLeader.equals(loginController.localUserName) || itemLeader == null || itemLeader.length() == 0) {
            return false;
        }
        //项目名称不可以为空
        if (itemName == null || itemName.length() == 0){
            return false;
        }
        //调用Dao.ItemDao中的judgeItemName方法用于删除该项目Item
        return Dao.ItemDao.judgeItemName(itemName);
    }

    /**
     * 给定itemLeader、itemName、item_Fund用于实现添加项目功能
     *
     * @param itemLeader 项目负责人
     * @param itemName  项目名称
     * @param item_Fund     项目资金
     * @return   boolean
     */
    public static boolean addItem(String itemLeader, String itemName, String item_Fund) {
        Integer itemFund = Integer.parseInt(item_Fund);
        //调用ItemDao类的addItem方法来添加项目
        //成功返回true，失败返回false
        return ItemDao.addItem(itemLeader, itemName, itemFund);
    }

    /**
     * 调用ItemDao的getItemCount方法 ：
     * 从数据库中读取数据，返回已有项目的数量
     *
     * @return Integer itemsCount
     */
    public static  Integer getItemCount(){
        //调用ItemDao的getItemCount方法返回数据库中已有项目的数量
        Integer itemsCount = ItemDao.getItemCount();

        return itemsCount;
    }

    /**
     * 调用ItemDao的getList方法 ：
     * 从数据库中读取数据，返回所有项目的资金总和
     *
     * @return Integer itemsFund
     */
    public static Integer getItemsFund(){
        //定义整形，用于保存项目资金和
        Integer itemsFund = 0;
        //调用ItemDao的getList方法读取数据库数据
        List<Item> list = ItemDao.getList();

        //循环遍历每一个元素，将其itemFund属性值相加保存至itemsFund
        for(int i=0;i<list.size();i++){
            Item item = list.get(i);
            itemsFund += item.getItemFund();
        }

        return itemsFund;
    }

    /**
     * 调用ItemDao的getList方法 ：
     * 从数据库中读取数据，返回所有项目中的最高资金
     *
     * @return
     */
    public static Integer getMaxItemsFund(){
        //定义整形，用于保存所有项目最高资金
        Integer itemsMaxFund = 0;
        //调用ItemDao的getList方法读取数据库数据
        List<Item> list = ItemDao.getList();

        //循环遍历每一个元素，比较所有元素的itemFund值将最大的保存至itemsFund
        for(int i=0;i<list.size();i++){
            Item item = list.get(i);
            if(item.getItemFund() > itemsMaxFund){
                itemsMaxFund = item.getItemFund();
            }
        }

        return itemsMaxFund;
    }

    /**
     * 调用ItemDao的getList方法 ：
     * 从数据库中读取数据，返回所有项目中的最低资金
     *
     * @return
     */
    public static Integer getMinItemsFund(){
        //定义整形，用于保存所有项目最高资金
        Integer itemsMinFund = 10000;
        //调用ItemDao的getList方法读取数据库数据
        List<Item> list = ItemDao.getList();

        //循环遍历每一个元素，比较所有元素的itemFund值将最小的保存至itemsFund
        for(int i=0;i<list.size();i++){
            Item item = list.get(i);
            if(item.getItemFund() < itemsMinFund){
                itemsMinFund = item.getItemFund();
            }
        }

        return itemsMinFund;
    }
}
