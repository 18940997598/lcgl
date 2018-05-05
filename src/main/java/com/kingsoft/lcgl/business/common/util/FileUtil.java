package com.kingsoft.lcgl.business.common.util;



import org.jboss.logging.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by yangdiankang on 2017/11/30.
 */
public class FileUtil {
    private static final Logger logger = Logger.getLogger(FileUtil.class);

    /**
     * 文件压缩
     * @param srcfile
     * @param zipfile
     */
    public static void zipFiles(List<File> srcfile, File zipfile){
        byte[] buf=new byte[1024];
        try {
           
            ZipOutputStream out=new ZipOutputStream(new FileOutputStream(zipfile));
            for(int i=0;i<srcfile.size();i++){
                FileInputStream in=new FileInputStream(srcfile.get(i));
                out.putNextEntry(new ZipEntry(srcfile.get(i).getName()));
                int len;
                while((len=in.read(buf))>0){
                    out.write(buf,0,len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
        } catch (Exception e) {
            logger.error("文件压缩错误",e);
        }
    }

}
