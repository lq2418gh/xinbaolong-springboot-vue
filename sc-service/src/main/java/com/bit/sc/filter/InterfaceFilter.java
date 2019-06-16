package com.bit.sc.filter;

import com.alibaba.fastjson.JSONObject;
import com.bit.base.exception.BusinessException;
import com.bit.base.vo.BaseVo;
import com.bit.base.vo.UserInfo;
import com.bit.common.ResultCode;
import com.bit.sc.common.Const;
import com.bit.sc.module.equipment.service.EquipmentService;
import com.bit.sc.module.sys.pojo.InterfacePermission;
import com.bit.sc.module.sys.pojo.RoleRelInterfacePermission;
import com.bit.sc.module.sys.service.InterfacePermissionService;
import com.bit.sc.module.sys.service.RoleRelInterfacePermissionService;
import com.bit.sc.module.user.service.UserService;
import com.bit.utils.CacheUtil;
import com.bit.utils.JSONUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * 接口地址过滤器
 *
 * @author liuyancheng
 * @create 2018-11-21 13:17
 */
//@WebFilter(urlPatterns = {"/*"}, filterName = "interfaceFilter")
public class InterfaceFilter implements Filter {

    private static Log logger = LogFactory.getLog(InterfaceFilter.class);

    private CacheUtil cacheUtil;
    private InterfacePermissionService interfacePermissionService;
    private RoleRelInterfacePermissionService roleRelInterfacePermissionService;

    private final String tokenName = "at";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("----------------接口地址过滤器初始化------------------------");
        ServletContext sc = filterConfig.getServletContext();
        WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(sc);
        if (cxt != null && cxt.getBean(CacheUtil.class) != null && cacheUtil == null) {
            cacheUtil = (CacheUtil) cxt.getBean(CacheUtil.class);
        }
        if (cxt != null && cxt.getBean(InterfacePermissionService.class) != null && interfacePermissionService == null) {
            interfacePermissionService = (InterfacePermissionService) cxt.getBean(InterfacePermissionService.class);
        }
        if (cxt != null && cxt.getBean(RoleRelInterfacePermissionService.class) != null && roleRelInterfacePermissionService == null) {
            roleRelInterfacePermissionService = (RoleRelInterfacePermissionService) cxt.getBean(RoleRelInterfacePermissionService.class);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("----------------接口地址进入------------------------");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //白名单不拦截
        if (isInclude(((HttpServletRequest) servletRequest).getRequestURI())) {
            try {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String token = "";
            //支持从header头中取值
            if (request.getHeader(tokenName) != null && !"".equals((request.getHeader(tokenName)))) {
                token = request.getHeader(tokenName);
            } else {
                //支持从url中获取
                token = request.getParameter(tokenName);
            }

            //1.获取当前登录人的角色
            String userInfo = (String) cacheUtil.get(token);
            UserInfo info = JSONObject.parseObject(userInfo, UserInfo.class);
            //登录人角色id
            List<Long> roles = info.getRoles();
            //2.查询角色拥有什么接口权限，并去重
            Set set = new HashSet();
            for (Long role : roles) {
                //2.1 根据roleid查询中间表中的权限id,set去重
                List<RoleRelInterfacePermission> list = roleRelInterfacePermissionService.findByRoleId(role);
                for (RoleRelInterfacePermission roleRelInterfacePermission : list) {
                    set.add(roleRelInterfacePermission.getPermissionId());
                }
            }
            //存放权限地址
            List<String> interfaceList = new ArrayList();
            //2.2 根据权限id查询权限路径
            for (Iterator it = set.iterator(); it.hasNext(); ) {
                InterfacePermission interfacePermission = interfacePermissionService.findById(Long.valueOf(it.next().toString()));
                interfaceList.add(interfacePermission.getUrl());
            }
            //获取当前请求地址
            String url = ((HttpServletRequest) servletRequest).getRequestURI();
            //3.拦截的请求对比权限，符合则通过，不符合则抛异常
            boolean flag = false;
            for (String interfaceAddress : interfaceList) {
                if (url.contains(interfaceAddress)) {
                    logger.info("地址匹配");
                    flag = true;
                    break;
                }
            }
            if (flag){
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

    @Override
    public void destroy() {
        logger.info("----------------接口地址过滤器销毁------------------------");
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