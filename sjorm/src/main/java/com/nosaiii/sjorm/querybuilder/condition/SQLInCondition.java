package com.nosaiii.sjorm.querybuilder.condition;

import com.nosaiii.sjorm.utility.SQLUtility;

import java.util.Arrays;
import java.util.List;

public class SQLInCondition implements SQLCondition {
    private final String field;
    private final List<Object> list;

    public SQLInCondition(String field, List<Object> list) {
        this.field = field;
        this.list = list;
    }

    @Override
    public String build() {
        StringBuilder builder = new StringBuilder();
        builder.append(SQLUtility.quote(field)).append(" IN ");
        builder.append("(");

        String[] parameterCharacters = new String[list.size()];
        Arrays.fill(parameterCharacters, "?");
        builder.append(String.join(", ", parameterCharacters));

        builder.append(")");

        return builder.toString();
    }

    @Override
    public Object[] getObfuscatedValues() {
        return list.toArray(new Object[0]);
    }
}
