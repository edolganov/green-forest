package mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gf.components.mybatis.AbstractMapper;

import example.common.model.Doc;

public interface DocMapper extends AbstractMapper {
	
	void createDocSchema(
            @Param("nameSize")int nameSize);
	
	int nextDocId();
	
	void createDoc(
            @Param("id")long id,
            @Param("name")String name);
	
	int getDocsCount();
	
	List<Doc> getDocsPage(
			@Param("limit")int limit,
			@Param("offset")int offset);
	
	int renameDoc(
			@Param("id")long id,
            @Param("name")String name);

}
