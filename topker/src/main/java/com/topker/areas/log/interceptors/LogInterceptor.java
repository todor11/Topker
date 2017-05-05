package com.topker.areas.log.interceptors;

import com.topker.areas.log.models.dtoModels.LogDto;
import com.topker.areas.log.services.LogService;
import com.topker.areas.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor implements HandlerInterceptor {

    private final LogService logService;

    @Autowired
    public LogInterceptor(LogService logService) {
        this.logService = logService;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        httpServletRequest.setAttribute("preHandleTime", System.currentTimeMillis());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        httpServletRequest.setAttribute("postHandleTime", System.currentTimeMillis());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {
        long currentTime = System.currentTimeMillis();
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (httpServletRequest.getAttribute("postHandleTime") == null) {
            return;
        }

        long preHandleTime = (long) httpServletRequest.getAttribute("preHandleTime");
        long postHandleTime = (long) httpServletRequest.getAttribute("postHandleTime");

        long actionExecution = postHandleTime - preHandleTime;
        long overallExecution = currentTime - preHandleTime;

        String ipAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = httpServletRequest.getRemoteAddr();
        }

        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String user = "анонимен";
        if (object != null && !object.equals("anonymousUser")) {
          User activeUser = (User) object;
          user = activeUser.getUsername();

        }
        String message = String.format("[User IP: %s][User: %s][%s - %s] Action Execute Time: %d ms, Overall Execute Time: %d ms",
                ipAddress,
                user,
                handlerMethod.getBean().getClass(),
                handlerMethod.getMethod().getName(),
                actionExecution,
                overallExecution);

        LogDto logDto = new LogDto(message);

        this.logService.create(logDto);
    }
}
