package com.github.smalnote.heron.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

@Mapper
public interface GenesisLogMapper {

    GenesisLog select(@Param("log") GenesisLog log, @Param("fake") boolean fake) throws SQLException;

    GenesisLog selectById(@Param("eventId") long eventId) throws SQLException;

}
