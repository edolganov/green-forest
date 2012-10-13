package servlet_jdbc;

import junit.framework.Assert;

import org.junit.After;

import com.gf.util.file.FileUtil;

public abstract class AbstractTest extends Assert {
	
	public final String sessionId = String.valueOf(System.currentTimeMillis());
	public final String tmpDirPath = "./tmp";
	
	
	@After
	public void after(){
		FileUtil.deleteDirRecursive(tmpDirPath);
	}

}
