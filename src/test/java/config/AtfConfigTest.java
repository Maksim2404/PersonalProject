package config;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class AtfConfigTest {

    @Test
    public void canReadConfig() {
        AtfConfig.init();
        String appUrl = AtfConfig.getAppUrl();

        Assertions.assertThat(appUrl).isNotNull();
    }
}
