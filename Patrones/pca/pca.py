# -*- coding: utf-8 -*-

import numpy as np
import matplotlib.pyplot as plt
import pandas as pd


def loadDataSet(filename):
    df = pd.read_table(filename, sep='\t')
    return np.array(df)

def showData(dataMat, reconMat):
    fig = plt.figure()
    ax = fig.add_subplot(111)
    ax.scatter(dataMat[:, 0], dataMat[:, 1], c='green')
    ax.scatter(np.array(reconMat[:, 0]), reconMat[:, 1], c='red')
    plt.show()

def pca(dataMat, topNfeat=999999):

 
    meanVals = np.mean(dataMat, axis=0)
    meanRemoved = dataMat - meanVals

 
    covmat = np.cov(meanRemoved, rowvar=0)
    print(covmat)


    eigVals, eigVects = np.linalg.eig(np.mat(covmat))
    eigValInd = np.argsort(eigVals)
    eigValInd = eigValInd[:-(topNfeat+1):-1]    
    redEigVects = eigVects[:, eigValInd]        


    lowDDataMat = meanRemoved * redEigVects    
    reconMat = (lowDDataMat * redEigVects.T) + meanVals 
    return np.array(lowDDataMat), np.array(reconMat)


# ---------------------------- main ---------------------------- #

dataMat = loadDataSet('./data/testSet.txt')
lowDDataMat, reconMat = pca(dataMat, 1)
#showData(dataMat, lowDDataMat)
showData(dataMat, reconMat)
print(lowDDataMat)