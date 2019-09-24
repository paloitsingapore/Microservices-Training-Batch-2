package com.ntuc.income.edgeservice;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ReflectionUtils;

public class RateLimitingFilter extends ZuulFilter {

  private final HttpStatus tooManyRequests = HttpStatus.TOO_MANY_REQUESTS;

  @Autowired
  private final RateLimiter rateLimiter;

  @Autowired
  public RateLimitingFilter(RateLimiter rateLimiter) {
    this.rateLimiter = rateLimiter;
  }


  @Override public String filterType() {
    return "pre";
  }

  @Override public int filterOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }

  @Override public boolean shouldFilter() {
    return true;
  }

  @Override public Object run() throws ZuulException {
    try {
      RequestContext currentContext = RequestContext.getCurrentContext();
      HttpServletResponse response = currentContext.getResponse();

      if (!rateLimiter.tryAcquire()) {
        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        response.setStatus(this.tooManyRequests.value());
        response.getWriter().append(this.tooManyRequests.getReasonPhrase());
        currentContext.setSendZuulResponse(false);
        throw new ZuulException(this.tooManyRequests.getReasonPhrase(),
            this.tooManyRequests.value(), this.tooManyRequests.getReasonPhrase());
      }
    } catch (Exception e){
      //  ReflectionUtils.rethrowRuntimeException(e);
      ReflectionUtils.rethrowRuntimeException(e);
    }

    return null;
  }
}
