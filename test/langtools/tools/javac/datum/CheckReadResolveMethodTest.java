/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * @test
 * @summary check that members of abtract datum has protected access
 * @modules jdk.jdeps/com.sun.tools.classfile
 *          jdk.compiler/com.sun.tools.javac.util
 * @compile CheckReadResolveMethodTest.java
 * @run main CheckReadResolveMethodTest
 */

import java.io.*;

import com.sun.tools.classfile.*;

public class CheckReadResolveMethodTest {

    // readResolve should be generated as the user is providing a constructor
    record Point1(int i, int j) implements Serializable {
        Point1(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    // no readResolve should be generated as the user is not providing a constructor
    record Point2(int i, int j) implements Serializable;

    public static void main(String args[]) throws Throwable {
        new CheckReadResolveMethodTest().run();
    }

    void run() throws Throwable {
        checkReadResolveMethod("Point1", true);
        checkReadResolveMethod("Point2", false);
    }

    void checkReadResolveMethod(String className, boolean shouldBeThere) throws Throwable {
        File testClasses = new File(System.getProperty("test.classes"));
        File file = new File(testClasses,
                CheckReadResolveMethodTest.class.getName() + "$" + className +".class");
        ClassFile classFile = ClassFile.read(file);
        boolean found = false;
        for (Method f : classFile.methods) {
            if (f.getName(classFile.constant_pool).equals("readResolve")) {
                found = true;
            }
        }
        if (found != shouldBeThere) {
            throw new AssertionError("incorrect generation of readResolve method at class " + className);
        }
    }
}
