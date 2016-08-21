package com.ryangehring.api.database;

/**
 * Created by ryan on 8/20/16.
 */

import com.ryangehring.api.core.Saying;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import com.ryangehring.api.mappers.SayingMapper;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(SayingMapper.class)
public interface SayingDAO  {


    @SqlQuery("select * from sayings;")
    List<Saying> getAll() ;


    @SqlQuery("select * from sayings where id = :id")
    Saying find(@Bind("id") int id);

    /**
     * close with no args is used to close the connection
     */
    void close();
}