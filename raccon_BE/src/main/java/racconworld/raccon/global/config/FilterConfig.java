package racconworld.raccon.global.config;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import racconworld.raccon.domain.log.service.LogService;
import racconworld.raccon.global.log.ApiLogFilter;

@Configuration
@RequiredArgsConstructor
public class FilterConfig {

    //Filter 부분에서 Data를 변경할 떄 Transaction안에 변경하는것이 좋기때문에
    //리포지토리가 아닌 service를 받아서 처리한다.
    private final LogService logService;
//    private final VisitService visitService;


    @Bean
    public FilterRegistrationBean<ApiLogFilter> apiLogFilter() {
        FilterRegistrationBean<ApiLogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApiLogFilter(logService));
        registrationBean.setOrder(1); // 필터를 적용할 URL 패턴
        return registrationBean;
    }
//
//    @Bean
//    public FilterRegistrationBean<VisitCountFilter> visitedCountFilter() {
//        FilterRegistrationBean<VisitCountFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new VisitCountFilter(visitService));
//        registrationBean.setOrder(2); // 필터를 적용할 URL 패턴
//        return registrationBean;
//    }

}
