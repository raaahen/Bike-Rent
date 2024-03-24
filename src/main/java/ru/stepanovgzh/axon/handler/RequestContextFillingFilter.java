package ru.stepanovgzh.axon.handler;

import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Service
public class RequestContextFillingFilter extends OncePerRequestFilter
{
    public static final String ACCOUNT_ID = "accountId";

    private final RequestContext requestContext;

    public RequestContextFillingFilter(RequestContext requestContext)
    {
        this.requestContext = requestContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {
        try
        {
            Optional.ofNullable(request.getHeader(ACCOUNT_ID)).ifPresent(
                    requestContext::setAccountId
            );
        }
        finally
        {
            filterChain.doFilter(request, response);
        }
    }

    public static class RequestContext
    {
        private String accountId;

        public String getAccountId()
        {
            return accountId;
        }

        public void setAccountId(String accountId)
        {
            this.accountId = accountId;
        }
    }
}
