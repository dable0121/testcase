package exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author Dable
 * @date 2015年10月13日 基础异常处理类 errorCode,errorMSg参考异常信息配置 error_message.properties
 *       静态解析类 MessageCode
 */
public class BaseException extends RuntimeException {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * 自定义错误编码
     */
    protected String errorCode =null;
    /**
     * 自定义错误信息
     */
    protected String errorMsg =null;
    /**
     * 异常对象
     */
    protected Throwable cause = null;

    /**
     * 默认构造
     */
    public BaseException() {
        super();
    }

    /**
     * @param cause
     *            异常对象
     */
    public BaseException(Throwable cause) {
        super(cause);
        this.cause = cause;
    }

    /**
     * @param errCode
     *            自定义错误编码
     */
    public BaseException(String errCode) {
        super(errCode);
        String em = MessageCode.getMsg(errCode);
        if (em == null) {
            this.errorCode = errCode;
            this.errorMsg = "异常";
        } else {
            this.errorCode = errCode;
            this.errorMsg = em;
        }
    }

    /**
     * @param errCode
     *            自定义 错误编码
     * @param msg
     *            自定义错误信息
     */
    public BaseException(String errCode, String msg) {
        super("errCode:" + errCode + ",errMsg:" + msg);
        this.errorCode = errCode;
        this.errorMsg = msg;
    }

    /**
     * @param errCode
     *            自定义 错误 信息
     * @param throwable
     *            异常对象
     */
    public BaseException(String errCode, Throwable throwable) {
        String em = MessageCode.getMsg(errCode);
        if (em == null) {
            em = "异常";
        }
        this.errorCode = errCode;
        this.errorMsg = em;
        this.cause = throwable;
    }

    /**
     * @param errCode
     *            自定义 错误编码
     * @param message
     *            自定义 错误 信息
     * @param cause
     *            异常对象
     */
    public BaseException(String errCode, String message, Throwable cause) {
        this(errCode, message);
        this.cause = cause;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @return the errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
     */
    @Override
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
     */
    @Override
    public void printStackTrace(PrintWriter w) {
        super.printStackTrace(w);
    }

}
