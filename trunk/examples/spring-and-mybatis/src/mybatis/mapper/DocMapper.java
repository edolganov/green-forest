package mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import com.gf.components.mybatis.AbstractMapper;

public interface DocMapper extends AbstractMapper {
	
	void createDocSchema(
            @Param("nameSize")int nameSize);
	
	int nextDocId();
	
	void createDoc(
            @Param("id")int id,
            @Param("name")String name);

}
