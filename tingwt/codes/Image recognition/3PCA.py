#encoding:utf-8
import sys
reload(sys)
sys.setdefaultencoding('utf-8')
#用来降维并绘制图像
import numpy
import matplotlib.pylab as plt
from sklearn.decomposition import PCA

X=numpy.loadtxt(r'C:\Users\WT\Desktop\data\\picture.txt'.decode())

pca = PCA(n_components=2)
newDate_x= pca.fit_transform(X)

plt.figure()
plt.plot(X,'b.')
#plt.plot(newDate_x,'b.')
plt.show()