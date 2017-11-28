#coding=utf-8
import cv2
import scipy as sp

img1 = cv2.imread('honda.jpg',0) # queryImage
img2 = cv2.imread('logo.jpg',0) # trainImage
x1,y1 = img1.shape
x2,y2 = img2.shape
num = 0
x=0
y=0
i=0
j=0
a=x1-x2
b=y1-y2
print x1,y1,x2,y2
while i<a:
    while j<b:
        crop_img = img1[i:i+x2, j:j+y2]
        sift = cv2.SIFT()
        kp1, des1 = sift.detectAndCompute(crop_img,None)
        kp2, des2 = sift.detectAndCompute(img2,None)
        # FLANN parameters
        FLANN_INDEX_KDTREE = 0
        index_params = dict(algorithm = FLANN_INDEX_KDTREE, trees = 5)
        search_params = dict(checks=50)   # or pass empty dictionary
        flann = cv2.FlannBasedMatcher(index_params,search_params)
        matches = flann.knnMatch(des1,des2,k=2)
        if num<len(matches):
            num=len(matches)
            x=i
            y=j
            good = []
            print len(matches)
            for m,n in matches:
                if m.distance < 0.75*n.distance:
                    good.append(m)
        j+=10
    i+=10
    j=0

print x,y


img1 = img1[x:x+x2, y:y+y2]
h1, w1 = img1.shape[:2]
h2, w2 = img2.shape[:2]
view = sp.zeros((max(h1, h2), w1 + w2, 3), sp.uint8)
view[:h1, :w1, 0] = img1
view[:h2, w1:, 0] = img2
view[:, :, 1] = view[:, :, 0]
view[:, :, 2] = view[:, :, 0]

cv2.imshow('result',img1)
cv2.waitKey(0)
