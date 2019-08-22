package cn.ucaner.skeleton.opencv.recognition.exam.answercard;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import static org.opencv.core.CvType.CV_8U;
import static org.opencv.imgproc.Imgproc.MORPH_RECT;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.opencv.recognition.exam.answercard
 * @Description： <p> AnswerUtils </p>
 * @Author： - Jason
 * @CreatTime：2019/8/22 - 16:13
 * @Modify By：
 * @ModifyTime： 2019/8/22
 * @Modify marker：
 */
public class AnswerUtils {

    private static final Logger logger = LoggerFactory.getLogger(AnswerUtils.class);

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    /**
     * 存储位置
     */
    private static  final String DESTINATION_PATH = "E:\\githubSpace\\Skeleton-X\\skeleton-opencv\\src\\main\\resources\\asset\\exam\\result.jpg";
    private static  final String ANSWER_CARD_PATH = "E:\\githubSpace\\Skeleton-X\\skeleton-opencv\\src\\main\\resources\\asset\\exam\\A4AnswerCard.jpg";

    /**
     * canny
     * @param oriImg 原始图片
     * @param dstImg
     * @param threshold
     */
    public static void canny(String oriImg, String dstImg, int threshold) {
        //装载图片
        Mat img = Imgcodecs.imread(oriImg);
        Mat srcImage2 = new Mat();
        Mat srcImage3 = new Mat();
        Mat srcImage4 = new Mat();
        Mat srcImage5 = new Mat();

        //图片变成灰度图片
        Imgproc.cvtColor(img, srcImage2, Imgproc.COLOR_RGB2GRAY);
        //图片二值化
        Imgproc.adaptiveThreshold(srcImage2, srcImage3, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY_INV, 255, 1);
        //确定腐蚀和膨胀核的大小
        Mat element = Imgproc.getStructuringElement(MORPH_RECT, new Size(1, 6));
        //腐蚀操作
        Imgproc.erode(srcImage3, srcImage4, element);
        //膨胀操作
        Imgproc.dilate(srcImage4, srcImage5, element);
        // 确定每张答题卡的ROI区域
        Mat imag_ch1 = srcImage4.submat(new Rect(200, 1065, 1930, 2210));

        //识别所有轮廓
        Vector<MatOfPoint> chapter1 = new Vector<>();
        Imgproc.findContours(imag_ch1, chapter1, new Mat(), 2, 3);
        Mat result = new Mat(imag_ch1.size(), CV_8U, new Scalar(255));
        Imgproc.drawContours(result, chapter1, -1, new Scalar(0), 2);

        Imgcodecs.imwrite(DESTINATION_PATH, result);

        //new一个 矩形集合 用来装 轮廓
        List<RectComp> RectCompList = new ArrayList<>();
        for (int i = 0; i < chapter1.size(); i++) {
            Rect rm = Imgproc.boundingRect(chapter1.get(i));
            RectComp ti = new RectComp(rm);
            //把轮廓宽度区间在 50 - 80 范围内的轮廓装进矩形集合
            if (ti.rm.width > 60 && ti.rm.width < 85) {
                RectCompList.add(ti);
            }
        }
        // new一个 map 用来存储答题卡上填的答案 (A\B\C\D)
        TreeMap<Integer, String> listenAnswer = new TreeMap<>();
        // 按 X轴 对listenAnswer进行排序
        RectCompList.sort((o1, o2) -> {
            if (o1.rm.x > o2.rm.x) {
                return 1;
            }
            if (o1.rm.x == o2.rm.x) {
                return 0;
            }
            if (o1.rm.x < o2.rm.x) {
                return -1;
            }
            return -1;
        });
        /**
         * 根据 Y轴 确定被选择答案 (A\B\C\D)
         */
        for (RectComp rc : RectCompList) {
            for (int h = 0; h < 7; h++) {
                if ((rc.rm.contains(new Point(rc.rm.x + 20, 115 + (320 * h))))) {
                    for (int w = 0; w < 4; w++) {
                        if (rc.rm.contains(new Point(55 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(1 + (20 * h) + (5 * w), "A");
                        } else if (rc.rm.contains(new Point(135 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(2 + (20 * h) + (5 * w), "A");
                        } else if (rc.rm.contains(new Point(215 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(3 + (20 * h) + (5 * w), "A");
                        } else if (rc.rm.contains(new Point(300 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(4 + (20 * h) + (5 * w), "A");
                        } else if (rc.rm.contains(new Point(380 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(5 + (20 * h) + (5 * w), "A");
                        }
                    }
                } else if ((rc.rm.contains(new Point(rc.rm.x + 20, 165 + (320 * h))))) {
                    for (int w = 0; w < 4; w++) {
                        if (rc.rm.contains(new Point(55 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(1 + (20 * h) + (5 * w), "B");
                        } else if (rc.rm.contains(new Point(135 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(2 + (20 * h) + (5 * w), "B");
                        } else if (rc.rm.contains(new Point(215 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(3 + (20 * h) + (5 * w), "B");
                        } else if (rc.rm.contains(new Point(300 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(4 + (20 * h) + (5 * w), "B");
                        } else if (rc.rm.contains(new Point(380 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(5 + (20 * h) + (5 * w), "B");
                        }
                    }
                } else if ((rc.rm.contains(new Point(rc.rm.x + 20, 220 + (320 * h))))) {
                    for (int w = 0; w < 4; w++) {
                        if (rc.rm.contains(new Point(55 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(1 + (20 * h) + (5 * w), "C");
                        } else if (rc.rm.contains(new Point(135 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(2 + (20 * h) + (5 * w), "C");
                        } else if (rc.rm.contains(new Point(215 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(3 + (20 * h) + (5 * w), "C");
                        } else if (rc.rm.contains(new Point(300 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(4 + (20 * h) + (5 * w), "C");
                        } else if (rc.rm.contains(new Point(380 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(5 + (20 * h) + (5 * w), "C");
                        }
                    }
                } else if ((rc.rm.contains(new Point(rc.rm.x + 20, 275 + (320 * h))))) {
                    for (int w = 0; w < 4; w++) {
                        if (rc.rm.contains(new Point(55 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(1 + (20 * h) + (5 * w), "D");
                        } else if (rc.rm.contains(new Point(135 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(2 + (20 * h) + (5 * w), "D");
                        } else if (rc.rm.contains(new Point(215 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(3 + (20 * h) + (5 * w), "D");
                        } else if (rc.rm.contains(new Point(300 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(4 + (20 * h) + (5 * w), "D");
                        } else if (rc.rm.contains(new Point(380 + (500 * w), rc.rm.y))) {
                            listenAnswer.put(5 + (20 * h) + (5 * w), "D");
                        }
                    }
                }
            }
        }

        /**
         * entrySet
         */
        Iterator iter = listenAnswer.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            logger.info("== 第:[{}]题,选项为:[{}] ... ",key,value);
        }

    }

    /**
     * rowsAndCols
     * @param oriImg
     * @param dstImg
     * @return
     */
    public static String rowsAndCols(String oriImg, String dstImg) {
        StringBuffer cardInfo = new StringBuffer();
        canny(oriImg, dstImg, 50);
        Mat mat = Imgcodecs.imread(dstImg);
        cardInfo.append("\n行数:").append(mat.rows())
                .append("\n列数:").append(mat.cols())
                .append("\nheight:").append(mat.height())
                .append("\nwidth:").append(mat.width())
                .append("\nelemSide:").append(mat.elemSize());
        return cardInfo.toString();
    }

    /**
     * just for test
     * @param args
     */
    public static void main(String []args) {
        String msg = rowsAndCols(ANSWER_CARD_PATH, DESTINATION_PATH);
        logger.info(">>> 识别信息如下:\n{}",msg);
    }
}
