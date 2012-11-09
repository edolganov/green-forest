package example;

import org.junit.After;

import com.gf.util.file.FileUtil;
import com.gf.util.junit.AssertExt;

public abstract class AbstractTest extends AssertExt {
	
	public final String sessionId = String.valueOf(System.currentTimeMillis());
	public final String tmpDirPath = "./tmp";
	
	
	@After
	public void after(){
		FileUtil.deleteDirRecursive(tmpDirPath);
	}

}
