package com.msj.goods.common.utils;

import com.msj.goods.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import java.util.Collection;

/**
 * 类ShiroUtils的功能描述: Shiro工具类
 *
 * @auther hxy
 * @date 2017-08-25 16:19:35
 */
public class ShiroUtils {

	/**
	 * 加密算法
	 */
	public static final String algorithmName = "SHA-256";
	/**
	 * 加密散列次数
	 */
	public static final int hashIterations = 1;

	public static String encodeSalt(String password, String salt) {
		return new SimpleHash(algorithmName, password, salt, hashIterations).toString();
	}

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static void setSession() {
		SecurityUtils.getSubject().getSession().setTimeout(-10001);
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static SysUser getUserEntity() {
		return (SysUser) SecurityUtils.getSubject().getPrincipal();
		//		return  SecurityUtils.getSubject().getPrincipal();
	}

	//	public static EhcInOrg getEhcInOrg(){
	//		return (EhcInOrg)SecurityUtils.getSubject().getPrincipal();
	//	}
	//
	//	public static String getEhcInOrgId(){
	//		return getEhcInOrg().getId();
	//	}

	public static String getUserId() {
		return getUserEntity().getUserId() + "";
	}

	public static Integer getDeptId() {
		return getUserEntity().getDeptId();
	}

	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public static void logout() {
		SecurityUtils.getSubject().logout();
	}

	public static String getKaptcha(String key) {
		String kaptcha = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return kaptcha;
	}

	public static String getSessionAttr(String key) {
		String value = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return value;
	}

	public static void getAuthenticationInfo(String userName) {

		// 处理session
		SessionsSecurityManager securityManager =
				(SessionsSecurityManager) SecurityUtils.getSecurityManager();
		DefaultSessionManager sessionManager =
				(DefaultSessionManager) securityManager.getSessionManager();
		Collection<Session> sessions =
				sessionManager.getSessionDAO().getActiveSessions(); // 获取当前已登录的用户session列表
		for (Session session : sessions) {
			// 如果和当前session是同一个session，则不剔除
			String user =
					String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
			if (!SecurityUtils.getSubject().getSession().getId().equals(session.getId())) {
				if (StringUtils.isNotEmpty(user) && user.contains("loginName=" + userName)) {
					System.out.println(userName + "已登录，剔除中...");
					sessionManager.getSessionDAO().delete(session);
				}
			}
		}
	}
}
