package com.github.smalnote.heron.mybatis.log;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GenesisLogMapper {

    GenesisLog select(@Param("log") GenesisLog log);

    GenesisLog selectById(@Param("eventId") long eventId);

    int insert(@Param("log") GenesisLog log);

    int insertBatch(@Param("logs") List<GenesisLog> logs);

    GenesisLogWithItself selectAssociationLog(long eventId);

}
