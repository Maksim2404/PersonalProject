package config;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class AtfConfigTest {

    @Ignore
    @Test
    public void canReadConfigTest() {

        String appUrl = System.getenv("APP_URL");
        Assertions.assertThat(appUrl).isNotNull();
    }
}
