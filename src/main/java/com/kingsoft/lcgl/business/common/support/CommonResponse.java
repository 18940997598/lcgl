package com.kingsoft.lcgl.business.common.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * 通用的应答
 * @author Liu Sixian <liusixian@kingsoft.com>
 */
public class CommonResponse extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	/**
	 * 默认构造方法
	 */
	public CommonResponse() {
		super();
	}

	/**
	 * 设置为成功应答
	 */
	public CommonResponse success() {
		this.put("success", true);
        return this;
	}

	/**
	 * 设置带指定消息的成功应答
	 * @param message 成功提示消息
	 */
	public CommonResponse success(String message) {
		this.put("success", true);
		this.put("message", message);
        return this;
	}

	/**
	 * 返回一个失败消息
	 * @param message 失败的提示消息
	 */
	public CommonResponse fail(String message) {
        return this.put("success", false).put("message", message);
	}


	/**
	 * 重定向地址
	 * @param url 要重定向到的URL
	 */
	public CommonResponse redirect(String url) {
        return this.put("redirect", url);
	}

	/**
	 * 向通用应答内设置一项数据对象
	 * @param data
	 */
	public CommonResponse setData(Object data) {
		Collection collection;
		if(!containsKey("data") || get("data") == null) {
			collection = new ArrayList();
			put("data", collection);
		} else {
			collection = (Collection) get("data");
		}
		collection.add(data);
        return this;
	}

	/**
	 * 向通用应答内设置一个集合对象
	 * @param collection
	 */
	public CommonResponse setData(Collection collection) {
		this.put("data", collection);
        return this;
	}

    /**
     * 通用应答对象设置一项key，value
     * @param key
     * @param value
     * @return
     */
    public CommonResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

	/**
	 * 快速创建一个成功应答对象
	 * @return
	 */
	public static CommonResponse createCommonResponse() {
		CommonResponse commonResponse = new CommonResponse();
		//commonResponse.success();
		return commonResponse;
	}

	/**
	 * 快速创建一个应答对象, 可传入一个数据对象, 并置为success
	 * @param data
	 * @return
	 */
	public static CommonResponse createCommonResponse(Object data) {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.success();
		commonResponse.setData(data);
		return commonResponse;
	}

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for(Entry<String, Object> entry : this.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }

        return sb.toString();
    }
}
