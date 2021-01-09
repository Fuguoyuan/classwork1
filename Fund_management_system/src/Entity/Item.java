package Entity;

/**
 * 定义Item类 用于保存下项目信息：
 * 属性有id、itemName、itemLeader、itemFund分别代表项目编号、项目名称、项目负责人、项目资金
 * 并且定义了每个属性的get和set方法、重写了toString方法。
 */
public class Item {
    int id;
    String itemName;
    String itemLeader;
    int itemFund;

    public Item() {

    }

    public Item(int id, String itemName, String itemLeader, int itemFund) {
        this.id = id;
        this.itemName = itemName;
        this.itemLeader = itemLeader;
        this.itemFund = itemFund;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemLeader() {
        return itemLeader;
    }

    public void setItemLeader(String itemLeader) {
        this.itemLeader = itemLeader;
    }

    public int getItemFund() {
        return itemFund;
    }

    public void setItemFund(int itemFund) {
        this.itemFund = itemFund;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName=" + itemName +
                ", itemLeader=" + itemLeader +
                ", itemFund=" + itemFund +
                '}';
    }

}
