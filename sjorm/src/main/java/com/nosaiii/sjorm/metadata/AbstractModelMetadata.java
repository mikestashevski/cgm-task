package com.nosaiii.sjorm.metadata;

import com.nosaiii.sjorm.Model;
import com.nosaiii.sjorm.SJORM;
import com.nosaiii.sjorm.annotations.SJORMTable;

public abstract class AbstractModelMetadata {
    private final Class<? extends Model> type;
    private final String table;
    private final String[] primaryKeyFields;

    public AbstractModelMetadata(Class<? extends Model> type) {
        this.type = type;
        this.table = type.getAnnotation(SJORMTable.class).tableName();
        primaryKeyFields = SJORM.getInstance().getSJORMConnection().getPrimaryKeys(table);
    }

    /**
     * The type the model this metadata is associated with
     *
     * @return The type the model this metadata is associated with
     */
    public Class<? extends Model> getType() {
        return type;
    }

    /**
     * The table name as it is described in the database
     *
     * @return The table name as it is described in the database
     */
    public String getTable() {
        return table;
    }

    /**
     * The column name(s) of the primary key of the table in the database
     *
     * @return The column name(s) of the primary key of the table in the database
     */
    public String[] getPrimaryKeyFields() {
        return primaryKeyFields;
    }
}