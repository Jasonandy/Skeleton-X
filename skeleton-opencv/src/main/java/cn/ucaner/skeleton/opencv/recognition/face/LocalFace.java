package cn.ucaner.skeleton.opencv.recognition.face;

import cn.ucaner.skeleton.opencv.framework.common.consts.CommonConst;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.opencv.recognition.face
 * @Description： <p> LocalFace </p>
 * @Author： - Jason
 * @CreatTime：2019/8/14 - 14:53
 * @Modify By：
 * @ModifyTime： 2019/8/14
 * @Modify marker：
 */
public class LocalFace {

    private static final Logger logger = LoggerFactory.getLogger(LocalFace.class);

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    /**
     * OpenCV-4.0.0 人脸识别
     * @param imageFile
     */
    public static void face(String imageFile) {
        // 1 读取OpenCV自带的人脸识别特征XML文件
        //OpenCV 图像识别库一般位于 opencv\sources\data 下面
        CascadeClassifier facebook=new CascadeClassifier(CommonConst.OPEN_CV_CASCADE_CLASSIFIER_FACE_PATH);
        // 2 读取测试图片
        Mat image= Imgcodecs.imread(imageFile);
        // 3 特征匹配
        MatOfRect face = new MatOfRect();
        facebook.detectMultiScale(image, face);
        // 4 匹配 Rect 矩阵 数组
        Rect[] rects=face.toArray();
        logger.info(">>> 匹配到[{}]个人脸 ...",rects.length);
        // 5 为每张识别到的人脸画一个圈
        for (int i = 0; i < rects.length; i++) {
            Imgproc.rectangle(image,new Point(rects[i].x, rects[i].y), new Point(rects[i].x + rects[i].width, rects[i].y + rects[i].height), new Scalar(0, 0, 255));
            Imgproc.putText(image,"HUMAN", new Point(rects[i].x, rects[i].y),Imgproc.FONT_HERSHEY_SCRIPT_SIMPLEX, 1.0, new Scalar(0, 255, 0),1,Imgproc.LINE_AA,false);
        }
        // 6 展示图片
        HighGui.imshow("人脸识别", image);
        //必须要写 要不会一闪而过
        HighGui.waitKey(0);
    }


    /**
     * just for test
     * @param args
     */
    public static void main(String[] args) {
        String imageFile = "E:\\githubSpace\\Skeleton-X\\skeleton-opencv\\src\\main\\resources\\asset\\face\\01.jpg";
        face(imageFile);
    }
}
