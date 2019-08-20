package cn.ucaner.skeleton.opencv.common.utils.paint;

import cn.ucaner.skeleton.opencv.common.utils.contours.ContoursUtils;
import cn.ucaner.skeleton.opencv.common.utils.general.GeneralUtils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.opencv.common.utils.paint
 * @Description： <p> PaintUtils - 画图工具类</p>
 * @Author： - Jason
 * @CreatTime：2019/8/20 - 11:48
 * @Modify By：
 * @ModifyTime： 2019/8/20
 * @Modify marker：
 */
public class PaintUtils {

    private static final Logger logger = LoggerFactory.getLogger(PaintUtils.class);

    /**
     * 画出所有的矩形
     * @param src
     * @return
     */
    public static Mat paintCon(Mat src) {
        Mat cannyMat = GeneralUtils.canny(src);
        List<MatOfPoint> contours = ContoursUtils.findContours(cannyMat);

        Mat rectMat = src.clone();
        Scalar scalar = new Scalar(0, 0, 255);
        for (int i = contours.size() - 1; i >= 0; i--) {
            MatOfPoint matOfPoint = contours.get(i);
            MatOfPoint2f matOfPoint2f = new MatOfPoint2f(matOfPoint.toArray());
            RotatedRect rect = Imgproc.minAreaRect(matOfPoint2f);
            Rect r = rect.boundingRect();
            logger.info("== r.area():[{}],index:[{}] ===",r.area(),i);
            rectMat = paintRect(rectMat, r, scalar);
        }
        return rectMat;
    }


    /**
     * 画出最大的矩形
     * @param src
     * @return
     */
    public static Mat paintMaxCon(Mat src) {
        Mat cannyMat = GeneralUtils.canny(src);
        RotatedRect rect = ContoursUtils.findMaxRect(cannyMat);
        Rect r = rect.boundingRect();
        Mat rectMat = src.clone();
        Scalar scalar = new Scalar(0, 0, 255);
        rectMat = paintRect(rectMat, r, scalar);
        return rectMat;
    }


    /**
     *  画矩形
     * @param src
     * @param r
     * @param scalar
     * @return
     */
    public static Mat paintRect(Mat src, Rect r, Scalar scalar) {
        Point pt1 = new Point(r.x, r.y);
        Point pt2 = new Point(r.x + r.width, r.y);
        Point pt3 = new Point(r.x + r.width, r.y + r.height);
        Point pt4 = new Point(r.x, r.y + r.height);
        Imgproc.line(src, pt1, pt2, scalar, 5);
        Imgproc.line(src, pt2, pt3, scalar, 5);
        Imgproc.line(src, pt3, pt4, scalar, 5);
        Imgproc.line(src, pt4, pt1, scalar, 5);
        return src;
    }

    /**
     * 画实心圆
     * @param src
     * @param point 点
     * @param size 点的尺寸
     * @param scalar  颜色
     * @param path   保存路径
     * @return
     */
    public static boolean paintCircle(Mat src, Point[] point, int size, Scalar scalar, String path) {
        if (src == null || point == null) {
            throw new RuntimeException("Mat 或者 Point 数组不能为NULL");
        }
        for (Point p : point) {
            Imgproc.circle(src, p, size, scalar, -1);
        }
        if (path != null && !"".equals(path)) {
            return GeneralUtils.saveImg(src, path);
        }
        return false;
    }

    public static void main(String[] args) {
        logger.info("== 开始处理数据 ===");
        Mat mat = GeneralUtils.matFactory("C:\\Users\\Jason's-PC\\Pictures\\avatar\\01.jpeg");
        Mat outPut = paintCon(mat);
        GeneralUtils.saveImg(outPut,"C:\\Users\\Jason's-PC\\Pictures\\avatar\\0001.jpeg");
    }

}
