package edu.hw2;

import edu.hw2.Task4.CallingInfo;
import edu.hw2.Task4.CallingInfoUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Task4Test {
    static class TestCaller {
        public CallingInfo callMethodA() {
            return CallingInfoUtil.callingInfo();
        }
    }

    @Test
    @DisplayName("Проверка вызова метода callMethodA в классе TestCaller")
    void testCallingInfoMethod() {
        // given
        TestCaller caller = new TestCaller();

        // when
        CallingInfo callInfo = caller.callMethodA();

        // then
        CallingInfo expectedInfo = new CallingInfo("edu.hw2.Task4Test$TestCaller", "callMethodA");
        assertThat(callInfo).isEqualTo(expectedInfo);
    }

    @Test
    @DisplayName("Проверка CallingInfoUtil.callingInfo() с активным стеком вызовов")
    void testCallingInfo() {
        // when
        CallingInfo callingInfo = CallingInfoUtil.callingInfo();

        // then
        assertNotNull(callingInfo);
        assertNotNull(callingInfo.className());
        assertNotNull(callingInfo.methodName());
        assertNotEquals("UnknownClass", callingInfo.className());
        assertNotEquals("unknownMethod", callingInfo.methodName());
    }

}
