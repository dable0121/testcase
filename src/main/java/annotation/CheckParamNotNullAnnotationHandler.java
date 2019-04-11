package annotation;

import com.alibaba.fastjson.JSON;
import exception.MessageCode.MessageCodeEnum;
import exception.XXProjXXException;
import java.lang.reflect.Field;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CheckParamNotNullAnnotationHandler {

  /**
   * logger
   */
  private static final Logger logger = LoggerFactory.getLogger(CheckParamNotNullAnnotationHandler.class);

  @Before(value = "@annotation(annot)")
  public void checkAppKeyNotNull(JoinPoint joinPoint, CheckParamNotNull annot)
      throws Throwable {
    // 获取到使用了注解的方法的参数 这里面根据测试类 是 request,response
    Object[] args = joinPoint.getArgs();
    //获取使用了注解的方法的 注解的参数  这里面根据测试类 是 username,password
    Class clazz = annot.clazz();
    //循环参数
    for (int i = 0; i < args.length; i++) {
      if (clazz.isInstance(args[i])) {
        Field field = clazz.getDeclaredField("param");
        field.setAccessible(true);
        //获取字段的param值
        Object value = field.get(args[i]);
        //向下转型
        String param = (String) value;
        if (StringUtils.isBlank(param)) {
          XXProjXXException exception = XXProjXXException
              .exception(MessageCodeEnum.ORDER_APP_INFO_MISSING);
          logger.error(
              //StringBuilder线程不安全，但是效率比StringBuffer高,最好初始化大小，保证效率
              new StringBuilder(128).append("请求类[").append(joinPoint.getTarget().getClass().getName())
                  .append("]的方法[").append(joinPoint.getSignature().getName())
                  .append("]的参数[").append(JSON.toJSONString(args))
                  .append("],参数里面'appKeys'为空异常,做此查询功能时不能为空，为空表示没有权限查询，可以找管理员配置查询权限！").toString());
          throw exception;
        }
      }
    }
  }

}
