package com.zcmu.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import units.Common;
import units.JwtUtil;

import javax.servlet.http.HttpServletRequest;

@Component
public class WebFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 在请求前pre或者后post执行
     * 前置过滤器
     * pre ：可以在请求被路由之前调用
     * route ：在路由请求时候被调
     * post ：在route和error过滤器之后被调用
     * error ：处理请求时发生错误时被调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的优先级
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否执行该过滤器，此处为true，说明需要过滤
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤的逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        //向header中添加权限令牌
        HttpServletRequest request = requestContext.getRequest();
        System.out.println("jinguoguolvqi");
        if(request.getMethod().equals("OPTIONS")){
            return null;
        }
        if(request.getRequestURL().indexOf("login")>0){
            return null;
        }
        //得到请求头对象
        String authorization = request.getHeader("Authorization");
        //如果不等于空向后转发
        if(!Common.isEmpty(authorization)){
                if(authorization.startsWith("Bearer ")){
                String token = authorization.substring(7);
                try{
                    Claims claims = jwtUtil.parseJWT(token);
                    requestContext.addZuulRequestHeader("Authorization",authorization);
                    return null;
                }catch (Exception e){
                    e.printStackTrace();
                    //终止运行，并且不往下转发
                    requestContext.setSendZuulResponse(false);
                }

            }
        }
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(403);
        requestContext.setResponseBody("权限不足");
        requestContext.getResponse().setContentType("text/html;charset=UTF‐8");
        return null;
    }
}
