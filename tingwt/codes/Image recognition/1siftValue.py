#encoding:utf-8
import sys
reload(sys)
sys.setdefaultencoding('utf-8')

import cv2
import os

#获得某目录中的全部文件
def GetFileList(direction, fileList):
    if os.path.isfile(direction):
        fileList.append(direction.encode('utf-8'))
    elif os.path.isdir(direction):
        for s in os.listdir(direction):
            newDir=os.path.join(direction,s)
            GetFileList(newDir, fileList)
    return fileList

def Writesvm(filename,ind):
    #read image
    img = cv2.imread(filename)
    fileout = r'C:\Users\WT\Desktop\data\point_e\\'+'picture'+str(ind)+'.txt'
    fout = open(fileout.decode(),'a')
    #SIFT
    detector = cv2.SIFT()
    keypoints ,des = detector.detectAndCompute(img,None)
    S='\n'.join(str(num)[1:-1] for num in des)
    fout.write(S)


filelist=GetFileList(r'C:\Users\WT\Desktop\data\train_pre'.decode(), [])
keyfilename={}#存放文件名字
ind=0

for filename in filelist:
    Writesvm(filename,ind)
    ind+=1
