package cn.ucaner.skeleton.opencv.common.utils.noise;

import cn.ucaner.skeleton.opencv.common.utils.general.GeneralUtils;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.opencv.common.utils.noise
 * @Description： <p> RemoveNoiseUtils - 降噪工具类 </p>
 * @Author： - Jason
 * @CreatTime：2019/8/20 - 15:24
 * @Modify By：
 * @ModifyTime： 2019/8/20
 * @Modify marker：
 */
public class RemoveNoiseUtils {

    /**
     * 8邻域降噪，又有点像9宫格降噪;即如果9宫格中心被异色包围，则同化 降噪(默认白底黑字)
     * @param src Mat矩阵对象
     * @param pNum 阀值 默认取1即可
     * @return
     */
    public static Mat eghitRemoveNoise(Mat src, int pNum) {
        int i, j, m, n, nValue, nCount;
        int width = GeneralUtils.getImgWidth(src), height = GeneralUtils.getImgHeight(src);
        // 如果一个点的周围都是白色的，自己确实黑色的，同化
        for (j = 1; j < height - 1; j++) {
            for (i = 1; i < width - 1; i++) {
                nValue = GeneralUtils.getPixel(src, j, i);
                if (nValue == 0) {
                    nCount = 0;
                    // 比较(j , i)周围的9宫格，如果周围都是白色，同化
                    for (m = j - 1; m <= j + 1; m++) {
                        for (n = i - 1; n <= i + 1; n++) {
                            if (GeneralUtils.getPixel(src, m, n) == 0) {
                                nCount++;
                            }
                        }
                    }
                    if (nCount <= pNum) {
                        // 周围黑色点的个数小于阀值pNum,把自己设置成白色
                        GeneralUtils.setPixel(src, j, i, GeneralUtils.getWHITE());
                    }
                } else {
                    nCount = 0;
                    // 比较(j , i)周围的9宫格，如果周围都是黑色，同化
                    for (m = j - 1; m <= j + 1; m++) {
                        for (n = i - 1; n <= i + 1; n++) {
                            if (GeneralUtils.getPixel(src, m, n) == 0) {
                                nCount++;
                            }
                        }
                    }
                    if (nCount >= 8 - pNum) {
                        // 周围黑色点的个数大于等于(8 - pNum),把自己设置成黑色
                        GeneralUtils.setPixel(src, j, i, GeneralUtils.getBLACK());
                    }
                }
            }
        }
        return src;
    }


    /**
     * 连通域降噪  降噪(默认白底黑字)
     * @param src Mat矩阵对象
     * @param pArea 阀值 默认取1即可
     * @return
     */
    public static Mat connectedRemoveNoise(Mat src, double pArea) {
        int i, j, color = 1;
        int width = GeneralUtils.getImgWidth(src), height = GeneralUtils.getImgHeight(src);
        Result result = floodFill(new Result(src) , pArea);
        src = result.mat;
        // 二值化
        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                if (GeneralUtils.getPixel(src, j, i) < GeneralUtils.getWHITE()) {
                    GeneralUtils.setPixel(src, j, i, GeneralUtils.getBLACK());
                }
            }
        }
        if(result.status == false && result.count <= 100){
            connectedRemoveNoise(src , pArea);
        }
        return src;
    }


    /**
     * 连通域填充颜色
     * @param result
     * @param pArea
     * @return
     */
    public static Result floodFill(Result result , double pArea){
        Mat src = result.mat;
        if(src == null){
            return null;
        }
        int i, j, color = 1;
        int width = GeneralUtils.getImgWidth(src), height = GeneralUtils.getImgHeight(src);
        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                if (GeneralUtils.getPixel(src, j, i) == GeneralUtils.getBLACK()) {
                    // 用不同的颜色填充连接区域中的每个黑色点
                    // floodFill就是把与点(i , j)的所有相连通的区域都涂上color颜色
                    int area = Imgproc.floodFill(src, new Mat(), new Point(i, j), new Scalar(color));
                    if(area <= pArea) {
                        Imgproc.floodFill(src, new Mat(), new Point(i, j), new Scalar(255));
                    }else{
                        color++;
                    }
                    if(color == 255){
                        //连通域还没填充完
                        result.status = false;
                        result.mat = src;
                        result.count = result.count + 1;
                        return result;
                    }
                }
            }
        }
        result.mat = src;
        //表示所有的连通域都已填充完毕
        result.status = true;
        return result;
    }


    /**
     * 连通域填充颜色
     * @param src
     * @param pArea
     * @return
     */
    public static Mat floodFill(Mat src ,double pArea){
        if(src == null){
            return null;
        }
        int i, j, color = 1;
        int width = GeneralUtils.getImgWidth(src), height = GeneralUtils.getImgHeight(src);
        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                if (GeneralUtils.getPixel(src, j, i) == GeneralUtils.getBLACK()) {
                    // 用不同的颜色填充连接区域中的每个黑色点
                    // floodFill就是把与点(i , j)的所有相连通的区域都涂上color颜色
                    int area = Imgproc.floodFill(src, new Mat(), new Point(i, j), new Scalar(color));
                    if(area <= pArea) {
                        System.out.println(color);
                        Imgproc.floodFill(src, new Mat(), new Point(i, j), new Scalar(255));
                    }else {
                        color++;
                    }
                    System.out.println(color);
                }
            }
        }
        return src;
    }

    /**
     * 只填充最大的连通域
     * @param src
     * @return
     */
    public static Mat findMaxConnected(Mat src){
        int i, j, color = 127;
        int width = GeneralUtils.getImgWidth(src), height = GeneralUtils.getImgHeight(src);
        int maxArea = Integer.MAX_VALUE;
        int maxI = -1 , maxJ = -1;
        for (i = 0; i < width; i++) {
            for (j = 0; j < height; j++) {
                if (GeneralUtils.getPixel(src, j, i) == GeneralUtils.getBLACK()) {
                    // 用不同的颜色填充连接区域中的每个黑色点
                    // floodFill就是把与点(i , j)的所有相连通的区域都涂上color颜色
                    int area = Imgproc.floodFill(src, new Mat(), new Point(i, j), new Scalar(color));
                    if(maxI != -1 && maxJ != -1){
                        if(area > maxArea){
                            maxArea = area;
                            Imgproc.floodFill(src, new Mat(), new Point(maxI, maxJ), new Scalar(255));
                            maxI = i;
                            maxJ = j;
                        }else{
                            Imgproc.floodFill(src, new Mat(), new Point(i, j), new Scalar(255));
                        }
                    }else{
                        maxI = i;
                        maxJ = j;
                        maxArea = area;
                    }
                }
            }
        }
        return src;
    }


    /**
     * Result
     */
    private static class Result{
        /**
         * Mat对象
         */
        Mat mat;

        /**
         * 是否填充完毕
         */
        boolean status;

        /**
         * 记录填充的次数
         */
        int count;

        /**
         * 记录上一次填充的height位置
         */
        int height;

        /**
         * 记录上一次填充的width位置
         */
        int width;
        public Result(){}
        public Result(Mat src){
            this.mat = src;
        }
    }

}
