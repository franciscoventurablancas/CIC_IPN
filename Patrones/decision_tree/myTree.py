# -*- coding: utf-8 -*-

import math
import operator
import pickle   


def createDataSet():
    dataSet = [
        [1, 1, 'yes'],
        [1, 1, 'yes'],
        [1, 0, 'no'],
        [0, 1, 'no'],
        [0, 1, 'no']
    ]
    labels = ['no surfacing', 'flippers']
    return dataSet, labels


def calcEntropy(dataSet):
    numEntities = len(dataSet)
    labelCounts = {}     
    for featVec in dataSet:
        curLabel = featVec[-1]
        if curLabel not in labelCounts.keys():
            labelCounts[curLabel] = 1
        else:
            labelCounts[curLabel] += 1
    entropy = 0.0
    for key in labelCounts:
        prob = float(labelCounts[key]) / numEntities
        entropy += prob * math.log(prob, 2)
    entropy = -1.0 * entropy
    return entropy

def splitDataSet(dataSet, axis, value):
    retDataSet = []
    for featVec in dataSet:
        if featVec[axis] == value:
            reducedFeatVec = featVec[:axis]
            reducedFeatVec.extend(featVec[axis+1:])
            retDataSet.append(reducedFeatVec)
    return retDataSet

def chooseBestFeatureToSplit(dataSet):
    numFeatures = len(dataSet[0]) - 1
    baseEntropy = calcEntropy(dataSet)
    bestInfoGain = 0.0
    bestFeature = -1
    for i in range(numFeatures):
        featList = [example[i] for example in dataSet]
        uniqueVals = set(featList)
        newEntropy = 0.0
        for value in uniqueVals:
            subDataSet = splitDataSet(dataSet, i, value)
            prob = len(subDataSet) / float(len(dataSet))
            newEntropy += prob * calcEntropy(subDataSet)    
        infoGain = baseEntropy - newEntropy     
        if bestInfoGain < infoGain:     
            bestInfoGain = infoGain
            bestFeature = i
    return bestFeature

def majorityCnt(classList):
    classCounts = {}
    for vote in classList:
        if vote not in classCounts.keys():
            classCounts[vote] = 0
        classCounts[vote] += 1
    sortedClassCounts = sorted(classCounts.iteritems(), key=operator.itemgetter(1), reverse=True)
    return sortedClassCounts[0][0]


def createTree(dataSet, labels):
    classList = [example[-1] for example in dataSet]
    if classList.count(classList[0]) == len(classList):    
        return classList[0]
    if len(dataSet[0]) == 1:        
        return majorityCnt(classList)
    bestFeat = chooseBestFeatureToSplit(dataSet)
    bestFeatLabel = labels[bestFeat]
    myTree = {bestFeatLabel: {}}
    del labels[bestFeat]
    featValues = [example[bestFeat] for example in dataSet]
    uniqueVals = set(featValues)     
    for value in uniqueVals:
        subLabels = labels[:]
        myTree[bestFeatLabel][value] = createTree(splitDataSet(dataSet, bestFeat, value), subLabels)
    return myTree


def classify(inputTree, featLabels, testVec):
    firstStr = inputTree.keys()[0]
    secondDict = inputTree[firstStr]
    featIndex = featLabels.index(firstStr)      
    for key in secondDict.keys():
        if testVec[featIndex] == key:
            if type(secondDict[key]).__name__ == 'dict':
                classLabel = classify(secondDict[key], featLabels, testVec)
            else:
                classLabel = secondDict[key]
    return classLabel

def storeTree(inputTree, filename):
    fw = open(filename, 'w')
    pickle.dump(inputTree, fw)
    fw.close()

def grabTree(filename):
    fr = open(filename)
    return pickle.load(fr)


# ---------------------------- main ---------------------------- #

ds, labels = createDataSet()
print ds
print labels
myTree = createTree(ds, labels)
print myTree
testVec = [1, 1]
ds, labels = createDataSet()
classLabel = classify(myTree, labels, testVec)
print classLabel
storeTree(myTree, './tree.txt')
myTree1 = grabTree('./tree.txt')
print myTree1