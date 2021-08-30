# -*- coding: utf-8 -*-
import numpy as np
import matplotlib.pyplot as plt

def loadDataSet():
    dataMat = []
    labelMat = []
    fr = open('./data/testSet.txt', 'r')
    lines = fr.readlines()
    for line in lines:
        strArr = line.strip().split()
        dataMat.append([1.0, float(strArr[0]), float(strArr[1])])
        labelMat.append(int(strArr[2]))
    return dataMat, labelMat

# Sigmoid 
def sigmoid(inX):
    return 1.0 / (1 + np.exp(-inX))


def gradientAscent(dataMatIn, classLabels):
    dataMatrix = np.mat(dataMatIn)
    labelMatrix = np.mat(classLabels).transpose()
    m, n = np.shape(dataMatrix)
    alpha = 0.001   
    maxCycles = 500     
    weights = np.ones((n, 1))
    for i in range(maxCycles):
        h = sigmoid(dataMatrix * weights)     
        error = (labelMatrix - h)               
        weights = weights + alpha * dataMatrix.transpose() * error  
    return weights

def stocGradAscent0(dataMatrix, classLabels):
    dataMatrix = np.mat(dataMatrix)
    labelMatrix = np.mat(classLabels).transpose()
    m, n = np.shape(dataMatrix)
    alpha = 0.01
    weights = np.ones((n, 1))
    for i in range(m):
        h = sigmoid(np.sum(dataMatrix[i] * weights))
        error = classLabels[i] - h
        weights = weights + alpha * error * dataMatrix[i].transpose()
    return weights


def stocGradAscent1(dataMatrix, classLabels, numIter=150):
    dataMatrix = np.mat(dataMatrix)
    labelMatrix = np.mat(classLabels).transpose()
    m, n = np.shape(dataMatrix)
    weights = np.ones((n, 1))
    for j in range(numIter):
        dataIndex = range(m)
        for i in range(m):
            alpha = 4 / (1.0 + i + j) + 0.01
            randIndex = int(np.random.uniform(0, len(dataIndex)))
            h = sigmoid(np.sum(dataMatrix[randIndex] * weights))
            error = classLabels[randIndex] - h
            weights = weights + alpha * error * dataMatrix[randIndex].transpose()
            del(dataIndex[randIndex])
    return weights

def plotBestFit(weights):
    weights = np.array(weights)
    dataArr, labelArr = loadDataSet()
    dataArr = np.array(dataArr)
    n = np.shape(dataArr)[0]
    xcord1 = []
    ycord1 = []
    xcord2 = []
    ycord2 = []
    for i in range(n):
        if int(labelArr[i]) == 1:
            xcord1.append(dataArr[i, 1])
            ycord1.append(dataArr[i, 2])
        else:
            xcord2.append(dataArr[i, 1])
            ycord2.append(dataArr[i, 2])
    fig = plt.figure()
    ax = fig.add_subplot(111)
    ax.scatter(xcord1, ycord1, c='red')
    ax.scatter(xcord2, ycord2, c='green')
    x = np.arange(-3.0, 3.0, 0.1)
    y = (-weights[0] - weights[1] * x) / weights[2]
    ax.plot(x, y)
    plt.xlabel('x1'); plt.ylabel('x2');
    plt.show()


# ---------------------------- main ---------------------------- #

dataArr, labelArr = loadDataSet()
#weights = gradientAscent(dataArr, labelArr)
weights = stocGradAscent1(dataArr, labelArr)

print weights
plotBestFit(weights)