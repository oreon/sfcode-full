'''
Created on 2013-06-18

@author: t900058
'''

from pybrain.tools.shortcuts import buildNetwork
from pybrain.datasets import SupervisedDataSet
from pybrain.supervised.trainers import BackpropTrainer

ds = SupervisedDataSet(2, 1)
 
 
ds.addSample((0, 0), (0,))
ds.addSample((0, 1), (1,))
ds.addSample((1, 0), (1,))
ds.addSample((1, 1), (0,))

for inpt, target in ds:
    print inpt, target


net = buildNetwork(2, 3,  1,  bias=True)
trainer = BackpropTrainer(net, ds)
print "error " ,trainer.trainUntilConvergence(); 

print net['in'] 
print  net['hidden0']

print net.activate([1,1])

