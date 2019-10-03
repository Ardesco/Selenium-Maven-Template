package io.iljapavlovs.cucumber.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

@Getter
@Setter
public class TestDataContext {

    private static final TestDataContext INSTANCE = new TestDataContext();
    private Map<String, Supplier<String>> testDataMap = new HashMap<>();

    private TestDataContext() {
        this.testDataMap.put("RND3", () -> RandomStringUtils.random(3));
        this.testDataMap.put("ddMMyyyyHHmmss", () -> new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()));
    }

    public static TestDataContext getInstance() {
        return INSTANCE;
    }
}
