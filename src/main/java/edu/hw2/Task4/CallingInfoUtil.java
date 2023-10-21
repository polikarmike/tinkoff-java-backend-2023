package edu.hw2.Task4;

public class CallingInfoUtil {
    private CallingInfoUtil() {
    }

    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();

        StackTraceElement callingMethod = stackTrace[1];
        String className = callingMethod.getClassName();
        String methodName = callingMethod.getMethodName();

        return new CallingInfo(className, methodName);

    }
}
