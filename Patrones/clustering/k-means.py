# -*- coding: utf-8 -*-

import numpy as np
import matplotlib.pyplot as plt


def loadDataSet(filename):
    dataSet = []
    fr = open(filename, 'r')
    for line in fr.readlines():
        lineArr = line.strip().split('\t')
        fltLine = map(float, lineArr)
        dataSet.append(fltLine)
    return dataSet

def showData(dataMat, centroids):
    fig = plt.figure()
    ax = fig.add_subplot(111)
    ax.scatter(dataMat[:, 0], dataMat[:, 1], c='green', marker='o', s=30)
    ax.scatter(centroids[:, 0], centroids[:, 1], c='red', marker='+', s=100)
    plt.show()


def distEuclid(vecA, vecB):
    dist = np.sqrt(np.sum(np.power(vecA - vecB, 2)))
    return dist


def randCenter(dataMat, k):
    m = np.shape(dataMat)[1]
    centroid = np.mat(np.zeros((k, m)))
    for i in range(m):
        minV_i = np.min(dataMat[:, i])
        maxV_i = np.max(dataMat[:, i])
        range_i = maxV_i - minV_i
        rand_i = minV_i + range_i * np.random.rand(k, 1)
        centroid[:, i] = rand_i
    return centroid


def kmeans(dataMat, k, distMeasure=distEuclid, createCent=randCenter):
    n = np.shape(dataMat)[0]
    clusterAssment = np.mat(np.zeros((n, 2)))       
    centroids = createCent(dataMat, k)
    clusterChanged = True
    while clusterChanged: 
        clusterChanged = False
        for i in range(n): 
            minDist = np.inf; minIndex = -1
            for j in range(k):
                distJI = distMeasure(centroids[j, :], dataMat[i, :])
                if minDist > distJI:
                    minDist = distJI
                    minIndex = j
            if clusterAssment[i, 0] != minIndex:
                clusterChanged = True
            clusterAssment[i, :] = minIndex, minDist**2
        print(centroids)
        for cent in range(k):  
            ix = np.nonzero(clusterAssment[:, 0].A == cent)[0]
            ptsInClust = dataMat[ix]
            centroids[cent, :] = np.mean(ptsInClust, axis=0)    
    return centroids, clusterAssment


# ---------------------------- main ---------------------------- #

dataSet = loadDataSet('./data/testSet.txt')
dataMat = np.mat(dataSet)

k = 4
centroids, clustAssing = kmeans(dataMat, k)
showData(dataMat, centroids)