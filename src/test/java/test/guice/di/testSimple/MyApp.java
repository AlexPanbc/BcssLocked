package test.guice.di.testSimple;

import com.google.inject.Inject;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/3/28
 * \* Time: 上午9:38
 * \* Description:
 * \
 */
public class MyApp implements Application {
    private UserService userService;
    private LogService logService;

    @Inject
    public MyApp(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    @Override
    public void work() {
        userService.process();
        logService.log("程序正常运行");
    }
}
