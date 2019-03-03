package com.zz.rest.demo.mapper.inf;

import com.zz.rest.demo.model.po.TestPo;
import com.zz.rest.demo.model.po.TestPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TestPoMapper {
    int countByExample(TestPoExample example);

    int deleteByExample(TestPoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TestPo record);

    int insertSelective(TestPo record);

    List<TestPo> selectByExampleWithRowbounds(TestPoExample example, RowBounds rowBounds);

    TestPo selectOneByExample(TestPoExample example);

    List<TestPo> selectByExample(TestPoExample example);

    TestPo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TestPo record, @Param("example") TestPoExample example);

    int updateByExample(@Param("record") TestPo record, @Param("example") TestPoExample example);

    int updateByPrimaryKeySelective(TestPo record);

    int updateByPrimaryKey(TestPo record);

    int insertBatch(List<TestPo> records);
}