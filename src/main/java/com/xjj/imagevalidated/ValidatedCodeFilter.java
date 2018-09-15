package com.xjj.imagevalidated;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.util.ValidationEventCollector;
import java.io.IOException;

/**
 * Created by jie on 2018/9/12.
 * 验证码的校验逻辑类
 */
public class ValidatedCodeFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if(StringUtils.equals("/userLogin",httpServletRequest.getRequestURI())
                &&StringUtils.equalsIgnoreCase(httpServletRequest.getMethod(),"post")){
            try{
                validate(new ServletWebRequest(httpServletRequest));
            }catch (ValidatedCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
                return;
            }
        }
            //直接让他执行下一步
            filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException, ValidatedCodeException {

        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request,ValidatedCodeController.SESSION_KEY);
        logger.info("会话中的验证码："+codeInSession.getCode());

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"imageCode");

        logger.info("请求中的验证码："+codeInRequest);

        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidatedCodeException("验证码不能为空");
        }

        if(codeInSession==null){
            throw new ValidatedCodeException("验证码不存在");
        }

        if(codeInSession.isExpired()){
            sessionStrategy.removeAttribute(request,ValidatedCodeController.SESSION_KEY);
            throw new ValidatedCodeException("验证码过期");
        }
        if(!StringUtils.equals(codeInSession.getCode(),codeInRequest)){
            throw new ValidatedCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request, ValidatedCodeController.SESSION_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
