package mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import com.gf.components.mybatis.AbstractMapper;

public interface DocMapper extends AbstractMapper {
	
	void createDocSchema(
            @Param("nameSize")int nameSize);

}
