package com.cgm.task.model;

import com.nosaiii.sjorm.Model;
import com.nosaiii.sjorm.annotations.SJORMTable;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;

@SJORMTable(tableName = "doctors")
@NoArgsConstructor
public class Doctor extends Model {

    public Doctor(ResultSet rs) {
        super(rs);
    }
}
