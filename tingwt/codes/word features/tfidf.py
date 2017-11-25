#!/usr/bin/python
# -*- coding: utf8 -*-
from sklearn.feature_extraction.text import TfidfTransformer
from sklearn.feature_extraction.text import CountVectorizer
import sys
reload(sys)
sys.setdefaultencoding( "utf-8")

content = open('data\\content_jieba.txt',"r")
corpus = []
corpus.append(content)

vectorizer = CountVectorizer(input="file",encoding="utf-8",decode_error="ignore")
x_train = vectorizer.fit_transform(corpus)

transformer = TfidfTransformer()

tfidf = transformer.fit_transform(x_train)

word = vectorizer.get_feature_names() #所有文本的关键字
weight = tfidf.toarray()              #对应的tfidf矩阵

for i in range(len(weight)):
    for j in range(len(word)):
        print word[j],weight[i][j]
