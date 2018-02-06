package com.zdd.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 获取客户端的真实IP地址
 * @author Administrator
 *
 */
public class GetRealIp {

	public static String getIp2(HttpServletRequest request) {
         String ip = request.getHeader("X-Forwarded-For");
         if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
             //多次反向代理后会有多个ip值，第一个ip才是真实ip
             int index = ip.indexOf(",");
             if(index != -1){
                 return ip.substring(0,index);
             }else{
                 return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
}
