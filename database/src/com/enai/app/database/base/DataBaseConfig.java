package com.enai.app.database.base;

import android.os.Environment;


/**
 * Created by zhaowenchao on 16/5/19.
 */
public class DataBaseConfig {
    public static final String DEFAULTDBNAME = "defauleDBName";
    /**
     * 数据库路径
     */
    public final String path;
    /**
     * 数据库名称
     */
    public final String name;

    private DataBaseConfig(Builder builder) {
        path = builder.path;
        name = builder.name;
    }

    public static class Builder {
        private String path;
        private String name;

        public Builder setDatabasePath(String path) {
            this.path = path;
            return this;
        }

        public Builder setDatabaseName(String name) {
            this.name = name;
            return this;
        }

        private void initEmptyFieldsWithDefaultValues() {
            if (name == null) {
                name = DEFAULTDBNAME;
            }
            if (path == null) {
                path = Environment.getExternalStorageDirectory().getPath();
            }
        }

        public DataBaseConfig build() {
            initEmptyFieldsWithDefaultValues();
            return new DataBaseConfig(this);
        }
    }
}
