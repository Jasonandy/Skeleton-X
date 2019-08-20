package cn.ucaner.skeleton.opencv.common.utils.resize;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.opencv.common.utils.resize
 * @Description： <p> ResizeUtils 归一化工具类</p>
 * @Author： - Jason
 * @CreatTime：2019/8/20 - 11:55
 * @Modify By：
 * @ModifyTime： 2019/8/20
 * @Modify marker：
 */
public class ResizeUtils {

    /**
     * 把图片归一化到相同的大小
     * @param src Mat矩阵对象
     * @return
     */
    public static Mat resize(Mat src) {
        return resize(src , new Size(28 , 28));
    }


    /**
     * resize
     * @param src
     * @param d_size
     * @return
     */
    public static Mat resize(Mat src , Size d_size) {
        Mat dst = new Mat();
        // 区域插值(INTER_AREA):图像放大时类似于线性插值，图像缩小时可以避免波纹出现。
        Imgproc.resize(src, dst, d_size, 0, 0, Imgproc.INTER_AREA);
        return dst;
    }

}
