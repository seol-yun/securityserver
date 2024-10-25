//package fitness.securityserver.service;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Enumeration;
//import java.util.regex.Pattern;
//
///**
// * SQL Injection 패턴을 감지하고 차단하는 필터
// */
//@Component
//public class SQLInjectionFilter extends OncePerRequestFilter {
//
//    // 구체적인 SQL Injection 탐지 패턴 (정규식으로 작성)
//    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile(
//            "(?i)(union\\s+select|select\\s+.*\\s+from|or\\s+1=1|and\\s+1=1|--|;|insert\\s+into|drop\\s+table|delete\\s+from|update\\s+.*\\s+set)",
//            Pattern.CASE_INSENSITIVE);
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//        // 요청 파라미터와 헤더를 검사
//        if (hasSqlInjection(request)) {
//            // SQL Injection이 감지된 경우 응답 차단
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.getWriter().write("Request blocked due to SQL injection attempt");
//            return;
//        }
//
//        // SQL Injection이 감지되지 않으면 다음 필터로 요청 전달
//        filterChain.doFilter(request, response);
//    }
//
//    // SQL Injection을 감지하는 메서드
//    private boolean hasSqlInjection(HttpServletRequest request) {
//        // 특정 파라미터 예외 처리 (id와 password는 SQL Injection 검사 제외)
//        for (String param : request.getParameterMap().keySet()) {
//            if ("id".equals(param) || "password".equals(param)) {
//                continue; // ID 및 비밀번호는 검사 제외
//            }
//            String value = request.getParameter(param);
//            if (value != null && SQL_INJECTION_PATTERN.matcher(value).find()) {
//                return true;
//            }
//        }
//
//        // 요청 헤더 검사 (Enumeration을 사용하여 헤더 이름 수동 탐색)
//        Enumeration<String> headerNames = request.getHeaderNames();
//        if (headerNames != null) {
//            while (headerNames.hasMoreElements()) {
//                String headerName = headerNames.nextElement();
//                String headerValue = request.getHeader(headerName);
//                if (headerValue != null && SQL_INJECTION_PATTERN.matcher(headerValue).find()) {
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }
//}
