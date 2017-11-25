#!/usr/bin/python
# -*- coding: utf8 -*-
import numpy as np
from gensim.models import word2vec



sentences =word2vec.Text8Corpus(u"data\\content_jieba.txt")# 加载语料
model = word2vec.Word2Vec(sentences, size=2)#训练skip-gram模型，默认window=5  

list1 = model.wv['学生'.decode('utf-8')]
list2 = model.wv['神经'.decode('utf-8')]
print model.wv['不错'.decode('utf-8')]
print model.wv['好'.decode('utf-8')]
y1 = model.similarity(u"不错", u"好")
print u"【不错】和【好】的相似度为：", y1
print "--------\n"