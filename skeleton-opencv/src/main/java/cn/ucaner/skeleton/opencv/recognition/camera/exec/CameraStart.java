package cn.ucaner.skeleton.opencv.recognition.camera.exec;

import cn.ucaner.skeleton.opencv.framework.common.consts.CommonConst;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.opencv.recognition.camera.exec
 * @Description： <p> CameraStart </p>
 * @Author： - Jason
 * @CreatTime：2019/8/22 - 15:42
 * @Modify By：
 * @ModifyTime： 2019/8/22
 * @Modify marker：
 */
public class CameraStart {

    private static final Logger logger = LoggerFactory.getLogger(CameraStart.class);

    /**
     * run open_cv 识别人脸
     * @param img
     * @return
     */
    public static Mat run(Mat img) {
        String xmlPath = CommonConst.OPEN_CV_CASCADE_CLASSIFIER_FACE_PATH;
        // 级联分类器
        CascadeClassifier faceDetector = new CascadeClassifier();
        // 加载级联分类器
        faceDetector.load(xmlPath);
        //矩形向量组
        MatOfRect faceDetections = new MatOfRect();
        try {
            //检测出人脸 , 用矩阵保存
            faceDetector.detectMultiScale(img, faceDetections);
        }catch (Exception e) {
            logger.error("=== 读取文件异常error:[{}] ...",e.getMessage());
            System.out.println(""+faceDetections);
            logger.error("== 矩形向量组为:\n{}",faceDetections);
        }

        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(img, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    //设置颜色
                    new Scalar(0, 255, 0));
        }
        return img;
    }

}
