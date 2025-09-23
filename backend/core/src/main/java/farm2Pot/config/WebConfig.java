package farm2Pot.config;

/**
 * packageName    : config
 * fileName       : CorsConfig
 * author         : Administrator
 * date           : 2025-09-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-09-20        Administrator       최초 생성
 */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${frontend.host}")
    private String frontendHost;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://" + frontendHost + ":5173")
                .allowedMethods("*")
                .allowCredentials(true); // 필요 시 true
    }
}
