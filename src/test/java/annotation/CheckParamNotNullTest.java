package annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@ActiveProfiles("dev")
public class CheckParamNotNullTest {

  @Autowired
  private CheckMethodParamService checkMethodParam;

  @Test
  public void check() {
    checkMethodParam.paramMethod(new ParamDTO(), "", 1);
    ParamDTO paramDTO = new ParamDTO();
    paramDTO.setParam("aaaaa");
    checkMethodParam.paramMethod(paramDTO, "", 1);
  }
}
