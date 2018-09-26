package com.wpisen.trace.agent.common.logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.wpisen.trace.agent.common.logger.jdkLog.JdkLogger;
import com.wpisen.trace.agent.common.logger.support.FailsafeLogger;

/**
 * 日志输出器工厂
 * 
 * @author sunli
 */
public class LoggerFactory {

	private LoggerFactory() {
	}
	
	private static final ConcurrentMap<String, FailsafeLogger> LOGGERS = new ConcurrentHashMap<String, FailsafeLogger>();

	// 查找常用的日志框架
	static {
        
	}

	/**
	 * 获取日志输出器
	 * 
	 * @param key
	 *            分类键
	 * @return 日志输出器, 后验条件: 不返回null.
	 */
	public static Logger getLogger(final Class<?> key) {
		FailsafeLogger logger = LOGGERS.get(key.getName());
		if (logger == null) {
			JdkLogger jdkLogger=new JdkLogger(java.util.logging.Logger.getLogger(key.getName()));
			LOGGERS.putIfAbsent(key.getName(), new FailsafeLogger(jdkLogger));
			logger = LOGGERS.get(key.getName());
		}
		return logger;
	}

	/**
	 * 获取日志输出器
	 * 
	 * @param key
	 *            分类键
	 * @return 日志输出器, 后验条件: 不返回null.
	 */
	public static Logger getLogger(String key) {
		FailsafeLogger logger = LOGGERS.get(key);
		if (logger == null) {
			JdkLogger jdkLogger=new JdkLogger(java.util.logging.Logger.getLogger(key));
			LOGGERS.putIfAbsent(key, new FailsafeLogger(jdkLogger));
			logger = LOGGERS.get(key);
		}
		return logger;
	}

	/**
	 * 动态设置输出日志级别
	 * 
	 * @param level
	 *            日志级别
	 *//*
	public static void setLevel(Level level) {
		LOGGER_ADAPTER.setLevel(level);
	}*/

	/**
	 * 获取日志级别
	 * 
	 * @return 日志级别
	 *//*
	public static Level getLevel() {
		return LOGGER_ADAPTER.getLevel();
	}*/

	/**
	 * 获取日志文件
	 * 
	 * @return 日志文件
	 *//*
	public static File getFile() {
		return LOGGER_ADAPTER.getFile();
	}*/

}
