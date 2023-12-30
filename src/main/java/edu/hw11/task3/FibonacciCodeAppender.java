package edu.hw11.task3;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

public class FibonacciCodeAppender implements ByteCodeAppender {
    private static final String CLASS_NAME = "Fibonacci";
    private static final String METHOD_NAME = "fib";
    private static final String METHOD_DESCRIPTOR = "(I)I";
    private static final int MAX_STACK_SIZE = 3;

    @Override
    public Size apply(
        MethodVisitor mv,
        Implementation.Context context,
        MethodDescription methodDescription
    ) {
        Label loop = new Label();

        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitJumpInsn(Opcodes.IF_ICMPGT, loop);
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.IRETURN);

        mv.visitLabel(loop);
        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.ICONST_1);
        mv.visitInsn(Opcodes.ISUB);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, CLASS_NAME, METHOD_NAME, METHOD_DESCRIPTOR, false);
        mv.visitVarInsn(Opcodes.ILOAD, 0);
        mv.visitInsn(Opcodes.ICONST_2);
        mv.visitInsn(Opcodes.ISUB);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, CLASS_NAME, METHOD_NAME, METHOD_DESCRIPTOR, false);
        mv.visitInsn(Opcodes.IADD);
        mv.visitInsn(Opcodes.IRETURN);

        return new ByteCodeAppender.Size(MAX_STACK_SIZE, methodDescription.getStackSize());
    }
}
