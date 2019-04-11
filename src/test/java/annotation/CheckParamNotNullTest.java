package annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@ActiveProfiles("dev")
public class CheckParamNotNullTest {
  /** logger */
  private static final Logger logger = LoggerFactory.getLogger(CheckParamNotNullTest.class);

  @Autowired
  private CheckMethodParamService checkMethodParam;

  @Test
  public void check() {
    try {
      checkMethodParam.paramMethod(new ParamDTO(), "", 1);
    } catch (Exception e) {
     logger.error("检查错误成功");
    }
    ParamDTO paramDTO = new ParamDTO();
    paramDTO.setParam("aaaaa");
    checkMethodParam.paramMethod(paramDTO, "", 1);
  }
}
