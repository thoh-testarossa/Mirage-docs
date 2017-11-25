#!/usr/bin/python
# -*- coding: utf8 -*-
import gensim, logging
import os
logging.basicConfig(format = '%(asctime)s : %(levelname)s : %(message)s', level = logging.INFO)

sentences = gensim.models.doc2vec.TaggedLineDocument('data\original_data\\train_content_jieba.txt')
print type(sentences)
model = gensim.models.Doc2Vec(sentences, size = 100, window = 5)
#model.save('train_model.txt')
print len(model.docvecs)

out = file('train_vector.txt', 'w+')
for idx, docvec in enumerate(model.docvecs):
    for value in docvec:
      out.write(str(value) + ' ')
    out.write('\n')
out.close()

print "success"
