# -*- coding: utf-8 -*-

import numpy as np
import math

def loadDataSet():
    postingList = [
        ['my', 'dog', 'has', ' flea', 'problems', 'help', 'please'],
        ['maybe', 'not', 'take', 'him', 'to', 'dog', 'park', 'stupid'],
        ['my', 'dalmation', 'is', 'so', 'cute', 'I', 'love', 'him'],
        ['stop', 'posting', 'stupid', 'worthless', 'garbage'],
        ['mr', 'licks', 'ate', 'my', 'steak', 'how', 'to', 'stop', 'him'],
        ['quit', 'buying', 'worthless', 'dog', 'food', 'stupid']
    ]
    classVec = [0, 1, 0, 1, 0, 1]   
    return postingList, classVec


def createVocabList(dataSet):
    vocabSet = set([])
    for document in dataSet:
        vocabSet = vocabSet | set(document)   
    return list(vocabSet)


def setOfWords2Vec(vocabList, inputSet):
    resVec = [0] * len(vocabList)
    for word in inputSet:
        if word in vocabList:
            resVec[vocabList.index(word)] = 1
        else:
            print("the word: {:s} is not in my Vocabulary!".format(word))
    return resVec


def trainNB0(trainMatrix, trainCategory):
    numTrainDocs = len(trainMatrix)
    numWords = len(trainMatrix[0])
    probAbusive = sum(trainCategory) / float(numTrainDocs)
    prob0Num = np.ones(numWords)
    prob1Num = np.ones(numWords)
    prob0Denom = 2.0
    prob1Denom = 2.0
    for i in range(numTrainDocs):
        if trainCategory[i] == 1:
            prob1Num += trainMatrix[i]
            prob1Denom += sum(trainMatrix[i])
        else:
            prob0Num += trainMatrix[i]
            prob0Denom += sum(trainMatrix[i])
    prob1Vect = np.log(prob1Num / prob1Denom)
    prob0Vect = np.log(prob0Num / prob0Denom)
    #prob1Vect = map(math.log, prob1Num / prob1Denom)
    #prob0Vect = map(math.log, prob0Num / prob0Denom)
    return prob0Vect, prob1Vect, probAbusive

def classifyNB(vec2Classify, p0Vec, p1Vec, pClass1):
    p1 = sum(vec2Classify * p1Vec) + math.log(pClass1)
    p0 = sum(vec2Classify * p0Vec) + math.log(1.0 - pClass1)
    if p1 > p0:
        return 1
    else:
        return 0


def testingNB():
    listOPosts, listClasses = loadDataSet()
    myVocabList = createVocabList(listOPosts)
    trainMat = []
    for postinDoc in listOPosts:
        trainMat.append(setOfWords2Vec(myVocabList, postinDoc))
    p0v, p1v, pAb = trainNB0(trainMat, listClasses)
    testEntry = ['love', 'my', 'dalmation']
    thisDoc = np.array(setOfWords2Vec(myVocabList, testEntry))
    print(testEntry, 'classified as: ', classifyNB(thisDoc, p0v, p1v, pAb))
    testEntry = ['stupid', 'garbage']
    thisDoc = np.array(setOfWords2Vec(myVocabList, testEntry))
    print(testEntry, 'classified as: ', classifyNB(thisDoc, p0v, p1v, pAb))


# ---------------------------- main ---------------------------- #

testingNB()