package com.geekshow.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * 完成用户认证功能
 * @author Administrator
 *
 */
public class AuthenticationDemo {

	public static void main(String[] args) {
		
		//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//2、通过SecurityManager工厂获取SecurityManager的实例
		SecurityManager securityManager = factory.getInstance();
		//3、将SecurityManager对象设置到运行环境
		SecurityUtils.setSecurityManager(securityManager);
		//3、通过SecurityUtils获取主体Subject
		Subject subject = SecurityUtils.getSubject();
		//5、假如登录的用户名zhangsan和1111，这个地方的zhangsan和1111表示用户登录时输入的信息
		//而shiro.ini文件中的信息相当于数据库中存放的用户信息
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "112");
		//6、进行用户身份验证
		subject.login(token);
		try {
			
			//7、通过subject来判断用户是否通过验证
			if(subject.isAuthenticated()){
				System.out.println("用户登录成功");
			}
			
		} catch (UnknownAccountException e) {
			
			System.out.println("用户名或密码错误");

		}catch (IncorrectCredentialsException e) {
			
			System.out.println("用户名或密码错误");
		}
		
	}
}
