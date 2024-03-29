package controlefinanceiro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class ControleFinanceiroConfig implements WebMvcConfigurer {
	
	private static final String AUTHORIZATION_KEY = "Authorization";
	
	@Bean
	public OpenAPI openApi() {
		
		SecurityRequirement securityItem = new SecurityRequirement();
		securityItem.addList(AUTHORIZATION_KEY);
		
		Components components = new Components();
		components.addSecuritySchemes(AUTHORIZATION_KEY, new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"));
		
		return new OpenAPI()
				.components(components)
				.addSecurityItem(securityItem)
				.info(
						new Info()
						.title("Controle Financeiro")
						.contact(new Contact().name("controle-financeiro")).version("1.0.0"));
	}

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedHeaders("*")
            .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }
    
     @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/resources/**").addResourceLocations("/resources/"); 
         registry.addResourceHandler("/api/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
		 registry.addResourceHandler("/api/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		 registry.addResourceHandler("/api/**").addResourceLocations("/api/**");
     }
     
     @Override
     public void addViewControllers(ViewControllerRegistry registry) {
         registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
         registry.addRedirectViewController("/api/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
         registry.addRedirectViewController("/api/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
         registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
     }
     
}