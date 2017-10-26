package lv.iljapavlovs.cucumber.constants;

public class Constants {
    public static final int WAIT_EXPLICIT_SEC = !(System.getProperty("wait.explicit.sec") == null) ? Integer.parseInt(System.getProperty("wait.explicit.sec")) : 3;
}