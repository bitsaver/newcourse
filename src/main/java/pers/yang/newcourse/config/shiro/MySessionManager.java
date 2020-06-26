package pers.yang.newcourse.config.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class MySessionManager extends DefaultWebSessionManager {
    @Override
    protected void onStart(Session session, SessionContext context) {
        super.onStart(session, context);
    }

    /**
     * 定义的请求头中使用的标记key，用来传递 token
     */
    private static final String TOKEN = "Token";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";



    public MySessionManager() {
        super();
        //设置 shiro session 有效时间 为60分钟
        setGlobalSessionTimeout(MILLIS_PER_MINUTE * 60);
    }


    /**
     * 获取sessionId，原本是根据sessionKey来获取一个sessionId
     * 重写的部分多了一个把获取到的token设置到request的部分。这是因为app调用登陆接口的时候，是没有token的，登陆成功后，产生了token,我们把它放到request中，返回结
     * 果给客户端的时候，把它从request中取出来，并且传递给客户端，客户端每次带着这个token过来，就相当于是浏览器的cookie的作用，也就能维护会话了
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //获取请求头中的 TOKEN 的值，如果请求头中有 TOKEN 则其值为sessionId。shiro就是通过sessionId 来控制的
        String sessionId = WebUtils.toHttp(request).getHeader(TOKEN);

        System.out.println("+++++++++"+sessionId);
        if (StringUtils.isEmpty(sessionId)){
            //如果没有携带id参数则按照父类的方式在cookie进行获取sessionId
            return super.getSessionId(request, response);

        } else {
            //请求头中如果有 TOKEN, 则其值为sessionId
//            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionId;
        }
    }
}
