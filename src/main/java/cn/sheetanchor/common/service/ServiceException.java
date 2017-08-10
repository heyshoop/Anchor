package cn.sheetanchor.common.service;

/**
 * @Author: 阁楼麻雀
 * @Date: 2017/8/10 上午11:54
 * @Desc: Service层公用的Exception, 从由Spring管理事务的函数中抛出时会触发事务回滚
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
