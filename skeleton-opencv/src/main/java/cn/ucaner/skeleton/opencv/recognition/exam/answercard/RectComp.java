package cn.ucaner.skeleton.opencv.recognition.exam.answercard;

import org.opencv.core.Rect;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.opencv.recognition.exam.answercard
 * @Description： <p> RectComp - 样稿 </p>
 * @Author： - Jason
 * @CreatTime：2019/8/22 - 16:11
 * @Modify By：
 * @ModifyTime： 2019/8/22
 * @Modify marker：
 */
public class RectComp {

    /**
     * 矩形
     */
    public Rect rm;

    public RectComp(Rect rms) {
        super();
        this.rm = rms;
    }

    public Rect getRm() {
        return rm;
    }

    public void setRm(Rect rm) {
        this.rm = rm;
    }

    public boolean operator(RectComp ti) {
        return rm.x < ti.rm.x;
    }

}
