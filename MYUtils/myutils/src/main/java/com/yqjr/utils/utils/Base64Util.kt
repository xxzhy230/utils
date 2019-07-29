package com.yqjr.utils.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Base64
import java.io.*


object Base64Util {
    /**
     * bitmap转为base64
     * @param bitmap
     * @return base64
     */
    fun bitmapToBase64(bitmap: Bitmap?): String {
        var result: String = ""
        var baos: ByteArrayOutputStream? = null
        try {
            if (bitmap != null) {
                baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 40, baos)
                baos.flush()
                baos.close()

                var bitmapBytes = baos.toByteArray()
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                if (baos != null) {
                    baos.flush()
                    baos.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return result
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    fun base64ToBitmap(base64Data: String): Bitmap {
        var bytes = Base64.decode(base64Data, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    /**
     * 文件转base64字符串
     * @param file
     * @return
     */
    fun fileToBase64(file: File): String? {
        var base64: String? = null
        var `in`: InputStream? = null
        try {
            `in` = FileInputStream(file)
            val bytes = ByteArray(`in`!!.available())
            val length = `in`!!.read(bytes)
            base64 = Base64.encodeToString(bytes, 0, length, Base64.DEFAULT)
        } catch (e: FileNotFoundException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } finally {
            try {
                if (`in` != null) {
                    `in`!!.close()
                }
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }

        }
        return base64
    }

    /**
     * base64字符串转文件
     * @param base64
     * @return
     */
    fun base64ToFile(base64: String): File {
        var file: File? = null
        val fileName = "/Petssions/record/testFile.amr"
        var out: FileOutputStream? = null
        try {
            // 解码，然后将字节转换为文件
            file = File(Environment.getExternalStorageDirectory(), fileName)
            if (!file!!.exists())
                file.createNewFile()
            val bytes = Base64.decode(base64, Base64.DEFAULT)// 将字符串转换为byte数组
            val input = ByteArrayInputStream(bytes)
            val buffer = ByteArray(1024)
            out = FileOutputStream(file)
            var bytesum = 0
            var byteread = 0
            var read = input.read(buffer)
            byteread = read
            while (byteread != -1) {
                bytesum += byteread
                out.write(buffer, 0, byteread) // 文件写操作
            }
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        } finally {
            try {
                out?.close()
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }

        }
        return file!!
    }


    /**
     *  
     *  * 图片按比例大小压缩方法 
     *  * 
     *  * @param image （根据Bitmap图片压缩） 
     *  * @return 
     *  
     */
    fun compressScale(image: Bitmap): Bitmap {
        //对图片宽高压缩
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        var isBm = ByteArrayInputStream(baos.toByteArray())
        val newOpts = BitmapFactory.Options()
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true
        var bitmap = BitmapFactory.decodeStream(isBm, null, newOpts)
        newOpts.inJustDecodeBounds = false
        val w = newOpts.outWidth
        val h = newOpts.outHeight
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        val hh = 480f//这里设置高度为800f
        val ww = 800f//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        var be = 1//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (newOpts.outWidth / ww).toInt()
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (newOpts.outHeight / hh).toInt()
        }
        if (be <= 0)
            be = 1
        newOpts.inSampleSize = be//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = ByteArrayInputStream(baos.toByteArray())
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts)

        //对图片质量压缩
        val bao = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bao)
        var options = 100
        while (bao.toByteArray().size / 1024 > 50) {
            bao.reset()//重置baos即清空baos
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, bao)
            options -= 10
        }
        var byte = bao.toByteArray()
        return BitmapFactory.decodeByteArray(byte, 0, byte.size)
    }
}