package org.jfaster.mango.jdbc.exception;

import org.jfaster.mango.jdbc.JdbcTemplate;
import org.jfaster.mango.support.Config;
import org.jfaster.mango.support.Table;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author ash
 */
public class BadSqlGrammarExceptionTest {

    private final static DataSource ds = Config.getDataSource();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void before() throws Exception {
        Connection conn = ds.getConnection();
        Table.PERSON.load(conn);
        conn.close();
    }

    @Test
    public void test() {
        thrown.expect(BadSqlGrammarException.class);
        JdbcTemplate t = new JdbcTemplate();
        t.update(ds, "insert intoo ..", new Object[]{});
    }

}