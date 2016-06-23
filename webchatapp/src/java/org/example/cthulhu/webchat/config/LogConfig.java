package org.example.cthulhu.webchat.config;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Log4jConfigurer;

/**
 *
 * @author Cthulhu
 */
@Configuration
public class LogConfig {

    @PostConstruct
    public void initLog4j() {
        try {
            Log4jConfigurer.initLogging(
                    "classpath:org/example/cthulhu/webchat/config/log4j.properties");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LogConfig.class.getName())
                    .log(Level.WARNING, null, ex);
        }
    }
}
