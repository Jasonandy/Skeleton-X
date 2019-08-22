package cn.ucaner.skeleton.opencv.recognition.face;

import cn.hutool.core.io.FileUtil;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.opencv.recognition.face
 * @Description： <p> Identification </p>
 * @Author： - Jason
 * @CreatTime：2019/8/22 - 17:29
 * @Modify By：
 * @ModifyTime： 2019/8/22
 * @Modify marker：
 */
public class Identification {

    private static final Logger logger = LoggerFactory.getLogger(Identification.class);

    public final static String FACE_PATH = "asset/face/01.jpg";
    public final static String CASCADE_PATH = "config/cascade/cascade_storage.xml";
    public final static String FACE_STORAGE_PATH = "identification.png";

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }


    /**
     * just for test
     * @param args
     */
    public static void main(String[] args) {
        String inputImagePath = FileUtil.getAbsolutePath(FACE_PATH);
        String xmlPath = FileUtil.getAbsolutePath(CASCADE_PATH);
        String storagePath = FileUtil.getAbsolutePath(FACE_STORAGE_PATH);
        CascadeClassifier faceDetector = new CascadeClassifier(xmlPath);
        Mat faceImage = Imgcodecs.imread(inputImagePath);
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(faceImage, faceDetections);
        // 画出脸的位置
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(faceImage, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255));
        }
        // 写入到文件
        Imgcodecs.imwrite(storagePath, faceImage);
        logger.info("识别后的数据存入地址为:[{}]",storagePath);
    }
}
