package annotation;

import org.springframework.stereotype.Service;

@Service
public class CheckMethodParamService {

  @CheckParamNotNull(clazz = ParamDTO.class)
  public void paramMethod(ParamDTO dto, String str, int i) {
    System.out.println("我进参数方法了，说明参数校验不为空通过了,param=" + dto.getParam());
  }

}
