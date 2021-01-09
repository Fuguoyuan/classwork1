package Util;

import Controllers.MainPageController;
import sun.applet.Main;

import java.util.Set;

public class ControllerUtil {

    static MainPageController mainPage = null;

    public static void  setmainPage(MainPageController Page){
        mainPage=Page;
    }

    public static MainPageController getmainPage(){
        return mainPage;
    }
}
