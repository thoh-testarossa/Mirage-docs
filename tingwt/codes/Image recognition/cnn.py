# coding=utf-8
import sys
reload(sys)
sys.setdefaultencoding('utf-8')

#导入各种用到的模块组件
from keras.models import Sequential
from keras.layers.core import Dense, Activation, Flatten
from keras.layers.convolutional import Convolution2D, MaxPooling2D
from keras.optimizers import SGD
from keras.utils import np_utils
import numpy as np
import os
import cv2

#获得某目录中的全部文件
def GetFileList(direction, fileList):
    if os.path.isfile(direction):
        fileList.append(direction.encode('utf-8'))
    elif os.path.isdir(direction):
        for s in os.listdir(direction):
            newDir=os.path.join(direction,s)
            GetFileList(newDir, fileList)
    return fileList

#读取图片
def load_data(filelist,num):
    data = np.empty((num,1,28,28),dtype="float32")
    label = np.empty((num,1),dtype="uint8")
    i=0
    for filename in filelist:
        img = cv2.imread(filename)
        img = cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)
        arr = np.asarray(img,dtype="float32")
        data[i,:,:,:] = arr
        if filename.find('Citroen')!=-1:
            label[i] = 0
        elif filename.find('FAW')!=-1:
            label[i] = 1
        elif filename.find('Fukude')!=-1:
            label[i]= 2
        elif filename.find('Honda')!=-1:
            label[i]= 3
        i+=1
    return data,label

filelist=GetFileList(r'C:\Users\WT\Desktop\data\train'.decode(), [])
keyfilename={}#存放文件名字
data, label = load_data(filelist,560)

filelist=GetFileList(r'C:\Users\WT\Desktop\data\test'.decode(), [])
keyfilename={}#存放文件名字
testdata,testlabel = load_data(filelist,371)

#label为0~3共4个类别，keras要求格式为binary class matrices,转化一下，直接调用keras提供的这个函数
label = np_utils.to_categorical(label, 4)
testlabel = np_utils.to_categorical(testlabel, 4)

#开始建立CNN模型

#生成一个model
model = Sequential()

#第一个卷积层，4个卷积核，每个卷积核大小3*3。1表示输入的图片的通道,灰度图为1通道。
#激活函数用tanh
model.add(Convolution2D(4 ,3 , 3, border_mode='valid',input_shape=(1,28,28)))
model.add(Activation('tanh'))

#第二个卷积层，8个卷积核，每个卷积核大小3*3。4表示输入的特征图个数，等于上一层的卷积核个数
#激活函数用tanh
#采用maxpooling，poolsize为(2,2)
model.add(Convolution2D(8 , 3 , 3, border_mode='valid'))#边界模式
model.add(Activation('tanh'))#[-1 1]
model.add(MaxPooling2D(pool_size=(2, 2)))

#第三个卷积层，16个卷积核，每个卷积核大小3*3
#激活函数用tanh
#采用maxpooling，poolsize为(2,2)
model.add(Convolution2D(16 , 3 , 3, border_mode='valid'))
model.add(Activation('tanh'))
model.add(MaxPooling2D(pool_size=(2, 2)))

#全连接层，先将前一层输出的二维特征图flatten为一维的。
#Dense就是隐藏层。16就是上一层输出的特征图个数。4是根据每个卷积层计算出来的：(28-3+1)得到26,(26-3+1)/2得到12，(12-3+1)/2得到5
#全连接有128个神经元节点,初始化方式为normal
model.add(Flatten())
model.add(Dense(128, init='normal'))
model.add(Activation('tanh'))

#Softmax分类，输出是4类别
model.add(Dense(4, init='normal'))
model.add(Activation('softmax'))


#开始训练模型

#使用SGD + momentum
#model.compile里的参数loss就是损失函数(目标函数)
sgd = SGD(lr=0.01, decay=1e-7, momentum=0.9,nesterov=True)

model.compile(loss='categorical_crossentropy', optimizer=sgd,class_mode="categorical",metrics=["accuracy"])

#调用fit方法，就是一个训练过程. 训练的epoch数设为10，batch_size为100．
#数据经过随机打乱shuffle=True。verbose=1，训练过程中输出的信息，0、1、2三种方式都可以，无关紧要。show_accuracy=True，训练时每一个epoch都输出accuracy。
#validation_split=0.2，将20%的数据作为验证集。
model.fit(data, label, batch_size=100,nb_epoch=20,shuffle=True,verbose=1,show_accuracy=True)#,validation_split=0.2

#fit方法在达到设定的nb_epoch时结束，并且自动地保存了效果最好的model,之后你可以调用model.evaluate()方法对测试数据进行测试，

score = model.evaluate(testdata,testlabel, batch_size=100,show_accuracy=True, verbose=1)

predictlabel = model.predict_classes(testdata, batch_size=32, verbose=1)

print('Test score:', score[0])
print('Test accuracy:', score[1])

for i in predictlabel:
    print i

