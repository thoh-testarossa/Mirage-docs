# coding=utf-8
import sys
reload(sys)
sys.setdefaultencoding('utf-8')

from PIL import Image
import  os
import cv2

def GetFileList(direction, fileList):
    if os.path.isfile(direction):
        fileList.append(direction.encode('utf-8'))
    elif os.path.isdir(direction):
        for s in os.listdir(direction):
            newDir=os.path.join(direction,s)
            GetFileList(newDir, fileList)
    return fileList

def change_image(image_path):
    size=(28,28)
    img = Image.open(image_path).convert('L')#转换为灰度图
    img = img.resize(size)
    print image_path
    img.save(image_path)


filelist=GetFileList(r'C:\Users\WT\Desktop\data\test'.decode(), [])
keyfilename={}#存放文件名字
ind=0
for filename in filelist:
    change_image(filename)
    ind+=1
print ind