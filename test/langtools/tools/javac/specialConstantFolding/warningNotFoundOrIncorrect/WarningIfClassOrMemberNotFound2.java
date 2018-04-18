/*
 * @test /nodynamiccopyright/
 * @summary warn if a class or member is not found at compile time
 * @compile/fail/ref=WarningIfClassOrMemberNotFound2.out -XDdoConstantFold -Werror -XDrawDiagnostics WarningIfClassOrMemberNotFound2.java
 */

import java.lang.invoke.*;
import java.lang.invoke.constant.*;

public class WarningIfClassOrMemberNotFound2 {
    private static final ClassDesc THIS = ClassDesc.of("WarningIfClassOrMemberNotFound2");
    public int m(int i) { return i; }

    public void test() {
        MethodHandleDesc negIMethodRef = MethodHandleDesc.of(MethodHandleDesc.Kind.INTERFACE_VIRTUAL, THIS, "m", MethodTypeDesc.ofDescriptor("(I)I"));
        Intrinsics.ldc(negIMethodRef);
    }
}
