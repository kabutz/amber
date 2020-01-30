# Copyright (c) 2018, 2020, Oracle and/or its affiliates. All rights reserved.
# DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
#
# This code is free software; you can redistribute it and/or modify it
# under the terms of the GNU General Public License version 2 only, as
# published by the Free Software Foundation.
#
# This code is distributed in the hope that it will be useful, but WITHOUT
# ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
# FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
# version 2 for more details (a copy is included in the LICENSE file that
# accompanied this code).
#
# You should have received a copy of the GNU General Public License version
# 2 along with this work; if not, write to the Free Software Foundation,
# Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
#
# Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
# or visit www.oracle.com if you need additional information or have any
# questions.

# Generate the @test tags inside TestOptionsWithRanges.java
# @ignore
# This is not a test, but rather a script that generates actual tests.

echo "// --- start auto-generated"
echo "// the following portion is auto-generated by $0. Do not edit manually."

for i in {1..10}; do 
    cat <<EOF
/*
 * @test
 * @bug 8205633
 * @summary Test VM Options with ranges ($i of 10)
 * @library /test/lib /runtime/CommandLine/OptionsValidation/common
 * @modules java.base/jdk.internal.misc
 *          java.management
 *          jdk.attach/sun.tools.attach
 *          jdk.internal.jvmstat/sun.jvmstat.monitor
 * @run main/othervm/timeout=1800 TestOptionsWithRanges $i of 10
 */
EOF
done
echo "// --- end auto-generated"
