//package racconworld.raccon.global.log;
//
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import lombok.extern.slf4j.Slf4j;
//import racconworld.raccon.domain.visit.repository.VisitRepository;
//import racconworld.raccon.domain.visit.service.VisitService;
//
//import java.io.IOException;
//
//
//
//@Slf4j
//@WebFilter(filterName = "VisitCountFilter" , urlPatterns = "/quiz")
//public class VisitCountFilter implements Filter {
//
//    private final VisitService visitService;
//
//    public VisitCountFilter(VisitService visitService) {
//        this.visitService = visitService;
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//
//
//        visitService.incrementVisitCount();
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
