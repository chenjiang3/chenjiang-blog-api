package com.chenjiang.endurance.common;

public abstract class BaseSQLProvider<E> {

    protected abstract String table();

    protected abstract void doInsert(E e, SQL sql);

    public final String insert(E e) {
        SQL sql = new SQL().INSERT_INTO(table());
        this.doInsert(e, sql);
        return sql.toString();
    }

}
