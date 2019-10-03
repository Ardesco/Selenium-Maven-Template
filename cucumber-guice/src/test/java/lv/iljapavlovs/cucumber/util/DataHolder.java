package lv.iljapavlovs.cucumber.util;

import cucumber.runtime.java.guice.ScenarioScoped;
import lombok.Getter;
import lombok.Setter;

@ScenarioScoped
@Getter
@Setter
public class DataHolder {

    private String sharedVariable;
}
