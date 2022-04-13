package com.bjsxt.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 限流器
 */
@Component
public class RateLimitFilter extends ZuulFilter {

    //创建令牌桶
    //RateLimiter.create(1)1:是每秒生成令牌的数量
    //数值越大代表处理请求量月多，数值越小代表处理请求量越少
    private static final RateLimiter RATE_LIMIT = RateLimiter.create(1);


    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * 限流器的优先级应为最高
     * @return
     */
    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        //是否能从令牌桶中获取到令牌
        if(!RATE_LIMIT.tryAcquire()){
            RequestContext requestContext = RequestContext.getCurrentContext();
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(429);
            System.out.println("限流");
        }

        return null;
    }
}
