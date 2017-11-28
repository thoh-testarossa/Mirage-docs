#encoding:utf-8
import sys
reload(sys)
sys.setdefaultencoding('utf-8')

import cv2
import os
import numpy as np

#获得某目录中的全部文件
def GetFileList(direction, fileList):
    if os.path.isfile(direction):
        fileList.append(direction.encode('utf-8'))
    elif os.path.isdir(direction):
        for s in os.listdir(direction):
            newDir=os.path.join(direction,s)
            GetFileList(newDir, fileList)
    return fileList

def Writesvm(filename,fileout):
    fin = open(filename,'r')
    fout = open(fileout.decode(),'a')
    i=0
    newstr=''
    for ars in fin.readlines():

        ars = ars.strip('\n')
        ars = ars.replace('.','')
        newstr = newstr+ars
        i+=1
        if i==11:
            i=0
            fout.write(newstr+'\n')
            newstr=''

filelist=GetFileList(r'C:\Users\WT\Desktop\data\point_before'.decode(), [])
keyfilename={}#存放文件名字

ind=0
fileout = r'C:\Users\WT\Desktop\data\picture.txt'


for filename in filelist:
    Writesvm(filename,fileout)
    ind+=1