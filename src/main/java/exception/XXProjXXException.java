package exception;


/**
 * @author Dable
 * @date 2015年10月13日
 */
public class XXProjXXException extends BaseException {

  private static final long serialVersionUID = 1L;

  public static XXProjXXException exception(MessageCode.MessageCodeEnum messageCodeEnum,
      String... args) {
    return new XXProjXXException(messageCodeEnum, args);
  }

  public static XXProjXXException exception(String errorCode) {
    return new XXProjXXException(errorCode);
  }

  public static XXProjXXException exception(Throwable throwable) {
    return new XXProjXXException(throwable);
  }

  public static XXProjXXException exception(String errorCode, String errorMessage) {
    return new XXProjXXException(errorCode, errorMessage);
  }

  public static XXProjXXException exception(MessageCode.MessageCodeEnum messageCodeEnum) {
    return new XXProjXXException(messageCodeEnum.getCode(), messageCodeEnum.getMessage());
  }

  public static XXProjXXException exception(String errorCode, String errorMessage,
      Throwable throwable) {
    return new XXProjXXException(errorCode, errorMessage, throwable);
  }

  public static XXProjXXException exception(MessageCode.MessageCodeEnum code, Throwable throwable) {
    return new XXProjXXException(code.getCode(), code.getMessage(), throwable);
  }

  /**
   * 默认构造
   */
  public XXProjXXException() {
    super();
  }

  /**
   * @param errorCode 自定义错误编码
   */
  public XXProjXXException(String errorCode) {
    super(errorCode);
  }


  /**
   * @param throwable 异常对象
   */
  public XXProjXXException(Throwable throwable) {
    super(throwable);
  }

  /**
   * @param errorCode 自定义错误编码
   * @param errorMessage 自定义错误信息
   */
  public XXProjXXException(String errorCode, String errorMessage) {
    super(errorCode, errorMessage);
  }

  /**
   * @param errorCode 自定义错误编码
   * @param errorMessage 自定义错误信息
   * @param throwable 异常对象
   */
  public XXProjXXException(String errorCode, String errorMessage, Throwable throwable) {
    super(errorCode, errorMessage, throwable);
  }

  /**
   * @param messageCodeEnum 自定义异常枚举
   * @param throwable 异常对象
   */
  public XXProjXXException(MessageCode.MessageCodeEnum messageCodeEnum, Throwable throwable) {
    super(messageCodeEnum.getCode(), messageCodeEnum.getMessage(), throwable);
  }

  /**
   * @param messageCodeEnum 自定义异常枚举
   */
  public XXProjXXException(MessageCode.MessageCodeEnum messageCodeEnum) {
    super(messageCodeEnum.getCode(), messageCodeEnum.getMessage());
  }

  /**
   * @param messageCodeEnum 自定义异常枚举
   */
  public XXProjXXException(MessageCode.MessageCodeEnum messageCodeEnum, String... args) {
    super(messageCodeEnum.getCode(), MessageCode.getMsg(messageCodeEnum.getCode(), args));
  }


  /**
   * @param message 无错误编码,自定义提示信息
   * @param throwable 异常对象
   */
  public XXProjXXException(String message, Throwable throwable) {
    super(message, throwable);
  }

}
