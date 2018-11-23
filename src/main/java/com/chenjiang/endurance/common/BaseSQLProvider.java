package com.chenjiang.endurance.common;

public abstract class BaseSQLProvider<E> {

    protected abstract String table();

    protected abstract void doInsert(E e, SQL sql);
    protected void doUpdate(SQL sql) {}

    public final String insert(E e) {
        SQL sql = new SQL().INSERT_INTO(table());
        this.doInsert(e, sql);
        return sql.toString();
    }

    public final String update() {
        SQL sql = new SQL().UPDATE(table());
        this.doUpdate(sql);
        return sql.toString();
    }

}
