/*
// Licensed to Julian Hyde under one or more contributor license
// agreements. See the NOTICE file distributed with this work for
// additional information regarding copyright ownership.
//
// Julian Hyde licenses this file to you under the Apache License,
// Version 2.0 (the "License"); you may not use this file except in
// compliance with the License. You may obtain a copy of the License at:
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
*/
package net.hydromatic.optiq;

import net.hydromatic.linq4j.*;
import net.hydromatic.linq4j.expressions.FunctionExpression;
import net.hydromatic.linq4j.expressions.Types;
import net.hydromatic.linq4j.function.*;
import net.hydromatic.optiq.impl.java.ReflectiveSchema;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Builtin methods.
 *
 * @author jhyde
 */
public enum BuiltinMethod {
    QUERYABLE_SELECT(
        Queryable.class, "select", FunctionExpression.class),
    AS_QUERYABLE(
        Enumerable.class, "asQueryable"),
    GET_SUB_SCHEMA(
        DataContext.class, "getSubSchema", String.class),
    GET_TARGET(
        ReflectiveSchema.class, "getTarget"),
    SCHEMA_GET_TABLE(
        Schema.class, "getTable", String.class),
    JOIN(
        ExtendedEnumerable.class, "join", Enumerable.class, Function1.class,
        Function1.class, Function2.class),
    SELECT(
        ExtendedEnumerable.class, "select", Function1.class),
    SELECT2(
        ExtendedEnumerable.class, "select", Function2.class),
    WHERE(
        ExtendedEnumerable.class, "where", Predicate1.class),
    WHERE2(
        ExtendedEnumerable.class, "where", Predicate2.class),
    GROUP_BY(
        ExtendedEnumerable.class, "groupBy", Function1.class),
    ORDER_BY(
        ExtendedEnumerable.class, "orderBy", Function1.class, Comparator.class),
    UNION(
        ExtendedEnumerable.class, "union", Enumerable.class),
    CONCAT(
        ExtendedEnumerable.class, "concat", Enumerable.class),
    INTERSECT(
        ExtendedEnumerable.class, "intersect", Enumerable.class),
    EXCEPT(
        ExtendedEnumerable.class, "except", Enumerable.class),
    SINGLETON_ENUMERABLE(
        Linq4j.class, "singletonEnumerable", Object.class),
    ARRAY_COMPARER(
        Functions.class, "arrayComparer"),
    AS_ENUMERABLE(
        Linq4j.class, "asEnumerable", Object[].class),
    ENUMERATOR_CURRENT(
        Enumerator.class, "current"),
    ENUMERATOR_MOVE_NEXT(
        Enumerator.class, "moveNext"),
    ENUMERATOR_RESET(
        Enumerator.class, "reset"),
    ENUMERABLE_ENUMERATOR(
        Enumerable.class, "enumerator");

    public final Method method;

    private static final HashMap<Method, BuiltinMethod> MAP =
        new HashMap<Method, BuiltinMethod>();

    static {
        for (BuiltinMethod builtinMethod : BuiltinMethod.values()) {
            MAP.put(builtinMethod.method, builtinMethod);
        }
    }

    BuiltinMethod(Class clazz, String methodName, Class... argumentTypes) {
        this.method = Types.lookupMethod(clazz, methodName, argumentTypes);
    }

    public static BuiltinMethod lookup(Method method) {
        return MAP.get(method);
    }
}

// End BuiltinMethod.java
