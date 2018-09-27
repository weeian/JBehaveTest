package base;

import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import steps.PutObjectStep;

/**
 * Created by root on 16-10-25.
 */

/**
 * 测试案例运行入口（对应到step文件，即实现场景代码的java类）
 */
public class BaseExecuteTest extends BaseAcceptanceTest {
    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(),
                new PutObjectStep()
        );
    }
}
