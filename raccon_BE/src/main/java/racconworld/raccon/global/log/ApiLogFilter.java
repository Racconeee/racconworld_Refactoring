package racconworld.raccon.global.log;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import racconworld.raccon.domain.log.entity.Log;
import racconworld.raccon.domain.log.service.LogService;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

@Slf4j
@WebFilter(filterName = "ApiLogFilter" , urlPatterns = "/*")
public class ApiLogFilter implements Filter  {

    private final LogService logService;

    public ApiLogFilter(LogService logService) {
        this.logService = logService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//
//
//        String requestUrl = httpServletRequest.getRequestURI();
//        String logUuid = UUID.randomUUID().toString();
//
//        Log logEntity = Log.builder()
//                .requestUrl(requestUrl)
//                .logUuid(logUuid)
//                .build();
//
//        logService.saveLog(logEntity);
//
//        log.info("@@@@@@@@@@@@@@@@@");
//        log.info("request info URL : {} ", httpServletRequest.getRequestURI());
//
//
//
//        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
//        while (parameterNames.hasMoreElements()) {
//            String paramName = parameterNames.nextElement();
//            String paramValue = httpServletRequest.getParameter(paramName);
//            System.out.println(paramName + " = " + paramValue);
//            log.info("@@@@@@@@@@@@@@@@@");
//        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
