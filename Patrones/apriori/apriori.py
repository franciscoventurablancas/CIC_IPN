# -*- coding: utf-8 -*-

import itertools


def loadDataSet():
    return [ [1, 3, 4], [2, 3, 5], [1, 2, 3, 5], [2, 5] ]
    #return [[1, 2, 5], [2, 4], [2, 3], [1, 2, 4], [1, 3], [2, 3], [1, 3], [1, 2, 3, 5], [1, 2, 3]]



def createC1(dataset):
    C1 = []
    for transection in dataset:
        for item in transection:
            if not [item] in C1:
                C1.append([item])
    C1.sort()
    return map(frozenset, C1)   



def scanD(D, Ck, minSupport):
  
    ssCnt = {}
    for transection in D:
        for can in Ck:
            if can.issubset(transection):
                if not ssCnt.has_key(can):
                    ssCnt[can] = 1
                else:
                    ssCnt[can] += 1

   
    numItems = float(len(D))
    retList = []        
    supportData = {}   ，key: ，value: 
    for key in ssCnt:
        supportValue = ssCnt[key] / numItems
        if supportValue >= minSupport:
            retList.insert(0, key)
        supportData[key] = supportValue

    return retList, supportData



# The apriori-gen function takes as argument Lk-1,the set of all large (k - 1)-itemsets. It returns a superset of the set of all large k-items.

def aprioriGen(Lk, k):
    retList = []
    lenLk = len(Lk)

    for i in range(lenLk-1):
        for j in range(i+1, lenLk):
            L1 = list(Lk[i])[:k-2]
            L2 = list(Lk[j])[:k-2]
            L1.sort()
            L2.sort()
            if L1 == L2:
                retList.append(Lk[i] | Lk[j])  

   
    rmList = []    
    for superset in retList:
        subsets = map(set, itertools.combinations(superset, k-1))
        for subset in subsets:
            if not subset in Lk:
                rmList.append(superset)
                #retList.remove(superset)
                break
    for rmItem in rmList:
        retList.remove(rmItem)

    return retList


# Apriori
def apriori(dataset, minSupport = 0.5):
    C1 = createC1(dataset)
    D = map(set, dataset)
    L1, supportData = scanD(D, C1, minSupport)
    L = [L1]
    k = 2

    while len(L[k-2]) > 0:
        Ck = aprioriGen(L[k-2], k)              
        Lk, supK = scanD(D, Ck, minSupport)
        supportData.update(supK)
        L.append(Lk)

        print 'C' + str(k)
        print Ck
        print 'L' + str(k)
        print Lk

        k += 1

    return L, supportData


# ---------------------------- main ---------------------------- #

ds = loadDataSet()
L, sup = apriori(ds, 0.5)
print '------------------ result ------------------'
print ds
print L
print sup