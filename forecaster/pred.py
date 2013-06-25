###
# xor.py
#
# author: Kristina Striegnitz
#
# version: 3/3/2010
#
# Simple example of training a neural network calculating XOR using
# the pybrain package.
###
from pybrain.datasets import SupervisedDataSet
from pybrain.tools.shortcuts import buildNetwork
from pybrain.supervised import BackpropTrainer

ts = "163.18,164.21,161.75,163.1,164.8,164.8,162.73,161.27,163.56,164.35,163.45,165.83,165.22,166.3,165.31,165.45,165.93,167.17,166.93,166.94,165.34,166.12,165.23,163.54,163.41,162.88,163.34,162.6,161.78,161.37,159.75,158.28,159.68,159.3,158.24,158.52,157.88,157.78,156.17,155.48,154.14,155.11,157.41,155.12,158.8,159.19,158.67,156.75,156.21,155.16,155.86,155.23,156.82,156.05,156.67,156.19,156.19,154.95,155.6,154.36,155.69".split(",")


testdata  = "145.08,145.41,141.78,139.41,140.93,141.12,141.72,142.16,143.46,142.64,143.71,142.13,140.47,141,141.87,141.8,140.84,140.78,140.36,139.88,139.63,139.83,140.52,140.49,139.84,138.72,139.44,139.73,137.85,137.6,137.54,134.81,134.15,134.38,136.21,136.69,136.58,136.46,138.12,141.32,140.23,139.94,141.2,139.73,139.73,139.81,139.41,139.8,141.77,141.75,144.15,144.53,143.88,142.43,141.26,141.72,141.64,142.55,143.97,144.47,144.46,143.43,142.85,142.7,142.32,142.99,141.65,142.45,143.98,144.2,144.26,144.25,144.17,144.29,144.78,144.14,141.98,141.51,141.12,141.92,141.37,138.56,138.68,138.8,138.15,139.15,139.04,139.18,139.15,138.31,139.45,139.39,139.82,139.81,139.62,138.6,138.44,138.42,138.49,138.26,138.15,137.98,137.29,137.02,134.36,135.29,135.41,136.37,136.37,133.9,131.72,131.69,132.84,134.16".split(",")
#ts = [11,12,13,14,15,15,12,14,13,17,14,18,19,20,21,10,11,12,9]
INPUTS = 8;

def normalize(d):
    d.reverse();
    for j in range(0, len(d)):
            d[j] = 0.001 * float(d[j]);
    

def make_dataset():
    
    #print ts[0:4]
    """
    Creates a set of training data.
    """
    data = SupervisedDataSet(INPUTS,1)
    
    normalize(ts);
    normalize(testdata);
    
    addValues(data, ts)
    
        
    return data

def addValues(dataset, arr):
     end = len(arr) - INPUTS
     for i in range(0, end):
        ss = arr[i:INPUTS+i]
        if arr[i+INPUTS] > arr[i+ INPUTS - 1]:
            op = [1] 
        else :
            op = [0]
        dataset.addSample( ss, op )

def training(d):
    """
    Builds a network and trains it.
    """
    n = buildNetwork(d.indim, INPUTS-3,INPUTS-4, d.outdim,recurrent=True)
    print n;
    t = BackpropTrainer(n, d, learningrate = 0.02, momentum = 0.88)
    #for epoch in range(0,700):
    t.trainUntilConvergence(d, 1190)
    
    return t


def test(trained):
    """
    Builds a new test dataset and tests the trained network on it.
    """
    testdatas = SupervisedDataSet(INPUTS,1)
    addValues(testdatas, testdata)
    
    trained.testOnData(testdatas, verbose= True)


def run():
    """
    Use this function to run build, train, and test your neural network.
    """
    trainingdata = make_dataset()
    trained = training(trainingdata)
    test(trained)
    #print trained.testOnData([0,0], verbose=False)
        # print trained.activate((1,1))
    
print ts;
print run()