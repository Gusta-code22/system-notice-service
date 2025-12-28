package github.io.Gusta_code22.config;

import github.io.Gusta_code22.Enum.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@ConfigurationProperties("system-notice")
@RefreshScope
public class SystemNoticeConfig {

    private Level level;
    private String message;
    private String defaultValue;


    public SystemNoticeConfig() {
    }

    public Level level() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String message() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String defaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }


}
