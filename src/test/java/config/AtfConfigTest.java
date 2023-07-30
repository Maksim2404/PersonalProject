package config;

import base.BaseTest;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AtfConfigTest {

    @Test
    public void canReadConfig() {
        AtfConfig.readConfig();
        String appUrl = AtfConfig.getAppUrl();

        Assertions.assertThat(appUrl).isNotNull();
    }
}
