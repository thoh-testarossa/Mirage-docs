# coding=utf-8
import sys
reload(sys)
sys.setdefaultencoding('utf-8')

import cv2
import numpy as np
from matplotlib import pyplot as plt

lena = cv2.imread('lena.jpg')
#图像按照BGR顺序存储的，得到的是标准的Numpy数组，读取图像默认的是BGR
print lena.dtype

#直方图是根据灰度图绘制的，x轴是灰度值
gray = cv2.cvtColor(lena,cv2.COLOR_BGR2GRAY)

hist = cv2.calcHist([gray],[0],None,[256],[0,256])
plt.plot(hist)
plt.xlim([0,256])
plt.show()
#plt.hist(gray.ravel(),256,[0,256])

hist = cv2.calcHist([gray],[0],None,[16],[16,256])
plt.plot(hist)
plt.show()

equ = cv2.equalizeHist(gray)#均衡化直方图
res = np.hstack((gray,equ))
cv2.imshow('res',res)
equ_hist = cv2.calcHist([res],[0],None,[256],[0,256])
plt.plot(equ_hist)
plt.xlim([0,256])
plt.show()


cv2.imshow('gray',gray)
ret, binary = cv2.threshold(gray,127,255,cv2.THRESH_BINARY)#黑白二值
cv2.imshow('blace_white',binary)
hsv = cv2.cvtColor(lena,cv2.COLOR_BGR2HSV)
cv2.imshow('hsv',hsv)
rgb = cv2.cvtColor(lena,cv2.COLOR_BGR2RGB)
cv2.imshow('rgb',rgb)

kernel = np.array([ [-1, -1, -1],
                    [-1,  8, -1],
                    [-1, -1, -1] ])

dst = cv2.filter2D(gray, -1, kernel)
cv2.imshow('dst',dst)
blur = cv2.blur(gray,(5,5))#低通滤波 降低图像的变化率，平滑强度变化明显的的区域
cv2.imshow('blur',blur)
gaussian = cv2.GaussianBlur(gray,(5,5),0) #高斯滤波  低通滤波中，滤波器中每个像素的权重是相同的。而高斯滤波器中像素的权重与其距中心像素的距离成比例
cv2.imshow('gaussian',gaussian)
median = cv2.medianBlur(gray,5) #中值滤波
cv2.imshow('median',median)
bilateral = cv2.bilateralFilter(gray,5,75,75) #双边滤波 函数有四个参数需要，d是领域的直径，后面两个参数是空间高斯函数标准差和灰度值相似性高斯函数标准差
cv2.imshow('bilateral',bilateral)

cv2.waitKey(0)
cv2.destroyAllWindows()
