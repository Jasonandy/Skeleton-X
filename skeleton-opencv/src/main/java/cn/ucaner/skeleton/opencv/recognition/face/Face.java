package cn.ucaner.skeleton.opencv.recognition.face;

import cn.ucaner.skeleton.opencv.framework.common.consts.CommonConst;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.opencv.recognition.face
 * @Description： <p> Face See{https://blog.csdn.net/qq_34814092/column/info/31599/1} </p>
 * @Author： - Jason
 * @CreatTime：2019/8/13 - 20:35
 * @Modify By：
 * @ModifyTime： 2019/8/13
 * @Modify marker：
 */
public class Face {

    private static final Logger logger = LoggerFactory.getLogger(Face.class);

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    /**
     * OpenCV-4.0.0 实时人脸识别
     */
    public static void videoFace() {
        VideoCapture capture=new VideoCapture(0);
        Mat image = new Mat();
        int index=0;
        if (capture.isOpened()) {
            while(true) {
                capture.read(image);
                HighGui.imshow("实时人脸识别", getFace(image));
                index=HighGui.waitKey(1);
                if (index==27) {
                    break;
                }
            }
        }
        return;
    }

    /**
     * OpenCV-4.0.0 人脸识别
     * @param image image 待处理Mat图片(视频中的某一帧)
     * @return 处理后的图片
     */
    public static Mat getFace(Mat image) {
        // 1 读取OpenCV自带的人脸识别特征XML文件
        CascadeClassifier facebook = new CascadeClassifier(CommonConst.OPEN_CV_CASCADE_CLASSIFIER_FACE_PATH);
        // 2  特征匹配类
        MatOfRect face = new MatOfRect();
        // 3 特征匹配
        facebook.detectMultiScale(image, face);
        Rect[] rects = face.toArray();
        // 框选数量
        logger.info("getFace:匹配到:[{}]个人脸 ...",rects.length);
        // 4 为每张识别到的人脸画一个圈
        for (int i = 0; i < rects.length; i++) {
            Imgproc.rectangle(image,new Point(rects[i].x,rects[i].y), new Point(rects[i].x + rects[i].width,
                            rects[i].y + rects[i].height), new Scalar(0, 255, 0));

            Imgproc.putText(image,"Human", new Point(rects[i].x, rects[i].y),
                    Imgproc.FONT_HERSHEY_SCRIPT_SIMPLEX, 1.0, new Scalar(0, 255, 0),1,Imgproc.LINE_AA,false);
        }
        return image;
    }

    /**
     * just for test
     * @param args
     */
    public static void main(String[] args) {
        logger.info("=== 开始进行人脸识别 ===");
        videoFace();
        logger.info("=== 人脸识别完毕 ===");

    }

}
