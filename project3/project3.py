# Project 3 CMSC 420 Eddie Zhou
import random

class HashTable:
    def __init__(self, m, k):
        self.m = m
        self.k = k
        self.arr = [0] * m

    # Using multiple hash functions to reduce collisions as much as possible 
    def hash_1(self, x):
        # Linear hash function
        return (47 * x + 23) % self.m
        
    def hash_2(self, x):
        # Multiplicative hash function
        return (137 * x) % self.m
        
    def hash_3(self, x):
        # Universal hash function, where we can easily find a p greater than m because m is fixed to 3000 at the maximum.
        return ((67 * x + 3) % 3007) % self.m
        
    def insert(self, x):
        if self.k == 2:
            self.arr[self.hash_1(x)] = 1
            self.arr[self.hash_2(x)] = 1
        else:
            self.arr[self.hash_1(x)] = 1
            self.arr[self.hash_2(x)] = 1
            self.arr[self.hash_3(x)] = 1
    
    def find(self, x):
        if self.k == 2:
            if self.arr[self.hash_1(x)] == 1 and self.arr[self.hash_2(x)] == 1:
                return True
            else:
                return False
        else:
            if self.arr[self.hash_1(x)] == 1 and self.arr[self.hash_2(x)] == 1 and self.arr[self.hash_3(x)] == 1:
                return True
            else:
                return False

    def to_string(self):
        return self.arr


# Main function for testing
if __name__ == "__main__":
    test1 = HashTable(2000, 2)
    test2 = HashTable(3000, 3)
    test3 = HashTable(2000, 2)
    test4 = HashTable(3000, 3)
    for x in range(1, 201):
        test1.insert(x)
        test2.insert(x)
        test3.insert(x)
        test4.insert(x)

    # Two separate T sets for the first two and other two tests
    T1 = []
    T2 = []
    for x in range(201, 401):
        T1.append(x)
        T2.append(random.randint(201, 400))

    for x in T1:
        print(test1.find(x), end=" ") # Uncomment to show test results for test1
        print(test2.find(x), end=" ") # Uncomment to show test results for test2

    for x in T2:
        print(test3.find(x), end=" ") # Uncomment to show test results for test1
        print(test4.find(x), end=" ") # Uncomment to show test results for test1

    

    

    
    

