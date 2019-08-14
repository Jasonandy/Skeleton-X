package cn.ucaner.skeleton.opencv.framework.common.utils.file;

import java.io.File;
import java.util.Vector;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.opencv.framework.common.utils.file
 * @Description： <p> FileUtil </p>
 * @Author： - Jason
 * @CreatTime：2019/8/14 - 15:31
 * @Modify By：
 * @ModifyTime： 2019/8/14
 * @Modify marker：
 */
public class FileUtil {


    /**
     * get all files under the directory path
     * @param path
     * @param files
     */
    public static void getFiles(final String path, Vector<String> files) {
        getFiles(new File(path), files);
    }


    /**
     * delete and create a new directory with the same name
     * @param dir
     */
    public static void recreateDir(final String dir) {
        new File(dir).delete();
        new File(dir).mkdir();
    }


    /**
     * getFiles
     * @param dir
     * @param files
     */
    private static void getFiles(final File dir, Vector<String> files) {
        File[] fileList = dir.listFiles();
        for (File file : fileList) {
            if (file.isDirectory()) {
                getFiles(file, files);
            } else {
                files.add(file.getAbsolutePath());
            }
        }
    }
}
