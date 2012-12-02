package com.gf.components.reflections;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.jboss.vfs.VirtualFile;
import org.reflections.ReflectionsException;
import org.reflections.vfs.SystemDir;
import org.reflections.vfs.Vfs;
import org.reflections.vfs.Vfs.UrlType;
import org.reflections.vfs.ZipDir;

public class JBossVFSUrlType implements UrlType {
	
	@Override
	public boolean matches(URL url) {
        return url.getProtocol().equals("vfs");
    }

    @Override
	public Vfs.Dir createDir(URL url) {
        VirtualFile content;
        try {
            content = (VirtualFile) url.openConnection().getContent();
        } catch (Throwable e) {
            throw new ReflectionsException("could not open url connection as VirtualFile [" + url + "]", e);
        }

        Vfs.Dir dir = null;
        try {
            dir = createDir(new File(content.getPhysicalFile().getParentFile(), content.getName()));
        } catch (IOException e) { 
        	/*continue*/ 
        }
        if (dir == null) {
            try {
                dir = createDir(content.getPhysicalFile());
            } catch (IOException e) { 
            	/*continue*/ 
            }
        }
        return dir;
    }

    Vfs.Dir createDir(File file) {
    	if( ! file.exists() || ! file.canRead()){
    		return null;
    	}
    	
        try {
        	URL url = file.toURI().toURL();
        	
        	if(file.isDirectory()){
        		return new SystemDir(url);
        	} else {
				return new ZipDir(url);
        	}
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
