package me.andpay.ti.daf.hibernate;

import org.springframework.core.io.Resource;

/**
 * Hbm配置类
 * @author sea.bao
 *
 */
public class HbmConfig {
	/**
	 * 模块名称
	 */
	private String module;
	
	/**
	 * 扫描的类包路径
	 */
	private String[] packagesToScan;
	
	/**
	 * 配置
	 */
	private Resource[] configLocations;

	public void setConfigLocation(Resource configLocation) {
		this.configLocations = new Resource[] { configLocation };
	}

	public Resource[] getConfigLocations() {
		return configLocations;
	}

	public void setConfigLocations(Resource[] configLocations) {
		this.configLocations = configLocations;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String[] getPackagesToScan() {
		return packagesToScan;
	}

	public void setPackagesToScan(String... packagesToScan) {
		this.packagesToScan = packagesToScan;
	}
}
