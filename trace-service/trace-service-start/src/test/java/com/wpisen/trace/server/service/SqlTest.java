package com.wpisen.trace.server.service;
/**
 * Created by Administrator on 2018/6/26.
 */

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * @author wpisen
 *         Created by wpisen on 2018/6/26
 **/
public class SqlTest {
	@Ignore
	@Test
    public void sqlExprTest() {
        String sql="SELECT info.*\n" +
                "FROM sys_information info\n" +
                "\tINNER JOIN sys_information_type type ON type.id = info.type_id\n" +
                "\t\tAND type.enable = 1\n" +
                "\t\tAND type.type_name = '院内公告'\n" +
                "WHERE info.enable = 1\n" +
                "ORDER BY if(info.top IS NULL, 0, info.top) DESC, if(info.top = 0\n" +
                "OR info.top IS NULL, info.create_time, info.modify_time) DESC\n" +
                "LIMIT '10'";
        SQLExpr expr = SQLUtils.toMySqlExpr(sql);
        List<SQLStatement> list = SQLUtils.parseStatements(sql, "mysql");
        for (SQLStatement sqlStatement : list) {
            MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
            sqlStatement.accept(visitor);

            //获取表名称
            System.out.println("Tables : " + visitor.getCurrentTable());
            //获取操作方法名称,依赖于表名称
            System.out.println("Manipulation : " + visitor.getTables());
            //获取字段名称
            System.out.println("fields : " + visitor.getColumns());
        }
        System.out.println(expr);
    }
}
