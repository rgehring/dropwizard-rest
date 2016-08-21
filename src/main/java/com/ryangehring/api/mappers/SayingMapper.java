package com.ryangehring.api.mappers;

/**
 * Created by ryan on 8/20/16.
 */

import com.ryangehring.api.core.Saying;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SayingMapper implements ResultSetMapper<Saying>
{
    public Saying map(int index, ResultSet resultSet, StatementContext statementContext) throws SQLException
    {
        return new Saying(resultSet.getInt("id"), resultSet.getString("content"));
    }
}

