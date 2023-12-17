package edu.hw11;

import edu.hw11.task2.ArithmeticUtils;
import edu.hw11.task2.ArithmeticUtilsInterceptor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    @DisplayName("Перехват метода класса")
    @Disabled
    public void testClassMethodInterception() throws Exception {
        ByteBuddyAgent.install();
        try {
            new ByteBuddy()
                .redefine(ArithmeticUtils.class)
                .method(named("sum"))
                .intercept(MethodDelegation.to(ArithmeticUtilsInterceptor.class))
                .make()
                .load(ArithmeticUtilsInterceptor.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
            assertEquals(ArithmeticUtils.sum(2,3), 6);
        } finally {
//            ClassReloadingStrategy.fromInstalledAgent().reset(ArithmeticUtils.class);
        }

//        assertEquals(ArithmeticUtils.sum(2,3), 5);
    }
}
