package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 의존관계 자동 주입
@Configuration
@ComponentScan(
//        basePackages = "hello.core.member",
//        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
// 프로젝트 최상단에 AppConfig 같은 메인 설정 정보를 두고, @ComponentScan이 붙은 곳이 루트의 기본 위치이다.
// 스프링부트를 쓰면 ComponentScan 자동으로 생성.
public class AutoAppConfig {

}
