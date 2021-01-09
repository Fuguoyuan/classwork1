package test;

import Util.DataBaseUtil;
import org.junit.Test;

import java.sql.Connection;

/**
 * 数据库连接功能测试
 */

public class TestDemo {

    @Test
    public void test01(){
        Connection connection = DataBaseUtil.getConnection();
        System.out.println(connection);
    }
}
