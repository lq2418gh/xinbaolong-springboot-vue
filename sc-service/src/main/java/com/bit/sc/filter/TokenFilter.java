package com.bit.sc.filter;

import com.alibaba.fastjson.JSONObject;
import com.bit.base.exception.BusinessException;
import com.bit.base.vo.BaseVo;

import com.bit.base.vo.UserInfo;
import com.bit.common.ResultCode;
import com.bit.sc.common.Const;
import com.bit.sc.module.sys.pojo.InterfacePermission;
import com.bit.sc.module.sys.service.InterfacePermissionService;
import com.bit.utils.CacheUtil;
import com.bit.utils.JSONUtil;
import com.bit.utils.thread.RequestThreadBinder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import sun.misc.Request;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


import static com.bit.sc.common.Const.TOKEN_EXPIRE_SECONDS;


@WebFilter(urlPatterns = {"/*"}, filterName = "tokenAuthorFilter")
public class TokenFilter implements Filter {

    private static Log logger = LogFactory.getLog(TokenFilter.class);

    @Autowired
    private CacheUtil cacheUtil;


    private final String tokenName = "at";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        logger.info("----------------过滤器初始化------------------------");
        ServletContext sc = filterConfig.getServletContext();
        WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(sc);
        if (cxt != null && cxt.getBean(CacheUtil.class) != null && cacheUtil == null) {
            cacheUtil = (CacheUtil) cxt.getBean(CacheUtil.class);
        }
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods",
                "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Content-Type, x-requested-with, X-Custom-Header,at");
        if (isInclude(((HttpServletRequest) servletRequest).getRequestURI())) {//白名单不拦截
            try {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String token = "";
            if (request.getHeader(tokenName) != null && !"".equals((request.getHeader(tokenName)))) {//支持从header头中取值
                token = request.getHeader(tokenName);
            } else {
                token = request.getParameter(tokenName);//支持从url中获取
            }
            if (token == null || "".equals(token)) {//判断是否为空
                BaseVo baseVo = new BaseVo();
                baseVo.setCode(ResultCode.UNAUTH.getCode());
                baseVo.setMsg(ResultCode.UNAUTH.getInfo());
                responseOutWithJson(response, baseVo);
                return;
            } else {
                String userInfo = (String) cacheUtil.get(token);
                UserInfo info = JSONObject.parseObject(userInfo, UserInfo.class);
                //JsonResult rs = tokenFeign.verify(token);
                if (userInfo != null) {
                    long l = cacheUtil.getExpire(token);
                    if (l < 300) {
                        // 更新过期时间
                        cacheUtil.expire(token, TOKEN_EXPIRE_SECONDS);
                    }

                    RequestThreadBinder.bindUser(userInfo);
                    filterChain.doFilter(servletRequest, servletResponse);
                    return;
                } else {
                    BaseVo baseVo = new BaseVo();
                    baseVo.setCode(ResultCode.UNAUTH.getCode());
                    baseVo.setMsg(ResultCode.UNAUTH.getInfo());
                    responseOutWithJson(response, baseVo);
                    return;
                }
            }
        }


    }

    @Override
    public void destroy() {
        logger.info("--------------过滤器销毁--------------");
    }


    /**
     * 是否需要过滤
     *
     * @param url
     * @return
     */
    private boolean isInclude(String url) {
        for (String pattern : Const.INTERFACE_ADDRESS_CONSTANT) {
            if (url.indexOf(pattern) != -1) {
                return true;
            }
        }
        return false;
    }



	/**
	 * 以JSON格式输出
	 *
	 * @param response
	 */
	protected void responseOutWithJson(HttpServletResponse response,
	                                   Object responseObject) {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(JSONUtil.toJSONString(responseObject));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
