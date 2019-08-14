package cn.ucaner.skeleton.opencv.framework.common.utils.convert;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.Mat;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.SampleModel;

/**
 * @projectName：Skeleton-X
 * @Package：cn.ucaner.skeleton.opencv.framework.common.utils.convert
 * @Description： <p> ConvertUtil </p>
 * @Author： - Jason
 * @CreatTime：2019/8/14 - 15:51
 * @Modify By：
 * @ModifyTime： 2019/8/14
 * @Modify marker：
 */
public class ConvertUtil {

    /**
     * toFloat
     * @param pointer
     * @return
     */
    public static float toFloat(BytePointer pointer) {
        byte[] buffer = new byte[4];
        pointer.get(buffer);
        return toFloat(buffer);
    }

    /**
     * toDouble
     * @param pointer
     * @return
     */
    public static double toDouble(BytePointer pointer) {
        byte[] buffer = new byte[8];
        pointer.get(buffer);
        return toDouble(buffer);
    }

    /**
     * toInt
     * @param pointer
     * @return
     */
    public static int toInt(BytePointer pointer) {
        byte[] buffer = new byte[4];
        pointer.get(buffer);
        return toInt(buffer);
    }

    /**
     * toLong
     * @param pointer
     * @return
     */
    public static long toLong(BytePointer pointer) {
        byte[] buffer = new byte[8];
        pointer.get(buffer);
        return toLong(buffer);
    }

    /**
     * getBytes
     * @param value
     * @return
     */
    public static byte[] getBytes(float value) {
        return getBytes(Float.floatToIntBits(value));
    }

    /**
     * getBytes
     * @param value
     * @return
     */
    public static byte[] getBytes(double value) {
        return getBytes(Double.doubleToLongBits(value));
    }

    /**
     * getBytes
     * @param value
     * @return
     */
    public static byte[] getBytes(int value) {
        final int length = 4;
        byte[] buffer = new byte[length];
        for (int i = 0; i < length; ++i) {
            buffer[i] = (byte) ((value >> (i * 8)) & 0xFF);
        }
        return buffer;
    }

    /**
     * getBytes
     * @param value
     * @return
     */
    public static byte[] getBytes(long value) {
        final int length = 8;
        byte[] buffer = new byte[length];
        for (int i = 0; i < length; ++i){
            buffer[i] = (byte) ((value >> (i * 8)) & 0xFF);
        }
        return buffer;
    }

    /**
     * toInt
     * @param value
     * @return
     */
    public static int toInt(byte[] value) {
        final int length = 4;
        int n = 0;
        for (int i = 0; i < length; ++i) {
            n += (value[i] & 0xFF) << (i * 8);
        }
        return n;
    }

    /**
     * toLong
     * @param value
     * @return
     */
    public static long toLong(byte[] value) {
        final int length = 8;
        long n = 0;
        for (int i = 0; i < length; ++i) {
            n += ((long) (value[i] & 0xFF)) << (i * 8);
        }
        return n;
    }

    /**
     * toDouble
     * @param value
     * @return
     */
    public static double toDouble(byte[] value) {
        return Double.longBitsToDouble(toLong(value));
    }

    /**
     * toFloat
     * @param value
     * @return
     */
    public static float toFloat(byte[] value) {
        return Float.intBitsToFloat(toInt(value));
    }


    /**
     * getFrame
     * @param image
     * @param gamma
     * @param flipChannels
     * @return
     */
    private Mat getFrame(BufferedImage image, double gamma, boolean flipChannels) {
        if (image == null) {
            return null;
        }
        SampleModel sm = image.getSampleModel();
        int depth = 0, numChannels = sm.getNumBands();
        switch (image.getType()) {
            case BufferedImage.TYPE_INT_RGB:
            case BufferedImage.TYPE_INT_ARGB:
            case BufferedImage.TYPE_INT_ARGB_PRE:
            case BufferedImage.TYPE_INT_BGR:
                depth = 8;
                numChannels = 4;
                break;
        }
        if (depth == 0 || numChannels == 0) {
            switch (sm.getDataType()) {
                /** DEPTH_BYTE   =  -8, DEPTH_UBYTE  =   8, DEPTH_SHORT  = -16,  DEPTH_USHORT =  16, DEPTH_INT    = -32,DEPTH_LONG   = -64, DEPTH_FLOAT  =  32,   DEPTH_DOUBLE =  64; */
                case DataBuffer.TYPE_BYTE:   depth = 8;  break;
                case DataBuffer.TYPE_USHORT: depth = 16; break;
                case DataBuffer.TYPE_SHORT:  depth = -16; break;
                case DataBuffer.TYPE_INT:    depth = -32; break;
                case DataBuffer.TYPE_FLOAT:  depth = 32;  break;
                case DataBuffer.TYPE_DOUBLE: depth = 64; break;
                default: assert false;
            }
        }
        Mat mat = null;
        if ( image.getWidth()>0&&image.getHeight()>0) {
            mat=new Mat(image.getWidth(), image.getHeight(), depth, numChannels);


        }
        return mat;
    }


    /**
     * mat2Img
     * @param mat
     * @return
     */
    public static BufferedImage mat2Img(Mat mat){
        BufferedImage out;
        byte[]data=new byte[320*240*(int)mat.elemSize()];
        int type;
        //mat.get(0,0,data);
        mat.arrayData().get(data);
        if(mat.channels()==1) {
            type=BufferedImage.TYPE_BYTE_GRAY;
        } else {
            type=BufferedImage.TYPE_3BYTE_BGR;
        }
        out=new BufferedImage(320,240,type);
        out.getRaster().setDataElements(0,0,320,240,data);
        return out;
    }


    /**
     * getMatDepth
     * @param depth
     * @return
     */
    public static int getMatDepth(int depth) {
        /** DEPTH_BYTE   =  -8, DEPTH_UBYTE  =   8, DEPTH_SHORT  = -16,  DEPTH_USHORT =  16, DEPTH_INT    = -32,DEPTH_LONG   = -64, DEPTH_FLOAT  =  32,   DEPTH_DOUBLE =  64; */
        switch (depth) {
            case 8:  return opencv_core.CV_8U;
            case -8:   return opencv_core.CV_8S;
            case 16: return opencv_core.CV_16U;
            case -16:  return opencv_core.CV_16S;
            case 32:  return opencv_core.CV_32F;
            case -32:    return opencv_core.CV_32S;
            case 64: return opencv_core.CV_64F;
            default:  return -1;
        }
    }
}
